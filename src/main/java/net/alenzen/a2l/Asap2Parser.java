package net.alenzen.a2l;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.List;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.io.input.CloseShieldInputStream;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import net.alenzen.a2l.antlr.a2lLexer;
import net.alenzen.a2l.antlr.a2lParser;
import net.alenzen.a2l.antlr.a2lParser.Module_sub_nodesContext;
import net.alenzen.a2l.antlr.a2lParser.Project_sub_nodesContext;

public class Asap2Parser {
	private InputStream filecontent;
	private IParserEventHandler eventHandler = (line, position, message) -> {
		System.err.println("Line " + line + "@" + position + ": " + message);
	};
	private IIncludedFileMapper includeFileMapper = (f) -> {
		throw new FileNotFoundException("Cannot map included file " + f);
	};
	private Charset defaultCharset = StandardCharsets.ISO_8859_1;

	/**
	 * Creates a parser instance to parse ASAP2 files.
	 * 
	 * A default includeFileMapper will be used which resolves /include paths
	 * relative to the given filename
	 * 
	 * @see Asap2Parser#parse()
	 * @see Asap2Parser#getStandardFileMapper(String)
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public Asap2Parser(String filename) throws FileNotFoundException {
		includeFileMapper = getStandardFileMapper(filename);
		this.filecontent = new FileInputStream(new File(filename));
	}

	/**
	 * Creates a parser instance to parse ASAP2 files.
	 * 
	 * A default includeFileMapper will be used which resolves /include paths
	 * relative to the given asap2File
	 * 
	 * @see Asap2Parser#parse()
	 * @see Asap2Parser#getStandardFileMapper(String)
	 * @param asap2File
	 * @throws FileNotFoundException
	 */
	public Asap2Parser(File asap2File) throws FileNotFoundException {
		includeFileMapper = getStandardFileMapper(asap2File.getAbsolutePath());
		this.filecontent = new FileInputStream(asap2File);
	}

	public IIncludedFileMapper getStandardFileMapper(String rootFile) {
		return (filepath) -> {
			String finalPathToIncludeFile = rootFile;
			if (filepath != null) {
				// If filepath is not an absolute path, combine it with rootFile's directory
				File file = new File(filepath);
				if (!file.isAbsolute()) {
					String rootFileDir = new File(rootFile).getParent();
					finalPathToIncludeFile = Paths.get(rootFileDir, filepath).toString();
				} else {
					// If filepath is absolute, just use filepath
					finalPathToIncludeFile = filepath;
				}
			}
			return new FileInputStream(finalPathToIncludeFile);
		};
	}

	/**
	 * Creates a parser instance to parse ASAP2 files. The filecontent stream will
	 * be closed by using parse()
	 * 
	 * @see Asap2Parser#parse()
	 * @param filecontent       The ASAP2 content to parse.
	 * @param includeFileMapper In case an /include is processed the parser will ask
	 *                          this mapper for the InputStream of the included
	 *                          file.
	 */
	public Asap2Parser(InputStream filecontent, IIncludedFileMapper includeFileMapper) {
		this.includeFileMapper = includeFileMapper;
		this.filecontent = filecontent;
	}

	/**
	 * Creates a parser instance to parse ASAP2 files. The filecontent stream will
	 * be closed by using parse()
	 * 
	 * @see Asap2Parser#parse()
	 * @param filecontent The ASAP2 content to parse.
	 */
	public Asap2Parser(InputStream filecontent) {
		this.filecontent = filecontent;
	}

	/**
	 * Sets the new includeFileMapper to be used for parsing.
	 * 
	 * @see Asap2Parser#parse()
	 * @param inclFileMapper will be consulted whenever the parser needs to process
	 *                       and /include for the InputStream.
	 */
	public void setIncludeFileMapper(IIncludedFileMapper inclFileMapper) {
		this.includeFileMapper = inclFileMapper;
	}

	/**
	 * Sets the event handler which will be fired during parsing issues.
	 * 
	 * @see Asap2Parser#parse()
	 * @param eh Will be called whenever the parser couldn't parse parts of the
	 *           file. It gives the line number, position and a message.
	 */
	public void setEventHandler(IParserEventHandler eh) {
		this.eventHandler = eh;
	}

	private ANTLRErrorListener createANTLRErrorListener(IParserEventHandler eventHandler) {
		return new ANTLRErrorListener() {

			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				eventHandler.log(line, charPositionInLine, msg);
			}

			@Override
			public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
					int prediction, ATNConfigSet configs) {
			}

			@Override
			public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
					BitSet conflictingAlts, ATNConfigSet configs) {
			}

			@Override
			public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact,
					BitSet ambigAlts, ATNConfigSet configs) {
			}
		};
	}

	/**
	 * This function parses the given InputStream by determing the encoding, lexing,
	 * parsing and creating an object structure. The registered event handler will
	 * be fired whenever the file does not match the specification and therefore
	 * parts of the file might not being parsed. Not calling the eventHandler does
	 * not indicate that the file matches all specifications but it has been
	 * completely transformed into the Object structure of Asap2File.
	 * 
	 * @see Asap2Parser#setEventHandler(IParserEventHandler)
	 * @return The object structure representing the Asap2 stream.
	 * @throws IOException Will be thrown when there is an issue with the input
	 *                     stream.
	 */
	public Asap2File parse() throws IOException {
		ANTLRErrorListener listener = createANTLRErrorListener(eventHandler);
		CharStream chStream = determineCharStream();
		// lexing file
		a2lLexer lexer = new a2lLexer(chStream);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);

		checkTokenForTokenIssues(lexer, eventHandler);

		// parsing tokens
		a2lParser parser = new a2lParser(new CommonTokenStream(lexer));
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		ParseTree tree = parser.a2l_file();

		// visit ParseTree to create usable object structure
		return (Asap2File) new A2LVisitor(eventHandler, includeFileMapper).visit(tree);
	}

	private CharStream determineCharStream() throws IOException {
		return determineCharStream(this.filecontent, this.defaultCharset);
	}

	private static CharStream determineCharStream(InputStream input, Charset defaultCharset) throws IOException {
		CharStream chStream;
		try (BOMInputStream is = createBOMInputStream(input)) {
			Charset fileEncoding = determineCharset(is, defaultCharset);
			chStream = CharStreams.fromStream(is, fileEncoding);
		}
		return chStream;
	}

	private static BOMInputStream createBOMInputStream(InputStream in) throws IOException {
		return BOMInputStream.builder().setInputStream(in).setByteOrderMarks(ByteOrderMark.UTF_8,
				ByteOrderMark.UTF_16LE, ByteOrderMark.UTF_16BE, ByteOrderMark.UTF_32LE, ByteOrderMark.UTF_32BE).get();
	}

	private static Charset determineCharset(BOMInputStream is, Charset defaultCharset) throws IOException {
		if (!is.hasBOM()) {
			return defaultCharset;
		}

		if (is.hasBOM(ByteOrderMark.UTF_8)) {
			return StandardCharsets.UTF_8;
		}

		if (is.hasBOM(ByteOrderMark.UTF_16LE)) {
			return StandardCharsets.UTF_16LE;
		}

		if (is.hasBOM(ByteOrderMark.UTF_16BE)) {
			return StandardCharsets.UTF_16BE;
		}

		if (is.hasBOM(ByteOrderMark.UTF_32LE)) {
			return Charset.forName("UTF-32LE");
		}

		if (is.hasBOM(ByteOrderMark.UTF_32BE)) {
			return Charset.forName("UTF-32BE");
		}

		throw new IOException("Unknown charset spepcified in the BOM area: " + is.getBOMCharsetName());
	}

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		Options options = new Options();

		Option versionOption = new Option("v", "version", false,
				"Prints the current version of the package to stdout and exits.");
		options.addOption(versionOption);

		Option jsonOption = new Option("j", "json", true,
				"Either specify a JSON file or pipe JSON content to convert it to A2L");
		jsonOption.setOptionalArg(true);
		options.addOption(jsonOption);

		Option a2lOption = new Option("a2l", "asap2", true,
				"Either specify an A2L file or pipe A2L content to convert it to JSON");
		a2lOption.setOptionalArg(true);
		options.addOption(a2lOption);

		Option schemaOption = new Option("jsc", "jsonSchema", false, "Outputs the JSON schema for JSON outputs");
		options.addOption(schemaOption);

		Option minimizeOption = new Option("mj", "minJson", false, "Outputs the JSON without null fields");
		options.addOption(minimizeOption);

		Option indentOption = new Option("ij", "indentJson", false, "Outputs the JSON with indentation");
		options.addOption(indentOption);

		Option outputOption = new Option("o", "output", true, "Write result to file instead of std out");
		outputOption.setOptionalArg(false);
		options.addOption(outputOption);

		Option encodingOption = new Option("c", "encoding", true,
				"Specify the encoding for the output file. e.g. US-ASCII, ISO-8859-1, UTF-8, UTF-16BE, UTF-16LE, UTF-32LE, UTF-32BE, UTF-16");
		options.addOption(encodingOption);

		Option inputEncodingOption = new Option("ic", "inputEncoding", true,
				"Specify the encoding of the input file. The default encoding is ISO-8859-1 if no BOM is given. With this option you can overwrite the encoding in case no BOM is given.");
		options.addOption(inputEncodingOption);

		Option helpOption = new Option("h", "help", false, "Prints this help");
		options.addOption(helpOption);

		CommandLineParser cmdParser = new DefaultParser();
		CommandLine cmd;
		try {
			cmd = cmdParser.parse(options, args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			printHelp(options);
			return;
		}

		if (cmd.hasOption(versionOption)) {
			printPackageVersion();
			return;
		}

		try (PrintStream outputStream = getPrintStream(cmd.getOptionValue(outputOption.getOpt()),
				cmd.getOptionValue(encodingOption.getOpt()))) {
			if (cmd.hasOption(helpOption.getOpt())) {
				PrintStream stdOut = System.out;
				System.setOut(outputStream);

				try {
					printHelp(options);
				} finally {
					System.setOut(stdOut);
				}

				return;
			}

			if (cmd.hasOption(schemaOption.getOpt())) {
				outputStream.print(Asap2File.generateJsonSchema(cmd.hasOption(minimizeOption.getOpt()), // exclude null
																										// fields
						cmd.hasOption(indentOption.getOpt()) // indent
				));
				return;
			}

			if (cmd.hasOption(a2lOption.getOpt())) {
				String a2lFile = cmd.getOptionValue(a2lOption.getOpt());
				Asap2Parser parser;

				if (a2lFile == null) {
					parser = new Asap2Parser(CloseShieldInputStream.wrap(System.in));
				} else {
					parser = new Asap2Parser(a2lFile);
				}
				
				if(cmd.hasOption(inputEncodingOption.getOpt())) {
					parser.setDefaultEncoding(cmd.getOptionValue(encodingOption.getOpt()));
;				}

				outputStream.print(parser.parse().toJson(cmd.hasOption(minimizeOption.getOpt()), // exclude null fields
						cmd.hasOption(indentOption.getOpt()) // indent
				));
				return;
			}

			if (cmd.hasOption(jsonOption.getOpt())) {
				String jsonFile = cmd.getOptionValue(jsonOption.getOpt());

				Asap2File a2l;
				if (jsonFile == null) {
					a2l = Asap2File.fromJsonStream(CloseShieldInputStream.wrap(System.in));
				} else {
					a2l = Asap2File.fromJsonFile(jsonFile);
				}
				outputStream.println(a2l.toA2L());
				return;
			}

			printHelp(options);
		}
	}

	private void setDefaultEncoding(String optionValue) {
		this.defaultCharset  = Charset.forName(optionValue);
	}

	private static void printPackageVersion() {
		String version = Asap2Parser.class.getPackage().getImplementationVersion();
		System.out.println(version);
	}

	private static PrintStream getPrintStream(String outputFile, String encoding)
			throws FileNotFoundException, UnsupportedEncodingException, IOException {
		if (outputFile == null) {
			return System.out;
		}

		String csn = Charset.defaultCharset().displayName();
		if (encoding != null) {
			csn = encoding;
		}

		PrintStream printStream = new PrintStream(outputFile, csn);
		printStream.write(getBOMBytes(csn));
		return printStream;
	}

	public static ByteOrderMark determineBOM(Charset charset) {
		if (StandardCharsets.UTF_8.equals(charset)) {
			return ByteOrderMark.UTF_8;
		} else if (StandardCharsets.UTF_16LE.equals(charset)) {
			return ByteOrderMark.UTF_16LE;
		} else if (StandardCharsets.UTF_16BE.equals(charset)) {
			return ByteOrderMark.UTF_16BE;
		} else if (Charset.forName("UTF-32LE").equals(charset)) {
			return ByteOrderMark.UTF_32LE;
		} else if (Charset.forName("UTF-32BE").equals(charset)) {
			return ByteOrderMark.UTF_32BE;
		}

		return null;
	}

	private static byte[] getBOMBytes(String csn) {
		ByteOrderMark bom = determineBOM(Charset.forName(csn));

		if (bom == null) {
			return new byte[0];
		}

		return bom.getBytes();
	}

	private static void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("A2LParser", options, true);
	}

	protected ProjectSubBlocks parseProjectInclude() throws IOException {
		ANTLRErrorListener listener = createANTLRErrorListener(eventHandler);
		CharStream chStream = determineCharStream();
		// lexing file
		a2lLexer lexer = new a2lLexer(chStream);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);

		checkTokenForTokenIssues(lexer, eventHandler);

		// parsing tokens
		a2lParser parser = new a2lParser(new CommonTokenStream(lexer));
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		Project_sub_nodesContext tree = parser.project_sub_nodes();

		// visit ParseTree to create usable object structure
		ProjectSubBlocks p = new ProjectSubBlocks();
		new A2LVisitor(eventHandler, this.includeFileMapper).visitProjectSubNodes(p, tree);
		return p;
	}

	private void checkTokenForTokenIssues(a2lLexer lexer, IParserEventHandler log) {
		List<? extends Token> tokens = lexer.getAllTokens();
		tokens.stream().forEach(identifier -> {
			String text = identifier.getText();
			if (identifier.getType() == a2lLexer.IDENTIFIER
					&& (text.contains("(") || text.contains(")") || text.contains("#"))) {
				log.log(identifier.getLine(), identifier.getCharPositionInLine(),
						"The identifier contains an invalid character '(',')','#': " + text);
			}
		});
		lexer.reset();
	}

	protected ModuleSubBlocks parseModuleInclude() throws IOException {
		ANTLRErrorListener listener = createANTLRErrorListener(eventHandler);
		CharStream chStream = determineCharStream();
		// lexing file
		a2lLexer lexer = new a2lLexer(chStream);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);

		checkTokenForTokenIssues(lexer, eventHandler);

		// parsing tokens
		a2lParser parser = new a2lParser(new CommonTokenStream(lexer));
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		Module_sub_nodesContext tree = parser.module_sub_nodes();

		// visit ParseTree to create usable object structure
		ModuleSubBlocks m = new ModuleSubBlocks();
		new A2LVisitor(eventHandler, this.includeFileMapper).visitModuleSubNodes(m, tree);
		return m;
	}
}

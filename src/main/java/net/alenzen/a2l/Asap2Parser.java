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
import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
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

public class Asap2Parser {

	private InputStream filecontent;
	private IParserEventHandler eventHandler = (line, position, message) -> {
		System.err.println("Line " + line + "@" + position + ": " + message);
	};

	public Asap2Parser(String filename) throws FileNotFoundException {
		this.filecontent = new FileInputStream(new File(filename));
	}

	public Asap2Parser(File f) throws FileNotFoundException {
		this.filecontent = new FileInputStream(f);
	}

	/**
	 * The filecontent stream will be closed by using parse()
	 * 
	 * @see Asap2Parser#parse()
	 * @param filecontent
	 */
	public Asap2Parser(InputStream filecontent) {
		this.filecontent = filecontent;
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

	private Asap2File parse(IParserEventHandler eventHandler) throws IOException {
		ANTLRErrorListener listener = new ANTLRErrorListener() {

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
		CharStream chStream = determineCharStream();
		// lexing file
		a2lLexer lexer = new a2lLexer(chStream);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);

		// parsing tokens
		a2lParser parser = new a2lParser(new CommonTokenStream(lexer));
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		ParseTree tree = parser.a2l_file();

		// visit ParseTree to create usable object structure
		return (Asap2File) new A2LVisitor(eventHandler).visit(tree);
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
		return parse(eventHandler);
	}

	private CharStream determineCharStream() throws IOException {
		return determineCharStream(filecontent);
	}

	private static CharStream determineCharStream(InputStream input) throws IOException {
		CharStream chStream;
		try (BOMInputStream is = createBOMInputStream(input)) {
			Charset fileEncoding = determineCharset(is);
			chStream = CharStreams.fromStream(is, fileEncoding);
		}
		return chStream;
	}

	private static BOMInputStream createBOMInputStream(InputStream in) {
		return new BOMInputStream(in, ByteOrderMark.UTF_8, ByteOrderMark.UTF_16LE, ByteOrderMark.UTF_16BE,
				ByteOrderMark.UTF_32LE, ByteOrderMark.UTF_32BE);
	}

	private static Charset determineCharset(BOMInputStream is) throws IOException {
		if (!is.hasBOM()) {
			return StandardCharsets.ISO_8859_1;
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

		Option outputOption = new Option("o", "output", true, "Write result to file instead of std out");
		outputOption.setOptionalArg(false);
		options.addOption(outputOption);

		Option encodingOption = new Option("c", "encoding", true,
				"Specify the encoding for the output file. e.g. US-ASCII, ISO-8859-1, UTF-8, UTF-16BE, UTF-16LE, UTF-32LE, UTF-32BE, UTF-16");
		options.addOption(encodingOption);

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

		try (PrintStream outputStream = getPrintStream(cmd.getOptionValue("o"), cmd.getOptionValue("c"))) {
			if (cmd.hasOption("h")) {
				PrintStream stdOut = System.out;
				System.setOut(outputStream);

				try {
					printHelp(options);
				} finally {
					System.setOut(stdOut);
				}

				return;
			}

			if (cmd.hasOption("jsc")) {
				outputStream.print(Asap2File.generateJsonSchema());
				return;
			}

			if (cmd.hasOption(a2lOption.getOpt())) {
				String a2lFile = cmd.getOptionValue(a2lOption.getOpt());
				Asap2Parser parser;

				if (a2lFile == null) {
					parser = new Asap2Parser(new CloseShieldInputStream(System.in));
				} else {
					parser = new Asap2Parser(a2lFile);
				}

				outputStream.print(parser.parse().toJson());
				return;
			}

			if (cmd.hasOption(jsonOption.getOpt())) {
				String jsonFile = cmd.getOptionValue(jsonOption.getOpt());

				Asap2File a2l;
				if (jsonFile == null) {
					a2l = Asap2File.fromJsonStream(new CloseShieldInputStream(System.in));
				} else {
					a2l = Asap2File.fromJsonFile(jsonFile);
				}
				outputStream.println(a2l.toA2L());
				return;
			}

			printHelp(options);
		}
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

}

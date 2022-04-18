package net.alenzen.a2l;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import net.alenzen.a2l.antlr.formulaLexer;
import net.alenzen.a2l.antlr.formulaParser;
import net.alenzen.a2l.antlr.formulaParser.ExpressionContext;
import net.alenzen.a2l.antlr.formulaParserBaseVisitor;

public class FormulaValidator {
	private String formula;
	private HashSet<String> variables;

	public FormulaValidator(String formula) {
		this.formula = formula;
		this.variables = new HashSet<String>();
	}

	public List<FormulaSyntaxError> validate() {
		List<FormulaSyntaxError> events = new ArrayList<FormulaSyntaxError>();
		ANTLRErrorListener errorListener = new ANTLRErrorListener() {

			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				events.add(new FormulaSyntaxError(line, charPositionInLine, msg));
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

		formulaLexer lexer = new formulaLexer(CharStreams.fromString(formula));
		lexer.removeErrorListeners();
		lexer.addErrorListener(errorListener);

		formulaParser parser = new formulaParser(new CommonTokenStream(lexer));
		parser.removeErrorListeners();
		parser.addErrorListener(errorListener);

		ExpressionContext tree = parser.expression();

		this.variables.clear();
		formulaParserBaseVisitor<Object> visitor = new formulaParserBaseVisitor<Object>() {
			@Override
			public Object visitExpression(ExpressionContext ctx) {
				if (ctx.IDENTIFIER() != null) {
					variables.add(ctx.IDENTIFIER().getText());
				}

				return super.visitChildren(ctx);
			}
		};

		visitor.visit(tree);

		return events;
	}

	public Set<String> getVariables() {
		return this.variables;
	}
}

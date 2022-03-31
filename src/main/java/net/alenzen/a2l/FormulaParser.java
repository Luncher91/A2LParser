package net.alenzen.a2l;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;

public class FormulaParser {
	private final String formula;
	private final Map<String, Double> variables;
	private List<FormulaSyntaxError> errors;

	private FormulaParser(String formula) {
		this.formula = formula;
		this.variables = new HashMap<String, Double>();
		FormulaValidator v = new FormulaValidator(formula);
		this.errors = v.validate();

		Set<String> variableList = v.getVariables();
		for (String var : variableList) {
			this.variables.putIfAbsent(var, null);
		}
	}

	private List<Function> getCustomFunctions() {
		List<Function> funcs = new ArrayList<Function>();
		funcs.add(getSyscFunction());
		return funcs;
	}

	private List<Operator> getCustomOperators() {
		List<Operator> ops = new ArrayList<Operator>();
		ops.add(getNotOperator());
		ops.add(getBitwiseNotOperator());
		ops.add(getAndOperator());
		ops.add(getBitwiseAndOperator());
		ops.add(getOrOperator());
		ops.add(getBitwiseOrOperator());
		ops.add(getBitwiseXorOperator());
		ops.add(getBitshiftLeftOperator());
		ops.add(getBitshiftRightOperator());
		ops.add(getSyscOperator());
		return ops;
	}

	private Function getSyscFunction() {
		Function func = new Function("sysc", 1) {

			@Override
			public double apply(double... arg0) {
				return arg0[0];
			}
		};
		return func;
	}

	private Operator getSyscOperator() {
		Operator operator = new Operator("$", 1, true, Operator.PRECEDENCE_UNARY_PLUS) {

			@Override
			public double apply(double... arg0) {
				return arg0[0];
			}
		};
		return operator;
	}

	private Operator getNotOperator() {
		Operator operator = new Operator("!", 1, true, Operator.PRECEDENCE_UNARY_PLUS) {
			@Override
			public double apply(double... args) {
				final int arg = (int) args[0];
				return arg == 0 ? 1 : 0;
			}
		};
		return operator;
	}

	private Operator getBitwiseNotOperator() {
		Operator operator = new Operator("~", 1, true, Operator.PRECEDENCE_UNARY_PLUS) {
			@Override
			public double apply(double... args) {
				final int arg = (int) args[0];
				return ~arg;
			}
		};
		return operator;
	}

	private Operator getAndOperator() {
		Operator operator = new Operator("&&", 2, true, Operator.PRECEDENCE_ADDITION - 8) {
			@Override
			public double apply(double... args) {
				final int arg = (int) args[0];
				final int arg2 = (int) args[1];
				return arg != 0 && arg2 != 0 ? 1 : 0;
			}
		};
		return operator;
	}

	private Operator getBitwiseAndOperator() {
		Operator operator = new Operator("&", 2, true, Operator.PRECEDENCE_ADDITION - 5) {
			@Override
			public double apply(double... args) {
				final int arg = (int) args[0];
				final int arg2 = (int) args[1];
				return arg & arg2;
			}
		};
		return operator;
	}

	private Operator getOrOperator() {
		Operator operator = new Operator("||", 2, true, Operator.PRECEDENCE_ADDITION - 9) {
			@Override
			public double apply(double... args) {
				final int arg = (int) args[0];
				final int arg2 = (int) args[1];
				return arg != 0 || arg2 != 0 ? 1 : 0;
			}
		};
		return operator;
	}

	private Operator getBitwiseOrOperator() {
		Operator operator = new Operator("|", 2, true, Operator.PRECEDENCE_ADDITION - 7) {
			@Override
			public double apply(double... args) {
				final int arg = (int) args[0];
				final int arg2 = (int) args[1];
				return arg | arg2;
			}
		};
		return operator;
	}

	private Operator getBitwiseXorOperator() {
		Operator operator = new Operator("^", 2, true, Operator.PRECEDENCE_ADDITION - 6) {
			@Override
			public double apply(double... args) {
				final int arg = (int) args[0];
				final int arg2 = (int) args[1];
				return arg ^ arg2;
			}
		};
		return operator;
	}

	private Operator getBitshiftLeftOperator() {
		Operator operator = new Operator("<<", 2, true, Operator.PRECEDENCE_ADDITION - 1) {
			@Override
			public double apply(double... args) {
				final int arg = (int) args[0];
				final int arg2 = (int) args[1];
				return arg << arg2;
			}
		};
		return operator;
	}

	private Operator getBitshiftRightOperator() {
		Operator operator = new Operator(">>", 2, true, Operator.PRECEDENCE_ADDITION - 1) {
			@Override
			public double apply(double... args) {
				final int arg = (int) args[0];
				final int arg2 = (int) args[1];
				return arg >> arg2;
			}
		};
		return operator;
	}

	public static FormulaParser createFromFormula(String formula) {
		return new FormulaParser(formula);
	}

	public double calculate() {
		Expression expression = new ExpressionBuilder(formula).variables(variables.keySet())
				.functions(getCustomFunctions()).operator(getCustomOperators()).build();
		return expression.setVariables(variables).evaluate();
	}

	public Set<String> getVariables() {
		return variables.keySet();
	}

	public void setVariable(String variableName, double value) {
		this.variables.put(variableName, value);
	}

	public void setVariables(Map<String, Double> vars) {
		this.variables.putAll(vars);
	}

	public String getFormula() {
		return formula;
	}

	public List<FormulaSyntaxError> getErrors() {
		return errors;
	}

}

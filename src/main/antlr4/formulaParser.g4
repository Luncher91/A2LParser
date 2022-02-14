parser grammar formulaParser;
options { tokenVocab=formulaLexer; }

@header {
	package net.alenzen.a2l.antlr;
}

expression : IDENTIFIER 
	| value | in_brackets | unary_function 
	| firstExpression=expression binaryOpInfix=binary_middle_operator secondExpression=expression
	| binaryOpPrefix=binary_leeding_operator BRACKET_OPEN firstExpression=expression COMMA secondExpression=expression BRACKET_CLOSE
	;

value : INT | DECIMAL | HEX_VALUE;
in_brackets : BRACKET_OPEN expression BRACKET_CLOSE;

unary_function : bit_not_function | not_function 
	| exp_function | sqrt_function | abs_function
	| sin_function | cos_function | tan_function
	| asin_function | acos_function | atan_function
	| sinh_function | cosh_function | tanh_function
	| log_function | log10_function | sysc_function
	| plus_function | minus_function
	;

bit_not_function : BIT_NOT expression;
not_function : NOT expression;
exp_function : EXP in_brackets;
sqrt_function : SQRT in_brackets;
abs_function : ABS in_brackets;
sin_function : SIN in_brackets;
cos_function : COS in_brackets;
tan_function : TAN in_brackets;
asin_function : ASIN in_brackets;
acos_function : ACOS in_brackets;
atan_function : ATAN in_brackets;
sinh_function : SINH in_brackets;
cosh_function : COSH in_brackets;
tanh_function : TANH in_brackets;
log_function : LOG in_brackets;
log10_function : LOG10 in_brackets;
sysc_function : (SYSC | SYSC_DOLLAR) BRACKET_OPEN IDENTIFIER BRACKET_CLOSE;
plus_function : PLUS expression;
minus_function : MINUS expression;

binary_middle_operator : 
	  PLUS 
	| MINUS 
	| MULTIPLICATION 
	| DIVIDE 
	| BIT_AND 
	| BIT_OR 
	| BIT_XOR 
	| BITSHIFT_LEFT 
	| BITSHIFT_RIGHT 
	| AND 
	| OR
	;
	
binary_leeding_operator : POW;

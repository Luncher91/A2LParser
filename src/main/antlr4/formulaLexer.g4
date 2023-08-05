lexer grammar formulaLexer;

channels {
	WHITESPACES
}

@header {
	package net.alenzen.a2l.antlr;
}

WS : (' ' | '\t') -> channel(WHITESPACES);

AND : '&&';
OR : '||';
NOT : '!';
LOG : 'log';
LOG10 : 'log10';

PLUS : '+';
MINUS : '-';
MULTIPLICATION : '*';
DIVIDE : '/';

SIN : 'sin';
COS : 'cos';
TAN : 'tan';

ASIN : 'asin';
ACOS : 'acos';
ATAN : 'atan';

SINH : 'sinh';
COSH : 'cosh';
TANH : 'tanh';

EXP : 'exp';
SQRT : 'sqrt';
ABS : 'abs';
POW : 'pow';

BIT_AND : '&';
BIT_OR : '|';
BIT_XOR : '^';
BIT_NOT : '~';
BITSHIFT_LEFT : '<<';
BITSHIFT_RIGHT : '>>';

BRACKET_OPEN : '(';
BRACKET_CLOSE : ')';
COMMA : ',';

SYSC : 'sysc';
SYSC_DOLLAR : '$';

HEX_VALUE : '0x' [a-fA-F0-9]+;
INT : [0-9]+;
DECIMAL : (([0-9]* '.'? [0-9]+)|([0-9]+ '.'? [0-9]*)) ([eE][\-+]?[0-9]+)?;
IDENTIFIER : [a-zA-Z_][a-zA-Z0-9_]* ('[' [a-zA-Z0-9_]+ ']')* ([_.] [a-zA-Z0-9_]* ('[' [a-zA-Z0-9_]+ ']')*)*;
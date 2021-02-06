lexer grammar a2lLexer;

channels {
	WHITESPACES,
	COMMENTS
}
/*
@lexer::members {
    public static final int WHITESPACES = 1; 
    public static final int COMMENTS = 2;
}*/

@header {
	package net.alenzen.a2l.antlr;
}

COMMENT : '/*' .*? '*/' -> channel(COMMENTS);
COMMENT_LINE : '//' .*? NEWLINE  -> channel(COMMENTS);

WS : (' ' | '\t') -> channel(WHITESPACES), skip;
NEWLINE : ('\r'? '\n' | '\r')+ -> channel(WHITESPACES), skip;

STRING : '"' (~('"' | '\\') | '\\' ('"' | '\\' | '\'' | 'n' | 'r' | 't') | '""')* '"';

A2ML_VERSION : 'A2ML_VERSION';
ASAP2_VERSION : 'ASAP2_VERSION';

PROJECT : 'PROJECT';
HEADER : 'HEADER';
MODULE : 'MODULE';

A2ML : 'A2ML';
AXIS_PTS : 'AXIS_PTS';
BLOB : 'BLOB';
CHARACTERISTIC : 'CHARACTERISTIC';
COMPU_METHOD : 'COMPU_METHOD';
COMPU_TAB : 'COMPU_TAB';
COMPU_VTAB : 'COMPU_VTAB';
COMPU_VTAB_RANGE : 'COMPU_VTAB_RANGE';
FRAME : 'FRAME';
FUNCTION : 'FUNCTION';
GROUP : 'GROUP';
IF_DATA : 'IF_DATA';
INSTANCE : 'INSTANCE';
MEASUREMENT : 'MEASUREMENT';
MOD_COMMON : 'MOD_COMMON';
MOD_PAR : 'MOD_PAR';
RECORD_LAYOUT : 'RECORD_LAYOUT';
TRANSFORMER : 'TRANSFORMER';
TYPEDEF_AXIS : 'TYPEDEF_AXIS';
TYPEDEF_BLOB : 'TYPEDEF_BLOB';
TYPEDEF_CHARACTERISTIC : 'TYPEDEF_CHARACTERISTIC';
TYPEDEF_MEASUREMENT : 'TYPEDEF_MEASUREMENT';
TYPEDEF_STRUCTURE : 'TYPEDEF_STRUCTURE';
UNIT : 'UNIT';
USER_RIGHTS : 'USER_RIGHTS';
VARIANT_CODING : 'VARIANT_CODING';

ADDR_EPK : 'ADDR_EPK';
ALIGNMENT_BYTE : 'ALIGNMENT_BYTE';
ALIGNMENT_FLOAT32_IEEE : 'ALIGNMENT_FLOAT32_IEEE';
ALIGNMENT_FLOAT64_IEEE : 'ALIGNMENT_FLOAT64_IEEE';
ALIGNMENT_INT64 : 'ALIGNMENT_INT64';
ALIGNMENT_LONG : 'ALIGNMENT_LONG';
ALIGNMENT_WORD : 'ALIGNMENT_WORD';
ANNOTATION : 'ANNOTATION';
ANNOTATION_LABEL : 'ANNOTATION_LABEL';
ANNOTATION_ORIGIN : 'ANNOTATION_ORIGIN';
ANNOTATION_TEXT : 'ANNOTATION_TEXT';
ARRAY_SIZE : 'ARRAY_SIZE';
AXIS_DESCR : 'AXIS_DESCR';
AXIS_PTS_REF : 'AXIS_PTS_REF';
AXIS_PTS_X : 'AXIS_PTS_X';
AXIS_PTS_Y : 'AXIS_PTS_Y';
AXIS_PTS_Z : 'AXIS_PTS_Z';
AXIS_PTS_4 : 'AXIS_PTS_4';
AXIS_PTS_5 : 'AXIS_PTS_5';
AXIS_RESCALE_X : 'AXIS_RESCALE_X';
// the list of 1.7.1 only consists of the X axis but 1.5.1 describes X, Y, and Z
AXIS_RESCALE_Y : 'AXIS_RESCALE_Y';
AXIS_RESCALE_Z : 'AXIS_RESCALE_Z';
AXIS_RESCALE_4 : 'AXIS_RESCALE_4';
AXIS_RESCALE_5 : 'AXIS_RESCALE_5';
BIT_MASK : 'BIT_MASK';
BIT_OPERATION : 'BIT_OPERATION';
BYTE_ORDER : 'BYTE_ORDER';
CALIBRATION_ACCESS : 'CALIBRATION_ACCESS';
CALIBRATION_HANDLE : 'CALIBRATION_HANDLE';
CALIBRATION_HANDLE_TEXT : 'CALIBRATION_HANDLE_TEXT';
CALIBRATION_METHOD : 'CALIBRATION_METHOD';
COEFFS : 'COEFFS';
COEFFS_LINEAR : 'COEFFS_LINEAR';
COMPARISON_QUANTITY : 'COMPARISON_QUANTITY';
COMPU_TAB_REF : 'COMPU_TAB_REF';
CPU_TYPE : 'CPU_TYPE';
CURVE_AXIS_REF : 'CURVE_AXIS_REF';
CUSTOMER : 'CUSTOMER';
CUSTOMER_NO : 'CUSTOMER_NO';
DATA_SIZE : 'DATA_SIZE';
DEFAULT_VALUE : 'DEFAULT_VALUE';
DEFAULT_VALUE_NUMERIC : 'DEFAULT_VALUE_NUMERIC';
DEF_CHARACTERISTIC : 'DEF_CHARACTERISTIC';
DEPENDENT_CHARACTERISTIC : 'DEPENDENT_CHARACTERISTIC';
DEPOSIT : 'DEPOSIT';
DISCRETE : 'DISCRETE';
DISPLAY_IDENTIFIER : 'DISPLAY_IDENTIFIER';
DIST_OP_X : 'DIST_OP_X';
DIST_OP_Y : 'DIST_OP_Y';
DIST_OP_Z : 'DIST_OP_Z';
DIST_OP_4 : 'DIST_OP_4';
DIST_OP_5 : 'DIST_OP_5';
ECU : 'ECU';
ECU_ADDRESS : 'ECU_ADDRESS';
ECU_ADDRESS_EXTENSION : 'ECU_ADDRESS_EXTENSION';
ECU_CALIBRATION_OFFSET : 'ECU_CALIBRATION_OFFSET';
EPK : 'EPK';
ERROR_MASK : 'ERROR_MASK';
EXTENDED_LIMITS : 'EXTENDED_LIMITS';
FIX_AXIS_PAR : 'FIX_AXIS_PAR';
FIX_AXIS_PAR_DIST : 'FIX_AXIS_PAR_DIST';
FIX_AXIS_PAR_LIST : 'FIX_AXIS_PAR_LIST';
FIX_NO_AXIS_PTS_X : 'FIX_NO_AXIS_PTS_X';
FIX_NO_AXIS_PTS_Y : 'FIX_NO_AXIS_PTS_Y';
FIX_NO_AXIS_PTS_Z : 'FIX_NO_AXIS_PTS_Z';
FIX_NO_AXIS_PTS_4 : 'FIX_NO_AXIS_PTS_4';
FIX_NO_AXIS_PTS_5 : 'FIX_NO_AXIS_PTS_5';
FNC_VALUES : 'FNC_VALUES';
FORMAT : 'FORMAT';
FORMULA : 'FORMULA';
FORMULA_INV : 'FORMULA_INV';
FRAME_MEASUREMENT : 'FRAME_MEASUREMENT';
FUNCTION_LIST : 'FUNCTION_LIST';
FUNCTION_VERSION : 'FUNCTION_VERSION';
GUARD_RAILS : 'GUARD_RAILS';
IDENTIFICATION : 'IDENTIFICATION';
IN_MEASUREMENT : 'IN_MEASUREMENT';
LAYOUT : 'LAYOUT';
LEFT_SHIFT : 'LEFT_SHIFT';
LOC_MEASUREMENT : 'LOC_MEASUREMENT';
MAP_LIST : 'MAP_LIST';
MATRIX_DIM : 'MATRIX_DIM';
MAX_GRAD : 'MAX_GRAD';
MAX_REFRESH : 'MAX_REFRESH';
MEMORY_LAYOUT : 'MEMORY_LAYOUT';
MEMORY_SEGMENT : 'MEMORY_SEGMENT';
MODEL_LINK : 'MODEL_LINK';
MONOTONY : 'MONOTONY';
NO_AXIS_PTS_X : 'NO_AXIS_PTS_X';
NO_AXIS_PTS_Y : 'NO_AXIS_PTS_Y';
NO_AXIS_PTS_Z : 'NO_AXIS_PTS_Z';
NO_AXIS_PTS_4 : 'NO_AXIS_PTS_4';
NO_AXIS_PTS_5 : 'NO_AXIS_PTS_5';
NO_OF_INTERFACES : 'NO_OF_INTERFACES';
NO_RESCALE_X : 'NO_RESCALE_X';
NO_RESCALE_Y : 'NO_RESCALE_Y';
NO_RESCALE_Z : 'NO_RESCALE_Z';
NO_RESCALE_4 : 'NO_RESCALE_4';
NO_RESCALE_5 : 'NO_RESCALE_5';
NUMBER : 'NUMBER';
OFFSET_X : 'OFFSET_X';
OFFSET_Y : 'OFFSET_Y';
OFFSET_Z : 'OFFSET_Z';
OFFSET_4 : 'OFFSET_4';
OFFSET_5 : 'OFFSET_5';
OUT_MEASUREMENT : 'OUT_MEASUREMENT';
PHONE_NO : 'PHONE_NO';
PHYS_UNIT : 'PHYS_UNIT';
PROJECT_NO : 'PROJECT_NO';
READ_ONLY : 'READ_ONLY';
READ_WRITE : 'READ_WRITE';
REF_CHARACTERISTIC : 'REF_CHARACTERISTIC';
REF_GROUP : 'REF_GROUP';
REF_MEASUREMENT : 'REF_MEASUREMENT';
REF_MEMORY_SEGMENT : 'REF_MEMORY_SEGMENT';
REF_UNIT : 'REF_UNIT';
RESERVED : 'RESERVED';
RIGHT_SHIFT : 'RIGHT_SHIFT';
RIP_ADDR_W : 'RIP_ADDR_W';
RIP_ADDR_X : 'RIP_ADDR_X';
RIP_ADDR_Y : 'RIP_ADDR_Y';
RIP_ADDR_Z : 'RIP_ADDR_Z';
RIP_ADDR_4 : 'RIP_ADDR_4';
RIP_ADDR_5 : 'RIP_ADDR_5';
ROOT : 'ROOT';
SHIFT_OP_X : 'SHIFT_OP_X';
SHIFT_OP_Y : 'SHIFT_OP_Y';
SHIFT_OP_Z : 'SHIFT_OP_Z';
SHIFT_OP_4 : 'SHIFT_OP_4';
SHIFT_OP_5 : 'SHIFT_OP_5';
SIGN_EXTEND : 'SIGN_EXTEND';
SI_EXPONENTS : 'SI_EXPONENTS';
SRC_ADDR_X : 'SRC_ADDR_X';
SRC_ADDR_Y : 'SRC_ADDR_Y';
SRC_ADDR_Z : 'SRC_ADDR_Z';
SRC_ADDR_4 : 'SRC_ADDR_4';
SRC_ADDR_5 : 'SRC_ADDR_5';
STATIC_RECORD_LAYOUT : 'STATIC_RECORD_LAYOUT';
STRUCTURE_COMPONENT : 'STRUCTURE_COMPONENT';
S_REC_LAYOUT : 'S_REC_LAYOUT';
STATUS_STRING_REF : 'STATUS_STRING_REF';
STEP_SIZE : 'STEP_SIZE';
SUB_FUNCTION : 'SUB_FUNCTION';
SUB_GROUP : 'SUB_GROUP';
SUPPLIER : 'SUPPLIER';
SYMBOL_LINK : 'SYMBOL_LINK';
SYSTEM_CONSTANT : 'SYSTEM_CONSTANT';
TRANSFORMER_IN_OBJECTS : 'TRANSFORMER_IN_OBJECTS';
TRANSFORMER_OUT_OBJECTS : 'TRANSFORMER_OUT_OBJECTS';
UNIT_CONVERSION : 'UNIT_CONVERSION';
USER : 'USER';
VAR_ADDRESS : 'VAR_ADDRESS';
VAR_CHARACTERISTIC : 'VAR_CHARACTERISTIC';
VAR_CRITERION : 'VAR_CRITERION';
VAR_FORBIDDEN_COMB : 'VAR_FORBIDDEN_COMB';
VAR_MEASUREMENT : 'VAR_MEASUREMENT';
VAR_NAMING : 'VAR_NAMING';
VAR_SELECTION_CHARACTERISTIC : 'VAR_SELECTION_CHARACTERISTIC';
VAR_SEPARATOR : 'VAR_SEPARATOR';
VERSION : 'VERSION';
VIRTUAL_CHARACTERISTIC : 'VIRTUAL_CHARACTERISTIC';
VIRTUAL : 'VIRTUAL';

// conversion types
RAT_FUNC : 'RAT_FUNC';
TAB_INTP : 'TAB_INTP';
TAB_NOINTP: 'TAB_NOINTP';
TAB_VERB : 'TAB_VERB';
FORM : 'FORM';
IDENTICAL : 'IDENTICAL';
LINEAR : 'LINEAR';

// data types
UBYTE : 'UBYTE';
SBYTE : 'SBYTE';
UWORD : 'UWORD';
SWORD : 'SWORD';
ULONG : 'ULONG';
SLONG : 'SLONG';
A_UINT64 : 'A_UINT64';
A_INT64 : 'A_INT64';
FLOAT32_IEEE : 'FLOAT32_IEEE';
FLOAT64_IEEE : 'FLOAT64_IEEE';

// PrgType
PRG_CODE : 'PRG_CODE';
PRG_DATA : 'PRG_DATA';
PRG_RESERVED : 'PRG_RESERVED';

// PrgType Memory segment
CALIBRATION_VARIABLES : 'CALIBRATION_VARIABLES';
CODE : 'CODE';
DATA : 'DATA';
EXCLUDE_FROM_FLASH : 'EXCLUDE_FROM_FLASH';
OFFLINE_DATA : 'OFFLINE_DATA';
SERAM : 'SERAM';
VARIABLES : 'VARIABLES';

// memory type
EEPROM : 'EEPROM';
EPROM : 'EPROM';
FLASH : 'FLASH';
RAM : 'RAM';
ROM : 'ROM';
REGISTER : 'REGISTER';
NOT_IN_ECU : 'NOT_IN_ECU';

// DEPOSIT modes
ABSOLUTE : 'ABSOLUTE';
DIFFERENCE : 'DIFFERENCE';

// BYTE_ORDER
LITTLE_ENDIAN : 'LITTLE_ENDIAN';
BIG_ENDIAN : 'BIG_ENDIAN';
MSB_LAST : 'MSB_LAST';
MSB_FIRST : 'MSB_FIRST';

// IndexMode enum
COLUMN_DIR : 'COLUMN_DIR';
ROW_DIR : 'ROW_DIR';
ALTERNATE_WITH_X : 'ALTERNATE_WITH_X';
ALTERNATE_WITH_Y : 'ALTERNATE_WITH_Y';
ALTERNATE_CURVES : 'ALTERNATE_CURVES';

// addrtype enum
PBYTE : 'PBYTE';
PWORD : 'PWORD';
PLONG : 'PLONG';
DIRECT : 'DIRECT';

// index order enum
INDEX_INCR : 'INDEX_INCR';
INDEX_DECR : 'INDEX_DECR';

// calibration access enum
CALIBRATION : 'CALIBRATION'; 
NO_CALIBRATION : 'NO_CALIBRATION'; 
NOT_IN_MCD_SYSTEM : 'NOT_IN_MCD_SYSTEM'; 
OFFLINE_CALIBRATION : 'OFFLINE_CALIBRATION';

// data size
BYTE : 'BYTE';
WORD : 'WORD';
LONG : 'LONG';

// type enum
// used in characteristic
VALUE : 'VALUE';
CURVE : 'CURVE';
MAP : 'MAP';
CUBOID : 'CUBOID';
VAL_BLK : 'VAL_BLK';
ASCII : 'ASCII';
CUBE_4 : 'CUBE_4';
CUBE_5 : 'CUBE_5';

// type enum used in unit
DERIVED : 'DERIVED'; 
EXTENDED_SI : 'EXTENDED_SI';

// attribute enum
STD_AXIS : 'STD_AXIS';
FIX_AXIS : 'FIX_AXIS';
COM_AXIS : 'COM_AXIS';
RES_AXIS : 'RES_AXIS';
CURVE_AXIS : 'CURVE_AXIS';

// attribute_memory_segment
INTERN : 'INTERN';
EXTERN : 'EXTERN';

// monotony enum
MON_INCREASE : 'MON_INCREASE';
MON_DECREASE : 'MON_DECREASE';
STRICT_INCREASE : 'STRICT_INCREASE';
STRICT_DECREASE : 'STRICT_DECREASE';
MONOTONOUS : 'MONOTONOUS';
STRICT_MON : 'STRICT_MON';
NOT_MON : 'NOT_MON';

// var naming enum
NUMERIC : 'NUMERIC';
ALPHA : 'ALPHA';

// trigger condition enum
ON_CHANGE : 'ON_CHANGE';
ON_USER_REQUEST : 'ON_USER_REQUEST';

BEGIN : '/begin';
END : '/end';

HEX_VALUE : '0x' [a-fA-F0-9]+;
INT : [\-+]? [0-9]+;
DECIMAL : [\-+]? [0-9]* '.'? [0-9]+ ([eE][\-+]?[0-9]+)?;
IDENTIFIER : [a-zA-Z_][a-zA-Z0-9_\-]* ('[' [a-zA-Z0-9]+ ']')* ('.' [a-zA-Z0-9_\-]* ('[' [a-zA-Z0-9]+ ']')*)*;

A2ML_BLOCK : BEGIN WS+ A2ML (.*?) END WS+ A2ML;
BLOB_A2ML_BLOCK : BEGIN WS+ BLOB (.*?) END WS+ BLOB;
IF_DATA_BLOCK : BEGIN WS+ IF_DATA (.*?) END WS+ IF_DATA;

INCLUDE : '/include' -> pushMode(INCLUDE_MODE);

mode INCLUDE_MODE;
COMMENT_INCL : '/*' .*? '*/' -> skip;
COMMENT_LINE_INCL : '//' .*? NEWLINE_INCL  -> skip;
WS_INCL : (' ' | '\t') -> skip;
NEWLINE_INCL : ('\r'? '\n' | '\r')+ -> skip;
FILEPATH : (SPACELESS_PATH | PAR_PATH) -> popMode;
PAR_PATH : '"' ~["\r\n]* '"';
SPACELESS_PATH : ~[ \t\r\n"]+;
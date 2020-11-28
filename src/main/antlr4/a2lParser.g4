/*
TODO: check for guessed definitions and compare them to the real 1.7 definition (1.7 spec needed)
*/

parser grammar a2lParser;
options { tokenVocab=a2lLexer; }

@header {
	package net.alenzen.a2l.antlr;
}

string_exp : string=STRING;
conversion_type : RAT_FUNC | TAB_INTP | TAB_NOINTP | TAB_VERB | FORM | IDENTICAL | LINEAR;
prg_type_memory_layout : PRG_CODE | PRG_DATA | PRG_RESERVED;
prg_type_memory_segment : CALIBRATION_VARIABLES | CODE | DATA | EXCLUDE_FROM_FLASH | OFFLINE_DATA | RESERVED | SERAM | VARIABLES;
memory_type : EEPROM | EPROM | FLASH | RAM | ROM | REGISTER | NOT_IN_ECU;
byte_order_enum : LITTLE_ENDIAN | BIG_ENDIAN | MSB_FIRST | MSB_LAST;
deposit_mode : ABSOLUTE | DIFFERENCE;
datatype_enum : UBYTE | SBYTE | UWORD | SWORD | ULONG | SLONG | A_UINT64 | A_INT64 | FLOAT32_IEEE | FLOAT64_IEEE;
datasize_enum : BYTE | WORD | LONG;
index_mode_enum : COLUMN_DIR | ROW_DIR | ALTERNATE_WITH_X | ALTERNATE_WITH_Y | ALTERNATE_CURVES;
addresstype_enum : PBYTE | PWORD | PLONG | DIRECT;
index_order_enum : INDEX_INCR | INDEX_DECR;
calibration_access_enum : CALIBRATION | NO_CALIBRATION | NOT_IN_MCD_SYSTEM | OFFLINE_CALIBRATION;
type_enum : VALUE | CURVE | MAP | CUBOID | VAL_BLK | ASCII | CUBE_4 | CUBE_5;
unit_type_enum : DERIVED | EXTENDED_SI;
attribute_memory_segment : INTERN | EXTERN;
attribute_axis_descr_enum : STD_AXIS | FIX_AXIS | COM_AXIS | RES_AXIS | CURVE_AXIS;
monotony_enum : MON_INCREASE | MON_DECREASE | STRICT_INCREASE | STRICT_DECREASE | MONOTONOUS | STRICT_MON | NOT_MON;

int_value : INT | HEX_VALUE;
value : INT | DECIMAL | HEX_VALUE;
value_pair : InVal=value OutVal=value;

/*
ROOT rule to interprate content
*/
a2l_file : (a2ml_version_exp | asap2_version_exp)* project_block;

/*
ROOT rule to interprate includes
*/
a2l_file_includes : (Includes+=include_exp | .)*?;
include_exp : INCLUDE Filename=FILEPATH;
                    
/*
A2ML_VERSION
*/
a2ml_version_exp : A2ML_VERSION VersionNo=int_value UpgradeNo=int_value;

/*
ASAP2_VERSION
*/
asap2_version_exp : ASAP2_VERSION VersionNo=int_value UpgradeNo=int_value;

/*
PROJECT
*/
project_exp : PROJECT Name=IDENTIFIER LongIdentifier=string_exp;
project_block : BEGIN project_exp project_sub_nodes END PROJECT;
project_sub_nodes : (
                      header_block 
                    | module_block
                    )*;

/*
HEADER
*/
header_exp : HEADER Comment=string_exp;
header_block : BEGIN header_exp header_sub_nodes END HEADER;
header_sub_nodes : (version_exp | project_no_exp)*;

version_exp : VERSION VersionIdentifier=string_exp;
project_no_exp : PROJECT_NO ProjectNumber=IDENTIFIER;

/*
MODULE
*/
module_exp : MODULE Name=IDENTIFIER LongIdentifier=string_exp;
module_block : BEGIN module_exp module_sub_nodes END MODULE;
module_sub_nodes : (
	                    a2ml_block
	                | if_data_block
	                | mod_common_block
	                | mod_par_block
	                | compu_method_block
	                | compu_tab_block
	                | compu_vtab_block
	                | compu_vtab_range_block
	                | measurement_block
	                | record_layout_block
	                | characteristic_block
	                | axis_pts_block
	                | function_block
	                | group_block
	                | frame_block
	                | unit_block
	                | user_rights_block
	                | typedef_characteristic_block
	                | instance_block
	                | typedef_axis_block
	                | typedef_structure_block
	                | transformer_block
	                | blob_block
	                | variant_coding_block
	                  // TODO: TYPEDEF_BLOB TYPEDEF_MEASURMENT
                  )*
                ;

/*
UNIT
*/
unit_exp : UNIT Name=IDENTIFIER LongIdentifier=string_exp Display=string_exp Type=unit_type_enum;
unit_block : BEGIN unit_exp unit_sub_nodes END UNIT;
unit_sub_nodes : (
                   ref_unit_exp
                 | si_exponents_exp
                 | unit_conversion_exp
                 )*;

si_exponents_exp : SI_EXPONENTS Length=int_value Mass=int_value Time=int_value ElectricCurrent=int_value Temperature=int_value AmountOfSubstance=int_value LuminousIntensity=int_value;
unit_conversion_exp : UNIT_CONVERSION Gradient=value Offset=value;

/*
USER_RIGHTS
*/
user_rights_exp : USER_RIGHTS UserLevelId=IDENTIFIER;
user_rights_block : BEGIN user_rights_exp user_rights_sub_nodes END USER_RIGHTS;
user_rights_sub_nodes : (
                          read_only_exp
                        | ref_group_block
                        )*;

ref_group_block : BEGIN REF_GROUP Identifier+=IDENTIFIER* END REF_GROUP;

/*
FRAME
*/
frame_exp : FRAME Name=IDENTIFIER LongIdentifier=string_exp ScalingUnit=int_value Rate=int_value;
frame_block : BEGIN frame_exp frame_sub_nodes END FRAME;
frame_sub_nodes : (
                    frame_measurement_exp
                  | if_data_block
                  )*;

frame_measurement_exp : FRAME_MEASUREMENT Identifier+=IDENTIFIER*;

/*
A2ML
*/
a2ml_block : A2ML_BLOCK;

/*
IF_DATA
*/
if_data_block : IF_DATA_BLOCK;

/*
BLOB
*/
blob_block : BLOB_A2ML_BLOCK;

/*
MOD_COMMON
*/
mod_common_exp : MOD_COMMON Comment=string_exp;
mod_common_block : BEGIN mod_common_exp mod_common_sub_nodes END MOD_COMMON;
mod_common_sub_nodes : (
                         alignment_byte_exp
                        | alignment_word_exp
                        | alignment_long_exp
                        | alignment_int64_exp
                        | alignment_float32_exp
                        | alignment_float64_exp 
                        | deposit_exp 
                        | byte_order_exp 
                        | data_size_exp
                        | s_rec_layout_exp
                        )*;
                        
alignment_byte_exp : ALIGNMENT_BYTE AlignmentBorder=int_value;
alignment_word_exp : ALIGNMENT_WORD AlignmentBorder=int_value;
alignment_long_exp : ALIGNMENT_LONG AlignmentBorder=int_value;
alignment_int64_exp : ALIGNMENT_INT64 AlignmentBorder=int_value;
alignment_float32_exp : ALIGNMENT_FLOAT32_IEEE AlignmentBorder=int_value;
alignment_float64_exp : ALIGNMENT_FLOAT64_IEEE AlignmentBorder=int_value;

deposit_exp : DEPOSIT Mode=deposit_mode;
byte_order_exp : BYTE_ORDER ByteOrder=byte_order_enum;
data_size_exp : DATA_SIZE Size=int_value;
// S_REC_LAYOUT is not part of 1.7.1 but in 1.5.1
s_rec_layout_exp : S_REC_LAYOUT Name=IDENTIFIER;

/*
MOD_PAR
*/
mod_par_exp : MOD_PAR Comment=string_exp;
mod_par_block : BEGIN mod_par_exp mod_par_sub_nodes END MOD_PAR;
mod_par_sub_nodes : (
                      no_of_interfaces_exp
                    | memory_segment_block
                    | system_constant_exp
                    | version_exp
                    | addr_epk_exp
                    | epk_exp
                    | supplier_exp
                    | customer_exp
                    | customer_no_exp
                    | user_exp
                    | phone_no_exp
                    | ecu_exp
                    | cpu_type_exp
                    | ecu_calibration_offset_exp
                    | calibration_method_block
                    | memory_layout_block
                    )*;

no_of_interfaces_exp : NO_OF_INTERFACES Num=int_value;

memory_segment_exp : MEMORY_SEGMENT Name=IDENTIFIER LongIdentifier=string_exp PrgType=prg_type_memory_segment MemoryType=memory_type Attribute=attribute_memory_segment Address=int_value Size=int_value (Offset+=int_value Offset+=int_value Offset+=int_value Offset+=int_value Offset+=int_value);
memory_segment_block : BEGIN memory_segment_exp memory_segment_sub_nodes END MEMORY_SEGMENT;
memory_segment_sub_nodes : (if_data_block)*;

system_constant_exp : SYSTEM_CONSTANT Name=string_exp Value=string_exp;

memory_layout_exp : MEMORY_LAYOUT PrgType=prg_type_memory_layout Address=int_value Size=int_value (Offset+=int_value Offset+=int_value Offset+=int_value Offset+=int_value Offset+=int_value);
memory_layout_block : BEGIN memory_layout_exp memory_layout_sub_nodes END MEMORY_LAYOUT;
memory_layout_sub_nodes : (
                            if_data_block
                          )*;

calibration_method_exp : CALIBRATION_METHOD Method=string_exp Version=int_value;
calibration_method_block : BEGIN calibration_method_exp calibration_method_sub_nodes END CALIBRATION_METHOD;
calibration_method_sub_nodes: (
                                calibration_handle_block
                              )*;

calibration_handle_block : BEGIN CALIBRATION_HANDLE Handle+=int_value* calibration_handle_text_exp END CALIBRATION_HANDLE;
calibration_handle_text_exp : CALIBRATION_HANDLE_TEXT text=string_exp;

ecu_calibration_offset_exp : ECU_CALIBRATION_OFFSET Offset=int_value;

cpu_type_exp : CPU_TYPE CPU=string_exp;
ecu_exp : ECU ControlUnit=string_exp;
phone_no_exp : PHONE_NO PhoneNo=string_exp;
user_exp : USER UserName=string_exp;
customer_exp : CUSTOMER Customer=string_exp;
customer_no_exp : CUSTOMER_NO Number=string_exp;
supplier_exp : SUPPLIER Manufacturer=string_exp;
epk_exp : EPK Identifier=string_exp; 
addr_epk_exp : ADDR_EPK Address=int_value;

/*
COMPU_METHOD
*/
compu_method_exp : COMPU_METHOD Name=IDENTIFIER LongIdentifier=string_exp ConversionType=conversion_type Format=string_exp Unit=string_exp;
compu_method_block : BEGIN compu_method_exp compu_method_sub_nodes END COMPU_METHOD;
compu_method_sub_nodes : (
                           formula_block
                         | coeffs_exp
                         | compu_tab_ref_exp
                         | ref_unit_exp
                         | coeffs_linear_exp
                         | status_string_ref_exp
                         )*;

formula_exp : FORMULA f_x=string_exp;
formula_block : BEGIN formula_exp formula_inv_exp? END FORMULA;
formula_inv_exp : FORMULA_INV g_x=string_exp;

coeffs_exp : COEFFS a=value b=value c=value d=value e=value f=value;
coeffs_linear_exp : COEFFS_LINEAR a=value b=value;
compu_tab_ref_exp : COMPU_TAB_REF ConversionTable=IDENTIFIER;
ref_unit_exp : REF_UNIT Unit=IDENTIFIER;
status_string_ref_exp : STATUS_STRING_REF ConversionTable=IDENTIFIER;

/*
COMPU_TAB
*/
compu_tab_exp : COMPU_TAB Name=IDENTIFIER LongIdentifier=string_exp ConversionType=conversion_type NumberValuePairs=int_value ValuePairs+=value_pair*;
compu_tab_block : BEGIN compu_tab_exp compu_tab_sub_nodes END COMPU_TAB;
compu_tab_sub_nodes : (default_value_numeric_exp | default_value_exp)*;

default_value_numeric_exp : DEFAULT_VALUE_NUMERIC display_value=value;
default_value_exp : DEFAULT_VALUE display_string=string_exp;

/*
COMPU_VTAB
*/
value_description_pair : InVal=value OutVal=string_exp;
compu_vtab_exp : COMPU_VTAB Name=IDENTIFIER LongIdentifier=string_exp ConversionType=TAB_VERB NumberValuePairs=int_value ValuePairs+=value_description_pair*;
compu_vtab_block : BEGIN compu_vtab_exp compu_vtab_default_value? END COMPU_VTAB;
compu_vtab_default_value : DEFAULT_VALUE Value=string_exp;

/*
COMPU_VTAB_RANGE
*/
value_description_triple : InValMin=value InValMax=value OutVal=string_exp;
compu_vtab_range_exp : COMPU_VTAB_RANGE Name=IDENTIFIER LongIdentifier=string_exp NumberValueTriples=int_value ValueTriples+=value_description_triple*;
compu_vtab_range_block : BEGIN compu_vtab_range_exp compu_vtab_range_default_value? END COMPU_VTAB_RANGE;
compu_vtab_range_default_value : DEFAULT_VALUE Value=string_exp;

/*
MEASUREMENT
*/
measurement_exp : MEASUREMENT Name=IDENTIFIER LongIdentifier=string_exp Datatype=datatype_enum Conversion=IDENTIFIER Resolution=int_value Accuracy=value LowerLimit=value UpperLimit=value;
measurement_block : BEGIN measurement_exp measurement_sub_nodes END MEASUREMENT;
measurement_sub_nodes : (
                          display_identifier_exp
                        | read_write_exp
                        | format_exp
                        | array_size_exp
                        | bit_mask_exp
                        | bit_operation_block
                        | byte_order_exp
                        | max_refresh_exp
                        | virtual_block
                        | error_mask_exp
                        | function_list_block
                        | if_data_block
                        | ecu_address_exp
                        | ref_memory_segment_exp
                        | annotation_block
                        | matrix_dim_exp
                        | ecu_address_extension_exp
                        | discrete_exp
                        | layout_exp
                        | phys_unit_exp
                        | symbol_link_exp
                        )*;

symbol_link_exp : SYMBOL_LINK SymbolName=string_exp Offset=int_value;
indexmode_enum : ROW_DIR | COLUMN_DIR;
layout_exp : LAYOUT IndexMode=indexmode_enum;
display_identifier_exp : DISPLAY_IDENTIFIER display_name=IDENTIFIER;
read_write_exp : READ_WRITE;
format_exp : FORMAT Format=string_exp;
array_size_exp : ARRAY_SIZE Number=int_value;
bit_mask_exp : BIT_MASK Mask=int_value;

bit_operation_block : BEGIN BIT_OPERATION bit_operation_sub_nodes END BIT_OPERATION;
bit_operation_sub_nodes : (
                            left_shift_exp
                          | right_shift_exp
                          | sign_extend_exp
                          )*;

left_shift_exp : LEFT_SHIFT Bitcount=int_value;
right_shift_exp : RIGHT_SHIFT Bitcount=int_value;
sign_extend_exp : SIGN_EXTEND;

max_refresh_exp : MAX_REFRESH ScalingUnit=int_value Rate=int_value;
virtual_block : BEGIN VIRTUAL MeasuringChannels+=IDENTIFIER* END VIRTUAL;
error_mask_exp : ERROR_MASK Mask=int_value;
function_list_block : BEGIN FUNCTION_LIST Names+=IDENTIFIER* END FUNCTION_LIST;
ecu_address_exp : ECU_ADDRESS Address=int_value;
ref_memory_segment_exp: REF_MEMORY_SEGMENT Name=IDENTIFIER;

annotation_exp : ANNOTATION;
annotation_block : BEGIN annotation_exp annotation_sub_nodes END ANNOTATION;
annotation_sub_nodes : (
                         annotation_label_exp
                       | annotation_origin_exp
                       | annotation_text_block
                       )*;
annotation_label_exp : ANNOTATION_LABEL label=string_exp;
annotation_origin_exp : ANNOTATION_ORIGIN origin=string_exp;
annotation_text_block : BEGIN ANNOTATION_TEXT annotation_text+=string_exp* END ANNOTATION_TEXT;

matrix_dim_exp : MATRIX_DIM xDim=int_value yDim=int_value? zDim=int_value?;
ecu_address_extension_exp : ECU_ADDRESS_EXTENSION Extension=int_value;
discrete_exp : DISCRETE;
phys_unit_exp : PHYS_UNIT Unit=string_exp;

/*
RECORD_LAYOUT
*/
record_layout_exp : RECORD_LAYOUT Name=IDENTIFIER;
record_layout_block : BEGIN record_layout_exp record_layout_sub_nodes END RECORD_LAYOUT;
record_layout_sub_nodes : (
                            fnc_values_exp
                          | alignment_byte_exp
                          | alignment_word_exp
                          | alignment_long_exp
                          | alignment_int64_exp
                          | alignment_float32_exp
                          | alignment_float64_exp
                          | axis_pts_x_exp
                          | axis_pts_y_exp
                          | axis_pts_z_exp
                          | axis_pts_4_exp
                          | axis_pts_5_exp
                          | axis_rescale_x_exp
                          | axis_rescale_y_exp
                          | axis_rescale_z_exp
                          | axis_rescale_4_exp
                          | axis_rescale_5_exp
                          | dist_op_x_exp
                          | dist_op_y_exp
                          | dist_op_z_exp
                          | dist_op_4_exp
                          | dist_op_5_exp
                          | fix_no_axis_pts_x_exp
                          | fix_no_axis_pts_y_exp
                          | fix_no_axis_pts_z_exp
                          | fix_no_axis_pts_4_exp
                          | fix_no_axis_pts_5_exp
                          | identification_exp
                          | no_axis_pts_x_exp
                          | no_axis_pts_y_exp
                          | no_axis_pts_z_exp
                          | no_axis_pts_4_exp
                          | no_axis_pts_5_exp
                          | no_rescale_x_exp
                          | no_rescale_y_exp
                          | no_rescale_z_exp
                          | no_rescale_4_exp
                          | no_rescale_5_exp
                          | offset_x_exp
                          | offset_y_exp
                          | offset_z_exp
                          | offset_4_exp
                          | offset_5_exp
                          | reserved_exp
                          | rip_addr_w_exp
                          | rip_addr_x_exp
                          | rip_addr_y_exp
                          | rip_addr_z_exp
                          | rip_addr_4_exp
                          | rip_addr_5_exp
                          | shift_op_x_exp
                          | shift_op_y_exp
                          | shift_op_z_exp
                          | shift_op_4_exp
                          | shift_op_5_exp
                          | src_addr_x_exp
                          | src_addr_y_exp
                          | src_addr_z_exp
                          | src_addr_4_exp
                          | src_addr_5_exp
                          | static_record_layout_exp
                          )*;

static_record_layout_exp : STATIC_RECORD_LAYOUT;
fnc_values_exp : FNC_VALUES Position=int_value Datatype=datatype_enum IndexMode=index_mode_enum Addresstype=addresstype_enum;

axis_pts_x_exp : AXIS_PTS_X Parameters=axis_pts_xyz45_parameters;
axis_pts_y_exp : AXIS_PTS_Y Parameters=axis_pts_xyz45_parameters;
axis_pts_z_exp : AXIS_PTS_Z Parameters=axis_pts_xyz45_parameters;
axis_pts_4_exp : AXIS_PTS_4 Parameters=axis_pts_xyz45_parameters;
axis_pts_5_exp : AXIS_PTS_5 Parameters=axis_pts_xyz45_parameters;
axis_pts_xyz45_parameters : Position=int_value Datatype=datatype_enum IndexIncr=index_order_enum Adresing=addresstype_enum;

guard_rails_exp : GUARD_RAILS;
extended_limits_exp : EXTENDED_LIMITS LowerLimit=value UpperLimit=value;
calibration_access_exp : CALIBRATION_ACCESS Type=calibration_access_enum;

// in general for axis: 1.6 contains 4 and 5 but 1.5.1 does not

axis_rescale_x_exp : AXIS_RESCALE_X Parameters=axis_rescale_xyz45_parameters;
axis_rescale_y_exp : AXIS_RESCALE_Y Parameters=axis_rescale_xyz45_parameters;
axis_rescale_z_exp : AXIS_RESCALE_Z Parameters=axis_rescale_xyz45_parameters;
axis_rescale_4_exp : AXIS_RESCALE_4 Parameters=axis_rescale_xyz45_parameters;
axis_rescale_5_exp : AXIS_RESCALE_5 Parameters=axis_rescale_xyz45_parameters;
axis_rescale_xyz45_parameters : Position=int_value Datatype=datatype_enum MaxNumberOfrescalePairs=int_value IndexIncr=index_order_enum Adressing=addresstype_enum;

no_rescale_x_exp : NO_RESCALE_X Parameters=position_datatype_parameters;
no_rescale_y_exp : NO_RESCALE_Y Parameters=position_datatype_parameters;
no_rescale_z_exp : NO_RESCALE_Z Parameters=position_datatype_parameters;
no_rescale_4_exp : NO_RESCALE_4 Parameters=position_datatype_parameters;
no_rescale_5_exp : NO_RESCALE_5 Parameters=position_datatype_parameters;
position_datatype_parameters : Position=int_value Datatype=datatype_enum;

dist_op_x_exp : DIST_OP_X Parameters=position_datatype_parameters;
dist_op_y_exp : DIST_OP_Y Parameters=position_datatype_parameters;
dist_op_z_exp : DIST_OP_Z Parameters=position_datatype_parameters;
dist_op_4_exp : DIST_OP_4 Parameters=position_datatype_parameters;
dist_op_5_exp : DIST_OP_5 Parameters=position_datatype_parameters;

fix_no_axis_pts_x_exp : FIX_NO_AXIS_PTS_X NumberOfAxisPoints=int_value;
fix_no_axis_pts_y_exp : FIX_NO_AXIS_PTS_Y NumberOfAxisPoints=int_value;
fix_no_axis_pts_z_exp : FIX_NO_AXIS_PTS_Z NumberOfAxisPoints=int_value;
fix_no_axis_pts_4_exp : FIX_NO_AXIS_PTS_4 NumberOfAxisPoints=int_value;
fix_no_axis_pts_5_exp : FIX_NO_AXIS_PTS_5 NumberOfAxisPoints=int_value;

identification_exp : IDENTIFICATION Position=int_value Datatype=datatype_enum;

no_axis_pts_x_exp : NO_AXIS_PTS_X Parameters=position_datatype_parameters;
no_axis_pts_y_exp : NO_AXIS_PTS_Y Parameters=position_datatype_parameters;
no_axis_pts_z_exp : NO_AXIS_PTS_Z Parameters=position_datatype_parameters;
no_axis_pts_4_exp : NO_AXIS_PTS_4 Parameters=position_datatype_parameters;
no_axis_pts_5_exp : NO_AXIS_PTS_5 Parameters=position_datatype_parameters;

offset_x_exp : OFFSET_X Parameters=position_datatype_parameters;
offset_y_exp : OFFSET_Y Parameters=position_datatype_parameters;
offset_z_exp : OFFSET_Z Parameters=position_datatype_parameters;
offset_4_exp : OFFSET_4 Parameters=position_datatype_parameters;
offset_5_exp : OFFSET_5 Parameters=position_datatype_parameters;

reserved_exp : RESERVED Position=int_value DataSize=datasize_enum;

rip_addr_w_exp : RIP_ADDR_W Parameters=position_datatype_parameters;
rip_addr_x_exp : RIP_ADDR_X Parameters=position_datatype_parameters;
rip_addr_y_exp : RIP_ADDR_Y Parameters=position_datatype_parameters;
rip_addr_z_exp : RIP_ADDR_Z Parameters=position_datatype_parameters;
rip_addr_4_exp : RIP_ADDR_4 Parameters=position_datatype_parameters;
rip_addr_5_exp : RIP_ADDR_5 Parameters=position_datatype_parameters;

shift_op_x_exp : SHIFT_OP_X Parameters=position_datatype_parameters;
shift_op_y_exp : SHIFT_OP_Y Parameters=position_datatype_parameters;
shift_op_z_exp : SHIFT_OP_Z Parameters=position_datatype_parameters;
shift_op_4_exp : SHIFT_OP_4 Parameters=position_datatype_parameters;
shift_op_5_exp : SHIFT_OP_5 Parameters=position_datatype_parameters;

src_addr_x_exp : SRC_ADDR_X Parameters=position_datatype_parameters;
src_addr_y_exp : SRC_ADDR_Y Parameters=position_datatype_parameters;
src_addr_z_exp : SRC_ADDR_Z Parameters=position_datatype_parameters;
src_addr_4_exp : SRC_ADDR_4 Parameters=position_datatype_parameters;
src_addr_5_exp : SRC_ADDR_5 Parameters=position_datatype_parameters;

/*
CHARACTERISTIC
*/
characteristic_exp : CHARACTERISTIC Name=IDENTIFIER LongIdentifier=string_exp Type=type_enum Address=int_value Deposit=IDENTIFIER MaxDiff=value Conversion=IDENTIFIER LowerLimit=value UpperLimit=value;
characteristic_block : BEGIN characteristic_exp characteristic_sub_nodes END CHARACTERISTIC;
characteristic_sub_nodes : (
                             display_identifier_exp
                           | format_exp
                           | byte_order_exp
                           | bit_mask_exp
                           | function_list_block
                           | number_exp
                           | extended_limits_exp
                           | read_only_exp
                           | guard_rails_exp
                           | map_list_block
                           | max_refresh_exp
                           | dependent_characteristic_block
                           | virtual_characteristic_block
                           | ref_memory_segment_exp
                           | annotation_block
                           | comparison_quantity_exp
                           | if_data_block
                           | axis_descr_block
                           | calibration_access_exp
                           | matrix_dim_exp
                           | ecu_address_extension_exp
                           | discrete_exp
                           | phys_unit_exp
                           | step_size_exp
                           | symbol_link_exp
                           | var_address_block
                           | model_link_exp
                           )*;

// <guessed definition from demo file for 1.7.1>
model_link_exp : MODEL_LINK model=string_exp;
var_address_block : BEGIN VAR_ADDRESS Addresses+=int_value* END VAR_ADDRESS;
step_size_exp : STEP_SIZE StepSize=value;
number_exp : NUMBER Number=int_value;
read_only_exp : READ_ONLY;
map_list_block : BEGIN MAP_LIST Names+=IDENTIFIER* END MAP_LIST;

dependent_characteristic_exp : DEPENDENT_CHARACTERISTIC Formula=string_exp;
dependent_characteristic_block : BEGIN dependent_characteristic_exp Characteristic+=IDENTIFIER* END DEPENDENT_CHARACTERISTIC;

virtual_characteristic_exp : VIRTUAL_CHARACTERISTIC Formula=string_exp;
virtual_characteristic_block : BEGIN virtual_characteristic_exp Characteristics+=IDENTIFIER* END VIRTUAL_CHARACTERISTIC;

comparison_quantity_exp : COMPARISON_QUANTITY Name=IDENTIFIER;

axis_descr_exp : AXIS_DESCR Attribute=attribute_axis_descr_enum InputQuantity=IDENTIFIER Conversion=IDENTIFIER MaxAxisPoints=int_value LowerLimit=value UpperLimit=value;
axis_descr_block : BEGIN axis_descr_exp axis_descr_sub_nodes END AXIS_DESCR;
axis_descr_sub_nodes : (
                         read_only_exp
                       | format_exp
                       | annotation_block
                       | axis_pts_ref_exp
                       | max_grad_exp
                       | monotony_exp
                       | byte_order_exp
                       | extended_limits_exp
                       | fix_axis_par_exp
                       | fix_axis_par_dist_exp
                       | fix_axis_par_list_block
                       | deposit_exp
                       | curve_axis_ref_exp
                       | phys_unit_exp
                       | step_size_exp
                       )*;

axis_pts_ref_exp : AXIS_PTS_REF AxisPoints=IDENTIFIER;
max_grad_exp : MAX_GRAD MaxGradient=value;
monotony_exp : MONOTONY Monotony=monotony_enum;
fix_axis_par_exp : FIX_AXIS_PAR Offset=int_value Shift=int_value Numberapo=int_value;
fix_axis_par_dist_exp : FIX_AXIS_PAR_DIST Offset=int_value Distance=int_value Numberapo=int_value;
fix_axis_par_list_block : BEGIN FIX_AXIS_PAR_LIST AxisPts_Values+=value* END FIX_AXIS_PAR_LIST;
curve_axis_ref_exp : CURVE_AXIS_REF CurveAxis=IDENTIFIER;

/*
AXIS_PTS
*/
axis_pts_exp : AXIS_PTS Name=IDENTIFIER LongIdentifier=string_exp Address=int_value InputQuantity=IDENTIFIER Deposit=IDENTIFIER MaxDiff=value Conversion=IDENTIFIER MaxAxisPoints=int_value LowerLimit=value UpperLimit=value;
axis_pts_block : BEGIN axis_pts_exp axis_pts_sub_nodes END AXIS_PTS;
axis_pts_sub_nodes : (
                       display_identifier_exp
                     | format_exp
                     | deposit_exp
                     | byte_order_exp
                     | function_list_block
                     | ref_memory_segment_exp
                     | guard_rails_exp
                     | extended_limits_exp
                     | annotation_block
                     | if_data_block
                     | calibration_access_exp
                     | ecu_address_extension_exp
                     | monotony_exp
                     | phys_unit_exp
                     | read_only_exp
                     | step_size_exp
                     | symbol_link_exp
                     )*;

/*
FUNCTION
*/
function_exp : FUNCTION Name=IDENTIFIER LongIdentifier=string_exp;
function_block : BEGIN function_exp function_sub_nodes END FUNCTION;
function_sub_nodes : (
                       annotation_block
                     | def_characteristic_block
                     | function_version_exp
                     | if_data_block
                     | in_measurement_block
                     | loc_measurement_block
                     | out_measurement_block
                     | ref_characteristic_block
                     | sub_function_block
                     )*;

function_version_exp : FUNCTION_VERSION VersionIdentifier=string_exp;
def_characteristic_block : BEGIN DEF_CHARACTERISTIC Identifier+=IDENTIFIER* END DEF_CHARACTERISTIC;
ref_characteristic_block : BEGIN REF_CHARACTERISTIC Identifier+=IDENTIFIER* END REF_CHARACTERISTIC;
in_measurement_block : BEGIN IN_MEASUREMENT Identifier+=IDENTIFIER* END IN_MEASUREMENT;
loc_measurement_block : BEGIN LOC_MEASUREMENT Identifier+=IDENTIFIER* END LOC_MEASUREMENT;
out_measurement_block : BEGIN OUT_MEASUREMENT Identifier+=IDENTIFIER* END OUT_MEASUREMENT;
sub_function_block : BEGIN SUB_FUNCTION Identifier+=IDENTIFIER* END SUB_FUNCTION;

/*
GROUP
*/
group_exp : GROUP GroupName=IDENTIFIER GroupLongIdentifier=string_exp;
group_block : BEGIN group_exp group_sub_nodes END GROUP;
group_sub_nodes : (
                    annotation_block
                  | root_exp
                  | ref_characteristic_block
                  | ref_measurement_block
                  | function_list_block
                  | sub_group_block
                  | if_data_block
                  )*;

root_exp : ROOT;
ref_measurement_block : BEGIN REF_MEASUREMENT Identifier+=IDENTIFIER* END REF_MEASUREMENT;
sub_group_block : BEGIN SUB_GROUP Identifier+=IDENTIFIER* END SUB_GROUP;

/*
VARIANT_CODING
*/
variant_coding_exp : VARIANT_CODING;
variant_coding_block : BEGIN variant_coding_exp variant_coding_sub_nodes END VARIANT_CODING;
variant_coding_sub_nodes : (
                             var_characteristic_block
                           | var_criterion_block
                           | var_forbidden_comb_block
                           | var_naming_exp
                           | var_separator_exp
                           )*;

var_characteristic_exp : VAR_CHARACTERISTIC Name=IDENTIFIER CriterionNames+=IDENTIFIER*;
var_characteristic_block : BEGIN var_characteristic_exp var_characteristic_sub_nodes END VAR_CHARACTERISTIC;
var_characteristic_sub_nodes : (
                                 var_address_block
                                )*;

var_criterion_exp : VAR_CRITERION Name=IDENTIFIER LongIdentifier=string_exp Values+=IDENTIFIER*;
var_criterion_block : BEGIN var_criterion_exp var_criterion_sub_nodes END VAR_CRITERION;
var_criterion_sub_nodes : (
                            var_measurement_exp
                          | var_selection_characteristic_exp
                          )*;

var_measurement_exp : VAR_MEASUREMENT Name=IDENTIFIER;
var_selection_characteristic_exp : VAR_SELECTION_CHARACTERISTIC Name=IDENTIFIER;

var_forbidden_comb_block : BEGIN VAR_FORBIDDEN_COMB tuples+=criterion_tuple* END VAR_FORBIDDEN_COMB;
criterion_tuple : CriterionName=IDENTIFIER CriterionValue=IDENTIFIER;

var_naming_exp : VAR_NAMING Tag=(NUMERIC | ALPHA);
var_separator_exp : VAR_SEPARATOR Separator=string_exp;

/*
I cannot find any definition for the following elements which are part of 1.7.1
*/

/*
TYPEDEF_CHARACTERISTIC
<guessed definition from demo file for 1.7.1>
*/
typedef_characteristic_exp : TYPEDEF_CHARACTERISTIC IDENTIFIER string_exp type_enum IDENTIFIER value IDENTIFIER value value;
typedef_characteristic_block : BEGIN typedef_characteristic_exp typedef_characteristic_sub_nodes END TYPEDEF_CHARACTERISTIC;
typedef_characteristic_sub_nodes : (
                                     extended_limits_exp
                                   | format_exp
                                   | phys_unit_exp
                                   | axis_descr_block
                                   | number_exp
                                   )*;

/*
INSTANCE
<guessed definition from demo file for 1.7.1>
*/
instance_exp : INSTANCE IDENTIFIER string_exp IDENTIFIER int_value;
instance_block : BEGIN instance_exp instance_sub_nodes END INSTANCE;
instance_sub_nodes : (
                       matrix_dim_exp
                     | display_identifier_exp
                     )*;

/*
TYPEDEF_AXIS
<guessed definition from demo file for 1.7.1>
*/
typedef_axis_exp : TYPEDEF_AXIS IDENTIFIER string_exp IDENTIFIER IDENTIFIER value IDENTIFIER int_value value value;
typedef_axis_block : BEGIN typedef_axis_exp END TYPEDEF_AXIS;
// there is no example within the demo file for optional parameters
typedef_axis_sub_nodes : ;

/*
TYPEDEF_STRUCTURE
<guessed definition from demo file for 1.7.1>
*/
typedef_structure_exp : TYPEDEF_STRUCTURE IDENTIFIER string_exp int_value;
typedef_structure_block : BEGIN typedef_structure_exp typedef_structure_sub_nodes END TYPEDEF_STRUCTURE;
typedef_structure_sub_nodes : (
                                structure_component_block
                              )*;

structure_component_exp : STRUCTURE_COMPONENT IDENTIFIER IDENTIFIER int_value;
structure_component_block : BEGIN structure_component_exp structure_component_sub_nodes END STRUCTURE_COMPONENT;
// there is no example within the demo file for optional parameters
structure_component_sub_nodes : (
                                  matrix_dim_exp
                                )*;

/*
TRANSFORMER
<guessed definition from demo file for 1.7.1>
*/
transformer_exp : TRANSFORMER IDENTIFIER string_exp string_exp string_exp int_value trigger_conditions_enum IDENTIFIER;
transformer_block : BEGIN transformer_exp transformer_sub_nodes END TRANSFORMER;
transformer_sub_nodes : (
                          transformer_in_objects_block
                        | transformer_out_objects_block
                        )*;

trigger_conditions_enum : ON_CHANGE | ON_USER_REQUEST;

transformer_in_objects_block : BEGIN TRANSFORMER_IN_OBJECTS Identifier+=IDENTIFIER* END TRANSFORMER_IN_OBJECTS;
transformer_out_objects_block : BEGIN TRANSFORMER_OUT_OBJECTS Identifier+=IDENTIFIER* END TRANSFORMER_OUT_OBJECTS;
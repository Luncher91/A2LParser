package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.ArrayList;

import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class Asap2ParserTest {
	private final String TEST_ESCAPE_STRING = "\\" + "\"" + "'" + "\n" + "\r" + "\t";
	public static final Object TEST_FILE_JSON_SCHEMA = "{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Asap2File\",\"properties\":{\"a2mlVersion\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:A2mlVersion\",\"properties\":{\"versionNo\":{\"type\":\"integer\"},\"upgradeNo\":{\"type\":\"integer\"}}},\"asap2Version\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Asap2Version\",\"properties\":{\"versionNo\":{\"type\":\"integer\"},\"upgradeNo\":{\"type\":\"integer\"}}},\"project\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Project\",\"properties\":{\"modules\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Module\",\"properties\":{\"a2ml\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:A2ml\",\"properties\":{\"content\":{\"type\":\"string\"}}}},\"axisPts\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:AxisPts\",\"properties\":{\"name\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"},\"address\":{\"type\":\"integer\"},\"inputQuantitiy\":{\"type\":\"string\"},\"deposit\":{\"type\":\"string\"},\"maxDiff\":{\"type\":\"number\"},\"conversion\":{\"type\":\"string\"},\"maxAxisPoints\":{\"type\":\"integer\"},\"lowerLimit\":{\"type\":\"number\"},\"upperLimit\":{\"type\":\"number\"},\"notes\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Annotation\",\"properties\":{\"label\":{\"type\":\"string\"},\"origin\":{\"type\":\"string\"},\"text\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}}}}},\"byteorder\":{\"type\":\"string\",\"enum\":[\"LITTLE_ENDIAN\",\"BIG_ENDIAN\",\"MSB_LAST\",\"MSB_FIRST\"]},\"access\":{\"type\":\"string\",\"enum\":[\"CALIBRATION\",\"NO_CALIBRATION\",\"NOT_IN_MCD_SYSTEM\",\"OFFLINE_CALIBRATION\"]},\"axisPointDeposit\":{\"type\":\"string\",\"enum\":[\"ABSOLUTE\",\"DIFFERENCE\"]},\"displayIdentifier\":{\"type\":\"string\"},\"ecuAddressExtension\":{\"type\":\"integer\"},\"extendedLimits\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:ExtendedLimits\",\"properties\":{\"lowerLimit\":{\"type\":\"number\"},\"upperLimit\":{\"type\":\"number\"}}},\"format\":{\"type\":\"string\"},\"functions\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"guardRails\":{\"type\":\"boolean\"},\"ifData\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:IfData\",\"properties\":{\"content\":{\"type\":\"string\"}}}},\"monotony\":{\"type\":\"string\",\"enum\":[\"MON_DECREASE\",\"MON_INCREASE\",\"STRICT_DECREASE\",\"STRICT_INCREASE\",\"MONOTONOUS\",\"STRICT_MON\",\"NOT_MON\"]},\"physUnit\":{\"type\":\"string\"},\"readOnly\":{\"type\":\"boolean\"},\"memorySegment\":{\"type\":\"string\"},\"stepSize\":{\"type\":\"number\"},\"symbolLink\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:SymbolLink\",\"properties\":{\"symbolName\":{\"type\":\"string\"},\"offset\":{\"type\":\"integer\"}}}}}},\"characteristics\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Characteristic\",\"properties\":{\"name\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"},\"type\":{\"type\":\"string\",\"enum\":[\"ASCII\",\"CURVE\",\"MAP\",\"CUBOID\",\"CUBE_4\",\"CUBE_5\",\"VAL_BLK\",\"VALUE\"]},\"address\":{\"type\":\"integer\"},\"deposit\":{\"type\":\"string\"},\"maxDiff\":{\"type\":\"number\"},\"conversion\":{\"type\":\"string\"},\"lowerLimit\":{\"type\":\"number\"},\"upperLimit\":{\"type\":\"number\"},\"notes\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/notes/items\"}},\"axisDescriptions\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:AxisDescr\",\"properties\":{\"attribute\":{\"type\":\"string\",\"enum\":[\"CURVE_AXIS\",\"COM_AXIS\",\"FIX_AXIS\",\"RES_AXIS\",\"STD_AXIS\"]},\"inputQuantity\":{\"type\":\"string\"},\"conversion\":{\"type\":\"string\"},\"maxAxisPoints\":{\"type\":\"integer\"},\"lowerLimit\":{\"type\":\"number\"},\"upperLimit\":{\"type\":\"number\"},\"annotations\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/notes/items\"}},\"axisPoints_ref\":{\"type\":\"string\"},\"byteorder\":{\"type\":\"string\",\"enum\":[\"LITTLE_ENDIAN\",\"BIG_ENDIAN\",\"MSB_LAST\",\"MSB_FIRST\"]},\"curveAxis_ref\":{\"type\":\"string\"},\"deposit\":{\"type\":\"string\",\"enum\":[\"ABSOLUTE\",\"DIFFERENCE\"]},\"extendedLimits\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/extendedLimits\"},\"fixAxisPar\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:FixAxisPar\",\"properties\":{\"offset\":{\"type\":\"integer\"},\"shift\":{\"type\":\"integer\"},\"numberapo\":{\"type\":\"integer\"}}},\"fixAxisParDist\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:FixAxisParDist\",\"properties\":{\"offset\":{\"type\":\"integer\"},\"shift\":{\"type\":\"integer\"},\"numberapo\":{\"type\":\"integer\"}}},\"fixAxisParList\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:FixAxisParList\",\"properties\":{\"axisPtsValues\":{\"type\":\"array\",\"items\":{\"type\":\"number\"}}}},\"format\":{\"type\":\"string\"},\"maxGrad\":{\"type\":\"number\"},\"monotony\":{\"type\":\"string\",\"enum\":[\"MON_DECREASE\",\"MON_INCREASE\",\"STRICT_DECREASE\",\"STRICT_INCREASE\",\"MONOTONOUS\",\"STRICT_MON\",\"NOT_MON\"]},\"physUnit\":{\"type\":\"string\"},\"readOnly\":{\"type\":\"boolean\"},\"stepSize\":{\"type\":\"number\"}}}},\"bitmask\":{\"type\":\"integer\"},\"byteorder\":{\"type\":\"string\",\"enum\":[\"LITTLE_ENDIAN\",\"BIG_ENDIAN\",\"MSB_LAST\",\"MSB_FIRST\"]},\"access\":{\"type\":\"string\",\"enum\":[\"CALIBRATION\",\"NO_CALIBRATION\",\"NOT_IN_MCD_SYSTEM\",\"OFFLINE_CALIBRATION\"]},\"comparisonQuantityMeasurment\":{\"type\":\"string\"},\"dependetCharacteristic\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:DependentCharacteristic\",\"properties\":{\"formula\":{\"type\":\"string\"},\"characterstics\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}}}},\"discrete\":{\"type\":\"boolean\"},\"displayIdentifier\":{\"type\":\"string\"},\"ecuAddressExtension\":{\"type\":\"integer\"},\"extendedLimits\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/extendedLimits\"},\"format\":{\"type\":\"string\"},\"functions\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"guardRails\":{\"type\":\"boolean\"},\"ifData\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/ifData/items\"}},\"mapList\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"matrixDim\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:MatrixDim\",\"properties\":{\"xDim\":{\"type\":\"integer\"},\"yDim\":{\"type\":\"integer\"},\"zDim\":{\"type\":\"integer\"}}},\"maxRefresh\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:MaxRefresh\",\"properties\":{\"scalingUnit\":{\"type\":\"integer\"},\"rate\":{\"type\":\"integer\"}}},\"number\":{\"type\":\"integer\"},\"physUnit\":{\"type\":\"string\"},\"readOnly\":{\"type\":\"boolean\"},\"memorySegment\":{\"type\":\"string\"},\"stepSize\":{\"type\":\"number\"},\"symbolLink\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/symbolLink\"},\"virtualCharacteristic\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:VirtualCharacteristic\",\"properties\":{\"formula\":{\"type\":\"string\"},\"characterstics\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}}}}}}},\"compuMethods\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:CompuMethod\",\"properties\":{\"name\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"},\"conversionType\":{\"type\":\"string\",\"enum\":[\"IDENTICAL\",\"FORM\",\"LINEAR\",\"RAT_FUNC\",\"TAB_INTP\",\"TAB_NOINTP\",\"TAB_VERB\"]},\"format\":{\"type\":\"string\"},\"unit\":{\"type\":\"string\"},\"coeffs\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Coeffs\",\"properties\":{\"a\":{\"type\":\"number\"},\"b\":{\"type\":\"number\"},\"c\":{\"type\":\"number\"},\"d\":{\"type\":\"number\"},\"e\":{\"type\":\"number\"},\"f\":{\"type\":\"number\"}}},\"coeffsLinear\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:CoeffsLinear\",\"properties\":{\"a\":{\"type\":\"number\"},\"b\":{\"type\":\"number\"}}},\"compuTab_ref\":{\"type\":\"string\"},\"formula\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Formula\",\"properties\":{\"fx\":{\"type\":\"string\"},\"gx\":{\"type\":\"string\"}}},\"unit_ref\":{\"type\":\"string\"},\"conversionTable_ref\":{\"type\":\"string\"}}}},\"compuTabs\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:CompuTab\",\"properties\":{\"name\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"},\"conversionType\":{\"type\":\"string\",\"enum\":[\"IDENTICAL\",\"FORM\",\"LINEAR\",\"RAT_FUNC\",\"TAB_INTP\",\"TAB_NOINTP\",\"TAB_VERB\"]},\"numberOfValuePairs\":{\"type\":\"integer\"},\"valuePairs\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:ValuePair<java:lang:Double,java:lang:Double>\",\"properties\":{\"inVal\":{\"type\":\"number\"},\"outVal\":{\"type\":\"number\"}}}},\"defaultValue\":{\"type\":\"string\"},\"defaultValueNumeric\":{\"type\":\"number\"}}}},\"compuVTabs\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:CompuVTab\",\"properties\":{\"name\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"},\"conversionType\":{\"type\":\"string\",\"enum\":[\"IDENTICAL\",\"FORM\",\"LINEAR\",\"RAT_FUNC\",\"TAB_INTP\",\"TAB_NOINTP\",\"TAB_VERB\"]},\"numberOfValuePairs\":{\"type\":\"integer\"},\"valuePairs\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:ValuePair<java:lang:Double,java:lang:String>\",\"properties\":{\"inVal\":{\"type\":\"number\"},\"outVal\":{\"type\":\"string\"}}}},\"defaultValue\":{\"type\":\"string\"}}}},\"compuVTabRanges\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:CompuVTabRange\",\"properties\":{\"name\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"},\"numberOfValueTriples\":{\"type\":\"integer\"},\"valueTriples\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:ValueTriple<java:lang:Double,java:lang:String>\",\"properties\":{\"inValMin\":{\"type\":\"number\"},\"inValMax\":{\"type\":\"number\"},\"outVal\":{\"type\":\"string\"}}}},\"defaultValue\":{\"type\":\"string\"}}}},\"frame\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Frame\",\"properties\":{\"name\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"},\"scalingUnit\":{\"type\":\"integer\"},\"rate\":{\"type\":\"integer\"},\"frameMeasurements\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"ifDatas\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/ifData/items\"}}}},\"functions\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Function\",\"properties\":{\"name\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"},\"annotations\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/notes/items\"}},\"defCharacteristics\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"functionVersion\":{\"type\":\"string\"},\"ifDatas\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/ifData/items\"}},\"inMeasurments\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"locMeasurments\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"outMeasurments\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"refCharacteristics\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"subFunctions\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}}}}},\"groups\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Group\",\"properties\":{\"groupName\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"},\"annotations\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/notes/items\"}},\"functionList\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"ifDatas\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/ifData/items\"}},\"refCharacteristics\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"refMeasurements\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"root\":{\"type\":\"boolean\"},\"subGroups\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}}}}},\"ifDatas\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/ifData/items\"}},\"measurements\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Measurement\",\"properties\":{\"name\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"},\"datatype\":{\"type\":\"string\",\"enum\":[\"UBYTE\",\"SBYTE\",\"UWORD\",\"SWORD\",\"ULONG\",\"SLONG\",\"A_UINT64\",\"A_INT64\",\"FLOAT32_IEEE\",\"FLOAT64_IEEE\"]},\"conversion\":{\"type\":\"string\"},\"resolution\":{\"type\":\"integer\"},\"accuracy\":{\"type\":\"number\"},\"lowerLimit\":{\"type\":\"number\"},\"upperLimit\":{\"type\":\"number\"},\"annotations\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/notes/items\"}},\"arraySize\":{\"type\":\"integer\"},\"bitMask\":{\"type\":\"integer\"},\"bitOperation\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:BitOperation\",\"properties\":{\"leftShift\":{\"type\":\"integer\"},\"rightShift\":{\"type\":\"integer\"},\"signExtend\":{\"type\":\"boolean\"}}},\"byteorder\":{\"type\":\"string\",\"enum\":[\"LITTLE_ENDIAN\",\"BIG_ENDIAN\",\"MSB_LAST\",\"MSB_FIRST\"]},\"discrete\":{\"type\":\"boolean\"},\"displayIdentifier\":{\"type\":\"string\"},\"ecuAddress\":{\"type\":\"integer\"},\"ecuAddressExtension\":{\"type\":\"integer\"},\"errorMask\":{\"type\":\"integer\"},\"format\":{\"type\":\"string\"},\"functionList\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"ifDatas\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/ifData/items\"}},\"layout\":{\"type\":\"string\",\"enum\":[\"ROW_DIR\",\"COLUMN_DIR\"]},\"matrixDim\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/characteristics/items/properties/matrixDim\"},\"maxRefresh\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/characteristics/items/properties/maxRefresh\"},\"physUnit\":{\"type\":\"string\"},\"readWrite\":{\"type\":\"boolean\"},\"memorySegment\":{\"type\":\"string\"},\"symbolLink\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/symbolLink\"},\"virtual\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}}}}},\"modCommon\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:ModCommon\",\"properties\":{\"comment\":{\"type\":\"string\"},\"alignmentByte\":{\"type\":\"integer\"},\"alignmentFloat32IEEE\":{\"type\":\"integer\"},\"alignmentFloat64IEEE\":{\"type\":\"integer\"},\"alignmentInt64\":{\"type\":\"integer\"},\"alignmentLong\":{\"type\":\"integer\"},\"alignmentWord\":{\"type\":\"integer\"},\"byteorder\":{\"type\":\"string\",\"enum\":[\"LITTLE_ENDIAN\",\"BIG_ENDIAN\",\"MSB_LAST\",\"MSB_FIRST\"]},\"dataSize\":{\"type\":\"integer\"},\"deposit\":{\"type\":\"string\",\"enum\":[\"ABSOLUTE\",\"DIFFERENCE\"]},\"standardRecordLayout\":{\"type\":\"string\"}}},\"modPar\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:ModPar\",\"properties\":{\"comment\":{\"type\":\"string\"},\"addresses\":{\"type\":\"array\",\"items\":{\"type\":\"integer\"}},\"calibrationMethods\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:CalibrationMethod\",\"properties\":{\"name\":{\"type\":\"string\"},\"version\":{\"type\":\"integer\"},\"calibrationHandle\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:CalibrationHandle\",\"properties\":{\"handles\":{\"type\":\"array\",\"items\":{\"type\":\"integer\"}},\"calibrationText\":{\"type\":\"string\"}}}}}}},\"cpuType\":{\"type\":\"string\"},\"customer\":{\"type\":\"string\"},\"customerNo\":{\"type\":\"string\"},\"ecu\":{\"type\":\"string\"},\"ecuCalibrationOffset\":{\"type\":\"integer\"},\"epk\":{\"type\":\"string\"},\"memoryLayouts\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:MemoryLayout\",\"properties\":{\"prgType\":{\"type\":\"string\",\"enum\":[\"PRG_CODE\",\"PRG_DATA\",\"PRG_RESERVED\"]},\"address\":{\"type\":\"integer\"},\"size\":{\"type\":\"integer\"},\"offset\":{\"type\":\"array\",\"items\":{\"type\":\"number\"}},\"ifDatas\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/ifData/items\"}}}}},\"memorySegments\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:MemorySegment\",\"properties\":{\"name\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"},\"prgType\":{\"type\":\"string\",\"enum\":[\"CALIBRATION_VARIABLES\",\"CODE\",\"DATA\",\"EXCLUDE_FROM_FLASH\",\"OFFLINE_DATA\",\"RESERVED\",\"SERAM\",\"VARIABLES\"]},\"memoryType\":{\"type\":\"string\",\"enum\":[\"EEPROM\",\"EPROM\",\"FLASH\",\"RAM\",\"ROM\",\"REGISTER\",\"NOT_IN_ECU\"]},\"attribute\":{\"type\":\"string\",\"enum\":[\"INTERN\",\"EXTERN\"]},\"address\":{\"type\":\"integer\"},\"size\":{\"type\":\"integer\"},\"offset\":{\"type\":\"array\",\"items\":{\"type\":\"number\"}},\"ifDatas\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/ifData/items\"}}}}},\"numberOfInterfaces\":{\"type\":\"integer\"},\"phoneNumber\":{\"type\":\"string\"},\"supplier\":{\"type\":\"string\"},\"systemConstants\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:SystemConstant\",\"properties\":{\"name\":{\"type\":\"string\"},\"value\":{\"type\":\"string\"}}}},\"user\":{\"type\":\"string\"},\"version\":{\"type\":\"string\"}}},\"recordLayouts\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:RecordLayout\",\"properties\":{\"name\":{\"type\":\"string\"},\"alignmentByte\":{\"type\":\"integer\"},\"alignmentFloat32IEEE\":{\"type\":\"integer\"},\"alignmentFloat64IEEE\":{\"type\":\"integer\"},\"alignmentInt64\":{\"type\":\"integer\"},\"alignmentLong\":{\"type\":\"integer\"},\"alignmentWord\":{\"type\":\"integer\"},\"axisPtsX\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:AxisPtsXYZ45\",\"properties\":{\"position\":{\"type\":\"integer\"},\"datatype\":{\"type\":\"string\",\"enum\":[\"UBYTE\",\"SBYTE\",\"UWORD\",\"SWORD\",\"ULONG\",\"SLONG\",\"A_UINT64\",\"A_INT64\",\"FLOAT32_IEEE\",\"FLOAT64_IEEE\"]},\"indexorder\":{\"type\":\"string\",\"enum\":[\"INDEX_INCR\",\"INDEX_DECR\"]},\"addressing\":{\"type\":\"string\",\"enum\":[\"PBYTE\",\"PWORD\",\"PLONG\",\"DIRECT\"]}}},\"axisPtsY\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/axisPtsX\"},\"axisPtsZ\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/axisPtsX\"},\"axisPts4\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/axisPtsX\"},\"axisPts5\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/axisPtsX\"},\"axisRescaleX\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:AxisRescaleXYZ45\",\"properties\":{\"position\":{\"type\":\"integer\"},\"datatype\":{\"type\":\"string\",\"enum\":[\"UBYTE\",\"SBYTE\",\"UWORD\",\"SWORD\",\"ULONG\",\"SLONG\",\"A_UINT64\",\"A_INT64\",\"FLOAT32_IEEE\",\"FLOAT64_IEEE\"]},\"maxNumberOfRescalePairs\":{\"type\":\"integer\"},\"indexorder\":{\"type\":\"string\",\"enum\":[\"INDEX_INCR\",\"INDEX_DECR\"]},\"addressing\":{\"type\":\"string\",\"enum\":[\"PBYTE\",\"PWORD\",\"PLONG\",\"DIRECT\"]}}},\"axisRescaleY\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/axisRescaleX\"},\"axisRescaleZ\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/axisRescaleX\"},\"axisRescale4\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/axisRescaleX\"},\"axisRescale5\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/axisRescaleX\"},\"distOpX\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:DistOpXYZ45\",\"properties\":{\"position\":{\"type\":\"integer\"},\"dataType\":{\"type\":\"string\",\"enum\":[\"UBYTE\",\"SBYTE\",\"UWORD\",\"SWORD\",\"ULONG\",\"SLONG\",\"A_UINT64\",\"A_INT64\",\"FLOAT32_IEEE\",\"FLOAT64_IEEE\"]}}},\"distOpY\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/distOpX\"},\"distOpZ\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/distOpX\"},\"distOp4\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/distOpX\"},\"distOp5\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/distOpX\"},\"fixNoAxisPtsX\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:FixNoAxisPtsXYZ45\",\"properties\":{\"numberOfAxisPoints\":{\"type\":\"integer\"}}},\"fixNoAxisPtsY\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/fixNoAxisPtsX\"},\"fixNoAxisPtsZ\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/fixNoAxisPtsX\"},\"fixNoAxisPts4\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/fixNoAxisPtsX\"},\"fixNoAxisPts5\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/fixNoAxisPtsX\"},\"functionValues\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:FncValues\",\"properties\":{\"position\":{\"type\":\"integer\"},\"dataType\":{\"type\":\"string\",\"enum\":[\"UBYTE\",\"SBYTE\",\"UWORD\",\"SWORD\",\"ULONG\",\"SLONG\",\"A_UINT64\",\"A_INT64\",\"FLOAT32_IEEE\",\"FLOAT64_IEEE\"]},\"indexMode\":{\"type\":\"string\",\"enum\":[\"ALTERNATE_CURVES\",\"ALTERNATE_WITH_X\",\"ALTERNATE_WITH_Y\",\"COLUMN_DIR\",\"ROW_DIR\"]},\"addressType\":{\"type\":\"string\",\"enum\":[\"PBYTE\",\"PWORD\",\"PLONG\",\"DIRECT\"]}}},\"identification\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Identification\",\"properties\":{\"position\":{\"type\":\"integer\"},\"dataType\":{\"type\":\"string\",\"enum\":[\"UBYTE\",\"SBYTE\",\"UWORD\",\"SWORD\",\"ULONG\",\"SLONG\",\"A_UINT64\",\"A_INT64\",\"FLOAT32_IEEE\",\"FLOAT64_IEEE\"]}}},\"noAxisPtsX\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:NoAxisPtsXYZ45\",\"properties\":{\"position\":{\"type\":\"integer\"},\"dataType\":{\"type\":\"string\",\"enum\":[\"UBYTE\",\"SBYTE\",\"UWORD\",\"SWORD\",\"ULONG\",\"SLONG\",\"A_UINT64\",\"A_INT64\",\"FLOAT32_IEEE\",\"FLOAT64_IEEE\"]}}},\"noAxisPtsY\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/noAxisPtsX\"},\"noAxisPtsZ\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/noAxisPtsX\"},\"noAxisPts4\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/noAxisPtsX\"},\"noAxisPts5\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/noAxisPtsX\"},\"noRescaleX\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:NoRescaleXYZ45\",\"properties\":{\"position\":{\"type\":\"integer\"},\"dataType\":{\"type\":\"string\",\"enum\":[\"UBYTE\",\"SBYTE\",\"UWORD\",\"SWORD\",\"ULONG\",\"SLONG\",\"A_UINT64\",\"A_INT64\",\"FLOAT32_IEEE\",\"FLOAT64_IEEE\"]}}},\"noRescaleY\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/noRescaleX\"},\"noRescaleZ\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/noRescaleX\"},\"noRescale4\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/noRescaleX\"},\"noRescale5\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/noRescaleX\"},\"offsetX\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:OffsetXYZ45\",\"properties\":{\"position\":{\"type\":\"integer\"},\"dataType\":{\"type\":\"string\",\"enum\":[\"UBYTE\",\"SBYTE\",\"UWORD\",\"SWORD\",\"ULONG\",\"SLONG\",\"A_UINT64\",\"A_INT64\",\"FLOAT32_IEEE\",\"FLOAT64_IEEE\"]}}},\"offsetY\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/offsetX\"},\"offsetZ\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/offsetX\"},\"offset4\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/offsetX\"},\"offset5\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/offsetX\"},\"reserved\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Reserved\",\"properties\":{\"position\":{\"type\":\"integer\"},\"dataSize\":{\"type\":\"string\",\"enum\":[\"BYTE\",\"WORD\",\"LONG\"]}}}},\"ripAddressW\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:RipAddrWXYZ45\",\"properties\":{\"position\":{\"type\":\"integer\"},\"dataType\":{\"type\":\"string\",\"enum\":[\"UBYTE\",\"SBYTE\",\"UWORD\",\"SWORD\",\"ULONG\",\"SLONG\",\"A_UINT64\",\"A_INT64\",\"FLOAT32_IEEE\",\"FLOAT64_IEEE\"]}}},\"ripAddressX\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/ripAddressW\"},\"ripAddressY\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/ripAddressW\"},\"ripAddressZ\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/ripAddressW\"},\"ripAddress4\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/ripAddressW\"},\"ripAddress5\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/ripAddressW\"},\"srcAddressX\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:SrcAddrXYZ45\",\"properties\":{\"position\":{\"type\":\"integer\"},\"dataType\":{\"type\":\"string\",\"enum\":[\"UBYTE\",\"SBYTE\",\"UWORD\",\"SWORD\",\"ULONG\",\"SLONG\",\"A_UINT64\",\"A_INT64\",\"FLOAT32_IEEE\",\"FLOAT64_IEEE\"]}}},\"srcAddressY\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/srcAddressX\"},\"srcAddressZ\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/srcAddressX\"},\"srcAddress4\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/srcAddressX\"},\"srcAddress5\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/srcAddressX\"},\"shiftOpX\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:ShiftOpXYZ45\",\"properties\":{\"position\":{\"type\":\"integer\"},\"dataType\":{\"type\":\"string\",\"enum\":[\"UBYTE\",\"SBYTE\",\"UWORD\",\"SWORD\",\"ULONG\",\"SLONG\",\"A_UINT64\",\"A_INT64\",\"FLOAT32_IEEE\",\"FLOAT64_IEEE\"]}}},\"shiftOpY\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/shiftOpX\"},\"shiftOpZ\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/shiftOpX\"},\"shiftOp4\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/shiftOpX\"},\"shiftOp5\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items/properties/shiftOpX\"},\"staticRecordLayout\":{\"type\":\"boolean\"}}}},\"units\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Unit\",\"properties\":{\"name\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"},\"display\":{\"type\":\"string\"},\"type\":{\"type\":\"string\",\"enum\":[\"DERIVED\",\"EXTENDED_SI\"]},\"unit_ref\":{\"type\":\"string\"},\"siExponents\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:SiExponents\",\"properties\":{\"length\":{\"type\":\"integer\"},\"mass\":{\"type\":\"integer\"},\"time\":{\"type\":\"integer\"},\"electricCurrent\":{\"type\":\"integer\"},\"temperature\":{\"type\":\"integer\"},\"amountOfSubstance\":{\"type\":\"integer\"},\"luminousIntensity\":{\"type\":\"integer\"}}},\"unitConversion\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:UnitConversion\",\"properties\":{\"gradient\":{\"type\":\"number\"},\"offset\":{\"type\":\"number\"}}}}}},\"userRights\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:UserRights\",\"properties\":{\"userLevelId\":{\"type\":\"string\"},\"readOnly\":{\"type\":\"boolean\"},\"groups\":{\"type\":\"array\",\"items\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}}}}}},\"variantCoding\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:VariantCoding\",\"properties\":{\"varCharacteristics\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:VarCharacteristic\",\"properties\":{\"name\":{\"type\":\"string\"},\"criterions\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"addresses\":{\"type\":\"array\",\"items\":{\"type\":\"integer\"}}}}},\"varCriterion\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:VarCriterion\",\"properties\":{\"name\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"},\"values\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"measurement\":{\"type\":\"string\"},\"selectionCharacteristic\":{\"type\":\"string\"}}}},\"varForbiddenComb\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:VarForbiddenComb\",\"properties\":{\"tuples\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:CriterionTuple\",\"properties\":{\"name\":{\"type\":\"string\"},\"value\":{\"type\":\"string\"}}}}}}},\"varNaming\":{\"type\":\"string\",\"enum\":[\"NUMERIC\"]},\"varSeparator\":{\"type\":\"string\"}}},\"blobs\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Blob\",\"properties\":{\"content\":{\"type\":\"string\"}}}},\"included\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:ModuleSubBlocks\",\"properties\":{\"a2ml\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/a2ml/items\"}},\"axisPts\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items\"}},\"characteristics\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/characteristics/items\"}},\"compuMethods\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/compuMethods/items\"}},\"compuTabs\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/compuTabs/items\"}},\"compuVTabs\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/compuVTabs/items\"}},\"compuVTabRanges\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/compuVTabRanges/items\"}},\"frame\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/frame\"},\"functions\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/functions/items\"}},\"groups\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/groups/items\"}},\"ifDatas\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/axisPts/items/properties/ifData/items\"}},\"measurements\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/measurements/items\"}},\"modCommon\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/modCommon\"},\"modPar\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/modPar\"},\"recordLayouts\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/recordLayouts/items\"}},\"units\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/units/items\"}},\"userRights\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/userRights/items\"}},\"variantCoding\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/variantCoding\"},\"blobs\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/blobs/items\"}},\"included\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items/properties/included/items\"}}}}},\"name\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"}}}},\"included\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:ProjectSubBlocks\",\"properties\":{\"modules\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/modules/items\"}},\"included\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"$ref\":\"#/properties/project/properties/included/items\"}}}}},\"name\":{\"type\":\"string\"},\"longIdentifier\":{\"type\":\"string\"},\"header\":{\"type\":\"object\",\"id\":\"urn:jsonschema:net:alenzen:a2l:Header\",\"properties\":{\"comment\":{\"type\":\"string\"},\"projectNo\":{\"type\":\"string\"},\"version\":{\"type\":\"string\"}}}}}}}";

	@Test
	void testToA2LString() {
		String result = A2LWriter.toA2LString(TEST_ESCAPE_STRING);
		assertEquals("\"\\\\\\\"\\'\\n\\r\\t\"", result);
	}

	@Test
	void testToA2LString_backAndForth() throws InvalidParameterException {
		String resultA = A2LWriter.toA2LString(TEST_ESCAPE_STRING);
		String resultB = A2LVisitor.toJavaString(resultA);
		assertEquals(TEST_ESCAPE_STRING, resultB);
	}

	@Test
	void testUTF8BOMOutput() throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		testBOMOutput(StandardCharsets.UTF_8);
	}

	@Test
	void testUTF16LEBOMOutput() throws JsonGenerationException, JsonMappingException, URISyntaxException, IOException {
		testBOMOutput(StandardCharsets.UTF_16LE);
	}

	@Test
	void testUTF16BEBOMOutput() throws JsonGenerationException, JsonMappingException, URISyntaxException, IOException {
		testBOMOutput(StandardCharsets.UTF_16BE);
	}

	@Test
	void testUTF32LEBOMOutput() throws JsonGenerationException, JsonMappingException, URISyntaxException, IOException {
		testBOMOutput(Charset.forName("UTF-32LE"));
	}

	@Test
	void testUTF32BEBOMOutput() throws JsonGenerationException, JsonMappingException, URISyntaxException, IOException {
		testBOMOutput(Charset.forName("UTF-32BE"));
	}

	@Test
	void testSystemOutResult() throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
		String fileOutput = generateJsonFromA2l(TestFile.A, false, false);
		assertEquals(Asap2FileTest.TEST_FILE_A_JSON, fileOutput);
	}

	private String generateJsonFromA2l(TestFile file, boolean minimized, boolean indentation)
			throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
		String fileOutput;
		URL testFileResource = ClassLoader.getSystemResource(file.getFilename());
		String a2lPath = Paths.get(testFileResource.toURI()).toFile().getAbsolutePath();

		ArrayList<String> arguments = new ArrayList<String>();
		arguments.add("-a2l");
		arguments.add(a2lPath);

		if (minimized) {
			arguments.add("-mj");
		}

		if (indentation) {
			arguments.add("-ij");
		}

		PrintStream stdOut = System.out;
		try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
			System.setOut(new PrintStream(outStream));

			Asap2Parser.main(arguments.toArray(new String[0]));

			fileOutput = outStream.toString();

		} finally {
			System.setOut(stdOut);
		}
		return fileOutput;
	}

	@Test
	void testSystemInA2lFile() throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
		try (InputStream a2lFileContent = ClassLoader.getSystemResourceAsStream(TestFile.A.getFilename())) {
			String[] arguments = new String[] { "-a2l" };

			PrintStream stdOut = System.out;
			InputStream stdIn = System.in;
			try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
				System.setOut(new PrintStream(outStream));
				System.setIn(a2lFileContent);

				Asap2Parser.main(arguments);

				String fileOutput = outStream.toString();

				assertEquals(Asap2FileTest.TEST_FILE_A_JSON, fileOutput);
			} finally {
				System.setOut(stdOut);
				System.setIn(stdIn);
			}
		}
	}

	@Test
	void testSystemInJsonFile() throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException {
		try (InputStream a2lFileContent = new ByteArrayInputStream(
				Asap2FileTest.TEST_FILE_A_JSON.getBytes(StandardCharsets.UTF_8))) {
			String[] arguments = new String[] { "-j" };

			PrintStream stdOut = System.out;
			InputStream stdIn = System.in;
			try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
				PrintStream printStream = new PrintStream(outStream, false, StandardCharsets.UTF_8.name());
				System.setOut(printStream);
				System.setIn(a2lFileContent);

				Asap2Parser.main(arguments);

				String fileOutput = new String(outStream.toByteArray(), StandardCharsets.UTF_8);
				Asap2Parser parser = new Asap2Parser(
						new ByteArrayInputStream(fileOutput.getBytes(StandardCharsets.ISO_8859_1)));
				parser.setEventHandler((a, b, c) -> {
					System.setOut(stdOut);
					System.setIn(stdIn);
					fail(a + ":" + b + ":" + c + "\nComplete content: " + fileOutput);
				});

				try {
					Asap2File fromOutput = parser.parse();

					assertEquals(Asap2FileTest.TEST_FILE_A_JSON, fromOutput.toJson());
				} catch (IOException e) {
					System.setOut(stdOut);
					System.setIn(stdIn);
					fail("Cannot parse output: " + fileOutput, e);
				}
			} finally {
				System.setOut(stdOut);
				System.setIn(stdIn);
			}
		}
	}

	@Test
	void testMinimizedJson() throws JsonGenerationException, JsonMappingException, URISyntaxException, IOException {
		String minimizedJson = generateJsonFromA2l(TestFile.A, true, false);

		Asap2File a2l = Asap2File.fromJson(minimizedJson);
		String normalJson = a2l.toJson();

		assertEquals(Asap2FileTest.TEST_FILE_A_JSON, normalJson);
	}

	@Test
	void testMinimizedAndIndentedJson()
			throws JsonGenerationException, JsonMappingException, URISyntaxException, IOException {
		String minimizedJson = generateJsonFromA2l(TestFile.A, true, true);

		Asap2File a2l = Asap2File.fromJson(minimizedJson);
		String normalJson = a2l.toJson();

		assertEquals(Asap2FileTest.TEST_FILE_A_JSON, normalJson);
	}

	@Test
	void testIndentedJson() throws JsonGenerationException, JsonMappingException, URISyntaxException, IOException {
		String minimizedJson = generateJsonFromA2l(TestFile.A, false, true);

		Asap2File a2l = Asap2File.fromJson(minimizedJson);
		String normalJson = a2l.toJson();

		assertEquals(Asap2FileTest.TEST_FILE_A_JSON, normalJson);
	}

	private void testBOMOutput(Charset charsetToTest)
			throws URISyntaxException, IOException, JsonGenerationException, JsonMappingException {
		URL testFileResource = ClassLoader.getSystemResource(TestFile.A.getFilename());
		String a2lPath = Paths.get(testFileResource.toURI()).toFile().getAbsolutePath();
		String encoding = charsetToTest.displayName();
		File tempOutputFile = File.createTempFile(a2lPath, ".json");

		try {
			String tempFile = tempOutputFile.getAbsolutePath();
			String[] arguments = new String[] { "-a2l", a2lPath, "-o", tempFile, "-c", encoding };

			Asap2Parser.main(arguments);

			assertBOM(tempFile, charsetToTest);
		} finally {
			tempOutputFile.delete();
		}
	}

	private void assertBOM(String filePath, Charset charsetExpected) throws IOException {
		try (BOMInputStream bomIn = new BOMInputStream(new FileInputStream(filePath), ByteOrderMark.UTF_8,
				ByteOrderMark.UTF_16LE, ByteOrderMark.UTF_16BE, ByteOrderMark.UTF_32LE, ByteOrderMark.UTF_32BE)) {

			if (!bomIn.hasBOM()) {
				fail("File does not contain any known BOM!");
			}

			if (!bomIn.hasBOM(Asap2Parser.determineBOM(charsetExpected))) {
				fail("Expected BOM is " + charsetExpected.displayName() + " but instead is "
						+ bomIn.getBOMCharsetName());
			}
		}
	}
	
	@Test
	void testJsonSchema() throws IOException {
		String[] arguments = new String[] { "-jsc" };
		PrintStream stdOut = System.out;
		try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
			PrintStream printStream = new PrintStream(outStream, false, StandardCharsets.UTF_8.name());
			System.setOut(printStream);

			Asap2Parser.main(arguments);

			String fileOutput = new String(outStream.toByteArray(), StandardCharsets.UTF_8);
			assertEquals(Asap2ParserTest.TEST_FILE_JSON_SCHEMA, fileOutput);
		} finally {
			System.setOut(stdOut);
		}
	}
}

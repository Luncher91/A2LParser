package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Asap2FileTest {
	public enum TestFile {
		A("freeTest.a2l");

		private String filename;

		TestFile(String fName) {
			setFilename(fName);
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}
	}

	public static Asap2File getTestFile(TestFile tf) throws IOException {
		Asap2Parser parser = new Asap2Parser(ClassLoader.getSystemResourceAsStream(tf.getFilename()));
		parser.setEventHandler((line, position, message) -> { fail("Line " + line + "@" + position + ": " + message); });
		return parser.parse();
	}

	@Test
	void testAsap2Version() throws IOException {
		Asap2File f = getTestFile(TestFile.A);

		assertEquals(1L, f.getAsap2Version().getVersionNo());
		assertEquals(71L, f.getAsap2Version().getUpgradeNo());
	}
	
	@Test
	void testA2mlVersion() throws IOException {
		Asap2File f = getTestFile(TestFile.A);

		assertEquals(1L, f.getA2mlVersion().getVersionNo());
		assertEquals(31L, f.getA2mlVersion().getUpgradeNo());
	}
	
	public static final String TEST_FILE_A_JSON = "{\"a2mlVersion\":{\"versionNo\":1,\"upgradeNo\":31},\"asap2Version\":{\"versionNo\":1,\"upgradeNo\":71},\"project\":{\"name\":\"Free_Example\",\"longIdentifier\":\"MIT licensed example file\",\"header\":{\"comment\":\"Free Asap2 example file\",\"projectNo\":\"Free42\",\"version\":\"V1.7.1\"},\"modules\":[{\"name\":\"sample_module\",\"longIdentifier\":\"great example with different escaped quotation \\\" marks like double quotes \\\" and backslash escaped at the end \\\"\",\"a2ml\":[{\"content\":\"/begin A2ML i am not parsed /end A2ML\"}],\"axisPts\":[{\"name\":\"axis_pts_1\",\"longIdentifier\":\"I am a long identifier of an AXIS_PTS object\",\"address\":11259375,\"inputQuantitiy\":\"in_quant\",\"deposit\":\"axis_pts_deposit\",\"maxDiff\":1.1,\"conversion\":\"axis_pts_conv\",\"maxAxisPoints\":123,\"lowerLimit\":1.2,\"upperLimit\":1.3,\"notes\":[],\"byteorder\":\"MSB_FIRST\",\"access\":\"NOT_IN_MCD_SYSTEM\",\"axisPointDeposit\":\"DIFFERENCE\",\"displayIdentifier\":\"display.identifier.wohoo\",\"ecuAddressExtension\":38787,\"extendedLimits\":{\"lowerLimit\":0.1,\"upperLimit\":0.5},\"format\":\"%1212.21\",\"functions\":null,\"guardRails\":false,\"ifData\":[],\"monotony\":\"NOT_MON\",\"physUnit\":\"m/(s*s)\",\"readOnly\":false,\"memorySegment\":\"memorySegment.42\",\"stepSize\":820.028,\"symbolLink\":null}],\"characteristics\":[{\"name\":\"char_a\",\"longIdentifier\":\"description here\",\"type\":\"CUBE_5\",\"address\":291,\"deposit\":\"char_deposit\",\"maxDiff\":1.1,\"conversion\":\"char_conv\",\"lowerLimit\":0.0,\"upperLimit\":10.0,\"notes\":[{\"label\":null,\"origin\":null,\"text\":[\"hello world\"]}],\"axisDescriptions\":[{\"attribute\":\"CURVE_AXIS\",\"inputQuantity\":\"in_quant_char\",\"conversion\":\"char_axis_descr_conv\",\"maxAxisPoints\":22,\"lowerLimit\":5.5,\"upperLimit\":21.21,\"annotations\":[],\"axisPoints_ref\":\"axisPtsA\",\"byteorder\":\"MSB_FIRST\",\"curveAxis_ref\":\"curveAxisA\",\"deposit\":\"DIFFERENCE\",\"extendedLimits\":null,\"fixAxisPar\":{\"offset\":2,\"shift\":4,\"numberapo\":8},\"fixAxisParDist\":{\"offset\":4,\"shift\":16,\"numberapo\":32},\"fixAxisParList\":{\"axisPtsValues\":[1.1,2.2,3.3,4.4,5.5]},\"format\":\"%3.3\",\"maxGrad\":30.0,\"monotony\":\"NOT_MON\",\"physUnit\":\"m/(s*s)\",\"readOnly\":true,\"stepSize\":1.2}],\"bitmask\":16777215,\"byteorder\":\"MSB_LAST\",\"access\":\"CALIBRATION\",\"comparisonQuantityMeasurment\":\"comparisonQuantity\",\"dependetCharacteristic\":{\"formula\":\"X1*X2\",\"characterstics\":[\"characA\",\"characB\"]},\"discrete\":true,\"displayIdentifier\":\"display_ident_char\",\"ecuAddressExtension\":10000,\"extendedLimits\":{\"lowerLimit\":1.1,\"upperLimit\":5.5},\"format\":\"%12.12\",\"functions\":[\"f1\",\"f2\",\"f3\"],\"guardRails\":false,\"ifData\":[{\"content\":\"/begin IF_DATA i am not parsed yet /end IF_DATA\"}],\"mapList\":[\"identA\",\"identB\"],\"matrixDim\":{\"xDim\":3,\"yDim\":4,\"zDim\":5},\"maxRefresh\":{\"scalingUnit\":6,\"rate\":10},\"number\":1233123,\"physUnit\":\"kg\",\"readOnly\":true,\"memorySegment\":\"memory_seg_ref\",\"stepSize\":99.9,\"symbolLink\":{\"symbolName\":\"symbol name char\",\"offset\":7},\"virtualCharacteristic\":{\"formula\":\"sin(cos(X2-X1))\",\"characterstics\":[\"characteristicA\",\"characteristicB\"]}}],\"compuMethods\":[{\"name\":\"THE.IDENTITY\",\"longIdentifier\":\"very unique\",\"conversionType\":\"RAT_FUNC\",\"format\":\"%8.4\",\"unit\":\"°\",\"coeffs\":{\"a\":0.0,\"b\":1.1,\"c\":0.0,\"d\":0.0,\"e\":0.0,\"f\":1.1},\"coeffsLinear\":{\"a\":7.1,\"b\":-12.5},\"compuTab_ref\":null,\"formula\":null,\"unit_ref\":null,\"conversionTable_ref\":null},{\"name\":\"THE.FORMULA\",\"longIdentifier\":\"very unique\",\"conversionType\":\"FORM\",\"format\":\"%8.4\",\"unit\":\"°\",\"coeffs\":null,\"coeffsLinear\":null,\"compuTab_ref\":null,\"formula\":{\"fx\":\"X1-42\",\"gx\":\"X1+42\"},\"unit_ref\":null,\"conversionTable_ref\":null}],\"compuTabs\":[{\"name\":\"PERFECT_COMPU.TAB\",\"longIdentifier\":\"very perfect compu tab\",\"conversionType\":\"TAB_INTP\",\"numberOfValuePairs\":5,\"valuePairs\":[{\"inVal\":-1.1,\"outVal\":-1.1},{\"inVal\":1.0,\"outVal\":-1.0},{\"inVal\":1.123456,\"outVal\":0.312312},{\"inVal\":3123.0,\"outVal\":3123.0},{\"inVal\":0.0,\"outVal\":0.0}],\"defaultValue\":\"invalid input value\",\"defaultValueNumeric\":null}],\"compuVTabs\":[{\"name\":\"firstVtab\",\"longIdentifier\":\"very first vtab\",\"conversionType\":\"TAB_VERB\",\"numberOfValuePairs\":3,\"valuePairs\":[{\"inVal\":1.1,\"outVal\":\"first entry\"},{\"inVal\":0.9,\"outVal\":\"second entry\"},{\"inVal\":0.0,\"outVal\":\"third entry\"}],\"defaultValue\":\"default value\"}],\"compuVTabRanges\":[{\"name\":\"sophisticated.vtabRange\",\"longIdentifier\":\"I am very sophisticated, trust me\",\"numberOfValueTriples\":4,\"valueTriples\":[{\"inValMin\":-5.5,\"inValMax\":5.5,\"outVal\":\"from minus five point five to five point five\"},{\"inValMin\":5.5,\"inValMax\":10.0,\"outVal\":\"from five point five to ten\"},{\"inValMin\":10.0,\"inValMax\":100.0,\"outVal\":\"from ten to one hundred\"},{\"inValMin\":100.0,\"inValMax\":200.0,\"outVal\":\"from one hundred to two hundred\"}],\"defaultValue\":\"default\"}],\"frame\":{\"name\":\"frame\",\"longIdentifier\":\"big frame\",\"scalingUnit\":7,\"rate\":49,\"frameMeasurements\":[\"identA\",\"identB\",\"identC\"],\"ifDatas\":[]},\"functions\":[{\"name\":\"firstFunction\",\"longIdentifier\":\"very first function\",\"annotations\":[],\"defCharacteristics\":[\"char1\",\"char2\",\"char3\"],\"functionVersion\":\"version3.1.4.1\",\"ifDatas\":[],\"inMeasurments\":[\"meas1\",\"meas2\",\"meas3\"],\"locMeasurments\":[\"locMeas1\",\"locMeas2\",\"locMeas3\"],\"outMeasurments\":[\"outMeas1\",\"outMeas2\",\"outMeas3\"],\"refCharacteristics\":[\"refChar1\",\"refChar2\",\"refChar3\"],\"subFunctions\":[\"subFunc1\",\"subFunc2\",\"subFunc3\"]}],\"groups\":[{\"groupName\":\"groupies\",\"longIdentifier\":\"stick to the group\",\"annotations\":[],\"functionList\":null,\"ifDatas\":[],\"refCharacteristics\":[\"refChar1\",\"refChar2\",\"refChar3\"],\"refMeasurements\":[\"meas1\",\"meas2\",\"meas3\",\"meas[0][0]\"],\"root\":true,\"subGroups\":[\"subGroup1\",\"subGroup2\",\"subGroup3\"]}],\"ifDatas\":[],\"measurements\":[{\"name\":\"superMeasurement\",\"longIdentifier\":\"ultimate test\",\"datatype\":\"UBYTE\",\"conversion\":\"THE.IDENTITY\",\"resolution\":1,\"accuracy\":2.1,\"lowerLimit\":4.2,\"upperLimit\":255.0,\"annotations\":[{\"label\":\"annotation I\",\"origin\":\"me\",\"text\":[\"row \\\\'1'\\\\\",\"row '2'\\r\\n\",\"row\\t3\"]}],\"arraySize\":7,\"bitMask\":45055,\"bitOperation\":{\"leftShift\":null,\"rightShift\":4,\"signExtend\":true},\"byteorder\":\"MSB_FIRST\",\"discrete\":true,\"displayIdentifier\":\"mega.superMeasurement\",\"ecuAddress\":11259375,\"ecuAddressExtension\":255,\"errorMask\":14,\"format\":\"%3.1\",\"functionList\":[\"firstFunction\",\"secondFunction\",\"thirdFunction\"],\"ifDatas\":[{\"content\":\"/begin IF_DATA i am not parsed yet /end IF_DATA\"}],\"layout\":\"ROW_DIR\",\"matrixDim\":{\"xDim\":7,\"yDim\":8,\"zDim\":10},\"maxRefresh\":{\"scalingUnit\":12,\"rate\":42},\"physUnit\":\"kg/m\",\"readWrite\":true,\"memorySegment\":\"mem1\",\"symbolLink\":{\"symbolName\":\"symbol name\",\"offset\":84},\"virtual\":[\"meas_1\",\"meas_2\"]}],\"modCommon\":{\"comment\":\"\",\"alignmentByte\":2,\"alignmentFloat32IEEE\":8,\"alignmentFloat64IEEE\":8,\"alignmentInt64\":null,\"alignmentLong\":3,\"alignmentWord\":1,\"byteorder\":\"MSB_FIRST\",\"dataSize\":128,\"deposit\":\"DIFFERENCE\",\"standardRecordLayout\":null},\"modPar\":{\"comment\":\"cmt\",\"addresses\":[18,1193046,15],\"calibrationMethods\":[{\"name\":\"methodA\",\"version\":3141,\"calibrationHandle\":[{\"handles\":[65536,768,7,7340032,262144],\"calibrationText\":\"important!\"},{\"handles\":[0,16],\"calibrationText\":null}]}],\"cpuType\":\"RISC\",\"customer\":\"public domain\",\"customerNo\":\"42\",\"ecu\":\"RISC-V\",\"ecuCalibrationOffset\":84,\"epk\":\"EPROM_identification - should it be a string or an identifier?\",\"memoryLayouts\":[{\"prgType\":\"PRG_CODE\",\"address\":16,\"size\":4096,\"offset\":[5,4,3,2,1],\"ifDatas\":[]}],\"memorySegments\":[{\"name\":\"mem1\",\"longIdentifier\":\"lots of code in internal flash\",\"prgType\":\"CODE\",\"memoryType\":\"FLASH\",\"attribute\":\"INTERN\",\"address\":66,\"size\":4096,\"offset\":[-1,-1,-1,-1,-1],\"ifDatas\":[]}],\"numberOfInterfaces\":168,\"phoneNumber\":\"911\",\"supplier\":\"Andreas Lenzen\",\"systemConstants\":[{\"name\":\"close to 1\",\"value\":\"value 1\"},{\"name\":\"pi\",\"value\":\"about 3\"}],\"user\":\"A2l stakeholders\",\"version\":\"Very high :-)\"},\"recordLayouts\":[{\"name\":\"master_record\",\"alignmentByte\":2,\"alignmentFloat32IEEE\":4,\"alignmentFloat64IEEE\":8,\"alignmentInt64\":16,\"alignmentLong\":32,\"alignmentWord\":64,\"axisPtsX\":{\"position\":1,\"datatype\":\"SBYTE\",\"indexorder\":\"INDEX_INCR\",\"addressing\":\"DIRECT\"},\"axisPtsY\":{\"position\":2,\"datatype\":\"UBYTE\",\"indexorder\":\"INDEX_DECR\",\"addressing\":\"PBYTE\"},\"axisPtsZ\":{\"position\":3,\"datatype\":\"UBYTE\",\"indexorder\":\"INDEX_DECR\",\"addressing\":\"PBYTE\"},\"axisPts4\":{\"position\":4,\"datatype\":\"UBYTE\",\"indexorder\":\"INDEX_DECR\",\"addressing\":\"PBYTE\"},\"axisPts5\":{\"position\":5,\"datatype\":\"UBYTE\",\"indexorder\":\"INDEX_DECR\",\"addressing\":\"PBYTE\"},\"axisRescaleX\":{\"position\":6,\"datatype\":\"UBYTE\",\"maxNumberOfRescalePairs\":3141,\"indexorder\":\"INDEX_DECR\",\"addressing\":\"PBYTE\"},\"axisRescaleY\":{\"position\":7,\"datatype\":\"UBYTE\",\"maxNumberOfRescalePairs\":3141,\"indexorder\":\"INDEX_DECR\",\"addressing\":\"PBYTE\"},\"axisRescaleZ\":{\"position\":8,\"datatype\":\"UBYTE\",\"maxNumberOfRescalePairs\":3141,\"indexorder\":\"INDEX_DECR\",\"addressing\":\"PBYTE\"},\"axisRescale4\":{\"position\":9,\"datatype\":\"UBYTE\",\"maxNumberOfRescalePairs\":3141,\"indexorder\":\"INDEX_DECR\",\"addressing\":\"PBYTE\"},\"axisRescale5\":{\"position\":10,\"datatype\":\"UBYTE\",\"maxNumberOfRescalePairs\":3141,\"indexorder\":\"INDEX_DECR\",\"addressing\":\"PBYTE\"},\"distOpX\":{\"position\":11,\"dataType\":\"SBYTE\"},\"distOpY\":{\"position\":12,\"dataType\":\"SBYTE\"},\"distOpZ\":{\"position\":13,\"dataType\":\"SBYTE\"},\"distOp4\":{\"position\":14,\"dataType\":\"SBYTE\"},\"distOp5\":{\"position\":15,\"dataType\":\"SBYTE\"},\"fixNoAxisPtsX\":{\"numberOfAxisPoints\":1},\"fixNoAxisPtsY\":{\"numberOfAxisPoints\":2},\"fixNoAxisPtsZ\":{\"numberOfAxisPoints\":3},\"fixNoAxisPts4\":{\"numberOfAxisPoints\":4},\"fixNoAxisPts5\":{\"numberOfAxisPoints\":5},\"functionValues\":{\"position\":47,\"dataType\":\"UBYTE\",\"indexMode\":\"ALTERNATE_CURVES\",\"addressType\":\"PLONG\"},\"identification\":{\"position\":48,\"dataType\":\"ULONG\"},\"noAxisPtsX\":{\"position\":16,\"dataType\":\"UBYTE\"},\"noAxisPtsY\":{\"position\":17,\"dataType\":\"UBYTE\"},\"noAxisPtsZ\":{\"position\":18,\"dataType\":\"UBYTE\"},\"noAxisPts4\":{\"position\":19,\"dataType\":\"UBYTE\"},\"noAxisPts5\":{\"position\":20,\"dataType\":\"UBYTE\"},\"noRescaleX\":{\"position\":21,\"dataType\":\"ULONG\"},\"noRescaleY\":{\"position\":22,\"dataType\":\"ULONG\"},\"noRescaleZ\":{\"position\":23,\"dataType\":\"ULONG\"},\"noRescale4\":{\"position\":24,\"dataType\":\"ULONG\"},\"noRescale5\":{\"position\":25,\"dataType\":\"ULONG\"},\"offsetX\":{\"position\":26,\"dataType\":\"ULONG\"},\"offsetY\":{\"position\":27,\"dataType\":\"ULONG\"},\"offsetZ\":{\"position\":28,\"dataType\":\"ULONG\"},\"offset4\":{\"position\":29,\"dataType\":\"ULONG\"},\"offset5\":{\"position\":30,\"dataType\":\"ULONG\"},\"reserved\":[{\"position\":49,\"dataSize\":\"LONG\"},{\"position\":50,\"dataSize\":\"WORD\"}],\"ripAddressW\":{\"position\":31,\"dataType\":\"ULONG\"},\"ripAddressX\":{\"position\":32,\"dataType\":\"ULONG\"},\"ripAddressY\":{\"position\":33,\"dataType\":\"ULONG\"},\"ripAddressZ\":{\"position\":34,\"dataType\":\"ULONG\"},\"ripAddress4\":{\"position\":35,\"dataType\":\"ULONG\"},\"ripAddress5\":{\"position\":36,\"dataType\":\"ULONG\"},\"srcAddressX\":{\"position\":37,\"dataType\":\"ULONG\"},\"srcAddressY\":{\"position\":38,\"dataType\":\"ULONG\"},\"srcAddressZ\":{\"position\":39,\"dataType\":\"ULONG\"},\"srcAddress4\":{\"position\":40,\"dataType\":\"ULONG\"},\"srcAddress5\":{\"position\":41,\"dataType\":\"ULONG\"},\"shiftOpX\":{\"position\":42,\"dataType\":\"ULONG\"},\"shiftOpY\":{\"position\":43,\"dataType\":\"ULONG\"},\"shiftOpZ\":{\"position\":44,\"dataType\":\"ULONG\"},\"shiftOp4\":{\"position\":45,\"dataType\":\"ULONG\"},\"shiftOp5\":{\"position\":46,\"dataType\":\"ULONG\"},\"staticRecordLayout\":true}],\"units\":[{\"name\":\"unitA\",\"longIdentifier\":\"detailed description with spaces\",\"display\":\"display string\",\"type\":\"DERIVED\",\"unit_ref\":\"unitB\",\"siExponents\":{\"length\":5,\"mass\":2,\"time\":7,\"electricCurrent\":10,\"temperature\":12,\"amountOfSubstance\":14,\"luminousIntensity\":16},\"unitConversion\":{\"gradient\":10.1,\"offset\":2.2}}],\"userRights\":[{\"userLevelId\":\"admins\",\"readOnly\":true,\"groups\":[[\"g1\",\"g2\",\"g3\"]]}],\"variantCoding\":{\"varCharacteristics\":[{\"name\":\"varChar\",\"criterions\":[\"crit1\",\"crit2\",\"crit3\"],\"addresses\":[29764,29765,29766,29767]}],\"varCriterion\":[{\"name\":\"varCriterion\",\"longIdentifier\":\"var criterion\",\"values\":[\"A\",\"B\",\"C\",\"D\",\"E\",\"F\"],\"measurement\":\"superA\",\"selectionCharacteristic\":\"varSelectionCharac\"}],\"varForbiddenComb\":[{\"tuples\":[{\"name\":\"varCriterion\",\"value\":\"D\"},{\"name\":\"varCriterion\",\"value\":\"F\"}]}],\"varNaming\":\"NUMERIC\",\"varSeparator\":\"#\"},\"blobs\":[{\"content\":\"/begin BLOB i am not parsed neither /end BLOB\"}]}]}}"; 
	@Test
	void testToJson() throws JsonGenerationException, JsonMappingException, IOException {
		Asap2File f = getTestFile(TestFile.A);
		assertEquals(TEST_FILE_A_JSON, f.toJson());
	}
	
	@Test
	void testFromJson() throws JsonGenerationException, JsonMappingException, IOException {
		Asap2File fromJson = Asap2File.fromJson(TEST_FILE_A_JSON);
		Asap2File fromA2l = getTestFile(TestFile.A);
		
		assertEquals(fromJson.toJson(), fromA2l.toJson());
	}
	
	@Test
	void testToA2l_parsable() throws IOException {
		Asap2File fromA2l = getTestFile(TestFile.A);
		String generatedA2l = fromA2l.toA2L();
		Asap2Parser parser = new Asap2Parser(new ByteArrayInputStream(generatedA2l.getBytes()));
		parser.setEventHandler((a,b,c) -> { 
			fail(a + ":" + b + ":" + c); 
		});
		assertNotNull(parser.parse());
	}
	
	@Test
	void testToA2l_completness() throws IOException {
		Asap2File fromA2l = getTestFile(TestFile.A);
		String jsonTestfileA = fromA2l.toJson();
		String toA2lResult = fromA2l.toA2L();
		Asap2Parser parser = new Asap2Parser(new ByteArrayInputStream(toA2lResult.getBytes()));
		parser.setEventHandler((a,b,c) -> { 
			fail(a + ":" + b + ":" + c); 
		});
		Asap2File generatedA2lFile = parser.parse();
		String jsonGeneratedA2lFile = generatedA2lFile.toJson();
		
		double relativeDifference = (double)jsonGeneratedA2lFile.length() / jsonTestfileA.length() * 100.0;
		assertEquals(jsonTestfileA, jsonGeneratedA2lFile, "Completness: " + relativeDifference + "%");
	}
}

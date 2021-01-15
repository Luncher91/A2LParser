# A2LParser

This library parses an A2L file according the 1.6.1 standards and provides a Java Object structure with all the data.

## Java Samples

### Parse A2L

```java
Asap2Parser parser = new Asap2Parser("freeTest.a2l");
		
// optional
parser.setEventHandler((line, position, message) -> { System.err.println("Line " + line + "@" + position + ": " + message); });

Asap2File a2l = parser.parse();
System.out.println("Project: " + a2l.getProject().getName());
```

### A2L to JSON

```java
Asap2Parser parser = new Asap2Parser("freeTest.a2l");
Asap2File a2l = parser.parse();

System.out.print(a2l.toJson());
```

### Parse JSON

```java
Asap2File fromJson = Asap2File.fromJson(TEST_FILE_A_JSON);

// or directly from file
//Asap2File fromJson = Asap2File.fromJsonFile("sample_a2l.json");

System.out.println("Project: " + fromJson.getProject().getName());
```

## Command Line Samples

### A2L to JSON
```console
java -jar A2LParser-2.0.0-jar-with-dependencies.jar --json src/test/resources/freeTest.a2l
```

Output:

```console
{"a2mlVersion":{"versionNo":1,"upgradeNo":31},"asap2Version":{"versionNo":1,"upgradeNo":71},"project":{"name":"Free_Example","longIdentifier":"MIT licensed example file","header":{"comment":"Free Asap2 example file","projectNo":"Free42","version":"V1.7.1"},"modules":[{"name":"sample_module","longIdentifier":"great example with different escaped quotation \" marks like double quotes \" and backslash escaped at the end \"","a2ml":[{"content":"/begin A2ML i am not parsed /end A2ML"}],"axisPts":[{"name":"axis_pts_1","longIdentifier":"I am a long identifier of an AXIS_PTS object","address":11259375,"inputQuantitiy":"in_quant","deposit":"axis_pts_deposit","maxDiff":1.1,"conversion":"axis_pts_conv","maxAxisPoints":123,"lowerLimit":1.2,"upperLimit":1.3,"notes":[],"byteorder":"MSB_FIRST","access":"NOT_IN_MCD_SYSTEM","axisPointDeposit":"DIFFERENCE","displayIdentifier":"display.identifier.wohoo","ecuAddressExtension":38787,"extendedLimits":{"lowerLimit":0.1,"upperLimit":0.5},"format":"%1212.21","functions":null,"guardRails":false,"ifData":[],"monotony":"NOT_MON","physUnit":"m/(s*s)","readOnly":false,"memorySegment":"memorySegment.42","stepSize":820.028,"symbolLink":null}],"characteristics":[{"name":"char_a","longIdentifier":"description here","type":"CUBE_5","address":291,"deposit":"char_deposit","maxDiff":1.1,"conversion":"char_conv","lowerLimit":0.0,"upperLimit":10.0,"notes":[{"label":null,"origin":null,"text":["hello world"]}],"axisDescriptions":[{"attribute":"CURVE_AXIS","inputQuantity":"in_quant_char","conversion":"char_axis_descr_conv","maxAxisPoints":22,"lowerLimit":5.5,"upperLimit":21.21,"annotations":[],"axisPoints_ref":"axisPtsA","byteorder":"MSB_FIRST","curveAxis_ref":"curveAxisA","deposit":"DIFFERENCE","extendedLimits":null,"fixAxisPar":{"offset":2,"shift":4,"numberapo":8},"fixAxisParDist":{"offset":4,"shift":16,"numberapo":32},"fixAxisParList":{"axisPtsValues":[1.1,2.2,3.3,4.4,5.5]},"format":"%3.3","maxGrad":30.0,"monotony":"NOT_MON","physUnit":"m/(s*s)","readOnly":true,"stepSize":1.2}],"bitmask":16777215,"byteorder":"MSB_LAST","access":"CALIBRATION","comparisonQuantityMeasurment":"comparisonQuantity","dependetCharacteristic":{"formula":"X1*X2","characterstics":["characA","characB"]},"discrete":true,"displayIdentifier":"display_ident_char","ecuAddressExtension":10000,"extendedLimits":{"lowerLimit":1.1,"upperLimit":5.5},"format":"%12.12","functions":["f1","f2","f3"],"guardRails":false,"ifData":[{"content":"/begin IF_DATA i am not parsed yet /end IF_DATA"}],"mapList":["identA","identB"],"matrixDim":{"xDim":3,"yDim":4,"zDim":5},"maxRefresh":{"scalingUnit":6,"rate":10},"number":1233123,"physUnit":"kg","readOnly":true,"memorySegment":"memory_seg_ref","stepSize":99.9,"symbolLink":{"symbolName":"symbol name char","offset":7},"virtualCharacteristic":{"formula":"sin(cos(X2-X1))","characterstics":["characteristicA","characteristicB"]}}],"compuMethods":[{"name":"THE.IDENTITY","longIdentifier":"very unique","conversionType":"RAT_FUNC","format":"%8.4","unit":"°","coeffs":{"a":0.0,"b":1.1,"c":0.0,"d":0.0,"e":0.0,"f":1.1},"coeffsLienar":{"a":7.1,"b":-12.5},"compuTab_ref":null,"formula":null,"unit_ref":null,"conversionTable_ref":null},{"name":"THE.FORMULA","longIdentifier":"very unique","conversionType":"FORM","format":"%8.4","unit":"°","coeffs":null,"coeffsLienar":null,"compuTab_ref":null,"formula":{"fx":"X1-42","gx":"X1+42"},"unit_ref":null,"conversionTable_ref":null}],"compuTabs":[{"name":"PERFECT_COMPU.TAB","longIdentifier":"very perfect compu tab","conversionType":"TAB_INTP","numberOfValuePairs":5,"valuePairs":[{"inVal":-1.1,"outVal":-1.1},{"inVal":1.0,"outVal":-1.0},{"inVal":1.123456,"outVal":0.312312},{"inVal":3123.0,"outVal":3123.0},{"inVal":0.0,"outVal":0.0}],"defaultValue":"invalid input value","defaultValueNumeric":null}],"compuVTabs":[{"name":"firstVtab","longIdentifier":"very first vtab","conversionType":"TAB_VERB","numberOfValuePairs":3,"valuePairs":[{"inVal":1.1,"outVal":"first entry"},{"inVal":0.9,"outVal":"second entry"},{"inVal":0.0,"outVal":"third entry"}],"defaultValue":"default value"}],"compuVTabRanges":[{"name":"sophisticated.vtabRange","longIdentifier":"I am very sophisticated, trust me","numberOfValueTriples":4,"valueTriples":[{"inValMin":-5.5,"inValMax":5.5,"outVal":"from minus five point five to five point five"},{"inValMin":5.5,"inValMax":10.0,"outVal":"from five point five to ten"},{"inValMin":10.0,"inValMax":100.0,"outVal":"from ten to one hundred"},{"inValMin":100.0,"inValMax":200.0,"outVal":"from one hundred to two hundred"}],"defaultValue":"default"}],"frame":{"name":"frame","longIdentifier":"big frame","scalingUnit":7,"rate":49,"frameMeasurements":["identA","identB","identC"],"ifDatas":[]},"functions":[{"name":"firstFunction","longIdentifier":"very first function","annotations":[],"defCharacteristics":["char1","char2","char3"],"functionVersion":"version3.1.4.1","ifDatas":[],"inMeasurments":["meas1","meas2","meas3"],"locMeasurments":["locMeas1","locMeas2","locMeas3"],"outMeasurments":["outMeas1","outMeas2","outMeas3"],"refCharacteristics":["refChar1","refChar2","refChar3"],"subFunctions":["subFunc1","subFunc2","subFunc3"]}],"groups":[{"groupName":"groupies","longIdentifier":"stick to the group","annotations":[],"functionList":null,"ifDatas":[],"refCharacteristics":["refChar1","refChar2","refChar3"],"refMeasurements":["meas1","meas2","meas3"],"root":true,"subGroups":["subGroup1","subGroup2","subGroup3"]}],"ifDatas":[],"measurements":[{"name":"superMeasurement","longIdentifier":"ultimate test","datatype":"UBYTE","conversion":"THE.IDENTITY","resolution":1,"accuracy":2.1,"lowerLimit":4.2,"upperLimit":255.0,"annotations":[{"label":"annotation I","origin":"me","text":["row \\'1'\\","row '2'\r\n","row\t3"]}],"arraySize":7,"bitMask":45055,"bitOperation":{"leftShift":null,"rightShift":4,"signExtend":true},"byteorder":"MSB_FIRST","discrete":true,"displayIdentifier":"mega.superMeasurement","ecuAddress":11259375,"ecuAddressExtension":255,"errorMask":14,"format":"%3.1","functionList":["firstFunction","secondFunction","thirdFunction"],"ifDatas":[{"content":"/begin IF_DATA i am not parsed yet /end IF_DATA"}],"layout":"ROW_DIR","matrixDim":{"xDim":7,"yDim":8,"zDim":10},"maxRefresh":{"scalingUnit":12,"rate":42},"physUnit":"kg/m","readWrite":true,"memorySegment":"mem1","symbolLink":{"symbolName":"symbol name","offset":84},"virtual":["meas_1","meas_2"]}],"modCommon":{"comment":"","alignmentByte":2,"alignmentFloat32IEEE":8,"alignmentFloat64IEEE":8,"alignmentInt64":null,"alignmentLong":3,"alignmentWord":1,"byteorder":"MSB_FIRST","dataSize":128,"deposit":"DIFFERENCE","standardRecordLayout":null},"modPar":{"comment":"cmt","addresses":[18,1193046,15],"calibrationMethods":[{"name":"methodA","version":3141,"calibrationHandle":[{"handles":[65536,768,7,7340032,262144],"calibrationText":"important!"}]}],"cpuType":"RISC","customer":"public domain","customerNo":"42","ecu":"RISC-V","ecuCalibrationOffset":84,"epk":"EPROM_identification - should it be a string or an identifier?","memoryLayouts":[{"prgType":"PRG_CODE","address":16,"size":4096,"offset":[5,4,3,2,1],"ifDatas":[]}],"memorySegments":[{"name":"mem1","longIdentifier":"lots of code in internal flash","prgType":"CODE","memoryType":"FLASH","attribute":"INTERN","address":66,"size":4096,"offset":[-1,-1,-1,-1,-1],"ifDatas":[]}],"numberOfInterfaces":168,"phoneNumber":"911","supplier":"Andreas Lenzen","systemConstants":[{"name":"close to 1","value":"value 1"},{"name":"pi","value":"about 3"}],"user":"A2l stakeholders","version":"Very high :-)"},"recordLayouts":[{"name":"master_record","alignmentByte":2,"alignmentFloat32IEEE":4,"alignmentFloat64IEEE":8,"alignmentInt64":16,"alignmentLong":32,"alignmentWord":64,"axisPtsX":{"position":1,"datatype":"SBYTE","indexorder":"INDEX_INCR","addressing":"DIRECT"},"axisPtsY":{"position":2,"datatype":"UBYTE","indexorder":"INDEX_DECR","addressing":"PBYTE"},"axisPtsZ":{"position":3,"datatype":"UBYTE","indexorder":"INDEX_DECR","addressing":"PBYTE"},"axisPts4":{"position":4,"datatype":"UBYTE","indexorder":"INDEX_DECR","addressing":"PBYTE"},"axisPts5":{"position":5,"datatype":"UBYTE","indexorder":"INDEX_DECR","addressing":"PBYTE"},"axisRescaleX":{"position":6,"datatype":"UBYTE","maxNumberOfRescalePairs":3141,"indexorder":"INDEX_DECR","addressing":"PBYTE"},"axisRescaleY":{"position":7,"datatype":"UBYTE","maxNumberOfRescalePairs":3141,"indexorder":"INDEX_DECR","addressing":"PBYTE"},"axisRescaleZ":{"position":8,"datatype":"UBYTE","maxNumberOfRescalePairs":3141,"indexorder":"INDEX_DECR","addressing":"PBYTE"},"axisRescale4":{"position":9,"datatype":"UBYTE","maxNumberOfRescalePairs":3141,"indexorder":"INDEX_DECR","addressing":"PBYTE"},"axisRescale5":{"position":10,"datatype":"UBYTE","maxNumberOfRescalePairs":3141,"indexorder":"INDEX_DECR","addressing":"PBYTE"},"distOpX":{"position":11,"dataType":"SBYTE"},"distOpY":{"position":12,"dataType":"SBYTE"},"distOpZ":{"position":13,"dataType":"SBYTE"},"distOp4":{"position":14,"dataType":"SBYTE"},"distOp5":{"position":15,"dataType":"SBYTE"},"fixNoAxisPtsX":{"numberOfAxisPoints":1},"fixNoAxisPtsY":{"numberOfAxisPoints":2},"fixNoAxisPtsZ":{"numberOfAxisPoints":3},"fixNoAxisPts4":{"numberOfAxisPoints":4},"fixNoAxisPts5":{"numberOfAxisPoints":5},"functionValues":{"position":47,"dataType":"UBYTE","indexMode":"ALTERNATE_CURVES","addressType":"PLONG"},"identification":{"position":48,"dataType":"ULONG"},"noAxisPtsX":{"position":16,"dataType":"UBYTE"},"noAxisPtsY":{"position":17,"dataType":"UBYTE"},"noAxisPtsZ":{"position":18,"dataType":"UBYTE"},"noAxisPts4":{"position":19,"dataType":"UBYTE"},"noAxisPts5":{"position":20,"dataType":"UBYTE"},"noRescaleX":{"position":21,"dataType":"ULONG"},"noRescaleY":{"position":22,"dataType":"ULONG"},"noRescaleZ":{"position":23,"dataType":"ULONG"},"noRescale4":{"position":24,"dataType":"ULONG"},"noRescale5":{"position":25,"dataType":"ULONG"},"offsetX":{"position":26,"dataType":"ULONG"},"offsetY":{"position":27,"dataType":"ULONG"},"offsetZ":{"position":28,"dataType":"ULONG"},"offset4":{"position":29,"dataType":"ULONG"},"offset5":{"position":30,"dataType":"ULONG"},"reserved":[{"position":49,"dataSize":"LONG"},{"position":50,"dataSize":"WORD"}],"ripAddressW":{"position":31,"dataType":"ULONG"},"ripAddressX":{"position":32,"dataType":"ULONG"},"ripAddressY":{"position":33,"dataType":"ULONG"},"ripAddressZ":{"position":34,"dataType":"ULONG"},"ripAddress4":{"position":35,"dataType":"ULONG"},"ripAddress5":{"position":36,"dataType":"ULONG"},"srcAddressX":{"position":37,"dataType":"ULONG"},"srcAddressY":{"position":38,"dataType":"ULONG"},"srcAddressZ":{"position":39,"dataType":"ULONG"},"srcAddress4":{"position":40,"dataType":"ULONG"},"srcAddress5":{"position":41,"dataType":"ULONG"},"shiftOpX":{"position":42,"dataType":"ULONG"},"shiftOpY":{"position":43,"dataType":"ULONG"},"shiftOpZ":{"position":44,"dataType":"ULONG"},"shiftOp4":{"position":45,"dataType":"ULONG"},"shiftOp5":{"position":46,"dataType":"ULONG"},"staticRecordLayout":true}],"units":[{"name":"unitA","longIdentifier":"detailed description with spaces","display":"display string","type":"DERIVED","unit_ref":"unitB","siExponents":{"length":5,"mass":2,"time":7,"electricCurrent":10,"temperature":12,"amountOfSubstance":14,"luminousIntensity":16},"unitConversion":{"gradient":10.1,"offset":2.2}}],"userRights":[{"userLevelId":"admins","readOnly":true,"groups":[["g1","g2","g3"]]}],"variantCoding":{"varCharacteristics":[{"name":"varChar","criterions":["crit1","crit2","crit3"],"addresses":[29764,29765,29766,29767]}],"varCriterion":[{"name":"varCriterion","longIdentifier":"var criterion","values":["A","B","C","D","E","F"],"measurement":"superA","selectionCharacteristic":"varSelectionCharac"}],"varForbiddenComb":[{"tuples":[{"name":"varCriterion","value":"D"},{"name":"varCriterion","value":"F"}]}],"varNaming":"NUMERIC","varSeparator":"#"},"blobs":[{"content":"/begin BLOB i am not parsed neither /end BLOB"}]}]}}
```

### JSON to A2L
```console
java -jar A2LParser-2.0.0-jar-with-dependencies.jar --asap2 src/test/resources/freeTest.json
```

Output:

```console
ASAP2_VERSION 1 71
A2ML_VERSION 1 31
/begin PROJECT Free_Example "MIT licensed example file"
    /begin HEADER "Free Asap2 example file"
        PROJECT_NO Free42
        VERSION "V1.7.1"
    /end HEADER
    /begin MODULE sample_module "great example with different escaped quotation \" marks like double quotes \" and backslash escaped at the end \""
        /begin A2ML i am not parsed /end A2ML
        /begin AXIS_PTS axis_pts_1 "I am a long identifier of an AXIS_PTS object" 11259375 in_quant axis_pts_deposit 1.1 axis_pts_conv 123 1.2 1.3
            BYTE_ORDER MSB_FIRST
            CALIBRATION_ACCESS NOT_IN_MCD_SYSTEM
            DEPOSIT DIFFERENCE
            DISPLAY_IDENTIFIER display.identifier.wohoo
            ECU_ADDRESS_EXTENSION 38787
            EXTENDED_LIMITS 0.1 0.5
            FORMAT "%1212.21"
            MONOTONY NOT_MON
            PHYS_UNIT "m/(s*s)"
            REF_MEMORY_SEGMENT memorySegment.42
            STEP_SIZE 820.028
        /end AXIS_PTS
        /begin CHARACTERISTIC char_a "description here" CUBE_5 0x123 char_deposit 1.1 char_conv 0.0 10.0
            /begin ANNOTATION
                /begin ANNOTATION_TEXT
                    "hello world"
                /end ANNOTATION_TEXT
            /end ANNOTATION
            /begin AXIS_DESCR CURVE_AXIS in_quant_char char_axis_descr_conv 22 5.5 21.21
                AXIS_PTS_REF axisPtsA
                BYTE_ORDER MSB_FIRST
                CURVE_AXIS_REF curveAxisA
                DEPOSIT DIFFERENCE
                FIX_AXIS_PAR 2 4 8
                FIX_AXIS_PAR_DIST 4 16 32
                /begin FIX_AXIS_PAR_LIST
                    1.1
                    2.2
                    3.3
                    4.4
                    5.5
                /end FIX_AXIS_PAR_LIST
                FORMAT "%3.3"
                MAX_GRAD 30.0
                MONOTONY NOT_MON
                PHYS_UNIT "m/(s*s)"
                READ_ONLY
                STEP_SIZE 1.2
            /end AXIS_DESCR
            BIT_MASK 0xffffff
            BYTE_ORDER MSB_LAST
            CALIBRATION_ACCESS CALIBRATION
            COMPARISON_QUANTITY comparisonQuantity
            /begin DEPENDENT_CHARACTERISTIC "X1*X2"
                characA
                characB
            /end DEPENDENT_CHARACTERISTIC
            DISCRETE
            DISPLAY_IDENTIFIER display_ident_char
            ECU_ADDRESS_EXTENSION 0x2710
            EXTENDED_LIMITS 1.1 5.5
            FORMAT "%12.12"
            /begin FUNCTION_LIST
                f1
                f2
                f3
            /end FUNCTION_LIST
            /begin IF_DATA i am not parsed yet /end IF_DATA
            /begin MAP_LIST
                identA
                identB
            /end MAP_LIST
            MATRIX_DIM 3 4 5
            MAX_REFRESH 6 10
            NUMBER 1233123
            PHYS_UNIT "kg"
            READ_ONLY
            REF_MEMORY_SEGMENT memory_seg_ref
            STEP_SIZE 99.9
            SYMBOL_LINK "symbol name char" 7
            /begin VIRTUAL_CHARACTERISTIC "sin(cos(X2-X1))"
                characteristicA
                characteristicB
            /end VIRTUAL_CHARACTERISTIC
        /end CHARACTERISTIC
        /begin COMPU_METHOD THE.IDENTITY "very unique" RAT_FUNC "%8.4" "?"
            COEFFS 0.0 1.1 0.0 0.0 0.0 1.1
            COEFFS_LINEAR 7.1 -12.5
        /end COMPU_METHOD
        /begin COMPU_METHOD THE.FORMULA "very unique" FORM "%8.4" "?"
            /begin FORMULA "X1-42"
                FORMULA_INV "X1+42"
            /end FORMULA
        /end COMPU_METHOD
        /begin COMPU_TAB PERFECT_COMPU.TAB "very perfect compu tab" TAB_INTP 5
            -1.1 -1.1
            1.0 -1.0
            1.123456 0.312312
            3123.0 3123.0
            0.0 0.0
            DEFAULT_VALUE "invalid input value"
        /end COMPU_TAB
        /begin COMPU_VTAB firstVtab "very first vtab" TAB_VERB 3
            1.1 "first entry"
            0.9 "second entry"
            0.0 "third entry"
            DEFAULT_VALUE "default value"
        /end COMPU_VTAB
        /begin COMPU_VTAB_RANGE sophisticated.vtabRange "I am very sophisticated, trust me" 4
            -5.5 5.5 "from minus five point five to five point five"
            5.5 10.0 "from five point five to ten"
            10.0 100.0 "from ten to one hundred"
            100.0 200.0 "from one hundred to two hundred"
            DEFAULT_VALUE "default"
        /end COMPU_VTAB_RANGE
        /begin FRAME frame "big frame" 7 49
            FRAME_MEASUREMENT
            identA
            identB
            identC
        /end FRAME
        /begin FUNCTION firstFunction "very first function"
            /begin DEF_CHARACTERISTIC
                char1
                char2
                char3
            /end DEF_CHARACTERISTIC
            FUNCTION_VERSION "version3.1.4.1"
            /begin IN_MEASUREMENT
                meas1
                meas2
                meas3
            /end IN_MEASUREMENT
            /begin LOC_MEASUREMENT
                locMeas1
                locMeas2
                locMeas3
            /end LOC_MEASUREMENT
            /begin OUT_MEASUREMENT
                outMeas1
                outMeas2
                outMeas3
            /end OUT_MEASUREMENT
            /begin REF_CHARACTERISTIC
                refChar1
                refChar2
                refChar3
            /end REF_CHARACTERISTIC
            /begin SUB_FUNCTION
                subFunc1
                subFunc2
                subFunc3
            /end SUB_FUNCTION
        /end FUNCTION
        /begin GROUP groupies "stick to the group"
            /begin REF_CHARACTERISTIC
                refChar1
                refChar2
                refChar3
            /end REF_CHARACTERISTIC
            /begin REF_MEASUREMENT
                meas1
                meas2
                meas3
            /end REF_MEASUREMENT
            ROOT
            /begin SUB_GROUP
                subGroup1
                subGroup2
                subGroup3
            /end SUB_GROUP
        /end GROUP
        /begin MEASUREMENT superMeasurement "ultimate test" UBYTE THE.IDENTITY 1 2.1 4.2 255.0
            /begin ANNOTATION
                ANNOTATION_LABEL "annotation I"
                ANNOTATION_ORIGIN "me"
                /begin ANNOTATION_TEXT
                    "row \\\'1\'\\"
                    "row \'2\'\r\n"
                    "row\t3"
                /end ANNOTATION_TEXT
            /end ANNOTATION
            ARRAY_SIZE 7
            BIT_MASK 0xafff
            /begin BIT_OPERATION
                RIGHT_SHIFT 4
                SIGN_EXTEND
            /end BIT_OPERATION
            BYTE_ORDER MSB_FIRST
            DISCRETE
            DISPLAY_IDENTIFIER mega.superMeasurement
            ECU_ADDRESS 0xabcdef
            ECU_ADDRESS_EXTENSION 0xff
            ERROR_MASK 0xe
            FORMAT "%3.1"
            /begin FUNCTION_LIST
                firstFunction
                secondFunction
                thirdFunction
            /end FUNCTION_LIST
            /begin IF_DATA i am not parsed yet /end IF_DATA
            LAYOUT ROW_DIR
            MATRIX_DIM 7 8 10
            MAX_REFRESH 12 42
            PHYS_UNIT "kg/m"
            READ_WRITE
            REF_MEMORY_SEGMENT mem1
            SYMBOL_LINK "symbol name" 84
            /begin VIRTUAL
                meas_1
                meas_2
            /end VIRTUAL
        /end MEASUREMENT
        /begin MOD_COMMON ""
            ALIGNMENT_BYTE 2
            ALIGNMENT_FLOAT32_IEEE 8
            ALIGNMENT_FLOAT64_IEEE 8
            ALIGNMENT_LONG 3
            ALIGNMENT_WORD 1
            BYTE_ORDER MSB_FIRST
            DATA_SIZE 128
            DEPOSIT DIFFERENCE
        /end MOD_COMMON
        /begin MOD_PAR "cmt"
            ADDR_EPK 0x12
            ADDR_EPK 0x123456
            ADDR_EPK 0xf
            /begin CALIBRATION_METHOD "methodA" 3141
                /begin CALIBRATION_HANDLE
                    0x10000
                    0x300
                    0x7
                    0x700000
                    0x40000
                    CALIBRATION_HANDLE_TEXT "important!"
                /end CALIBRATION_HANDLE
            /end CALIBRATION_METHOD
            CPU_TYPE "RISC"
            CUSTOMER "public domain"
            CUSTOMER_NO "42"
            ECU "RISC-V"
            ECU_CALIBRATION_OFFSET 84
            EPK "EPROM_identification - should it be a string or an identifier?"
            /begin MEMORY_LAYOUT PRG_CODE 0x10 4096
                5
                4
                3
                2
                1
            /end MEMORY_LAYOUT
            /begin MEMORY_SEGMENT mem1 "lots of code in internal flash" CODE FLASH INTERN 0x42 4096
                -1
                -1
                -1
                -1
                -1
            /end MEMORY_SEGMENT
            NO_OF_INTERFACES 168
            PHONE_NO "911"
            SUPPLIER "Andreas Lenzen"
            SYSTEM_CONSTANT "close to 1" "value 1"
            SYSTEM_CONSTANT "pi" "about 3"
            USER "A2l stakeholders"
            VERSION "Very high :-)"
        /end MOD_PAR
        /begin RECORD_LAYOUT master_record
            ALIGNMENT_BYTE 2
            ALIGNMENT_FLOAT32_IEEE 4
            ALIGNMENT_FLOAT64_IEEE 8
            ALIGNMENT_INT64 16
            ALIGNMENT_LONG 32
            ALIGNMENT_WORD 64
            AXIS_PTS_X 1 SBYTE INDEX_INCR DIRECT
            AXIS_PTS_Y 2 UBYTE INDEX_DECR PBYTE
            AXIS_PTS_Z 3 UBYTE INDEX_DECR PBYTE
            AXIS_PTS_4 4 UBYTE INDEX_DECR PBYTE
            AXIS_PTS_5 5 UBYTE INDEX_DECR PBYTE
            AXIS_RESCALE_X 6 UBYTE 3141 INDEX_DECR PBYTE
            AXIS_RESCALE_Y 7 UBYTE 3141 INDEX_DECR PBYTE
            AXIS_RESCALE_Z 8 UBYTE 3141 INDEX_DECR PBYTE
            AXIS_RESCALE_4 9 UBYTE 3141 INDEX_DECR PBYTE
            AXIS_RESCALE_5 10 UBYTE 3141 INDEX_DECR PBYTE
            DIST_OP_X 11 SBYTE
            DIST_OP_Y 12 SBYTE
            DIST_OP_Z 13 SBYTE
            DIST_OP_4 14 SBYTE
            DIST_OP_5 15 SBYTE
            FIX_NO_AXIS_PTS_X 1
            FIX_NO_AXIS_PTS_Y 2
            FIX_NO_AXIS_PTS_Z 3
            FIX_NO_AXIS_PTS_4 4
            FIX_NO_AXIS_PTS_5 5
            FNC_VALUES 47 UBYTE ALTERNATE_CURVES PLONG
            IDENTIFICATION 48 ULONG
            NO_AXIS_PTS_X 16 UBYTE
            NO_AXIS_PTS_Y 17 UBYTE
            NO_AXIS_PTS_Z 18 UBYTE
            NO_AXIS_PTS_4 19 UBYTE
            NO_AXIS_PTS_5 20 UBYTE
            NO_RESCALE_X 21 ULONG
            NO_RESCALE_Y 22 ULONG
            NO_RESCALE_Z 23 ULONG
            NO_RESCALE_4 24 ULONG
            NO_RESCALE_5 25 ULONG
            OFFSET_X 26 ULONG
            OFFSET_Y 27 ULONG
            OFFSET_Z 28 ULONG
            OFFSET_4 29 ULONG
            OFFSET_5 30 ULONG
            RESERVED 49 LONG
            RESERVED 50 WORD
            RIP_ADDR_W 31 ULONG
            RIP_ADDR_X 32 ULONG
            RIP_ADDR_Y 33 ULONG
            RIP_ADDR_Z 34 ULONG
            RIP_ADDR_4 35 ULONG
            RIP_ADDR_5 36 ULONG
            SRC_ADDR_X 37 ULONG
            SRC_ADDR_Y 38 ULONG
            SRC_ADDR_Z 39 ULONG
            SRC_ADDR_4 40 ULONG
            SRC_ADDR_5 41 ULONG
            SHIFT_OP_X 42 ULONG
            SHIFT_OP_Y 43 ULONG
            SHIFT_OP_Z 44 ULONG
            SHIFT_OP_4 45 ULONG
            SHIFT_OP_5 46 ULONG
            STATIC_RECORD_LAYOUT
        /end RECORD_LAYOUT
        /begin UNIT unitA "detailed description with spaces" "display string" DERIVED
            REF_UNIT unitB
            SI_EXPONENTS 5 2 7 10 12 14 16
            UNIT_CONVERSION 10.1 2.2
        /end UNIT
        /begin USER_RIGHTS admins
            READ_ONLY
            /begin REF_GROUP
                g1
                g2
                g3
            /end REF_GROUP
        /end USER_RIGHTS
        /begin VARIANT_CODING
            /begin VAR_CHARACTERISTIC varChar
                crit1
                crit2
                crit3
                /begin VAR_ADDRESS
                    0x7444
                    0x7445
                    0x7446
                    0x7447
                /end VAR_ADDRESS
            /end VAR_CHARACTERISTIC
            /begin VAR_CRITERION varCriterion "var criterion"
                A
                B
                C
                D
                E
                F
                VAR_MEASUREMENT superA
                VAR_SELECTION_CHARACTERISTIC varSelectionCharac
            /end VAR_CRITERION
            /begin VAR_FORBIDDEN_COMB
                varCriterion D
                varCriterion F
            /end VAR_FORBIDDEN_COMB
            VAR_NAMING NUMERIC
            VAR_SEPARATOR "#"
        /end VARIANT_CODING
        /begin BLOB i am not parsed neither /end BLOB
    /end MODULE
/end PROJECT
```

### JSON Schema

```console
java -jar A2LParser-2.0.0-jar-with-dependencies.jar --schema
```

# Roadmap

This is a rough roadmap for the upcoming things I have in mind. I might reprioritize the features at any time.

* API documentation

## ~~Epic 1: basic read functionality~~ (Version 1.0)
* ~~write unit tests~~ (done; 90.2% java code coverage)

## Epic 2: writing a2l files
* ~~generate A2L code from the object structure~~

## Epic 3: handle includes
* preprocess the a2l files to perform includes

## Epic 4: library can be used with other languages as well 
This will be archived by creating object structures from the schema and control the parser via command line
* ~~generate JSON from it~~
* ~~make an executable which converts a2l to JSON~~
* ~~generate JSON schema from it~~
* ~~add a function that converts JSON to a2l~~

# Notes
I wont bother to support ulong; instead I use long! - An alternative would be to use BigInteger but it would probably make the library slower and consuming more memory.

# Known issues
* does not provide objects for typedef's and instance (need access to the most recent specification)
* does not support include (Epic 3)
* the library treats ulong as long (accepted)

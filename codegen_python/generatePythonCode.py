import subprocess
import sys
from typing import Dict

# get json schema
jsonFile = "pathyfiedSchema.json";
if(sys.argv[1].endswith(".jar")):
    subprocess.check_call([
        "java",
        "-jar",
        sys.argv[1],
        "-jsc",
        "-o",
        jsonFile,
    ])
elif (sys.argv[1].endswith(".json")):
    jsonFile = sys.argv[1]

subprocess.check_call([
    "datamodel-codegen",
    "--output",
    "a2l.py",
    "--class-name",
    "Asap2File",
    "--input",
    jsonFile
])
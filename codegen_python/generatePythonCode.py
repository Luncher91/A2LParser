import subprocess
import sys
from typing import Dict

# get json schema
subprocess.check_call([
    "java",
    "-jar",
    sys.argv[1],
    "-jsc",
    "-o",
    "pathyfiedSchema.json",
])

subprocess.check_call([
    "datamodel-codegen",
    "--output",
    "a2l.py",
    "--class-name",
    "Asap2File",
    "--input",
    "pathyfiedSchema.json"
])
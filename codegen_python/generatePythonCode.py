import subprocess
import sys
import json
import re
from typing import Dict

def collectPathIds(schema, path, idPathMap):
    if(isinstance(schema, dict)):
        for k,v in schema.items():
            if(isinstance(v, str) and k == "id"):
                idPathMap[v] = path
            else:
                collectPathIds(v, path + "/" + k, idPathMap)

# get json schema
jsonSchemaString = subprocess.getoutput([
    "java",
    "-jar",
    sys.argv[1],
    "-jsc",
])

jsonSchema = json.loads(jsonSchemaString)

idPathMap = {}
collectPathIds(jsonSchema, "#", idPathMap)

for k, v in idPathMap.items():
    jsonSchemaString = re.sub(r'"\$ref":\s*"' + k + r'"', r'"$ref":"' + v + r'"', jsonSchemaString)

f = open("pathyfiedSchema.json", "w")
f.write(jsonSchemaString)
f.close()

dm = subprocess.Popen([
    "datamodel-codegen",
    "--output",
    "a2l.py",
    "--class-name",
    "Asap2File",
    "--input",
    "pathyfiedSchema.json"
])
dm.wait()
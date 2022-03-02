import json
import a2l
import subprocess
import sys
import io

jsonContent = None
if(sys.argv[1].endswith(".jar")):
    proc = subprocess.Popen([
        "java",
        "-jar",
        sys.argv[1],
        "-mj",
        "-c", "ISO-8859-1",
        "-a2l",
        sys.argv[2],
    ], stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    rawJson, err = proc.communicate()
    jsonContent = rawJson.decode("ISO-8859-1")
elif(sys.argv[1].endswith(".json")):
    f = io.open(sys.argv[1], mode="r", encoding="ISO-8859-1")
    jsonContent = str(f.read())
else:
    print("Invalid arguments!")
    sys.exit(1)

a2lFile = a2l.Asap2File.parse_raw(jsonContent)
for c in a2lFile.project.modules[0].characteristics:
    print(c.name)
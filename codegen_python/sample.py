import a2l
import subprocess
import sys

rawJson = subprocess.getoutput([
    "java",
    "-jar",
    sys.argv[1],
    "-mj",
    "-a2l",
    sys.argv[2],
])
a2lFile = a2l.Asap2File.parse_raw(rawJson)
for c in a2lFile.project.modules[0].characteristics:
    print(c.name)
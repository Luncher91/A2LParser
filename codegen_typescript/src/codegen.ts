import { execSync } from 'child_process';
import fs from 'fs'
import { compile } from 'json-schema-to-typescript'

function patchAndCopy(bFile: any): any {
  let idPathMap: { [key: string]: string } = {};
  collectPathIds(bFile, "#", idPathMap);
  
  let stringSchema = JSON.stringify(bFile);
  for (const id in idPathMap) {
    let regex = "\"\\$ref\":\\s*\"" + id + "\"";
    stringSchema = stringSchema.replace(new RegExp(regex, "gm"), "\"$ref\": \"" + idPathMap[id] + "\"");
  }

  return JSON.parse(stringSchema);
}

function collectPathIds(root: any, currentPath: string, map: { [key: string]: string }) {
  if (typeof root == 'object' && !Array.isArray(root)) {
    for (const k in root) {
      const v = root[k];
      if (typeof v == "string" && k == "id") {
        map[v] = currentPath;
        // nicer names
        root[k] = v.split(":").pop();
      } else {
        collectPathIds(v, currentPath + "/" + k, map);
      }
    }
  }
}

console.log("Generating JSON schema...");
let jsonSchema: string = execSync("java -jar \"" + process.argv[2] + "\" -jsc").toString();
console.log("Preparing JSON schema...");
let parsedSchema = JSON.parse(jsonSchema);
var updatedSchema: any = patchAndCopy(parsedSchema);
console.log("Start parsing...")
compile(updatedSchema, "a2l").then(ts => {
  console.log("Generating code...");
  fs.writeFileSync('src/a2l.d.ts', ts);
});

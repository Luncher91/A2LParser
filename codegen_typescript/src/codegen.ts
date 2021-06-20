import { execSync } from 'child_process';
import fs from 'fs'
import { compile } from 'json-schema-to-typescript'

console.log("Generating JSON schema...");
let jsonSchema: string = execSync("java -jar \"" + process.argv[2] + "\" -jsc").toString();
console.log("Preparing JSON schema...");
let parsedSchema = JSON.parse(jsonSchema);
console.log("Start parsing...")
compile(parsedSchema, "a2l").then(ts => {
  console.log("Generating code...");
  fs.writeFileSync('src/a2l.d.ts', ts);
});

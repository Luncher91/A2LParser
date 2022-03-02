import { execSync } from "child_process";
import { Asap2File, Characteristic } from "./a2l";
import * as fs from 'fs';
import * as iconv from 'iconv';

function parseA2lFile(jar: string, a2lPath: string):Asap2File {
    let output: string = execSync("java -jar \"" + jar + "\" -mj -a2l \"" + a2lPath + "\"").toString();
    let a2lFile: Asap2File = JSON.parse(output);
    return a2lFile;
}

let a2lFile = null;
if(process.argv[2].endsWith(".jar")) {
    a2lFile = parseA2lFile(process.argv[2], process.argv[3]);
} else {
    let ic = new iconv.Iconv('iso-8859-1', 'utf-8');
    let content = ic.convert(fs.readFileSync(process.argv[2])).toString();
    a2lFile = JSON.parse(content);
}
for (let c of a2lFile.project?.modules![0].characteristics!) {
    console.log(c.name);
}
# Typescript sample code

Install dependencies
`npm install`

To compile the typescript to javascript run:

`npx tsc`

The first time compiling will throw an error because sample.ts cannot resolve './a2l'

Generating types:

`node ./dist/codegen.js ../target/A2LParser-2.3.0-jar-with-dependencies.jar`

Compile again:

`npx tsc`

Run sample:

`node ./dist/sample.js ../target/A2LParser-2.3.0-jar-with-dependencies.jar ../src/test/resources/freeTest.a2l`
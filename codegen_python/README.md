# Python sample code

I got it only to work with Python 3.7 because of the datamodel-codegen dependency and how it generates the code using the `future` feature.

Install dependencies:

`pip3 install -r requirements.txt`

Generate code:

`python3 generatePythonCode.py ../target/A2LParser-2.3.0-jar-with-dependencies.jar`

Run sample code:

`python3 sample.py ../target/A2LParser-2.3.0-jar-with-dependencies.jar ../src/test/resources/freeTest.a2l`


# A2LParser

This library parses an A2L file according the 1.6.1 standards and provides a Java Object structure with all the data.

## Features

* parse A2L to Java object structure
* JAR standalone
    * A2L to JSON
    * JSON to A2L

## Java Samples

### Parse A2L

```java
Asap2Parser parser = new Asap2Parser("freeTest.a2l");
		
// optional
parser.setEventHandler((line, position, message) -> { System.err.println("Line " + line + "@" + position + ": " + message); });

Asap2File a2l = parser.parse();
System.out.println("Project: " + a2l.getProject().getName());
```

### A2L to JSON

```java
Asap2Parser parser = new Asap2Parser("freeTest.a2l");
Asap2File a2l = parser.parse();

System.out.print(a2l.toJson());
```

### Parse JSON

```java
Asap2File fromJson = Asap2File.fromJson(TEST_FILE_A_JSON);

// or directly from file
//Asap2File fromJson = Asap2File.fromJsonFile("sample_a2l.json");

System.out.println("Project: " + fromJson.getProject().getName());
```

## Command Line Samples

In this chapter A2LParser.jar references the JAR with dependencies.

### Print out help

```console
java -jar A2LParser.jar --help
```

```console
usage: A2LParser [-a2l <arg>] [-c <arg>] [-h] [-ij] [-j <arg>] [-jsc]
       [-mj] [-o <arg>]
 -a2l,--asap2 <arg>    Either specify an A2L file or pipe A2L content to
                       convert it to JSON
 -c,--encoding <arg>   Specify the encoding for the output file. e.g.
                       US-ASCII, ISO-8859-1, UTF-8, UTF-16BE, UTF-16LE,
                       UTF-32LE, UTF-32BE, UTF-16
 -h,--help             Prints this help
 -ij,--indentJson      Outputs the JSON with indentation
 -j,--json <arg>       Either specify a JSON file or pipe JSON content to
                       convert it to A2L
 -jsc,--jsonSchema     Outputs the JSON schema for JSON outputs
 -mj,--minJson         Outputs the JSON without null fields
 -o,--output <arg>     Write result to file instead of std out
```

### A2L to JSON

It is possible to either give the parser a file path or stream the content to it through standard input while omitting the file path.

```console
java -jar A2LParser.jar --json src/test/resources/freeTest.a2l
```

Output:

```console
{"a2mlVersion":{"versionNo":1,"upgradeNo":31},"asap2Version":{"versionNo":1,"upgradeNo":71},"project":{
/* [...] */
}}
```

### JSON to A2L

It is possible to either give the parser a file path or stream the content to it through standard input while omitting the file path.

```console
java -jar A2LParser.jar --asap2 src/test/resources/freeTest.json
```

Output:

```console
ASAP2_VERSION 1 71
A2ML_VERSION 1 31
/begin PROJECT Free_Example "MIT licensed example file"
    /begin HEADER "Free Asap2 example file"
        PROJECT_NO Free42
        VERSION "V1.7.1"
    /end HEADER
    [...]
/end PROJECT
```

### JSON Schema

```console
java -jar A2LParser.jar --jsonSchema
```

Output:

```console
{"type":"object","id":"urn:jsonschema:net:alenzen:a2l:Asap2File","properties":
	{
	"a2mlVersion":{"type":"object","id":"urn:jsonschema:net:alenzen:a2l:A2mlVersion","properties":{"versionNo":{"type":"integer"},"upgradeNo":{"type":"integer"}}},
	"asap2Version":{"type":"object","id":"urn:jsonschema:net:alenzen:a2l:Asap2Version","properties":{"versionNo":{"type":"integer"},"upgradeNo":{"type":"integer"}}},
	"project":{ /* [...] */ }
	}
}
```

# Roadmap

This is a rough roadmap for the upcoming things I have in mind. Please create an issue if you like to see a feature being worked on.

* Deploy to https://mvnrepository.com/
* API documentation
* Add more detailed tests for JSON API
* Add a validation API
    * check of unique identifiers
    * valid identifier references
    * valid lower vs upper limit
    * ...
* FORMULA Parser
* fix integer types: ASAM defines int as 2-byte integer and long is a 4-byte integer
    * may speed up the parser
    * use short where int is specified
    * use int where uint is specified
    * use int where long is specified
    * use long where ulong is specified

# Known issues
* does not provide objects for typedef's and instance (I need access to the most recent specification to implement that)

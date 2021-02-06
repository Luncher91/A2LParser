# A2LParser

This library parses an A2L file according the 1.6.1 standards and provides a Java Object structure with all the data.

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

### Print out help

```console
java -jar A2LParser.jar --help
```

```console
usage: A2LParser [-a2l <arg>] [-c <arg>] [-h] [-j <arg>] [-jsc] [-o <arg>]
 -a2l,--asap2 <arg>    Either specify an A2L file or pipe A2L content to
                       convert it to JSON
 -c,--encoding <arg>   Specify the encoding for the output file. e.g.
                       US-ASCII, ISO-8859-1, UTF-8, UTF-16BE, UTF-16LE,
                       UTF-32LE, UTF-32BE, UTF-16
 -h,--help             Prints this help
 -j,--json <arg>       Either specify a JSON file or pipe JSON content to
                       convert it to A2L
 -jsc,--jsonSchema     Outputs the JSON schema for JSON outputs
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

This is a rough roadmap for the upcoming things I have in mind. I might reprioritize the features at any time.

* API documentation
* Add more tests for json API

## ~~Epic 1: basic read functionality~~ (Version 1.0)
* ~~write unit tests~~ (done; over 90% java code coverage of not generated code)

## ~~Epic 2: writing a2l files~~
* ~~generate A2L code from the object structure~~

## Epic 3: handle includes
* support includes
    * ~~include within project block~~
    * include within module block

## ~~Epic 4: library can be used with other languages as well~~
~~This will be archived by creating object structures from the schema and control the parser via command line~~
* ~~generate JSON from it~~
* ~~make an executable which converts a2l to JSON~~
* ~~generate JSON schema from it~~
* ~~add a function that converts JSON to a2l~~

# Notes
I wont bother to support ulong; instead I use long! - An alternative would be to use BigInteger but it would probably make the library slower and consuming more memory.

# Known issues
* does not provide objects for typedef's and instance (need access to the most recent specification)
* does not support include (Epic 3)
* the library treats ulong as long (accepted)

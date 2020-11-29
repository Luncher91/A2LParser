# A2LParser

This library parses an A2L file according the 1.6.1 standards and provides a Java Object structure with all the data.

Sample:
```java
Asap2Parser parser = new Asap2Parser("freeTest.a2l");
		
// optional
parser.setEventHandler((line, position, message) -> { System.err.println("Line " + line + "@" + position + ": " + message); });

Asap2File a2l = parser.parse();
System.out.println("Project: " + a2l.getProject().getName());
```

# Roadmap

This is a rough roadmap for the upcoming things I have in mind. I might reprioritize the features at any time.

* API documentation

## ~~Epic 1: basic read functionality~~ (Version 1.0)
* ~~write unit tests~~ (done; 90.2% java code coverage)

## Epic 2: writing a2l files
* generate A2L code from the object structure

## Epic 3: handle includes
* preprocess the a2l files to perform includes

## Epic 4: library can be used with other languages as well 
This will be archived by creating object structures from the schema and control the parser via command line
* generate JSON from it
* make an executable which converts a2l to JSON
* generate JSON schema from it
* add a function that converts JSON to a2l

# Notes
I wont bother to support ulong; instead I use long! - An alternative would be to use BigInteger but it would probably make the library slower and consuming more memory.

# Known issues
* does not provide objects for typedef's and instance (need access to the most recent specification)
* does not support include (Epic 3)
* the library treats ulong as long (accepted)

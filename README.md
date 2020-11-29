# A2LParser

This library is still in it's early stages.
To get started please have a look at [A2LVisitor.main](https://github.com/Luncher91/A2LParser/blob/master/src/main/java/net/alenzen/a2l/A2LVisitor.java)

The goal is to parse A2L files according the standards and provide a usable object structure to work with.

# Roadmap

This is a rough roadmap for the upcoming things I have in mind. I might reprioritize the features at any time.

* JUnit tests (done)
* demo a2l files under the MIT license terms (done)
* API documentation

## Epic 1: basic read functionality (Version 1.0)
* write unit tests (done; 90.2% java code coverage)

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

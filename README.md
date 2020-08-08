# A2LParser

This library is still in it's early stages.
To get started please have a look at [A2LVisitor.main](https://github.com/Luncher91/A2LParser/blob/master/src/main/java/net/alenzen/a2l/A2LVisitor.java)

The goal is to parse A2L files according the standards and provide a usable object structure to work with.

# ToDo

* JUnit tests
* demo a2l files under the MIT license terms
* API documentation

## 1.0.0: basic read functionality
* write unit tests

## 1.1.0: writing a2l files
* generate A2L code from the object structrue

## 1.2.0: handle includes
* pre process the a2l files to perform includes

## 1.3.0: library can be used with other languages as well 
This will be achived by creating object structures from the schema and control the parser via command line
* generate json from it
* make an executable which converts a2l to json
* generate json schema from it
* add a function that converts json to a2l

# Notes
I wont bother to support ulong; isntead I use long! - An alternative would be to use BigInteger but it would probably make the library slower and consuming more memory.

# Known issues
* does not provide objects for typedef's and instance (needs access to the specification)
* does not support include (yet)
* the library treats ulong as long (accepted)

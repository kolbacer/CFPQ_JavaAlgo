# CFPQ_JavaAlgo
The **CFPQ_JavaAlgo** is a repository for developing *Context-Free Path Querying* algorithms in Java, as well as testing and benchmarking them. The algorithms are based on the [Apache Common Math](https://commons.apache.org/proper/commons-math/index.html) library. At the moment, 1 algorithm has been implemented - *Context-Free Path Querying Algorithm Based on Matrix Multiplication*.

## Prerequisites
- `Java 17+`
- `Maven 3.9.5`

## Project dependencies
- [Apache Commons Math](https://commons.apache.org/proper/commons-math/index.html) 3.6.1 - library containing matrix algebra algorithms
- [JUnit](https://junit.org/junit5/) 5.10.1 - unit testing library
- [JMH](https://github.com/openjdk/jmh) 1.37 - benchmarking library

## Installation
Clone repository:

```shell
git clone https://github.com/kolbacer/CFPQ_JavaAlgo.git
cd CFPQ_JavaAlgo/
```

Build maven project:

```shell
mvn install
```

## Usage
You can run the demo program in the [main](./src/main/) directory, unit tests from the [test](./src/test/) directory or benchmarks located in [benchmark](./src/benchmark/). For example, to run the demo program you need to execute following:
```shell
java -cp target/CFPQ_JavaAlgo-1.0-SNAPSHOT.jar ru.spbu.cfpq.Main
```

## Project structure
```
.
├── LICENSE - MIT license text
├── README.md - project description
├── pom.xml - maven project configuration file
└── src
    ├── benchmark
    │   ├── java/ru/spbu/cfpq
    │   │                └── algorithm/ - algorithm benchmarks
    │   └── resources/ - dataset for benchmark cases
    ├── main
    │   └── java/ru/spbu/cfpq
    │                    ├── Main.java - demo program
    │                    ├── algorithm/ - CFPQ algorithms
    │                    ├── grammar/ - formal grammar representations
    │                    ├── graph/ - graph representations
    │                    └── matrix/ - matrix wrapper classes
    └── test
        └── java/ru/spbu/cfpq
                         └── algorithm/ - unit tests for CFPQ algorithms
```
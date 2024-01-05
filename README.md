# CFPQ_JavaAlgo

[![Maven build & test](https://github.com/kolbacer/CFPQ_JavaAlgo/actions/workflows/maven.yml/badge.svg)](https://github.com/kolbacer/CFPQ_JavaAlgo/actions/workflows/maven.yml)

The **CFPQ_JavaAlgo** is a repository for developing *Context-Free Path Querying* algorithms in Java, as well as testing and benchmarking them. The algorithms are based on the [Apache Common Math](https://commons.apache.org/proper/commons-math/index.html) library. At the moment, 1 algorithm has been implemented - *Context-Free Path Querying Algorithm Based on Matrix Multiplication*.

## Prerequisites
- `Java 17+`
- `Maven 3.9.6`

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
mvn clean install
```

## Usage

### Run tests
```
mvn test
```

### Run demo
```
java -cp target/CFPQ_JavaAlgo-1.0-SNAPSHOT.jar ru.spbu.cfpq.Main
```

### Run benchmarks
Before running becnhmarks, you need to put the data from [CFPQ-on-GPGPU](https://github.com/JetBrains-Research/CFPQ-on-GPGPU) repo into the [src/benchmark/resources/](/src/benchmark/resources) folder. Alternatively, you can configure *benchmarkCase*, *grammarCase* and *graphCase* params in the bechmark class.

Next run the following command:
```
java -cp target/CFPQ_JavaAlgo-1.0-SNAPSHOT.jar ru.spbu.cfpq.algorithm.BenchmarkRunner
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
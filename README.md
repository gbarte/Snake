# CSE2115: Lil' Snake

**Lil' Snake** is a student project for TU Delft's course *CSE2115*, Software Engineering Methods. It was developed by 5 students in 8 weeks. The application is a variation of the classical snake game.

## Table of Contents

- [Getting Started](#getting-started)
  - [Installation](#installation)
  - [Run](#run)

- [Reports](#reports)
  - [Jacoco's Test Coverage](#jacocos-test-coverage)
  - [Checkstyle](#checkstyle)
  - [Pipeline](#pipeline)

- [Built With](#built-with)

- [Developers](#developers)

- [License](#license)

## Getting Started

### Installation

##### SSH

```
// Clone this repository with SSH
$ git@gitlab.ewi.tudelft.nl:cse2115/2019-2020/SN/sem-group-10/template.git
```

##### HTTPS

```
// Clone this repository with HTTPS
$ https://gitlab.ewi.tudelft.nl/cse2115/2019-2020/SN/sem-group-10/template.git
```

### Run

1) Import cloned repo;
    - When asked in Gradle configuration, check `use auto import`
    - `Gradle JVM` choose `12`
2) Run `Main.java`;
    - MacOS may throw an error. In this case, use `-XstartOnFirstThread` VM option for the configuration.
3) Register a new user
4) Use credentials from Step 3 for authentication
5) Use WASD to control the snake.

## Reports

#### Jacoco's Test Coverage:  
Run:
1) `gradle clean` - Optional. Cleans the old data.
2) `gradle test jacocoTestReport` - generates the test coverage report.

#### Checkstyle:  
Run: `gradle checkstyleMain checkstyleTest`

#### SpotBugs:
Run: `gradle spotbugsMain spotbugsTest`

#### PMD:
Run: `gradle pmdMain pmdTest`


## Pipeline:

There is a Gitlabs CI pipeline that contains 3 stages:

- build: `gradle --build-cache assemble`
      
    
- validate:
    - `gradle checkstyleMain checkstyleTest`
    - `gradle spotbugsMain spotbugsTest`
            
- test: `gradle check`


## Built With
- [Gradle](http://gradle.org) - Dependency Management
- [JUnit](https://junit.org/junit5/), [Mockito](https://site.mockito.org) - Unit Tests
- [SQLite](http://sqlite.org) - database

## Developers

* Sanjay Rachwani
* Roman Širokov
* Mirijam Zhang
* Mihai Plotean
* Gabrielė Barteškaitė


## License

This project is licensed under the MIT License (2019) - see the [LICENSE.md](LICENSE.md) file for details

###### Academic year 2018/2019

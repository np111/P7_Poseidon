# Poseidon &middot; [![Build Status](https://travis-ci.com/np111/P7_poseidon.svg?branch=master)](https://travis-ci.com/np111/P7_poseidon) [![codecov.io](https://codecov.io/github/np111/P7_poseidon/coverage.svg?branch=master)](https://codecov.io/github/np111/P7_poseidon?branch=master)

A web-based enterprise software to generate transactions for institutional
investors.

## Getting Started

These instructions will get you a copy of the project up and running on your
local machine for development and testing purposes. See deployment for notes on
how to deploy the project on a live system.

### Prerequisites

- Install **Java 1.8**
  [(link)](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
- Install **Maven 3.6.2**
  [(link)](https://maven.apache.org/install.html)
- Install **Docker** and **docker-compose**

### Running App

Start the database:

```bash
./dev.sh up -d
```

Then compile and run the app:

```bash
mvn package
java -jar target/poseidon-app.jar
```

Then you can optionally seed the database with tests data:

```bash
./dev.sh db-seed
```

### Testing

```bash
./dev.sh up -d
mvn verify
```

## Notes

This is a school project (for OpenClassrooms).

The goal is to fix and add some features to an existing application.

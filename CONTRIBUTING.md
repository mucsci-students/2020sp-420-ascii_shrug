# Contributing to ¯\\\_(ツ)\_/¯ UML Editor
This is a guide for how to contribute to this repository.

## Tools
¯\\\_(ツ)\_/¯ uses [maven](https://maven.apache.org/) and Java 11.
## New Features
Feature branches are forked off of `develop` and generally have a lowerCamelCase name starting with the part of the project that will be modified (such as `gui`, `model`, `repl`, or `controller`) followed by `NameOfFeature`.
### Workflow
1. Make sure you have the most recent `develop`: `git checkout develop && git pull`
2. Checkout a new feature branch: `git checkout -b nameOfFeatureBranch`
3. Write tests for your new feature and commit them.
4. Implement the feature, making sure all tests succeed.
5. Once all tests succeed, open a Pull Request and fill out the template.

## Bug Fixes
Bugfix branches are named `issue<issuenumber>Fix`, follow the same workflow for a feature branch.  
## Testing
¯\\\_(ツ)\_/¯ uses [JUnit](https://junit.org/junit4/javadoc/latest/) to unit test. Tests go in `src/test/java`.

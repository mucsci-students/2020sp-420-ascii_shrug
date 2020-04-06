### ¯\\\_(ツ)\_/¯ UML Editor ¯\\\_(ツ)\_/¯

A basic UML editor with support for maintaining a diagram state and saving and loading diagrams in JSON format. Makes use of jgrapht for the underlying model, jgraphx to draw the model in the GUI, and java swing for the GUI.

#### Building:

1. Install maven and ensure java JDK 11 is installed

2. run `mvn package` to compile

#### Running:
CLI

1. run `java -jar target/uml-VERSION-jar-with-dependencies.jar -t` (replace VERSION with the version number)

GUI

1. run `java -jar target/uml-VERSION-jar-with-dependencies.jar` (replace VERSION with the version number)

#### Usage:

See USERGUIDE.md and help button/command.

#### Contributing:

See CONTRIBUTING.md
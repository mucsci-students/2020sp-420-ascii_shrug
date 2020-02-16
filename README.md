### ¯\\\_(ツ)\_/¯ UML Editor ¯\\\_(ツ)\_/¯

A basic UML editor with support for maintaining a diagram state and saving and loading diagrams in JSON format

#### Building:

1. Install maven

2. run `mvn package` to compile

#### Running:
1. run `java -jar target/uml-VERSION-jar-with-dependencies.jar` (replace VERSION with the version number)

#### Usage:
```
Commands:
add <classname>      : adds classname to the diagram
remove <classname>   : removes classname from the diagram
save <filename>.json : save the diagram in a specified json file (will overwrite filename.json if it exists)
load <filename>.json : load the diagram stored in specified json file
print                : prints the current diagram
exit                 : exit the editor (no warning for unsaved diagram)
```

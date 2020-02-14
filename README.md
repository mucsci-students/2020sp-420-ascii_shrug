### ¯\\\_(ツ)\_/¯ UML Editor ¯\\\_(ツ)\_/¯ 
#### Building:

1. Install maven

2. run `mvn package` to compile

#### Running:
1. run `java -cp target/uml-VERSION-jar-with-dependencies.jar Repl`


#### Usage:
```
Commands:
add <classname>      : adds classname to the diagram
remove <classname>   : removes classname from the diagram
save <filename>.json : save the diagram in a specified json file
load <filename>.json : load the diagram stored in specified json file
print                : prints the current diagram
exit                 : exit the editor (no warning for unsaved diagram)
```

### ¯\\\_(ツ)\_/¯ UML Editor ¯\\\_(ツ)\_/¯ 
#### Building:

1. Install maven

2. run `mvn package` to compile

#### Running:
1. run `java -cp target/uml-VERSION-jar-with-dependencies.jar Repl`


#### Usage:
```
Commands:
add <classname>      : adds classname to the diagram\n" +
remove <classname>   : removes classname from the diagram\n" +
save <filename>.json : save the diagram in a specified json file\n" +
load <filename>.json : load the diagram stored in specified json file\n" +
print                : prints the current diagram\n" +
exit                 : exit the editor (no warning for unsaved diagram)
```

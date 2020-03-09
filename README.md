### ¯\\\_(ツ)\_/¯ UML Editor ¯\\\_(ツ)\_/¯

A basic UML editor with support for maintaining a diagram state and saving and loading diagrams in JSON format

#### Building:

1. Install maven

2. run `mvn package` to compile

#### Running:
CLI

1. run `java -jar target/uml-VERSION-jar-with-dependencies.jar -t` (replace VERSION with the version number)

GUI

1. run `java -jar target/uml-VERSION-jar-with-dependencies.jar` (replace VERSION with the version number)

#### Usage:

#### CLI
```

¯\_(ツ)_/¯ UML Editor Help ¯\_(ツ)_/¯

Commands:

add <classname> [options]     : adds classname to the diagram

remove <classname>  [options] : removes classname from the diagram

save <filename>.json          : save the diagram in a specified json file

load <filename>.json          : load the diagram stored in specified json file

print                         : prints the current diagram

exit                          : exit the editor (no warning for unsaved diagram)


Options:


"-a" [ID]                     : will add all ID as attributes of classname

"-r" [ID]                     : will create relationships from classname to all ID``

```

#### GUI

-To add a class, click add and supply a valid name.

-To remove a class, click remove and supply a valid name of a class in the diagram.

-To edit a class, click edit, supply a name. Then provide the attributes to add and the 
 attributes to remove.

-To save a diagram, click save and enter a file name.

-To load a diagram, click load and enter a file name.

#### Quirks:

- Able to modify attributes in GUI, they will not display.

- Relationships made by hovering and dragging from one class to another
  will not modify the model. 

- Classes can be double clicked to change the representation. The mode is
  not changed.

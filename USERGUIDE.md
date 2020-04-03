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

- To add a class, click Add Class and supply a valid name.

- To remove a class, click Remove Class and supply a valid name of a class in the diagram.

- To edit a class, click Edit, supply a name. Then provide the attributes to add and the 
 attributes to remove.
 
- To add a relationship, click Add Relation and supply the source class followed by the destination class followed by the type.
 Valid types are Aggregation, Composition, Association, Generalization, and None (Case sensitive).
 
- To remove a relationship, click Remove Relation and supply the source class followed by the destination class.

- To save a diagram, click file -> save and enter a .json file name.

- To load a diagram, click file -> load and enter a .json file name.

- To move a class around, click and drag on it making sure that there is no green border as that will create an edge.

#### Quirks:

- Relationships made by hovering and dragging from one class to another
  will not modify the model. 

- Classes can be double clicked to change the representation. The model is
  not changed.

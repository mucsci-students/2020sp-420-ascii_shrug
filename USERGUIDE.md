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

undo                          : reverses the most recent command

exit                          : exit the editor (no warning for unsaved diagram)


Options:


"-a" [Field | Method]         : will add all Fields and Methods to the UML class with classname

"-r" RelType [ID]             : will create relationships from classname to all ID with type RelType``

(Note that removing relationships does not requre a type)

Fields, Methods, and Relationship types


Fields are of the form         : ('short' | 'int' | 'long' | 'float' | 'double' | 'char' | 'bool') ID

Methods are of the form        : Field '('{Field}')'

Relationships are of the form  : 'Aggregation' | 'Composition' | 'Generalization' | 'Association'

```

#### GUI

- To add a class, click Add Class and supply a valid name.

- To remove a class, click Remove Class and supply a valid name of a class in the diagram.

- To edit a class, click Edit, and supply a name. Then provide the attributes to add and the 
 attributes to remove. These attribute lists are comma delimited.
 Field attributes are of form `type id` and method attributes are of form `type id (type id, type id, ...)`.
 
- To add a relationship, click Add Relation and supply the source class followed by the destination class followed by the type.
 Valid types are `Aggregation`, `Composition`, `Association`, `Generalization`, and `None` (Case sensitive).
 
- To remove a relationship, click Remove Relation and supply the source class followed by the destination class.

- To save a diagram, click file -> save and enter a .json file name.

- To load a diagram, click file -> load and enter a .json file name.

- To move a class around, click and drag on it making sure that there is no solid green border as that will create an edge.

#### Quirks:

- Relationships made by hovering and dragging from one class to another
  will not modify the model. 

- Classes and edges can be double clicked to change the representation. The model is
  not changed.

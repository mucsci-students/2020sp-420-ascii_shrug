I had some wacky java issues getting this to compile. Here are some things that I found you guys might find useful:
1. You can't straight-up import source files in java
2. To include a source file, you make a package. In this case I called the package shrugUML with two class source files in it: shrugUMLClass.java and shrugUMLDiagram.java
3. You can include the package like any other java import libary: import packagename.classname
4. To compile. I ran javac -d shrugUML UMLClassTest.java

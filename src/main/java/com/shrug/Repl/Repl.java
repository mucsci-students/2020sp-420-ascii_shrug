/* Filename: Repl.java
 * Primary Author: Jack Fazio
 * Description: Text view of UML editor
 */

package com.shrug.Repl;

import Controller.*;
import Command.*;
import com.shrug.Parser.*;
import java.util.*;
import shrugUML.*;

public class Repl {
  public static Scanner scan = new Scanner(System.in);
  public static Controller control = new Controller();

  public Repl() {
    run();
  }

  /*
   *Method: parseLine ()
   * Precondition: Repl exists and stdin is valid
   * Postcondition: returns an array with commands parsed into
   * an array by space.
   */
  public static void run() {
    printHelp();
    while (true) { 
      System.out.print("-> "); 
      execute(parseLine(scan.nextLine()));
      continue;
    }
  }

  /*
   *Method: parseLine ()
   * Precondition: Repl exists and stdin is valid
   * Postcondition: returns an array with commands parsed into
   * an array by space.
   */
  public static ArrayList<String> parseLine(String line) {
    return new ArrayList<String>(Arrays.asList(line.trim().split("\\s+")));
  }

  /*
   * Method: execute (String[] cmds)
   * cmds is a list of the arguments on the command line, space delimited.
   * Precondition: Repl is initialized.
   * Postcondition: An action occurs to load, save, or modify the state of a UML diagram.
   */
  public static boolean execute(ArrayList<String> cmds) {
    if (cmds.size() > 1)
    {
      switch (cmds.get(0).toLowerCase()) {
        case "add":
        {
          Command command = build(cmds);
          if (command == null)
            return false;
          control.execute(new AddCommand(command));
          return true;
        }
        case "remove":
        {
          Command command = build(cmds);
          if (command == null)
            return false;
          control.execute(new RemoveCommand(command));
          return true;
        }
        case "save":
          return control.save(cmds.get(1));
        case "load":
          return control.load(cmds.get(1));
        case "":
          return true;
        case "help":
          {
            printHelp();
            return true;
          }
        default:
          {
            System.out.println("Error: Invalid Command (type 'help' for valid commands)");
            return false;
          }
      }
    }
    else
    {
      switch (cmds.get(0).toLowerCase()) {
        case "exit":
          return exit ();
        case "undo":
          control.undo();
          return true;
        case "":
          return true;
        case "print":
          {
            printDiagram();
            return true;
          }
        default:
        {
          System.out.println("Error: Invalid Command (type 'help' for valid commands)");
          return false;
        }
      }
    }

  }

  /* Function: printDiagram ()
   * Precondition: control is instantiated
   * Postcondition: The diagram is printed
   */
  public static void printDiagram() {
    for (ShrugUMLClass c : control.getClasses()) {
      String s = "Name: " + c.getName() + "\nFields: ";

      for (String attr : c.getAttributes()) 
        s += attr + ", ";

      if (s.charAt(s.length() - 2) == ',')
        s = s.substring(0, s.length() - 2);

      s += "\nMethods: ";

      for (String attr : c.getMethods()) 
        s += attr + ", ";

      if (s.charAt(s.length() - 2) == ',')
        s = s.substring(0, s.length() - 2);

      s += "\nRelationships: ";

      String relationships = control.getGraph().outgoingEdgesOf(c).toString().replace("[", "").replace("]", "").replace(":", "->");
      System.out.println(s + relationships + "\n");
    }
  }

  /* Function: printHelp ()
   * Precondition: None
   * Postcondition: help command is printed
   */
  public static void printHelp() {
    System.out.println(
        "¯\\_(ツ)_/¯ UML Editor Help ¯\\_(ツ)_/¯\n\n"
            + "Commands:\n"
            + "add <classname> [options]     : adds classname to the diagram\n"
            + "remove <classname>  [options] : removes classname from the diagram\n"
            + "save <filename>.json          : save the diagram in a specified json file\n"
            + "load <filename>.json          : load the diagram stored in specified json file\n"
            + "print                         : prints the current diagram\n"
            + "exit                          : exit the editor (no warning for unsaved diagram)\n"
            + "undo                          : reverses the most recent command\n\n"
            + "Options:\n\n"
            + "\"-a\" [Field | Method]       : will edit the Fields and Methods to classname\n"
            + "\"-r\" RelType [ID]           : will edit relationships from classname to all ID\n"
            + "(Note that removing relationships does not requre a type)\n\n"
            + "Fields, Methods, and Relationship Types:\n\n"
            + "Fields are of the form         : ('short' | 'int' | 'long' | 'float' | 'double' | 'char' | 'bool') ID\n"
            + "Methods are of the form        : Field '('{Field',}')'\n"
            + "Relationships are of the form  : 'Aggregation' | 'Composition' | 'Generalization' | 'Association'\n\n");
  }

  /* Function: build ()
   * precondition: repl is instantiated with an associated controller and model.
   * postcondition: the command is constructed with attributes and returned
   */
  public static Command build(ArrayList<String> cmds) {

    if (!Controller.isJavaID(cmds.get(1)))
    {
      System.out.println("Invalid ID");
      return null;
    }

    Command command = new Command (parseAttributes(cmds));
    command.setClassName (cmds.get(1));
    command.setRelationships (parseRelationships(cmds));


    return command;
  }


  /* Function: parse ()
   * precondition: repl is instantiated with an associated controller.
   * postcondition: returns an arraylist of the attributes
  */
  public static Command parseAttributes (ArrayList<String> cmds) {
    
    String attributes = "";
    // parse attribute arguments
    if (cmds.contains("-a")) 
      for (int i = cmds.indexOf("-a") + 1; i != cmds.indexOf("-r") && i != cmds.size(); ++i) 
        attributes = attributes.concat (" " + cmds.get(i));

    Parser p = new Parser(attributes);
    return p.parse();       
  }

  /* Function: parse ()
   * precondition: repl is instantiated with an associated controller.
   * postcondition: returns an arraylist  of the relationships
  */
  public static HashMap<String, RType> parseRelationships (ArrayList<String> cmds) {
    
    HashMap<String, RType> relationships = new HashMap<String, RType> ();
    
    RType relType = RType.None;

    // -r RType [destinationVertex]
    if (cmds.contains("-r")) 
    {
      if (cmds.get (0).equals("add"))
        relType = RType.valueOf(cmds.get(cmds.indexOf("-r") + 1).trim());
      for (int i = cmds.indexOf("-r") + 2; i != cmds.indexOf("-a") && i != cmds.size(); ++i) 
      {
        if (Controller.isJavaID(cmds.get(i)))
          relationships.put (cmds.get(i), relType);
        else 
          System.out.println(cmds.get(i) + "is not a valid Java ID");
      }
    }

    return relationships;
  }

  /* Function: exit ()
   * precondition: program is running and exit is entered into Repl.
   * postcondition: program is terminated.
   */
  public static boolean exit() {
    if (!control.noUndo())
    {
      System.out.println("You have unsaved changes!");
      System.out.println("Type y if you want discard changes, n otherwise.");

      if (!scan.next().equals("y")) 
      {
        return true;
      }
    }
    System.exit(0);
    return true;
  }

}

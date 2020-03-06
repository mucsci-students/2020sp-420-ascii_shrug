/* Filename: Repl.java
 * Primary Author: Jack Fazio
 * Description: Text view of UML editor
 */

package Repl;

import Controller.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import shrugUML.*;

public class Repl {
  private static Scanner scan = new Scanner(System.in);
  private static Controller control = new Controller();

  public Repl() {
    run();
  }

  private static void run() {
    printHelp();
    while (true) {
      System.out.print("-> ");
      execute(parseLine());
      continue;
    }
  }

  /*
   *Method: parseLine ()
   * Precondition: Repl exists and stdin is valid
   * Postcondition: returns an array with commands parsed into
   * an array by space.
   */
  private static ArrayList<String> parseLine() {
    return new ArrayList<String>(Arrays.asList(scan.nextLine().trim().split("\\s+")));
  }

  /*
   * Method: execute (String[] cmds)
   * cmds is a list of the arguments on the command line, space delimited.
   * Precondition: Repl is initialized.
   * Postcondition: An action occurs to load, save, or modify the state of a UML diagram.
   */
  private static boolean execute(ArrayList<String> cmds) {
    switch (cmds.get(0).toLowerCase()) {
      case "add":
        return add(cmds);
      case "remove":
        return remove(cmds);
      case "save":
        return control.save(cmds.get(1));
      case "load":
        return control.load(cmds.get(1));
      case "print":
        {
          printDiagram();
          return true;
        }
      case "exit":
        return exit();
      case "help":
        {
          printHelp();
          return true;
        }
      case "":
        return true;
      default:
        {
          System.out.println("Error: Invalid Command (type 'help' for valid commands)");
          return false;
        }
    }
  }

  /* Function: printDiagram ()
   * Precondition: control is instantiated
   * Postcondition: The diagram is printed
   */
  private static void printDiagram() {
    for (ShrugUMLClass c : control.getClasses()) {
      String s = "Name: " + c.getName() + "\nAttributes: ";

      for (String attr : c.getAttributes()) 
        s += attr + ", ";

      if (s.charAt(s.length() - 2) == ',')
        s = s.substring(0, s.length() - 2);

      s += "\nRelationships: ";

      String relationships = control.getGraph().outgoingEdgesOf(c).toString().replace("[", "").replace("]", "");
      System.out.println(s + relationships + "\n");
    }
  }

  /* Function: printHelp ()
   * Precondition: None
   * Postcondition: help command is printed
   */
  private static void printHelp() {
    System.out.println(
        "¯\\_(ツ)_/¯ UML Editor Help ¯\\_(ツ)_/¯\n\n"
            + "Commands:\n"
            + "add <classname> [options]     : adds classname to the diagram\n"
            + "remove <classname>  [options] : removes classname from the diagram\n"
            + "save <filename>.json          : save the diagram in a specified json file\n"
            + "load <filename>.json          : load the diagram stored in specified json file\n"
            + "print                         : prints the current diagram\n"
            + "exit                          : exit the editor (no warning for unsaved diagram)\n\n"
            + "Options:\n\n"
            + "\"-a\" [ID]                     : will add all ID as attributes of classname\n"
            + "\"-r\" [ID]                     : will create relationships from classname to all ID\n");
          
  }

  /* Function: add ()
   * precondition: repl is instantiated with an associated controller.
   * postcondition: the UML object is added to the diagram.
   */
  private static boolean add(ArrayList<String> cmds) {

    ArrayList<String> relationships = parseRelationships(cmds);
    ArrayList<String> attributes = parseAttributes(cmds);
    String name = cmds.get(1);
    control.addClass(name);

    if (!attributes.isEmpty())
      control.addAttributes(name, attributes);
    if(!relationships.isEmpty())
      control.addRelationships(name, relationships);

    return true;
  }


  /* Function: remove ()
   * precondition: repl is instantiated with an associated controller.
   * postcondition: the UML object is removed from the diagram.
   */
  private static boolean remove(ArrayList<String> cmds) {

    ArrayList<String> relationships = parseRelationships(cmds);
    ArrayList<String> attributes = parseAttributes(cmds);
    String name = cmds.get(1);

    if (control.contains(name)){
      if (!attributes.isEmpty())
        control.removeAttributes(name, attributes);
      if(!relationships.isEmpty())
        control.removeRelationships(name, relationships);
    }
    else {
      System.out.println(name + " is not in the diagram.\n");
    }

    return true;
  }

  /* Function: parse ()
   * precondition: repl is instantiated with an associated controller.
   * postcondition: returns an arraylist of the attributes
  */
  private static ArrayList<String> parseAttributes (ArrayList<String> cmds) {
    
    ArrayList<String> attributes = new ArrayList<String>();
    // parse attribute arguments
    if (cmds.contains("-a")) 
      for (int i = cmds.indexOf("-a") + 1; i != cmds.indexOf("-r") && i != cmds.size(); ++i) 
        attributes.add(cmds.get(i));

    return attributes;        
  }

  /* Function: parse ()
   * precondition: repl is instantiated with an associated controller.
   * postcondition: returns an arraylist  of the relationships
  */
  private static ArrayList<String> parseRelationships (ArrayList<String> cmds) {
    
    ArrayList<String> relationships = new ArrayList<String>();
    // parse relationship arguments
    if (cmds.contains("-r")) 
      for (int i = cmds.indexOf("-r") + 1; i != cmds.indexOf("-a") && i != cmds.size(); ++i) 
        relationships.add(cmds.get(i));

    return relationships;        
  }

  /* Function: exit ()
   * precondition: program is running and exit is entered into Repl.
   * postcondition: program is terminated.
   */
  private static boolean exit() {
    System.exit(0);
    return true;
  }
}

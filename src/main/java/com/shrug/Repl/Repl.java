// repl.java
// Author:
import Controller.*;
import java.util.Scanner;
import java.util.regex.*;
import shrugUML.*;

public class Repl {
  private static Scanner scan = new Scanner(System.in);
  private static Controller control = new Controller();
   
  public static void main (String[] args)
  {
    while (true)
      {
	execute (parseLine ());
	continue; 
      }
  } 
   
  /*
    Method: parseLine ()

    Precondition: Repl exists and stdin is valid

    Postcondition: returns an array with commands parsed into 
    an array by space. 
  */
  private static String[] parseLine ()
  {
    System.out.print ("-> ");
    String command = scan.nextLine ();
    return command.split(" ", 0);
  }

  /*
    Method: execute (String[] cmds)
    cmds is a list of the arguments on the command line, space delimited.

    Precondition: Repl is initialized.

    Postcondition: An action occurs to load, save, or modify the state of a UML diagram. 
  */
  private static boolean execute (String[] cmds)
  {
    // in each case 
    switch (cmds[0].toLowerCase ())
      {
      case "add":
	{
	  // Store result in case of error
	  boolean result = control.add (cmds[1]);
	  if (!result)
	    {
	      System.out.println ("Error: " + cmds[1] + " is an invalid name");
	    }
	  return result;
	}
      case "remove":
	{
	  // Store result in case of error
	  boolean result = control.remove (cmds[1]);
	  if (!result)
	    {	   
	      System.out.println ("Error: " + cmds[1] + " is an invalid name");
	    }
	  return result;
	}        
      case "save":
	return control.save (cmds[1]);
      case "load":
	return control.load (cmds[1]);
      case "print":
	{
	  printDiagram ();
	  return true;
	}
      case "exit":
	return exit ();
      case "help":
	printHelp ();
	return true;
      case "":
	return true;
      default:
	{
	  System.out.println ("Error: Invalid Command");
	  return false;
	}
      }
  }

  /* Function: printDiagram ()
   * Precondition: control is instantiated
   * Postcondition: The diagram is printed
   */
  private static void printDiagram ()
  {
    for (ShrugUMLClass c : control.getClasses ())
      System.out.println (c.getName ());
  }
  
  /* Function: printHelp ()
   * Precondition: None
   * Postcondition: help command is printed
   */
  private static void printHelp ()
  {
    System.out.println ("¯\\_(ツ)_/¯ UML Editor Help ¯\\_(ツ)_/¯\n" +
			"Commands:\n\n" +
			"add <classname>      : adds classname to the diagram\n" +
			"remove <classname>   : removes classname from the diagram\n" +
			"save <filename>.json : save the diagram in a specified json file\n" +
			"load <filename>.json : load the diagram stored in specified json file\n" +
			"print                : prints the current diagram\n" +
			"exit                 : exit the editor (no warning for unsaved diagram)\n");
    
  }
  
  /* Function: exit () 
   * precondition: program is running and exit is entered into Repl.
   * postcondition: program is terminated.
   */
  private static boolean exit () 
  {
    System.exit (0);
    return true;
  }
    
>>>>>>> develop
}

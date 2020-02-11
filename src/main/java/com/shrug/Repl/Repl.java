// repl.java
// Author: 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;
import Controller.*;
import shrugUML.*;

public class Repl
{
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
   
   // parses the current line and returns it to the main loop space 
   // delimited into an array for execution
   private static String[] parseLine ()
   {
    String command = scan.nextLine ();
    return command.split(" ", 0);
   }

   // encapsulate logic for choosing what action must be taken. 
   private static boolean execute (String[] cmds)
   {
     // in each case 
     switch (cmds[0])
     {
       case "add":
         return control.add (cmds[1]);
       case "remove":
         return control.remove (cmds[1]);
       case "save":
         return control.save (cmds[1]);
       case "load":
         return control.load (cmds[1]);
       case "print":
       {
         for (ShrugUMLClass c : control.getClasses ())
            System.out.println (c.getName ());
         return true;
       }
       case "exit":
         return exit ();
       case "":
         return true;
       default:
       {
         System.out.println ("Invalid Command.");
         return false;
       }
     }
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
    
}

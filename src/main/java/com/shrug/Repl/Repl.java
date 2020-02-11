// repl.java
// Author: 
import java.util.Scanner;
import java.util.regex.Pattern;
import Controller.*;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    String[] cmds = command.split(" ", 0);
    return cmds;
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
       case "exit":
         return exit ();
       default:
         return false;
     }
   }

   // TODO
   private static boolean exit () 
   {

     return false;
   }
    
}

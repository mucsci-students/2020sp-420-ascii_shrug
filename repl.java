// repl.java
// Author: 
import java.util.Scanner;
import java.util.regex.Pattern;

public class repl
{
  private Scanner scan = new Scanner(System.in);  
   // refactor st a UML editor (actor) does the adding, removing, loading, and saving ON our data representation.
   
   public void main (String[] args)
   {
     while (true)
     {
       String[] cmds = parseLine ();
       execute (cmds);
       continue; 
     }
   } 
   
   // parses the current line and returns it to the main loop space 
   // delimited into an array for execution
   private String[] parseLine ()
   {
    String command = scan.nextLine ();
    String[] cmds = command.split(" ", 0);
    if (cmds[0].equals("add") || cmds[0].equals("remove") ||
        cmds[0].equals("load") || cmds[0].equals("exit"))
    {
      return cmds;
    }
    return cmds;
   }

   // encapsulate logic for choosing what action must be taken. 
   private boolean execute (String[] cmds)
   {
     boolean success = false;
      
     // in each case, assign 
     switch (cmds[0])
     {
       case "add":
         // use adder
         break;
       case "remove":
         // use remover
         break;
       case "save":
         // use save
         break;
       case "load":
         // use load
         break;
       default: 
         success = false;
     }
     
     return success;
   }
    
}

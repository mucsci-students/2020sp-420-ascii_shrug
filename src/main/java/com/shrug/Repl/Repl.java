// repl.java
// Author: 
import java.util.Scanner;
import java.util.regex.Pattern;
import Controller.*;

public class Repl
{
  private Scanner scan = new Scanner(System.in);  
  private Controller control = new Controller();
   
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
     }
     return false;
   }
    
}

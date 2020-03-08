/* Filename: Main.java
 * Primary Author: Jack Fazio
 * Description: Entry point for the program. Selects view
 */
import GUI.*;
import Repl.*;

// import javafx.application.Application;

public class Main {

  public static void main(String[] args) {
    Repl r;
    GUI g;
    if (args.length > 0 && args[0].equals("-t")) r = new Repl();
    else g = new GUI();
  }
}

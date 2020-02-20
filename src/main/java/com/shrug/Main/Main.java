/* Filename: Main.java
 * Primary Author: Jack Fazio
 * Description: Entry point for the program. Selects view
 */
import Repl.*;

public class Main {

  public static void main(String[] args) {
    Repl r;
    if (args.length > 0 && args[0].equals("-t")) r = new Repl();
    else System.out.println("gui");
  }
}

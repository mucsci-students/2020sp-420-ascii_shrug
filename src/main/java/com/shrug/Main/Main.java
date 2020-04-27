/* Filename: Main.java
 * Primary Author: Jack Fazio
 * Description: Entry point for the program. Selects view
 */
import GUI.*;
import com.shrug.Repl.*;
import org.jline.reader.*;
import org.jline.terminal.*;

// import javafx.application.Application;

public class Main {

  public static void main(String[] args) {
    Repl r;
    GUI g;
    if (args.length > 0 && args[0].equals("-t")) {
      try {
        r = new Repl(TerminalBuilder.terminal());
      } catch (Exception e) {
      }
    } else g = new GUI();
  }
}

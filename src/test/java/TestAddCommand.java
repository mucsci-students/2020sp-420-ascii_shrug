
import static org.junit.Assert.*;

import Command.*;
import java.util.*;
import org.junit.*;

public class TestAddCommand {

  @Test
  public void testConstuctor() {
    ArrayList<String> fields = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    String className = "class";
    AddCommand add = new AddCommand(className, fields, methods);

    assertEquals (add.getFields(), fields);
    assertEquals (add.getMethods(), methods);
    assertEquals (add.getClassName(), className);
  }

  @Test
  public void testInvert() {
    ArrayList<String> fields = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    String className = "class";
    AddCommand add = new AddCommand(className, fields, methods);
    RemoveCommand undo = add.invert ();

    assertEquals (undo.getFields(), add.getFields());
    assertEquals (undo.getMethods(), add.getMethods());
    assertEquals (undo.getClassName(), add.getClassName());
  }

}

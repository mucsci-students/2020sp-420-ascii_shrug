
import static org.junit.Assert.*;

import Command.*;
import java.util.*;
import org.junit.*;

public class TestAddCommand {

  @Test
  public void testConstuctor() {
    ArrayList<String> fields = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    AddCommand add = new AddCommand(fields, methods);

    assertEquals (add.getFields(), fields);
    assertEquals (add.getMethods(), methods);
  }

  @Test
  public void testInvert() {
    ArrayList<String> fields = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    AddCommand add = new AddCommand(fields, methods);
    RemoveCommand undo = add.invert ();
    RemoveCommand remove = new RemoveCommand(fields, methods);

    

    assertEquals (undo.getFields(), remove.getFields());
    assertEquals (undo.getMethods(), remove.getMethods());
  }

}

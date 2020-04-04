
import static org.junit.Assert.*;

import Command.*;
import java.util.*;
import org.junit.*;

public class TestRemoveCommand {

  @Test
  public void testConstuctor() {
    ArrayList<String> fields = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    String className = "class";
    RemoveCommand remove = new RemoveCommand(className, fields, methods);

    assertEquals (remove.getFields(), fields);
    assertEquals (remove.getMethods(), methods);
    assertEquals (remove.getClassName(), className);
  }

  @Test
  public void testInvert() {
    ArrayList<String> fields = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    String className = "class";
    RemoveCommand remove = new RemoveCommand(className, fields, methods);
    AddCommand undo = remove.invert ();

    assertEquals (undo.getFields(), remove.getFields());
    assertEquals (undo.getMethods(), remove.getMethods());
  }
}

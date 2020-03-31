
import static org.junit.Assert.*;

import Command.*;
import java.util.*;
import org.junit.*;

public class TestRemoveCommand {

  @Test
  public void testConstuctor() {
    ArrayList<String> fields = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    RemoveCommand remove = new RemoveCommand(fields, methods);

    assertEquals (remove.getFields(), fields);
    assertEquals (remove.getMethods(), methods);
  }

  @Test
  public void testInvert() {
    ArrayList<String> fields = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    RemoveCommand remove = new RemoveCommand(fields, methods);
    AddCommand undo = remove.invert ();
    AddCommand add = new AddCommand(fields, methods);

    assertEquals (undo.getFields(), add.getFields());
    assertEquals (undo.getMethods(), add.getMethods());
  }
}

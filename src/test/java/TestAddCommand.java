
import static org.junit.Assert.*;

import Command.*;
import java.util.ArrayList;

public class testAddCommand {

  @Test
  public void testConstuctor() {
    ArrayList<String> fields = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    AddCommand add = new AddCommand(fields, methods);
    RemoveCommand remove = new RemoveCommand(fields, methods);

    add.m_fields = fields;
    add.m_methods = methods;

    add.invert() = remove;
  }
}

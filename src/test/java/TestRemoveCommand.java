
import static org.junit.Assert.*;

import Command.*;
import java.util.ArrayList;

public class testRemoveCommand {

  @Test
  public void testConstuctor() {
    ArrayList<String> fields = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c")); 
    RemoveCommand remove = new RemoveCommand(fields, methods);
    AddCommand add = new AddCommand(fields, methods);

    remove.m_fields = fields;
    remove.m_methods = methods;

    remove.invert() = add;
  }
}

//Author John fazio

import static org.junit.Assert.*;

import com.shrug.Attribute.*;
import java.util.*;
import org.junit.*;

public class TestMethod {

  @Test
  public void testConstructor ()
  {
    String type = "object";
    String name = "id";
    ArrayList<Field> parameters = new ArrayList<Field>(Arrays.asList(new Field ("a_t", "a_id") , new Field ("b_t", "b_id"), new Field ("c_t", "c_id"))); 
    Method routine = new Method(type, name, parameters);
    assertEquals(type, routine.getReturnType());
    assertEquals(name, routine.getName());
    assertEquals(parameters, routine.getParameters());
  }
}

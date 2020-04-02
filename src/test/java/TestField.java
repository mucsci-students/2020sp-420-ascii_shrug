// Author: John Fazio
//
import static org.junit.Assert.*;

import Attribute.*;
import java.util.*;
import org.junit.*;


public class TestField {

  @Test
  public void testConstructor ()
  {
    String type = "object";
    String name = "id";
    Field variable = new Field(type, name);
    assertEquals(type, variable.getType());
    assertEquals(name, variable.getName());
  }
}

import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;
import shrugUML.*;
import com.shrug.Parser.*;
import com.shrug.AST.*;
import Command.*;

public class TestParser {
  
  @Test
  public void TestConstructor () {
    assertNotNull (new Parser ("int a"));
  }

  @Test 
  public void testParse () {
    String s = "int a, int area (double pi, double rad), char c";
    ArrayList<String> fields = new ArrayList<String>(
                         Arrays.asList("int a", "char c"));
    ArrayList<String> methods = new ArrayList<String>(
                         Arrays.asList("int area (double pi, double rad)"));

    Parser p = new Parser(s);
    Command command = p.parse();

    assertEquals (fields, command.getFields());
    assertEquals (methods, command.getMethods());
  }

}

import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;
import shrugUML.*;
import com.shrug.Repl.*;

public class TestRepl {

  @Test
  public void testParseLine ()
  {
    String testCmd = "add classA -a a1 -r r1";
    ArrayList<String> test = new ArrayList<String>();
    test.add("add");
    test.add("classA");
    test.add("-a");
    test.add("a1");
    test.add("-r");
    test.add("r1");
    assertEquals(Repl.parseLine (testCmd), test);
  }


}


import static org.junit.Assert.*;

import java.util.*;
import Command.*;
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

  @Test
  public void testParseAttributes() {
    ArrayList<String> test = new ArrayList<String>(Arrays.asList("add", "classA", "-a", "int", "a"));

    assertEquals(Arrays.asList("int a"), Repl.parseAttributes(test).getFields());
  }

  @Test
  public void testParseRelationships() {
    ArrayList<String> test = new ArrayList<String>(Arrays.asList("add", "classA", "-r", "Aggregation", "classB"));
    HashMap<String, RType> rels = new HashMap<String, RType> ();
    rels.put ("classB", RType.Aggregation);

    assertEquals(rels, Repl.parseRelationships(test));
  }

  @Test
  public void testBuild() {
    ArrayList<String> test = new ArrayList<String>(Arrays.asList("add", "classA", "-a", "int", "a", "-r", "Aggregation", "classB"));

    HashMap<String, RType> rels = new HashMap<String, RType> ();
    rels.put ("classB", RType.Aggregation);

    Command command = Repl.build(test);
    assertEquals(Arrays.asList("int a"), command.getFields());
    assertEquals(rels, command.getRelationships());
  }
}


import static org.junit.Assert.*;

import Command.*;
import com.shrug.Repl.*;
import java.util.*;
import org.junit.*;
import shrugUML.*;

public class TestRepl {

  @Test
  public void testBuild() {
    Repl repl = new Repl();
    ArrayList<String> test =
        new ArrayList<String>(
            Arrays.asList("add", "classA", "-a", "int", "a", "-r", "Aggregation", "classB"));

    HashMap<String, RType> rels = new HashMap<String, RType>();
    rels.put("classB", RType.Aggregation);

    Command command = repl.build(test);
    assertEquals(Arrays.asList("int a"), command.getFields());
    assertEquals(rels, command.getRelationships());
  }
}

import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;
import com.shrug.shrugUML.*;
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
  public void testParseAttribute()
  {
    String testCmd = "add classA -a a1 a2 -r r1";
    ArrayList<String> test = new ArrayList<String>();
    test.add("a1");
    test.add("a2");
    assertEquals(Repl.parseAttributes(Repl.parseLine(testCmd)), test);
  }

  @Test
  public void testParseAttributeFilter()
  {
    String testCmd = "add classA -a a1 a2 !a -r r1";
    assertFalse(Repl.parseAttributes(Repl.parseLine(testCmd)).contains("!a"));
  }

  @Test
  public void testParseRelationship()
  {
    String testCmd = "add classA -a a1  -r r1 r2";
    ArrayList<String> test = new ArrayList<String>();
    test.add("r1");
    test.add("r2");
    assertEquals(Repl.parseRelationships (Repl.parseLine(testCmd)), test);
  }

  @Test
  public void testParseRelationshipFilter()
  {
    String testCmd = "add classA -a a1 -r r1 r2 !r";
    assertFalse(Repl.parseRelationships(Repl.parseLine(testCmd)).contains("!r"));
  }

}


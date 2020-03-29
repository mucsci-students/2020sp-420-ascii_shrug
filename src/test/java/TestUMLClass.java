import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;
import com.shrug.shrugUML.*;

public class TestUMLClass {

  @Test
  public void testClassNameCtor() {
    ShrugUMLClass myClass = new ShrugUMLClass("a");
    assertEquals(myClass.getName(), "a");
  }

  @Test
  public void testClassArrayListCtor() {
    String name = "class";
    ArrayList<String> attrs = new ArrayList<String>(Arrays.asList("a", "b", "c"));
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c"));
    ShrugUMLClass c = new ShrugUMLClass(name, attrs, methods);
    assertNotNull(c);
  }

  @Test
  public void testClassHashSetCtor() {
    String name = "class";
    HashSet<String> attrs = new HashSet<String>(Arrays.asList("a", "b", "c"));
    HashSet<String> methods = new HashSet<String>(Arrays.asList("a", "b", "c"));
    ShrugUMLClass c = new ShrugUMLClass(name, attrs, methods);
    assertNotNull(c);
  }

  @Test
  public void testGetName() {
    ShrugUMLClass myClass = new ShrugUMLClass("a");
    assertEquals(myClass.getName(), "a");
  }

  @Test
  public void testSetName() {
    ShrugUMLClass myClass = new ShrugUMLClass("a");
    assertEquals(myClass.getName(), "a");
    myClass.setName("b");
    assertEquals(myClass.getName(), "b");
  }

  @Test
  public void testGetAttributes() {
    String name = "class";
    ArrayList<String> attrs = new ArrayList<String>(Arrays.asList("a", "b", "c"));
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c"));
    ShrugUMLClass c = new ShrugUMLClass(name, attrs, methods);
    assertTrue(c.getAttributes().containsAll(attrs));
  }

  @Test
  public void testGetMethods() {
    String name = "class";
    ArrayList<String> attrs = new ArrayList<String>(Arrays.asList("a", "b", "c"));
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c"));
    ShrugUMLClass c = new ShrugUMLClass(name, attrs, methods);
    assertTrue(c.getMethods().containsAll(methods));
  }

  @Test
  public void testAddAttributes() {
    String name = "class";
    ArrayList<String> attrs = new ArrayList<String>(Arrays.asList("a", "b", "c"));
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c"));
    ShrugUMLClass c = new ShrugUMLClass(name, attrs, methods);
    c.addAttributes(
        new ArrayList<String>() {
          {
            add("d");
          }
        });
    assertTrue(c.getAttributes().contains("d"));
  }

  @Test
  public void testRemoveAttributes() {
    String name = "class";
    ArrayList<String> attrs = new ArrayList<String>(Arrays.asList("a", "b", "c"));
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c"));
    ShrugUMLClass c = new ShrugUMLClass(name, attrs, methods);
    c.removeAttributes(
        new ArrayList<String>() {
          {
            add("c");
          }
        });
    assertFalse(c.getAttributes().contains("c"));
  }

  @Test
  public void testAddMethods() {
    String name = "class";
    ArrayList<String> attrs = new ArrayList<String>(Arrays.asList("a", "b", "c"));
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("a", "b", "c"));
    ShrugUMLClass c = new ShrugUMLClass(name, attrs, methods);
    c.addMethods(
        new ArrayList<String>() {
          {
            add("d");
          }
        });
    assertTrue(c.getMethods().contains("d"));
  }
  /*
  @Test
  public void testRemoveMethods () {

  }
  */
}

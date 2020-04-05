import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;
import shrugUML.*;
import Command.*;
import com.shrug.Attribute.*;


public class TestUMLDiagram {
  @Test
  public void testUMLNullCtor() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    assertEquals(testDiagram.nameInDiagram("notAClass"), false);
  }

  @Test
  public void testAddClass() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    assertEquals(testDiagram.addClass("newClass"), true);
  }

  @Test
  public void testRepeatAddClass() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    assertEquals(testDiagram.addClass("newClass"), true);
    assertEquals(testDiagram.addClass("newClass"), false);
  }

  @Test
  public void testValidRemoveClass() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    assertEquals(testDiagram.addClass("newClass"), true);
    assertEquals(testDiagram.removeClass("newClass"), true);
  }

  @Test
  public void testInvalidRemoveClass() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    assertEquals(testDiagram.removeClass("newClass"), false);
  }

  @Test
  public void testValidNameInDiagram() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("newClass");
    assertEquals(testDiagram.nameInDiagram("newClass"), true);
  }

  @Test
  public void testInvalidNameInDiagram() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("newClass");
    assertEquals(testDiagram.nameInDiagram("newClass2"), false);
  }

  @Test
  public void testValidFindClass() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("newClass");
    assertNotNull(testDiagram.findClass("newClass"));
  }

  @Test
  public void testInvalidFindClass() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("newClass");
    assertNull(testDiagram.findClass("haha"));
  }

  @Test
  public void testGetDiagram() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("a");
    testDiagram.addClass("b");
    testDiagram.addClass("c");
    Set<ShrugUMLClass> diagram = testDiagram.getClasses();
    assertNotNull(testDiagram.findClass("a"));
    assertNotNull(testDiagram.findClass("b"));
    assertNotNull(testDiagram.findClass("c"));
  }

  @Test
  public void testValidAddRelationship() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("a");
    testDiagram.addClass("b");
    assertTrue(testDiagram.addRelationship("a", "b"));
  }

  @Test
  public void testInvalidAddRelationship() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("a");
    testDiagram.addClass("b");
    assertFalse(testDiagram.addRelationship("a", "c"));
  }

  @Test
  public void testValidRemoveRelationship() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("a");
    testDiagram.addClass("b");
    testDiagram.addRelationship("a", "b");
    assertTrue(testDiagram.removeRelationship("a", "b"));
  }

  @Test
  public void testInvalidRemoveRelationship() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("a");
    testDiagram.addClass("b");
    testDiagram.addRelationship("a", "b");
    assertFalse(testDiagram.removeRelationship("a", "c"));
  }

  @Test
  public void testAddClassTakesClass() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    ShrugUMLClass c = new ShrugUMLClass("a");
    testDiagram.addClass(c);
    assertTrue(testDiagram.nameInDiagram(c.getName()));
  }

  @Test
  public void testGetClasses() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    ArrayList<ShrugUMLClass> classes = new ArrayList<ShrugUMLClass>();

    ShrugUMLClass c = new ShrugUMLClass("a");
    classes.add(c);
    testDiagram.addClass(c);

    ShrugUMLClass c2 = new ShrugUMLClass("b");
    classes.add(c2);
    testDiagram.addClass(c2);

    ShrugUMLClass c3 = new ShrugUMLClass("c");
    classes.add(c3);
    testDiagram.addClass(c3);

    Set<ShrugUMLClass> s = testDiagram.getClasses();
    assertTrue(s.containsAll(classes));
  }


  @Test
  public void testEmptyGetRelationshipsOfClass() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("a");
    // Kind of have to do string checking here because of protected members
    assertEquals(testDiagram.getRelationshipsOfClass("a").toString(), "[]");
  }

  @Test
  public void testInvalidGetRelationshipsOfClass() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("a");
    assertNull(testDiagram.getRelationshipsOfClass("b"));
  }

  @Test
  public void testInvalidGetRelationship() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("a");
    testDiagram.addClass("b");
    testDiagram.addRelationship("a", "b");
    assertNull(testDiagram.getRelationship("a", "c"));
  }

  @Test
  public void testValidIsRelationshipInDiagram() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("a");
    testDiagram.addClass("b");
    testDiagram.addRelationship("a", "b");
    assertTrue(testDiagram.isRelationshipInDiagram("a", "b"));
  }

  @Test
  public void testInvalidIsRelationshipInDiagram() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("a");
    testDiagram.addClass("b");
    testDiagram.addRelationship("a", "b");
    assertFalse(testDiagram.isRelationshipInDiagram("a", "c"));
  }

  @Test
  public void testValidAddRelationshipWithType() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("a");
    testDiagram.addClass("b");
    testDiagram.addRelationshipWithType("a", "b", RType.Association);
    assertEquals(testDiagram.getRelationship("a", "b").getLabel(), RType.Association);
  }
  
  @Test
  public void testInvalidAddRelationshipWithType() {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    testDiagram.addClass("a");
    testDiagram.addClass("b");
    testDiagram.addRelationshipWithType("a", "c", RType.Association);
    assertNull(testDiagram.getRelationship("a", "b"));
  }


  @Test
  public void testAddCommand()
  {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    String className = "a";
    ArrayList<String> fields = new ArrayList<String>(Arrays.asList("int a", "string b"));
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("int a(int a1)", "int b(int b1, int b2)", "void c()")); 
    
    AddCommand add = new AddCommand (className, fields, methods);
    testDiagram.execute(add);

    assertTrue (testDiagram.nameInDiagram(className));
    ShrugUMLClass s = testDiagram.findClass(className);

    assertEquals(s.getAttributes(), new HashSet<String>(fields));
    assertEquals(s.getMethods(), new HashSet<String>(methods));
    
  }

  @Test 
  public void testRemoveCommand ()
  {
    ShrugUMLDiagram testDiagram = new ShrugUMLDiagram();
    String className = "a";
    ArrayList<String> fields = new ArrayList<String>(Arrays.asList("int a", "string b"));
    ArrayList<String> methods = new ArrayList<String>(Arrays.asList("int a(int a1)", "int b(int b1, int b2)", "void c()")); 
    
    AddCommand add = new AddCommand (className, fields, methods);
    testDiagram.execute(add);
    testDiagram.execute(add.invert());

    assertTrue (testDiagram.nameInDiagram(className));
    ShrugUMLClass s = testDiagram.findClass(className);

    assertEquals(s.getAttributes(), new HashSet<String>());
    assertEquals(s.getMethods(), new HashSet<String>());
    
  }
  
}

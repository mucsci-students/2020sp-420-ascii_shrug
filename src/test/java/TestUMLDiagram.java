import static org.junit.Assert.*;

import java.util.Set;
import org.junit.*;
import shrugUML.*;

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
}

import static org.junit.Assert.*;

import Controller.*;
import java.io.File;
import java.util.ArrayList;
import org.junit.*;
import shrugUML.*;

public class TestController {

  @Test
  public void testAdd() {
    Controller controller = new Controller();
    assertTrue(controller.addClass("newClass"));
  }

  @Test
  public void testRemove() {
    Controller controller = new Controller();
    assertTrue(controller.addClass("newClass"));
    assertTrue(controller.removeClass("newClass"));
  }

  /** *********************************************************************** */
  // Attribute tests
  @Test
  public void testAddAttribute() {
    Controller controller = new Controller();
    assertTrue(controller.addClass("newClass"));
    ArrayList<String> attributes = new ArrayList<String>();
    attributes.add("attr1");
    assertTrue(controller.addAttributes("newClass", attributes));
  }

  @Test
  public void testRemoveAttribute() {
    Controller controller = new Controller();
    assertTrue(controller.addClass("newClass"));
    ArrayList<String> attributes = new ArrayList<String>();
    attributes.add("attr1");
    controller.addAttributes("newClass", attributes);
    assertTrue(controller.removeAttributes("newClass", attributes));
  }

  /** *********************************************************************** */
  // Relationship tests
  @Test
  public void testAddRelationship() {
    Controller controller = new Controller();
    controller.addClass("c1");
    controller.addClass("c2");
    ArrayList<String> vectorList = new ArrayList<String>();
    vectorList.add("c2");
    assertTrue(controller.addRelationships("c1", vectorList));
  }

  @Test
  public void testAddRelationshipWithType() {
    Controller controller = new Controller();
    controller.addClass("a");
    controller.addClass("b");
    ArrayList<String> vectorList = new ArrayList<String>();
    vectorList.add("b");
    assertTrue(controller.addRelationships("a", vectorList, "Aggregation"));
  }

  @Test
  public void testRemoveRelationship() {
    Controller controller = new Controller();
    controller.addClass("c1");
    controller.addClass("c2");
    ArrayList<String> vectorList = new ArrayList<String>();
    vectorList.add("c2");
    controller.addRelationships("c1", vectorList);
    assertTrue(controller.removeRelationships("c1", vectorList));
  }

  /** *********************************************************************** */
  // Utility tests
  @Test
  public void testSave() {
    Controller controller = new Controller();
    assertTrue(controller.save("test.json"));
    File testFile = new File("./test.json");
    testFile.delete();
  }

  @Test
  public void testLoad() {
    Controller controller = new Controller();
    assertTrue(controller.save("test.json"));
    assertTrue(controller.load("test.json"));
  }
}

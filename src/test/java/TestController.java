import static org.junit.Assert.*;

import Command.*;
import Controller.*;
import java.io.File;
import java.util.*;
import org.junit.*;
import shrugUML.*;

public class TestController {

  /** *********************************************************************** */
  // Utility tests
  @Test
  public void testSave() {
    Controller controller = new Controller(new ShrugUMLDiagram());
    AddCommand add = new AddCommand("a");
    add.setFields(new ArrayList<String>(Arrays.asList("int a", "int b")));
    add.setMethods(new ArrayList<String>(Arrays.asList("int lol (char b)")));
    controller.execute(add);

    assertTrue(controller.save("test.json"));
    File testFile = new File("./test.json");
    testFile.delete();
  }

  @Test
  public void testLoad() {
    Controller controller = new Controller(new ShrugUMLDiagram());
    AddCommand add = new AddCommand("a");
    add.setFields(new ArrayList<String>(Arrays.asList("int a", "int b")));
    add.setMethods(new ArrayList<String>(Arrays.asList("int lol (char b)")));
    controller.execute(add);

    assertTrue(controller.save("test.json"));
    assertTrue(controller.load("test.json"));
  }

  @Test
  public void testAddExecute() {
    Controller controller = new Controller(new ShrugUMLDiagram());
    assertTrue(controller.execute(new AddCommand("a")));
    assertFalse(controller.execute(new AddCommand("a")));
  }

  @Test
  public void testRemoveExecute() {
    Controller controller = new Controller(new ShrugUMLDiagram());
    assertFalse(controller.execute(new RemoveCommand("a")));
    assertTrue(controller.execute(new AddCommand("a")));
    assertTrue(controller.execute(new RemoveCommand("a")));
  }

  @Test
  public void testUndo() {
    Controller controller = new Controller(new ShrugUMLDiagram());
    controller.execute(new AddCommand("a"));
    controller.undo();
    controller.undo();
  }

  @Test
  public void testNoUndo() {
    Controller controller = new Controller(new ShrugUMLDiagram());
    assertTrue(controller.noUndo());
    controller.execute(new AddCommand("a"));
    assertFalse(controller.noUndo());
    controller.undo();
    assertTrue(controller.noUndo());
  }
}

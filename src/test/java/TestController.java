import static org.junit.Assert.*;

import java.io.File;
import Controller.*;
import org.junit.*;
import shrugUML.*;

public class TestController {

  @Test
  public void testAdd() {
    Controller controller = new Controller();
    assertTrue(controller.add("newClass"));
  }

  @Test
  public void testRemove() {
    Controller controller = new Controller();
    assertTrue(controller.add("newClass"));
    assertTrue(controller.remove("newClass"));
  }

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

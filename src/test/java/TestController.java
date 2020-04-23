import static org.junit.Assert.*;

import Controller.*;
import java.io.File;
import java.util.ArrayList;
import org.junit.*;
import shrugUML.*;

public class TestController {


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

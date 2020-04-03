import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;
import shrugUML.*;

public class TestLabeledEdge {
  @Test
  public void testLabledEdgeCtor() {
    LabeledEdge e = new LabeledEdge(RType.Aggregation);
    assertNotNull(e);
  }

  @Test
  public void testGetLabel() {
    LabeledEdge e = new LabeledEdge(RType.Aggregation);
    assertEquals(e.getLabel(), RType.Aggregation);
  }

  @Test
  public void testToString() {
    LabeledEdge e = new LabeledEdge(RType.Aggregation);
    assertEquals(e.toString(), "(null:null:" + RType.Aggregation.toString() + ")");
  }

}

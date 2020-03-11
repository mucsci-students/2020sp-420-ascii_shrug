package shrugUML;

import org.jgrapht.graph.DefaultEdge;

public class LabeledEdge extends DefaultEdge {
  private RType m_label;

  /**
   * Constructs a relationship edge
   *
   * @param label the label of the new edge.
   */
  public LabeledEdge(RType label) {
    super();
  }

  /**
   * Gets the label associated with this edge.
   *
   * @return edge label
   */
  public RType getLabel() {
    return null;
  }

  @Override
  public String toString() {
    return null;
  }
}

package shrugUML;

import org.jgrapht.graph.DefaultEdge;

public class LabeledEdge extends DefaultEdge {
  private RType m_label;

  /**
   * Constructs a relationship edge
   *
   * @param label the label of the new edge.
   */
  public LabeledEdge() {
    super();
    m_label = RType.None;
  }
  
  /**
   * Constructs a relationship edge
   *
   * @param label the label of the new edge.
   */
  public LabeledEdge(RType label) {
    super();
    m_label = label;
  }

  /**
   * Gets the label associated with this edge.
   *
   * @return edge label
   */
  public RType getLabel() {
    return m_label;
  }

  public void setLabel(RType type) {
    m_label = type;
  }

  public Object myGetSource() {
    return new Object();
  }

  public Object myGetTarget() {
    return new Object();
  }

  @Override
  public String toString() {
    return "(" + getSource() + ":" + getTarget() + ":" + getLabel().toString() + ")";
  }
}

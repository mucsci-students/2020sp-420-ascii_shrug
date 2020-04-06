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
    return getSource();
  }

  public Object myGetTarget() {
    return getTarget();
  }

  @Override
  public String toString() {
    ShrugUMLClass source = (ShrugUMLClass) myGetSource();
    ShrugUMLClass target = (ShrugUMLClass) myGetTarget();
    return "(" + source.getName() + ":" + target.getName() + ":" + getLabel().toString() + ")";
  }
}

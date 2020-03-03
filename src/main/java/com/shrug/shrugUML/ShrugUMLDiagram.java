/* Filename:    ShrugUMLDiagram.java
 * Author:      Cole Vohs
 * Package:     shrugUML
 * Description: A class representing a UML diagram
 */
package shrugUML;

// Includes
import java.util.Set;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

/*
 // Deconstruct
 v = m_diagram.vertexSet ().toArray ();
 e = m_diagram.edgeSet ().toArray ();
 // Construct
 foreach (v) add v;
 foreach (e) add e;
*/

public class ShrugUMLDiagram {
  // Default Ctor - new Diagram with nothing in it
  public ShrugUMLDiagram() {
    m_diagram = new SimpleDirectedGraph<ShrugUMLClass, DefaultEdge>(DefaultEdge.class);
  }

  /** *********************************************************************** */
  // Public Methods

  /** *********************************************************************** */
  // Class Methods

  /*
   * Function: addClass (String className)
   * Precondition: className is the name of the class to be added to the diagram
   * Postcondition: className is added to the diagram if the name is valid. Returns true if it's added, false if it's
   * not
   */
  public boolean addClass(String className) {
    if (nameInDiagram(className)) {
      return false;
    } else {
      return m_diagram.addVertex(new ShrugUMLClass(className));
    }
  }

  public boolean addClass(ShrugUMLClass c) {
    if (nameInDiagram(c.getName())) {
      return false;
    } else {
      return m_diagram.addVertex(c);
    }
  }

  /*
   * Function: removeClass (String className)
   * Precondition: className is the name of the class to be removed from the
   * diagram
   * Postcondition: className is removed from the diagram if className is in the diagram. Returns true if
   * remove, false if not removed.
   */
  public boolean removeClass(String className) {
    if (m_diagram.removeVertex(findClass(className))) {
      return true;
    } else {
      return false;
    }
  }

  /* Function: getClassesAsSet ()
   * Precondition: this is instantiated
   * Postcondition: the classes in the diagram are returned as a set
   */
  public Set<ShrugUMLClass> getClasses() {
    return m_diagram.vertexSet();
  }

  /*
   * Function: nameInDiagram (String newName)
   * Precondition: newName is the string to be checked for Postcondition:
   * returns true if the name is in m_classes, false if it isn't
   */
  public boolean nameInDiagram(String className) {
    for (ShrugUMLClass classElement : getClasses())
      if (className.equals(classElement.getName())) return true;
    return false;
  }

  /*
   * Function: findClass (String className)
   * Precondition: className is the name of the class to be searched for
   * Postcondition: returns class object with name className if it's in m_classes, returns a default class with no
   * name.
   */
  public ShrugUMLClass findClass(String className) {
    for (ShrugUMLClass classElement : getClasses()) {
      if (className.contentEquals(classElement.getName())) {
        return classElement;
      }
    }
    return null;
  }

  /** *********************************************************************** */
  // Relationship Methods

  /* Function: addRelationship (String c1, String c2)
   * Precondition:
   * Postcondition: Relationship c1 -> c2 is added to the diagram
   */
  public boolean addRelationship(String n1, String n2) {
    if (!isRelationshipInDiagram(n1, n2) && findClass(n1) != null && findClass(n2) != null) {
      m_diagram.addEdge(findClass(n1), findClass(n2));
      return true;
    }
    return false;
  }

  /* Function: removeRelationship (String c1, String c2)
   * Precondition:
   * Postcondition: Relationship c1 -> c2 is added to the diagram
   */
  public boolean removeRelationship(String n1, String n2) {
    DefaultEdge edge = getRelationship(n1, n2);
    if (edge != null) {
      return m_diagram.removeEdge(edge);
    }
    return false;
  }

  /* Function: getEdgesOfClass (String className)
   * Precondition:
   * Postcondition: A set containing the edges going out from the class is returned; returns null if className is not in the diagram
   */
  public Set<DefaultEdge> getRelationshipsOfClass(String className) {
    ShrugUMLClass c = findClass(className);
    if (c != null) return m_diagram.edgesOf(c);
    return null;
  }

  /* Function: getRelationship (String n1, String n2)
   * Precondition: graph exists
   * Postcondition: Returns the edge n1 -> n2; null if edge doesn't exist or one of the classes doesn't exist
   */
  public DefaultEdge getRelationship(String n1, String n2) {
    ShrugUMLClass c1 = findClass(n1);
    ShrugUMLClass c2 = findClass(n2);
    DefaultEdge edge = m_diagram.getEdge(c1, c2);
    if ((edge != null) && (c1 != null) && (c2 != null)) return edge;
    else return null;
  }

  /* Function: isRelationshipInDiagram (String n1, String n2)
   * Precondition: graph exists
   * Postcondition: Returns true if the edge n1 -> n2 exists. false otherwise
   */
  public boolean isRelationshipInDiagram(String n1, String n2) {
    ShrugUMLClass c1 = findClass(n1);
    ShrugUMLClass c2 = findClass(n2);
    DefaultEdge edge = m_diagram.getEdge(c1, c2);
    if ((edge != null) && (c1 != null) && (c2 != null)) return true;
    else return false;
  }

  /** *********************************************************************** */
  // Utility Methods
  public SimpleDirectedGraph<ShrugUMLClass, DefaultEdge> getGraph() {
    return m_diagram;
  }

  public void setGraph(SimpleDirectedGraph<ShrugUMLClass, DefaultEdge> graph) {
    m_diagram = graph;
  }

  /** *********************************************************************** */
  // Private Methods

  /** *********************************************************************** */
  // Private Data Members
  private SimpleDirectedGraph<ShrugUMLClass, DefaultEdge> m_diagram;
}

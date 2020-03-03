package Controller;

/** *********************************************************************** */
// Imports (java/external/local)

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.*;
import org.jgrapht.alg.util.*;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.json.*;
import shrugUML.*;

public class Controller {

  private ShrugUMLDiagram m_diagram;

  public Controller() {
    this.m_diagram = new ShrugUMLDiagram();
  }

  public Controller(ShrugUMLDiagram obj) {
    this.m_diagram = obj;
  }

  /** *********************************************************************** */
  // Class methods

  /*
   * Function: add (String className)
   * Precondition: className is the name of the class to be added to the diagram
   * Postcondition: className is added to the diagram if the name is valid. Returns true if it's added, false if it's
   * not
   */
  public boolean addClass(String className) {
    return m_diagram.addClass(className);
  }

  /*
   * Method: remove (String className)
   * Precondition: className is the name of the class to be removed from the
   * diagram
   * Postcondition: className is removed from the diagram if className is in the diagram. Returns true if
   * remove, false if not removed.
   */
  public boolean removeClass(String className) {
    return m_diagram.removeClass(className);
  }

  /** *********************************************************************** */
  // Attribute methods

  /*
   * Function: addAttribute (String className, ArrayList<String> attributeList)
   * Precondition: className and n in attributeList are valid identifiers
   * Postcondition: className is added to the diagram if the name is valid with attributeList as its attributes.
   *                valid attributes are given to className if className exists.
   */
  public boolean addAttributes(String className, ArrayList<String> attributeList) {

    if (m_diagram.nameInDiagram(className))
      return m_diagram.findClass(className).addAttributes(attributeList);
    else {
      addClass(className);
      return addAttributes(className, attributeList);
    }
  }

  /*
   * Function: removeAttribute (String className, String[] attributeList)
   * Precondition: className exists and n in attributeList are valid identifiers
   * Postcondition: Removes all attributes of classname. returns false if
   *                className is not in diagram
   */
  public boolean removeAttributes(String className, ArrayList<String> attributeList) {
    if (m_diagram.nameInDiagram(className))
      return m_diagram.findClass(className).removeAttributes(attributeList);
    else return false;
  }

  /** *********************************************************************** */
  // Relationship methods

  /*
   * Function: addRelationship (String className, String[] vectorList)
   * Precondition: className and v in vectorList exist
   * Postcondition: className has relationships to v[i] st i != 0
   */
  public boolean addRelationships(String className, ArrayList<String> vectorList) {
    boolean success = false;

    for (String v : vectorList) success |= m_diagram.addRelationship(className, v);

    return success;
  }

  /*
   * Function: remove (String className, String[] vectorList)
   * Precondition: className and v in vectorList exist
   * Postcondition: className has relationships to v[i] st i != 0
   */
  public boolean removeRelationships(String className, ArrayList<String> vectorList) {
    boolean success = false;

    for (String v : vectorList) success |= m_diagram.removeRelationship(className, v);

    return success;
  }

  /** *********************************************************************** */
  // Utility methods

  public boolean contains(String className) {
    return m_diagram.nameInDiagram(className);
  }
  /*
    Method: save ()
    Precondition:
    this has an initialized m_diagram

    Postcondition:
    this.m_className is returned

    Parameters:
    path -- the path/name of the file to be saved without .json extension

    Returns:
    true if able to save file else return false

    BUGS:
    not catching objectMapper exceptions
    parameter path only works with file names and with file paths
  */

  public boolean save(String path) {
    try {
      FileWriter w = new FileWriter(path);

      JSONExporter<ShrugUMLClass, DefaultEdge> saver =
          new JSONExporter<ShrugUMLClass, DefaultEdge>();
      saver.setVertexIdProvider(
          (ShrugUMLClass c) -> {
            return c.getName();
          });
      saver.setVertexAttributeProvider(
          (ShrugUMLClass c) -> {
            Map<String, Attribute> map = new HashMap<String, Attribute>();
            map.put("Attributes", new DefaultAttribute(c.getAttributes(), AttributeType.UNKNOWN));
            map.put("Methods", new DefaultAttribute(c.getMethods(), AttributeType.UNKNOWN));
            return map;
          });
      saver.exportGraph(getGraph(), w);
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  /*
    Method: load (String path)
    Path: an absolute file path

    Precondition: Repl is running and path leads to a valid json file.
    Postcondition: A diagram has been constructed with equivalent state
    to the json file.
  */
  public boolean load(String path) {
    try {
      FileReader r = new FileReader(path);

      JSONImporter<ShrugUMLClass, DefaultEdge> creator =
          new JSONImporter<ShrugUMLClass, DefaultEdge>();
      SimpleDirectedGraph<ShrugUMLClass, DefaultEdge> g =
          new SimpleDirectedGraph<ShrugUMLClass, DefaultEdge>(DefaultEdge.class);

      BiConsumer<Pair<ShrugUMLClass, String>, Attribute> vertexConsumer =
          (pair, attr) -> {
            System.out.println(pair.getSecond());
            switch (pair.getSecond()) {
              case "id":
                {
                  pair.getFirst().setName(attr.getValue());
                  break;
                }
              case "Attributes":
                {
                  ArrayList<String> attributes =
                      new ArrayList<String>(Arrays.asList(attr.getValue().trim().split(", ")));
                  pair.getFirst().addAttributes(attributes);
                  break;
                }
              case "Methods":
                {
                  ArrayList<String> attributes =
                      new ArrayList<String>(Arrays.asList(attr.getValue().trim().split(", ")));
                  pair.getFirst().addMethods(attributes);
                  break;
                }
            }
          };

      /*
      creator.addVertexConsumer ((ShrugUMLClass c) -> {
          c.setName ()
        });
      */
      creator.addVertexAttributeConsumer(vertexConsumer);
      g.setVertexSupplier(() -> new ShrugUMLClass());
      creator.importGraph(g, r);
      m_diagram.setGraph(g);
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  /*
   * Function: getGraph ()
   * Precondition: this is instantiated
   * Postcondition: the underlying graph is returned
   */
  public SimpleDirectedGraph<ShrugUMLClass, DefaultEdge> getGraph() {
    return m_diagram.getGraph();
  }

  /*
   * Function: getClasses ()
   * Precondition: this is instantiated
   * Postcondition: the underlying model is returned
   */
  public Set<ShrugUMLClass> getClasses() {
    return m_diagram.getClasses();
  }
}

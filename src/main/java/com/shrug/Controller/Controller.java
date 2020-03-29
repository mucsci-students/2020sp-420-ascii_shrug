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
import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.util.*;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
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
    if (isJavaID(className)) return m_diagram.addClass(className);
    else return false;
  }

  /*
   *
   *
   */
  public boolean addClass(ShrugUMLClass c) {
    if (isJavaID(c.getName())) return m_diagram.addClass(c);
    else return false;
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
    ArrayList<String> validList = new ArrayList<String>();

    for (String s : attributeList) {
      if (isJavaID(s)) validList.add(s);
    }

    if (m_diagram.nameInDiagram(className))
      return m_diagram.findClass(className).addAttributes(validList);
    else {
      addClass(className);
      return m_diagram.findClass(className).addAttributes(validList);
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

    for (String v : vectorList) {
      if (isJavaID(v)) success |= m_diagram.addRelationship(className, v);
    }

    return success;
  }

     /* Function: addRelationship (String className, String[] vectorList)
   * Precondition: className and v in vectorList exist
   * Postcondition: className has relationships to v[i] st i != 0
   */
  public boolean addRelationships(String className, ArrayList<String> vectorList,
                                  String type) {
    boolean success = false;
    RType rType = RType.valueOf(type);
    
    for (String v : vectorList) {
      if (isJavaID(v)) success |= m_diagram.addRelationshipWithType(className, v, rType);
    }

    return success;
  }
  
  /*
   * Function: remove (String className, String[] vectorList)
   * Precondition: className and v in vectorList exist
   * Postcondition: className has relationships to v[i] st i != 0
   */
  public boolean removeRelationships(String className,
                                     ArrayList<String> vectorList) {
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

      JSONExporter<ShrugUMLClass, LabeledEdge> saver =
          new JSONExporter<ShrugUMLClass, LabeledEdge>();
      saver.setEdgeAttributeProvider(
          (LabeledEdge e) -> {
            Map<String, Attribute> map = new HashMap<String, Attribute>();
            map.put("rType", new DefaultAttribute(e.getLabel(), AttributeType.UNKNOWN));
            return map;
          });
      saver.setVertexIdProvider(
          (ShrugUMLClass c) -> {
            return c.getName();
          });
      saver.setVertexAttributeProvider(
          (ShrugUMLClass c) -> {
            Map<String, Attribute> map = new HashMap<String, Attribute>();
            map.put("Name", new DefaultAttribute(c.getName(), AttributeType.STRING));
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

      JSONImporter<ShrugUMLClass, LabeledEdge> creator =
          new JSONImporter<ShrugUMLClass, LabeledEdge>();
      ListenableGraph<ShrugUMLClass, LabeledEdge> g =
          new DefaultListenableGraph<ShrugUMLClass, LabeledEdge>(
              new SimpleDirectedGraph(LabeledEdge.class) {
                {
                  setVertexSupplier(() -> new ShrugUMLClass());
                }
              });

      BiConsumer<Pair<ShrugUMLClass, String>, Attribute> vertexConsumer =
          (pair, attr) -> {
            switch (pair.getSecond()) {
              case "Name":
                {
                  pair.getFirst().setName(attr.getValue());
                  break;
                }
              case "Attributes":
                {
                  ArrayList<String> attributes =
                      new ArrayList<String>(
                          Arrays.asList(
                              attr.getValue()
                                  .trim()
                                  .replace(",", "")
                                  .replace("[", "")
                                  .replace("]", "")
                                  .split("\\s+")));
                  pair.getFirst().addAttributes(attributes);
                  break;
                }
              case "Methods":
                {
                  ArrayList<String> methods =
                      new ArrayList<String>(Arrays.asList(attr.getValue().trim().split(", ")));
                  pair.getFirst().addMethods(methods);
                  break;
                }
            }            
          };

      BiConsumer<Pair<LabeledEdge, String>, Attribute> edgeConsumer =
          (pair, attr) -> {
        switch (attr.getValue()) {
          case "Association":
          {
            pair.getFirst().setLabel(RType.Association);
            break;
          }
          case "Generalization":
          {
            pair.getFirst().setLabel(RType.Generalization);
            break;
          }          
          case "Aggregation":
          {
            pair.getFirst().setLabel(RType.Aggregation);
            break;
          }          
          case "Composition":
          {
            pair.getFirst().setLabel(RType.Composition);
            break;
          }          
          case "None":
          {
            pair.getFirst().setLabel(RType.None);
            break;
          }          
        }
      };

      creator.addEdgeAttributeConsumer(edgeConsumer);
      creator.addVertexAttributeConsumer(vertexConsumer);
      creator.importGraph(g, r);
      m_diagram.setGraph(g);
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  /* * Function: getGraph ()
   * Precondition: this is instantiated
   * Postcondition: the underlying graph is returned
   */
  public ListenableGraph<ShrugUMLClass, LabeledEdge> getGraph() {
    return m_diagram.getGraph();
  }

  public ShrugUMLDiagram getDiagram() {
    return m_diagram;
  }

  /*
   * Function: getClasses ()
   * Precondition: this is instantiated
   * Postcondition: the underlying model is returned
   */
  public Set<ShrugUMLClass> getClasses() {
    return m_diagram.getClasses();
  }

  /* Function: isJavaID ()
   * precondition: input needs to be parsed
   * postcondition: returns if it is a valid identifier
   */
  public static boolean isJavaID(String name) {
    if (!(Character.isJavaIdentifierStart(name.charAt(0)))) return false;

    for (int i = 1; i < name.length(); i++) {
      if (!(Character.isJavaIdentifierPart(name.charAt(i)))) return false;
    }

    return true;
  }
}

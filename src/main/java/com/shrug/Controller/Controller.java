package Controller;

/** *********************************************************************** */
// Imports (java/external/local)

import Command.*;
import java.io.*;
import java.util.*;
import java.util.function.*;
import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.util.*;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.json.*;
import shrugUML.*;

public class Controller {

  private ShrugUMLDiagram m_diagram;
  private Stack<Command> m_log = new Stack<Command>();

  public Controller() {
    this.m_diagram = new ShrugUMLDiagram();
  }

  public Controller(ShrugUMLDiagram obj) {
    this.m_diagram = obj;
  }

  /** *********************************************************************** */
  // Utility methods

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
  */

  public boolean save(String path) {
    try {

      m_log.clear();
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

  // TODO
  /*Function: export ()
   * Will export the diagram as an image with the parameter as the file name
   */
  public boolean export(String fileName) {
    return true;
  }

  /*
   * function : undo()
   * rewinds the state by 1 command
   */
  public void undo() {
    if (!m_log.empty()) {
      if (m_log.peek() instanceof AddCommand) execute(new AddCommand(m_log.pop()));
      else execute(new RemoveCommand(m_log.pop()));

      m_log.pop();
    } else System.out.println("No commands to undo");
  }

  /*
   * Function: execute ()
   * paramaters;
   *   command: The command to be executed
   */
  public boolean execute(RemoveCommand command) {
    if (m_diagram.execute(command)) {
      m_log.push(command.invert());
      return true;
    } else {
      System.out.println("Error executing command");
      return false;
    }
  }

  /*
   * Function: execute ()
   * paramaters;
   *   command: The command to be executed
   */
  public boolean execute(AddCommand command) {
    if (m_diagram.execute(command)) {
      m_log.push(command.invert());
      return true;
    } else {
      System.out.println("Error executing command");
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

  public boolean noUndo() {
    return m_log.empty();
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

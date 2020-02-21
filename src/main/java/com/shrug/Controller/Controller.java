package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import shrugUML.*;

public class Controller {

  private ShrugUMLDiagram m_diagram;

  public Controller() {
    this.m_diagram = new ShrugUMLDiagram();
  }

  public Controller(ShrugUMLDiagram obj) {
    this.m_diagram = obj;
  }

  /*
   * Function: add (String className)
   * Precondition: className is the name of the class to be added to the diagram
   * Postcondition: className is added to the diagram if the name is valid. Returns true if it's added, false if it's
   * not
   */
  public boolean add(String className) {
    return m_diagram.addClass(className);
  }

  /*
   * Method: remove (String className)
   * Precondition: className is the name of the class to be removed from the
   * diagram
   * Postcondition: className is removed from the diagram if className is in the diagram. Returns true if
   * remove, false if not removed.
   */
  public boolean remove(String className) {
    return m_diagram.removeClass(className);
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
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      objectMapper.writeValue(new File(path), m_diagram);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
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
      ObjectMapper objectMapper = new ObjectMapper();
      m_diagram = objectMapper.readValue(new File(path), ShrugUMLDiagram.class);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
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

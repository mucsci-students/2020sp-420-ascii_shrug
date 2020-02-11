package Controller;

import shrugUML.*;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Arrays;
=======
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
>>>>>>> 4d268824b0637ef381a4f354d5d81dbcbaf2e1f6

public class Controller {

  private ShrugUMLDiagram m_diagram;

  public Controller() {
    this.m_diagram = new ShrugUMLDiagram();
  }

  public Controller(ShrugUMLDiagram obj) {
    this.m_diagram = obj;
  }

  // TODO
  public boolean add(String className) {
    return m_diagram.addClass(className);
  }

  // TODO
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
      objectMapper.writeValue(new File(path + ".json"), m_diagram);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
<<<<<<< HEAD
  
  //TODO
  public boolean load (String path) 
  {
    ObjectMapper mapper = new ObjectMapper();
    try
    {
      File jsonFile = new File (path);
      ShrugUMLClass[] diagram = mapper.readValue (jsonFile, ShrugUMLClass[].class);
    }
    catch (Exception e) { System.out.println ("Error loading file"); }


=======

  // TODO
  public boolean load(String path) {
>>>>>>> 4d268824b0637ef381a4f354d5d81dbcbaf2e1f6
    return true;
  }

  public ArrayList<ShrugUMLClass> getClasses() {
    return m_diagram.getClasses();
  }
}

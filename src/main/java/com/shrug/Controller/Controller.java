package Controller;

import shrugUML.*;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller
{

  private ShrugUMLDiagram m_diagram;

  public Controller ()
  {
    this.m_diagram = new ShrugUMLDiagram ();
  }

  public Controller (ShrugUMLDiagram obj)
  {
    this.m_diagram = obj;
  }

  //TODO
  public boolean add (String className)
  {
    return m_diagram.addClass (className);
  }

  //TODO
  public boolean remove (String className) 
  {
    return m_diagram.removeClass (className);
  }

  //TODO
  public boolean save (String path) 
  {
    return true;
  }
  
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


    return true;
  }

  public ArrayList<ShrugUMLClass> getClasses ()
  {
    return m_diagram.getClasses ();
  }
}


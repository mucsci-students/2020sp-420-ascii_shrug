package Controller;

<<<<<<< HEAD
import shrugUML.ShrugUMLDiagram;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
=======
import shrugUML.*;
import java.util.ArrayList;
>>>>>>> 7e038a416e5d32016b4c4b934b20b8e577fe8803

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
    File f = new File (path);

    return true;
  }

  public ArrayList<ShrugUMLClass> getClasses ()
  {
    return m_diagram.getClasses ();
  }
}


import shrugUML.*;

public class command
{

  private shrugUMLDiagram m_diagram;

  public command ()
  {
    this.m_diagram = null;
  }

  public command (shrugUMLDiagram obj)
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
  public boolean save (String fileName, String path) 
  {
    return true;
  }
  
  //TODO
  public boolean load (String path) 
  {
    return true;
  }
}


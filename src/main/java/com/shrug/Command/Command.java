package Command;

import java.util.*;
import shrugUML.*;

public class Command {

  /*
   * 
  */
  public Command (String className, ArrayList<String> fields, ArrayList<String> methods)
  {
    m_className = className; 
    m_fields = fields;
    m_methods = methods;
  }

  /*
   * 
  */
  public Command (ArrayList<String> fields, ArrayList<String> methods)
  {
    m_fields = fields;
    m_methods = methods;
  }
  
  /*
   * 
  */
  public Command (String className)
  {
    m_className = className;
    m_fields = new ArrayList<String>();
    m_methods = new ArrayList<String>();
  }

  /*
   * 
  */
  public String getClassName () {
    return m_className;
  }

  /*
   * 
  */
  public ArrayList<String> getFields ()
  {
    return m_fields;
  }

  /*
   * 
  */
  public ArrayList<String> getMethods ()
  {
    return m_methods;
  }

  public HashMap<String, RType> getRelationships () {
    return m_relationships;
  }

  /*
   * 
  */
  public void setClassName (String className)
  {
    m_className = className;
  }

  /*
   * 
  */
  public void setFields (ArrayList<String> fields)
  {
    m_fields = fields;
  }

  /*
   * 
  */
  public void setMethods (ArrayList<String> methods)
  {
    m_methods = methods;
  }

  /*
   * 
  */
  public void setRelationships (ArrayList<String> destinations, RType relationship)
  {
    for (String destination : destinations)
    {
      m_relationships.put(destination, relationship);
    }
  }

  String m_className;
  ArrayList<String> m_fields;
  ArrayList<String> m_methods;
  HashMap<String, RType> m_relationships = new HashMap<String, RType> ();
}

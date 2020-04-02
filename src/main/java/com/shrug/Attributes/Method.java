package Attribute;

import java.util.ArrayList;

public class Method {

  public Method () {}

  public Method (String returnType, String name, ArrayList<Field> parameters) 
  {
    m_returnType = returnType;
    m_name = name;
    m_parameters = parameters;
  }

  public String getReturnType () 
  {
    return m_returnType;
  }

  public String getName ()
  {
    return m_name;
  }

  public ArrayList<Field> getParameters ()
  {
    return m_parameters;
  }

  String m_returnType;
  String m_name;
  ArrayList<Field> m_parameters; 
}

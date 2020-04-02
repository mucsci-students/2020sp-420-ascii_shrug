package Attribute;

import java.util.ArrayList;

public class Field {

  public Field () {}

  public Field (String type, String name)
  {
    m_type = type;
    m_name = name;
  }

  public String getType ()
  {
    return m_type;
  }

  public String getName ()
  {
    return m_name;
  }

  String m_type;
  String m_name;
}

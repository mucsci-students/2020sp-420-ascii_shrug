package Command;

import java.util.ArrayList;

abstract class Command {

  abstract public Command invert ();

  public String getClassName () {
    return m_className;
  }

  public ArrayList<String> getFields ()
  {
    return m_fields;
  }

  public ArrayList<String> getMethods ()
  {
    return m_methods;
  }

  String m_className;
  ArrayList<String> m_fields;
  ArrayList<String> m_methods;

}

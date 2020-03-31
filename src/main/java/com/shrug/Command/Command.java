package Command;

import java.util.ArrayList;

abstract class Command {

  abstract public Command invert ();

  public ArrayList<String> getFields ()
  {
    return m_fields;
  }

  public ArrayList<String> getMethods ()
  {
    return m_methods;
  }

  ArrayList<String> m_fields;
  ArrayList<String> m_methods;

}

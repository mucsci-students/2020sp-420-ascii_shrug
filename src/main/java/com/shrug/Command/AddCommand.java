package Command;

import java.util.ArrayList;

public class AddCommand extends Command {
  
  public AddCommand (String className, ArrayList<String> fields, ArrayList<String> methods)
  {
    m_className = className;
    m_fields = fields;
    m_methods = methods;
  }

  public RemoveCommand invert () {
    return new RemoveCommand (m_className, m_fields, m_methods);
  }

}

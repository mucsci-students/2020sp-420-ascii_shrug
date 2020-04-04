package Command;

import java.util.ArrayList;

public class RemoveCommand extends Command {
  
  public RemoveCommand (String className, ArrayList<String> fields, ArrayList<String> methods)
  {
    m_className = className;
    m_fields = fields;
    m_methods = methods;
  }

  public AddCommand invert () {
    return new AddCommand (m_className, m_fields, m_methods);
  }


}

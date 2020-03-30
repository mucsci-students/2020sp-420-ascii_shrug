package Command;

import java.util.ArrayList;

public class RemoveCommand extends Command {
  
  public RemoveCommand (ArrayList<String> fields, ArrayList<String> methods)
  {
    m_fields = fields;
    m_methods = methods;
  }

  public AddCommand invert () {
    return new AddCommand (m_fields, m_methods);
  }


}

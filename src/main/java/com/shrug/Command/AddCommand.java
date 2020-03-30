package Command;

import java.util.ArrayList;

public class AddCommand extends Command {
  
  public AddCommand (ArrayList<String> fields, ArrayList<String> methods)
  {
    m_fields = fields;
    m_methods = methods;
  }

  public AddCommand (ArrayList<String> fields)
  {
    m_fields = fields;
    m_methods = new ArrayList<String>();
  }

  public AddCommand (ArrayList<String> methods)
  {
    m_fields = new ArrayList<String>();
    m_methods = methods;
  }



  public RemoveCommand invert () {
    return new RemoveCommand (m_fields, m_methods);
  }

}

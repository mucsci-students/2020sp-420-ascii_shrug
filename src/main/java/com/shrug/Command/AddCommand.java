package Command;

import java.util.ArrayList;

public class AddCommand extends Command {
  
  public AddCommand (String className, ArrayList<String> fields, ArrayList<String> methods)
  {
    super (className, fields, methods);
  }

  public AddCommand (String className)
  {
    super (className);
  }

  public AddCommand (Command command)
  {
    super (command);
  }

  public RemoveCommand invert () {
    return new RemoveCommand (m_className, m_fields, m_methods);
  }

}

package Command;

import java.util.ArrayList;

public class RemoveCommand extends Command {
  
  public RemoveCommand (String className, ArrayList<String> fields, ArrayList<String> methods)
  {
    super(className, fields, methods);
  }

  public RemoveCommand (String className)
  {
    super (className);
  }

  public RemoveCommand (Command command)
  {
    super (command);
  }

  public AddCommand invert () {
    return new AddCommand (m_className, m_fields, m_methods);
  }


}

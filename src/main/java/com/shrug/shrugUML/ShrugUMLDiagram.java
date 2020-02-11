package shrugUML;
// Includes
import java.util.ArrayList;
import java.util.Arrays;

public class ShrugUMLDiagram
{
    // Default Ctor - new Diagram with nothing in it and size 0
    public ShrugUMLDiagram ()
    {
	m_classes = new ArrayList<ShrugUMLClass> ();
	m_size = 0;
    }

    public ShrugUMLDiagram (ShrugUMLClass[] classes)
    {
	m_classes = new ArrayList<ShrugUMLClass> ();
	for (ShrugUMLClass c : classes)
	    {
		m_classes.add (c);
		++m_size;
	    }
    }

    /**************************************************************************/    
    // Public Methods
    
    /*
      Function: addClass (String className)
      Precondition: className is the name of the class to be added to the diagram
      Postcondition: className is added to the diagram if the name is valid. Returns true if it's added, false if it's not
     */
    public boolean addClass (String className)
    {
	if (nameInDiagram (className))
	    {
		return false;
	    }
	else
	    {
		ShrugUMLClass newClass = new ShrugUMLClass (className);
		m_classes.add (newClass);
		++m_size;
		return true;
	    }
    }

    /*
      Function: removeClass (String className)
      Precondition: className is the name of the class to be removed from the diagram
      Postcondition: className is removed from the diagram if className is in the diagram. Returns true if remove, false if not removed.
     */
    public boolean removeClass (String className)
    {
	if (m_classes.remove (findClass (className)))
	    {
		--m_size;
		return true;
	    }
	else
	    {
		return false;
	    }
    }

    /*
      Function: getClasses ()
      Precondition: this is instantiated
      Postcondition: the underlying model is returned
     */
    public ArrayList<ShrugUMLClass> getClasses ()
    {
      return m_classes; 
    }   

    /*
      Function: getSize ()
      Precondition: this is instantiated
      Postcondition: m_size, the size of the diagram, is returned
     */
    public int getSize ()
    {
	return m_size;
    }

    
    /**************************************************************************/    
    // Private Methods

    /*
      Function: classInDiagram (String newName)
      Precondition: newName is the string to be checked for
      Postcondition: returns true if the name is in m_classes, false if it isn't
     */
    public boolean nameInDiagram (String className)
    {
	for (ShrugUMLClass classElement : m_classes)
	    if (className.contentEquals (classElement.getName ()))
		return true;
	return false;
    }

    
    /*
      Function: findClass (String className)
      Precondition: className is the name of the class to be searched for
      Postcondition: returns class object with name className if it's in m_classes, returns a default class.
     */
    public ShrugUMLClass findClass (String className)
    {
	for (ShrugUMLClass classElement : m_classes)
	    {
		if (className.contentEquals (classElement.getName ()))
		    {
			return classElement;
		    }
	    }
	return new ShrugUMLClass(className);
    }

    /**************************************************************************/    
    // Private Data Members
    private int m_size;
    private ArrayList<ShrugUMLClass> m_classes;
    
}

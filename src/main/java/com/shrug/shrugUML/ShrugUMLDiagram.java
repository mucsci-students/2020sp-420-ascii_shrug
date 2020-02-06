/*
  Filename: shrugUMLDiagram.java
  Author:   Cole Vohs
  Description: A class representing a UML diagram
*/

// Package
package shrugUML;
// Includes
import java.util.ArrayList;

public class ShrugUMLDiagram
{
    // Default Ctor - new Diagram with nothing in it and size 0
    public ShrugUMLDiagram ()
    {
	m_classes = new ArrayList<ShrugUMLClass> ();
	m_size = 0;
    }

    /**************************************************************************/    
    // Public Methods
    
    /*
      Function: addClass (String className)
      Precondition: className is the name of the class to be added to the diagram
      Postcondition: className is added to the diagram if the name is valid, throws exception if name is invalid
     */
    public boolean addClass (String className)
    {
	if (nameInDiagram (className))
	    {
		// TODO: THROW EXCEPTION
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
      Postcondition: className is removed from the diagram if className is in the diagram, throws exception if name is invalid
     */
    public boolean removeClass (String className)
    {
	ShrugUMLClass remove = findClass (className);
	if (remove.getName () != null)
	    {
		m_classes.remove (remove);
		--m_size;
		return true;
	    }
	else
	    {
		// TODO: THROW EXCEPTION
		return false;
	    }
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
	    if (className == classElement.getName ())
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
	    if (className == classElement.getName ())
		return classElement;
	return new ShrugUMLClass(className);
    }

    /**************************************************************************/    
    // Private Data Members
    private int m_size;
    private ArrayList<ShrugUMLClass> m_classes;
    
}

/*
  Filename: shrugUMLClass.java
  Author:   Cole Vohs
  Description: A class representing a class in a UML diagram
*/
// Package
package shrugUML;

public class ShrugUMLClass
{
    // Default Ctor - sets the m_className to null
    public ShrugUMLClass ()
    {
	m_className = null;
    }
    // Name Ctor - sets m_className to newName
    public ShrugUMLClass (String newName)
    {
	m_className = newName;
    }
    // Public Methods

    /*
      Function: getName ()
      Precondition: this has an initialized m_className
      Postcondition: this.m_className is returned
     */
    public String getName ()
    {
	return m_className;
    }
    /*
      Function: setName ()
      Precondition: newName is a valid string
      Postcondition: this.m_className is set to newName
     */
    public void setName (String newName)
    {
	m_className = newName;
    }
    // Private Data Members
    private String m_className;
}

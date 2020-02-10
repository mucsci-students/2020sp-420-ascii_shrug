/*
  Filename: shrugUMLClass.java
  Author:   Cole Vohs
  Description: A class representing a class in a UML diagram
*/
// Package
package shrugUML;

public class ShrugUMLClass
{
    // Default Ctor - sets the className to null
    public ShrugUMLClass ()
    {
	this.className = null;
    }
    // Name Ctor - sets className to newName
    public ShrugUMLClass (String newName)
    {
	this.className = newName;
    }
    // Public Methods

    /*
      Function: getName ()
      Precondition: this has an initialized className
      Postcondition: this.className is returned
     */
    public String getName ()
    {
	return this.className;
    }
    /*
      Function: setName ()
      Precondition: newName is a valid string
      Postcondition: this.className is set to newName
     */
    public void setName (String newName)
    {
	this.className = newName;
    }
    // Private Data Members
    private String className;
}
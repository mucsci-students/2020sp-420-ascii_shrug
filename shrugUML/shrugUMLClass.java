/*
  Filename: shrugUMLClass.java
  Author:   Cole Vohs
  Description: A class representing a class in a UML diagram
*/
// Package
package shrugUML;

public class shrugUMLClass
{
    // Default Ctor - sets the className to null
    public shrugUMLClass ()
    {
	this.className = null;
    }
    // Name Ctor - sets className to newName
    public shrugUMLClass (String newName)
    {
	this.className = newName;
    }
    // Public Methods
    public String getName ()
    {
	return this.className;
    }

    public void setName (String newName)
    {
	this.className = newName;
    }
    // Private Data Members
    private String className;
}

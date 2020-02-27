/*
  Filename: shrugUMLClass.java
  Author:   Cole Vohs
  Description: A class representing a class in a UML diagram
*/

// Package
package shrugUML;

import java.util.ArrayList;
import java.util.HashSet;

public class ShrugUMLClass {
  // Default Ctor - sets the m_className to null
  public ShrugUMLClass() {
    m_className = null;
    m_attributes = new HashSet<String>();
    m_methods = new HashSet<String>();
  }

  // Name Ctor - sets m_className to newName
  public ShrugUMLClass(String newName, ArrayList<String> attributes, ArrayList<String> methods) {
    setName(newName);
    addAttributes(attributes);
    addMethods(methods);
  }

  // public ShrugUMLClass()
  public ShrugUMLClass(String newName) {
    setName(newName);
    m_attributes = new HashSet<String>();
    m_methods = new HashSet<String>();
  }

  // Public Methods

  /*
   * Function: getName ()
   * Precondition: this has an initialized m_className
   * Postcondition: this.m_className is
   * returned
   */
  public String getName() {
    return m_className;
  }

  /*
   * Function: setName ()
   * Precondition: newName is a valid string
   * Postcondition: this.m_className is set to newName
   */
  public void setName(String newName) {
    m_className = newName;
  }

  /* Function: getAttributes ()
   * Precondition:
   * Postcondition:
   */
  public HashSet<String> getAttributes() {
    return m_attributes;
  }

  /* Function: getMethods ()
   * Precondition:
   * Postcondition
   */
  public HashSet<String> getMethods() {
    return m_methods;
  }

  /* Function: addAttribute ()
   * Precondition:
   * Postcondition
   */
  public boolean addAttributes(ArrayList<String> attributeNames) {
    return m_attributes.addAll(attributeNames);
  }

  /* Function: addMethods ()
   * Precondition:
   * Postcondition
   */
  public boolean addMethods(ArrayList<String> methodNames) {
    return m_attributes.addAll(methodNames);
  }

  /* Function:
   * Precondition:
   * Postcondition
   */

  // Private Data Members
  private String m_className;
  private HashSet<String> m_attributes;
  private HashSet<String> m_methods;
}

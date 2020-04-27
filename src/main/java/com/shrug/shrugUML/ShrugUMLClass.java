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
    this.m_className = null;
    this.m_attributes = new HashSet<String>();
    this.m_methods = new HashSet<String>();
  }

  // ArrayList Ctor
  public ShrugUMLClass(String newName, ArrayList<String> attributes, ArrayList<String> methods) {
    this();
    this.setName(newName);
    this.addAttributes(attributes);
    this.addMethods(methods);
  }

  // HashSet ctor
  public ShrugUMLClass(String newName, HashSet<String> attributes, HashSet<String> methods) {
    this.m_className = newName;
    this.m_attributes = attributes;
    this.m_methods = methods;
  }

  // public ShrugUMLClass()
  public ShrugUMLClass(String newName) {
    setName(newName);
    m_attributes = new HashSet<String>();
    m_methods = new HashSet<String>();
  }

  /** *********************************************************************** */
  // Public Methods
  /** *********************************************************************** */
  // Class Methods

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

  /** *********************************************************************** */
  // Attribute methods

  /* Function: addAttribute (ArrayList<String> attributeList)
   * Precondition: diagram exists
   * Postcondition: all valid attributes have been added
   */
  public boolean addAttributes(ArrayList<String> attributeList) {
    return m_attributes.addAll(attributeList);
  }

  /* Function: removeAttributes (ArrayList<String> attributeList)
   * Precondition: diagram exists
   * Postcondition: all valid attributes have been removed
   */
  public boolean removeAttributes(ArrayList<String> attributeList) {
    return m_attributes.removeAll(attributeList);
  }

  /* Function: addMethods ()
   * Precondition:
   * Postcondition
   */
  public boolean addMethods(ArrayList<String> methodNames) {
    return m_methods.addAll(methodNames);
  }

  /* Function: removeMethods ()
   * Precondition:
   * Postcondition
   */
  public boolean removeMethods (ArrayList<String> methodNames) {
    return m_methods.removeAll(methodNames);
  }

  @Override
  public String toString() {
    return m_className 
        + "\n__________\n"
        + m_attributes.toString().replace("[", "").replace("]", "").replace(",", "\n")
        + "\n__________\n"
        + m_methods.toString().replace("[", "").replace("]", "").replace(",", "\n");
  }

  // Private Data Members
  private String m_className;
  private HashSet<String> m_attributes;
  private HashSet<String> m_methods;
}

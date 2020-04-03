package AST;

public abstract class TypeNode extends Node {

  public String getType () {
    return m_type; 
  }
  
  String m_type;
}

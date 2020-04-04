package com.shrug.AST;

public class ListNode extends TypeNode {

  public ListNode()  {}

  public void setSubtype (TypeNode type)
  {
    m_type = type;  
  }

  TypeNode m_type;
}

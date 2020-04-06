package com.shrug.AST;

public class IDNode extends Node {

  public IDNode(String name)
  {
    m_name = name;
  }

  public String getName() {
    return m_name;
  }
  
  String m_name;
}

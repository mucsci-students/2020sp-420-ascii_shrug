package com.shrug.Parser;

import Command.*;
import com.shrug.AST.*;
import java.util.*;
import java.io.*;

public class Parser {

  Lexer m_lexer;

  public Parser (String s) {
    m_lexer = new Lexer(new StringReader (s)); 
  }

  public ArrayList<Node> getTokenStream() {
    ArrayList<Node> tokens = new ArrayList<Node>();

    try{
      Node token = m_lexer.yylex();
      while (token != null) 
      {
        tokens.add(token);
        token = m_lexer.yylex();
      }
    }
    catch (IOException e)
    {
      System.out.println ("Bad Input");
    }

    return tokens;
  }
    
  /*
   * 
   *
   *
   *
  */
  public Command parse ()
  {
    ArrayList<Node> tokens = getTokenStream();
    ArrayList<String> fields = new ArrayList<String>();
    ArrayList<String> methods = new ArrayList<String>();

    Node curTok;
    ListIterator<Node> it = tokens.listIterator();

    while (it.hasNext())
    { 
      String res = "";
      curTok = it.next ();
      // field or method coming up
      if (curTok instanceof PrimitiveTypeNode)
      {
        res = parseVar (it, curTok);        

        if (it.hasNext()) 
        {
          curTok = it.next ();

          // method
          if (curTok instanceof LParenNode)
          {
            res += " (";
            while (it.hasNext())
            {
              curTok = it.next ();


              if (curTok instanceof RParenNode)
              {
                res += ")";
                methods.add(res);
                break;
              }
              else if (curTok instanceof CommaNode)
              {
                res += ", ";
                continue;
              }
              else if (curTok instanceof PrimitiveTypeNode)
              {
                res += parseVar (it, curTok);
              }
              else
                System.out.println ("Malformed Parameter");
            }
            continue;
          }
          // field
          else
          {
            curTok = it.previous ();         
          }
        }
        fields.add (res);
      }
      else if (curTok instanceof CommaNode)
        continue;
    }

    return new Command (fields, methods);
  }

  /*
   *
   */
  public String parseVar (ListIterator<Node> it, Node curTok)
  {
      String type = "";
      String id = "";

      // field or method coming up
      type = ((PrimitiveTypeNode)curTok).getType ();

      if (it.hasNext ())
      {
        curTok = it.next ();
        if (curTok instanceof IDNode)
          id = ((IDNode) curTok).getName ();
      }
      else
        System.out.println("Invalid Syntax ID expected after Type");


      return (type + " " + id);
  }

}

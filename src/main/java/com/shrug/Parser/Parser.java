package com.shrug.Parser;

import Command.*;
import com.shrug.AST.*;
import java.util.*;
import java.io.*;

public class Parser {

  Lexer m_lexer;

  public Parser (String s) {
    m_lexer = new Lexer(StringReader (s)); 
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
    Iterator<Node> it = tokens.iterator();
    while (it.hasNext())
    { 
      String res = "";
      curTok = it.next ();
      
      // field or method coming up
      if (curTok instanceof TypeNode)
      {
        res = parseVar (it, curTok);

        if (it.hasNext()) 
        {
          curTok = it.next ();

          // method
          if (curTok instanceof LParenNode)
          {
            while (it.hasNext())
            {
              curTok = it.next ();

              if (curTok instanceof CommaNode)
                continue;

              if (curTok instanceof RParenNode)
              {
                methods.add(res);
                break;
              }

              // TODO ensure curTok is typeNode
              res.concat (" " + parseVar (it, curTok));
            }
          }
          // field
          else if (curTok instanceof CommaNode)
          {
            fields.add (res); 
            continue;
          }
        }


      }
      else if (curTok instanceof CommaNode)
        continue;
    }

    return new Command (fields, methods);
  }

  public String parseVar (Iterator<Node> it, Node curTok)
  {
      String type = "";
      String id = "";
      // field or method coming up
      type = ((TypeNode)curTok).getType ();

      if (it.hasNext ())
        curTok = it.next ();
      else
        System.out.println("Invalid Syntax");

      if (curTok instanceof IDNode)
        id = ((IDNode) curTok).getName ();

      return type.concat (" " + id);
  }

}

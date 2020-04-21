package GUI;

import Controller.*;
import com.mxgraph.layout.*;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.*;
import com.mxgraph.view.*;
import com.mxgraph.util.*;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxCellRenderer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.AbstractAction;
import org.jgrapht.event.*;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.*;
import shrugUML.*;
import com.shrug.Parser.*;
import Command.*;

public class GUI {

  private JFrame frame;
  private JButton add, remove, edit, save, load, addR, removeR, help, layoutE, undo;
  private Controller control = new Controller();
  private JGraphXAdapter<ShrugUMLClass, LabeledEdge> jgxAdapter =
      new JGraphXAdapter<ShrugUMLClass, LabeledEdge>(control.getGraph());

  private mxHierarchicalLayout layout;
  private mxStylesheet stylesheet = new mxStylesheet();

  
  private mxGraphComponent graph;
  private JPanel content;

  private class undoHandler implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      control.getDiagram().undo();
      jgxAdapter.repaint();
    }
  }
  
  public GUI() {

    try {
      JFrame.setDefaultLookAndFeelDecorated(true);
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      System.setProperty("apple.laf.useScreenMenuBar", "true");
      System.setProperty("com.apple.mrj.application.apple.menu.about.name", "shrug_uml");
    } catch (ClassNotFoundException
             | InstantiationException
             | IllegalAccessException
             | UnsupportedLookAndFeelException e) {
    }

    start();
  }

  public void start() {
    
    frame = new JFrame();
    frame.setName("GUI");
    
    content = new JPanel(new BorderLayout(30, 30));
    content.setPreferredSize(new Dimension(800, 600));
    frame.setContentPane(content);
    
    initMenuBar();
    initButtons();

    initLayout ();
    initStylesheet();
    initGraphComponent();
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  public void initGraphComponent() {
    if (graph != null) content.remove(graph);
    jgxAdapter.setAutoSizeCells(true);
    graph = new mxGraphComponent(jgxAdapter);
    content.add(graph, BorderLayout.CENTER);
    layout.execute(jgxAdapter.getDefaultParent());
  }

  public void initStylesheet() {
    Hashtable<String, Object> style = new Hashtable<String, Object>();
    style.put(mxConstants.STYLE_FILLCOLOR, mxUtils.getHexColorString(Color.WHITE));
    style.put(mxConstants.STYLE_STROKEWIDTH, 1.5);
    style.put(mxConstants.STYLE_STROKECOLOR, mxUtils.getHexColorString(new Color(0, 0, 170)));
    style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
    style.put(mxConstants.STYLE_PERIMETER, mxConstants.PERIMETER_RECTANGLE);
    stylesheet.setDefaultVertexStyle (style);

    // Stylesheet for association
    final Hashtable<String, Object> associationEdgeStyle = new Hashtable<String, Object>();
    associationEdgeStyle.put(mxConstants.STYLE_ROUNDED, true);
    associationEdgeStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
    associationEdgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_OPEN);
    associationEdgeStyle.put(mxConstants.STYLE_ENDSIZE, 15);
    associationEdgeStyle.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
    associationEdgeStyle.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
    associationEdgeStyle.put(mxConstants.STYLE_STROKECOLOR, "#6482B9");
    associationEdgeStyle.put(mxConstants.STYLE_STROKEWIDTH, 2);
    associationEdgeStyle.put(mxConstants.STYLE_FONTCOLOR, "#446299");
    stylesheet.putCellStyle("association", associationEdgeStyle);
    
    // Stylesheet for generalization
    final Hashtable<String, Object> generalizationEdgeStyle = new Hashtable<String, Object>();
    generalizationEdgeStyle.put(mxConstants.STYLE_ROUNDED, true);
    generalizationEdgeStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
    generalizationEdgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
    generalizationEdgeStyle.put(mxConstants.STYLE_ENDSIZE, 15);
    generalizationEdgeStyle.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
    generalizationEdgeStyle.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
    generalizationEdgeStyle.put(mxConstants.STYLE_STROKECOLOR, "#6482B9");
    generalizationEdgeStyle.put(mxConstants.STYLE_STROKEWIDTH, 2);
    generalizationEdgeStyle.put(mxConstants.STYLE_FONTCOLOR, "#446299");
    stylesheet.putCellStyle("generalization", generalizationEdgeStyle);

    
    // Stylesheet for composition
    final Hashtable<String, Object> compositionEdgeStyle = new Hashtable<String, Object>();
    compositionEdgeStyle.put(mxConstants.STYLE_ROUNDED, true);
    compositionEdgeStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
    compositionEdgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_DIAMOND);
    compositionEdgeStyle.put(mxConstants.STYLE_ENDSIZE, 15);
    compositionEdgeStyle.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
    compositionEdgeStyle.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
    compositionEdgeStyle.put(mxConstants.STYLE_STROKECOLOR, "#6482B9");
    compositionEdgeStyle.put(mxConstants.STYLE_STROKEWIDTH, 2);
    compositionEdgeStyle.put(mxConstants.STYLE_FONTCOLOR, "#446299");    
    //stylesheet.setDefaultEdgeStyle(compositionEdgeStyle);
    stylesheet.putCellStyle("composition", compositionEdgeStyle);

    //Stylesheet for composition
    final Hashtable<String, Object> aggregationEdgeStyle = new Hashtable<String, Object>();
    aggregationEdgeStyle.put(mxConstants.STYLE_ROUNDED, true);
    aggregationEdgeStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
    aggregationEdgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_DIAMOND);
    aggregationEdgeStyle.put(mxConstants.STYLE_ENDFILL, 0);
    aggregationEdgeStyle.put(mxConstants.STYLE_ENDSIZE, 15);
    aggregationEdgeStyle.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
    aggregationEdgeStyle.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
    aggregationEdgeStyle.put(mxConstants.STYLE_STROKECOLOR, "#6482B9");
    aggregationEdgeStyle.put(mxConstants.STYLE_STROKEWIDTH, 2);
    aggregationEdgeStyle.put(mxConstants.STYLE_FONTCOLOR, "#446299");
    aggregationEdgeStyle.put(mxConstants.STYLE_FILLCOLOR, mxUtils.getHexColorString(Color.WHITE));
    //stylesheet.setDefaultEdgeStyle(aggregationEdgeStyle);
    stylesheet.putCellStyle("aggregation", aggregationEdgeStyle);
    
    jgxAdapter.setStylesheet(stylesheet);
    //jgxAdapter.setCellStyle("strokeColor=#CCCC00");
  } 

  public void initMenuBar () {

    final JMenu file = new JMenu("File");
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(file);

    // Set callback functions for each button
    JMenuItem save = new JMenuItem("Save");
    save.addActionListener(this::processButtonPressSave);
    JMenuItem load = new JMenuItem("Load");
    load.addActionListener(this::processButtonPressLoad);
    JMenuItem export = new JMenuItem("Export");
    export.addActionListener(this::ProcessButtonPressExportImage);

    file.add(save);
    file.add(load);
    file.add(export);

    frame.setJMenuBar(menuBar);
  }

  /* void initButtons ()
   * Initializes Buttons and adds them to a FlowPane to be displayed at the top of the window
   * TODO: Use this for Add/Remove instead of the MenuBar
   */
  public void initButtons() {
    add = new JButton("Add Class");
    add.addActionListener(this::processButtonPressAdd);

    remove = new JButton("Remove Class");
    remove.addActionListener(this::processButtonPressRemove);

    edit = new JButton("Edit Attributes");
    edit.addActionListener(this::processButtonPressEdit);

    addR = new JButton("Add Relation");
    addR.addActionListener(this::processButtonPressAddR);

    removeR = new JButton("Remove Relation");
    removeR.addActionListener(this::processButtonPressRemoveR);

    undo = new JButton("Undo");
    undo.addActionListener(new undoHandler ());

    layoutE = new JButton("Layout");
    layoutE.addActionListener(this::processButtonPressLayout);

    help = new JButton("Help");
    help.addActionListener(this::helpDialog);


    JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
    flow.add(add);
    flow.add(remove);
    flow.add(edit);
    flow.add(addR);
    flow.add(removeR);
    flow.add(layoutE);
    flow.add(undo);
    flow.add(help);

    content.add(flow, BorderLayout.NORTH);
  }

  /* void initLayout () 
   * initializes the default layout
   */
  public void initLayout () {
    layout = new mxHierarchicalLayout (jgxAdapter);
    
    layout.setIntraCellSpacing (120);
    layout.setInterRankCellSpacing (80);
    layout.setFineTuning(false);
    layout.setDisableEdgeStyle(true);
  }
  
  /* void redrawEdges ()
   * routine to redraw each edge using the stylesheet corresponding to its type
   */
  public void redrawEdges () {
    var styles = jgxAdapter.getStylesheet().getStyles();
    HashMap<mxICell, LabeledEdge> cellToEdgeMap = jgxAdapter.getCellToEdgeMap();
    Set<mxICell> edgeCellSet = cellToEdgeMap.keySet();
    // Iterate through edges to set their styles
    for (mxICell c : edgeCellSet) {
      mxICell[] cell = new mxICell[]{c};
      switch (cellToEdgeMap.get(c).getLabel()) {
        case Aggregation:
          for (Map.Entry<String, Object> style : styles.get("aggregation").entrySet())
            jgxAdapter.setCellStyles(style.getKey(), style.getValue().toString(), cell);
          break;
        case Composition:
          for (Map.Entry<String, Object> style : styles.get("composition").entrySet())
            jgxAdapter.setCellStyles(style.getKey(), style.getValue().toString(), cell);
          break;
        case Association:
          for (Map.Entry<String, Object> style : styles.get("association").entrySet())
            jgxAdapter.setCellStyles(style.getKey(), style.getValue().toString(), cell);
          break;
        case Generalization:
          for (Map.Entry<String, Object> style : styles.get("generalization").entrySet())
            jgxAdapter.setCellStyles(style.getKey(), style.getValue().toString(), cell);
          break;
        case None:
          break;   
      }
    }
    jgxAdapter.repaint();
    content.revalidate();
    content.repaint();
    jgxAdapter.refresh();
  }
  
  /* void processBurronPressAdd ()
   * @param ActionEvent event : unused, but necessary for now
   * Prompts the user for a class name and builds a UML object that's added
   * Diagram is repainted
   */
  public void processButtonPressAdd(ActionEvent event) {
    try {
      String name = getInputDialogBox("Add", "Add a class", "Enter a class name:");
      AddCommand command = new AddCommand (name);
      control.getDiagram().execute(command);
      jgxAdapter.repaint();
    } catch (NullPointerException e) {

    }
  }

  /* void processButtonPressRemove ()
   * @param ActionEvent event : unused, but necessary for now
   * Prompts the user for a class name and removes the object
   * Diagram is repainted
   */
  public void processButtonPressRemove(ActionEvent event) {
    try {
      String name = getInputDialogBox("Remove", "Remove a class", "Enter a class name:");
      RemoveCommand command = new RemoveCommand (name);
      control.getDiagram().execute(command);
      jgxAdapter.repaint();
      content.revalidate();
    } catch (NullPointerException e) {

    }
  }

  /* void processButtonPressLoad ()
   * @param ActionEvent event : unused, but necessary for now
   * Prompts the user for a json file to load
   * Draws the diagram from the json file
   */
  public void processButtonPressLoad(ActionEvent event) {
    try {
      String load = getInputDialogBox("Load", "Load", "Enter a json file:");
      control = new Controller();
      control.load(load);
      jgxAdapter = new JGraphXAdapter<ShrugUMLClass, LabeledEdge>(control.getGraph());
      initStylesheet();
      initGraphComponent();
      initLayout ();
      layout.execute(jgxAdapter.getDefaultParent());
      redrawEdges();
      jgxAdapter.repaint();
      content.revalidate();
    } catch (NullPointerException e) {

    }
  }

  /* void processButtonPressSave ()
   * @param ActionEvent event : unused, but necessary for now
   * User is prompted for a json file to save to
   * TODO: basic handling for overwriting files
   */
  public void processButtonPressSave(ActionEvent event) {
    try {
      String save = getInputDialogBox("Save", "Save", "Enter a json file:");
      control.save(save);
    } catch (NullPointerException e) {

    }
  }

  // Edit a class's attributes (both fields and methods)
  public void processButtonPressEdit(ActionEvent event) {
    try {
      String edit = getInputDialogBox("Edit", "Edit a Class", "Enter a class to edit:");
      String add =
          getInputDialogBox(
              "Edit", "Edit a class", "Enter attributes to add separated by commas:");
      String remove =
          getInputDialogBox(
              "Edit", "Edit a class", "Enter attributes to remove separated by commas:");
      if (!remove.isEmpty()) {
        // Parse inputs
        Parser removeParser = new Parser(remove);
        // Construct remove command with method and field lists
        Command r = removeParser.parse();
        r.setClassName(edit);
        RemoveCommand removeCommand = new RemoveCommand (r);
        // Execute
        control.getDiagram().execute(removeCommand);
      }
      if (!add.isEmpty()) {
        // Construct add command with method and field lists      
        Parser addParser = new Parser(add);
        Command a = addParser.parse();
        a.setClassName(edit);
        AddCommand addCommand = new AddCommand (a);
        // Execute
        control.getDiagram().execute(addCommand);
      }
      jgxAdapter.repaint();
      content.revalidate();
      jgxAdapter.refresh();
    } catch (NullPointerException e) {

    }
  }

  // Add a relationship src -> dest with type
  public void processButtonPressAddR(ActionEvent event) {
    try {
      String src = getInputDialogBox("Relationship", "Add a relation", "Enter source class:");
      String dest =
          getInputDialogBox(
              "Relationship",
              "Add a relation",
              "Enter destination classes separated by whitespace:");
      ArrayList<String> destL = new ArrayList<String>(Arrays.asList(dest.trim().split("\\s+")));
      String type =
          getInputDialogBox(
              "Relationship",
              "Add a relation",
              "Enter a type for these relations:"
                            );
      AddCommand command = new AddCommand (src);
      command.setRelationships(destL, RType.valueOf(type.trim()));
      control.getDiagram().execute(command);
      redrawEdges();
      jgxAdapter.repaint();
      content.revalidate();
    } catch (NullPointerException e) {

    }
  }

  // Remove a relationship
  public void processButtonPressRemoveR(ActionEvent event) {
    try {
      String src =
          getInputDialogBox("Relationship", "Remove a relation", "Enter source class:").trim();
      String dest =
          getInputDialogBox(
              "Relationship",
              "Remove a relation",
              "Enter destination classes separated by whitespace:");
      ArrayList<String> destL = new ArrayList<String>(Arrays.asList(dest.trim().split("\\s+")));
      control.removeRelationships(src, destL);
      RemoveCommand command = new RemoveCommand(src);
      command.setRelationships(destL, RType.None);
      jgxAdapter.repaint();
      content.revalidate();
    } catch (NullPointerException e) {

    }
  }

  // Gets a string from a dialog box
  public String getInputDialogBox(String title, String header, String content) {
    String result = JOptionPane.showInputDialog(frame, content, title, JOptionPane.PLAIN_MESSAGE);
    if (result == null || result.trim() == "") {
      return "";
    }
    return result.trim();
  }

  // Shows help message
  public void helpDialog(ActionEvent event) {
    JOptionPane.showMessageDialog(
        frame,
        "Add Class: Adds a class to the diagram\n"
        + "Remove Class: Remove a class from the diagram\n"
        + "Edit: Edit the attributes of a class. Enter the class name to edit, any number of attributes to add (either fields or methods), \n\t\t and any number of attributes to remove, both comma delimited.\n"
        + "Add Relation: Add a relationship src -> dest, valid types are Aggregation, Composition, Association, Generalization, and None\n"
        + "Remove Relation: Remove a relationship src -> dest\n"
        + "Click and drag classes to move them around\n"
        + "Click a class and drag its borders to change its size",
        "Help",
        JOptionPane.PLAIN_MESSAGE);
  }

  public void ProcessButtonPressExportImage (ActionEvent event) {
    try {
      BufferedImage image = mxCellRenderer.createBufferedImage(jgxAdapter, null, 1, Color.WHITE, true, null);
      String filename = getInputDialogBox ("Export", "Export to PNG", "Enter a .png file");
      try {
        ImageIO.write(image, "PNG", new File(filename));   
      }
      catch(IOException e) {
        e.printStackTrace();
      }   
    }
    catch (NullPointerException e) {
    }
  }

  public void processButtonPressLayout (ActionEvent event) {
    initLayout ();
    layout.execute (jgxAdapter.getDefaultParent());
    jgxAdapter.repaint();
    content.revalidate();
  }
}


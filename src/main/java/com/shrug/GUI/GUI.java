package GUI;

import Controller.*;
import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import com.mxgraph.view.*;
import com.mxgraph.util.*;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.model.mxGraphModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jgrapht.event.*;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.*;
import shrugUML.*;


public class GUI {

  private JFrame frame;
  private JButton add, remove, edit, save, load, addR, removeR, help;
  private Controller control = new Controller();
  private JGraphXAdapter<ShrugUMLClass, LabeledEdge> jgxAdapter =
      new JGraphXAdapter<ShrugUMLClass, LabeledEdge>(control.getGraph());

  private mxGraphLayout layout = new mxEdgeLabelLayout(jgxAdapter);
  private mxStylesheet stylesheet = new mxStylesheet();

  
  private mxGraphComponent graph;
  private JPanel content;

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
    style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
    style.put(mxConstants.STYLE_PERIMETER, mxConstants.PERIMETER_ELLIPSE);
    stylesheet.setDefaultVertexStyle (style);

    final Hashtable<String, Object> associationEdgeStyle = new Hashtable<String, Object>();
    associationEdgeStyle.put(mxConstants.STYLE_ROUNDED, true);
    associationEdgeStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
    associationEdgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_OPEN);
    associationEdgeStyle.put(mxConstants.STYLE_ENDSIZE, 15);
    associationEdgeStyle.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
    associationEdgeStyle.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
    associationEdgeStyle.put(mxConstants.STYLE_STROKECOLOR, "#6482B9");
    associationEdgeStyle.put(mxConstants.STYLE_FONTCOLOR, "#446299");
    stylesheet.putCellStyle("association", associationEdgeStyle);
    
    final Hashtable<String, Object> compositionEdgeStyle = new Hashtable<String, Object>();
    compositionEdgeStyle.put(mxConstants.STYLE_ROUNDED, true);
    compositionEdgeStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
    compositionEdgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_DIAMOND);
    compositionEdgeStyle.put(mxConstants.STYLE_ENDSIZE, 15);
    compositionEdgeStyle.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
    compositionEdgeStyle.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
    compositionEdgeStyle.put(mxConstants.STYLE_STROKECOLOR, "#6482B9");
    compositionEdgeStyle.put(mxConstants.STYLE_FONTCOLOR, "#446299");
    //stylesheet.setDefaultEdgeStyle(compositionEdgeStyle);
    stylesheet.putCellStyle("composition", compositionEdgeStyle);

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

    file.add(save);
    file.add(load);

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

    help = new JButton("Help");
    help.addActionListener(this::helpDialog);

    JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
    flow.add(add);
    flow.add(remove);
    flow.add(edit);
    flow.add(addR);
    flow.add(removeR);
    flow.add(help);
    content.add(flow, BorderLayout.NORTH);
  }

  /* void redrawEdges ()
   * routine to redraw each edge using the stylesheet corresponding to its type
   */
  public void redrawEdges () {
    // This feels like cheating
    var styles = jgxAdapter.getStylesheet().getStyles();
    mxIGraphModel model = jgxAdapter.getModel();
    HashMap<mxICell, LabeledEdge> cellToEdgeMap = jgxAdapter.getCellToEdgeMap();
    Set<mxICell> edgeCellSet = cellToEdgeMap.keySet();
    // Iterate through edges to set their styles
    // Right now the style is set, but the edge is still drawn the same
    for (mxICell c : edgeCellSet) {
      switch (cellToEdgeMap.get(c).getLabel()) {
        case Aggregation:
          model.setStyle(c, styles.get("aggregation").toString()
                         .replace("{", "").replace("}", "")
                         .replace(",", ";"));                     
          break;
        case Composition:
          model.setStyle(c, styles.get("composition").toString()
                         .replace("{", "").replace("}", "")
                         .replace(",", ";"));
          break;
        case Association:
          model.setStyle(c, styles.get("association").toString()
                     .replace("{", "").replace("}", "")
                     .replace(",", ";"));
          break;
        case Generalization:
          break;
        case None:
          break;   
      }
    }
    jgxAdapter.repaint();
    content.revalidate();
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
      ShrugUMLClass add = new ShrugUMLClass(name);
      control.addClass(add);
      jgxAdapter.repaint();
      content.revalidate();
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
      ShrugUMLClass remove = control.getDiagram().findClass(name);
      control.removeClass(name);
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
      layout = new mxOrganicLayout(jgxAdapter);
      initStylesheet();
      initGraphComponent();
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

  /*
   * TODO
   */
  public void processButtonPressEdit(ActionEvent event) {
    try {
      String edit = getInputDialogBox("Edit", "Edit a Class", "Enter a class to edit:");
      ShrugUMLClass c = control.getDiagram().findClass(edit);

      String add =
          getInputDialogBox(
              "Edit", "Edit a class", "Enter attributes to add separated by whitespace:");

      String remove =
          getInputDialogBox(
              "Edit", "Edit a class", "Enter attributes to add separated by whitespace:");
      ArrayList<String> addAttr = new ArrayList<String>(Arrays.asList(add.trim().split("\\s+")));
      control.addAttributes(c.getName(), addAttr);
      ArrayList<String> removeAttr =
          new ArrayList<String>(Arrays.asList(remove.trim().split("\\s+")));
      control.removeAttributes(c.getName(), removeAttr);
      jgxAdapter.repaint();
      content.revalidate();
    } catch (NullPointerException e) {

    }
  }

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
      control.addRelationships(src, destL, type.trim());
      redrawEdges();
      jgxAdapter.repaint();
      content.revalidate();
    } catch (NullPointerException e) {

    }
  }

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
      jgxAdapter.repaint();
      content.revalidate();
    } catch (NullPointerException e) {

    }
  }

  public String getInputDialogBox(String title, String header, String content) {
    String result = JOptionPane.showInputDialog(frame, content, title, JOptionPane.PLAIN_MESSAGE);
    if (result == null) {
      return null;
    }
    return result.trim();
  }

public void helpDialog(ActionEvent event) {
    JOptionPane.showMessageDialog(
        frame,
        "Add Class: Adds a class to the diagram\n"
            + "Remove Class: Remove a class from the diagram\n"
            + "Edit: Edit the attributes of a class\n"
            + "Add Relation: Add a relationship src -> dest"
            + "Remove Relation: Remove a relationship src -> dest\n"
            + "Click and drag classes to move them around\n"
            + "Click a class and drag its borders to change its size",
        "Help",
        JOptionPane.PLAIN_MESSAGE);
  }
}

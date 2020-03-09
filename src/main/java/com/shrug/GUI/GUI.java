package GUI;

import Controller.*;
import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
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
  private JButton add, remove, edit, save, load, addR, removeR;
  private Controller control = new Controller();
  private JGraphXAdapter<ShrugUMLClass, DefaultEdge> jgxAdapter =
      new JGraphXAdapter<ShrugUMLClass, DefaultEdge>(control.getGraph());

  private mxGraphLayout layout = new mxOrganicLayout(jgxAdapter);

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
    content.setPreferredSize(new Dimension(600, 400));
    frame.setContentPane(content);

    initMenuBar();
    initButtons();

    initGraphComponent();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  public void initGraphComponent() {
    if (graph != null) content.remove(graph);
    graph = new mxGraphComponent(jgxAdapter);
    layout.execute(jgxAdapter.getDefaultParent());
    content.add(graph, BorderLayout.CENTER);
  }

  /* void initMenuBar ()
   * Initializes the menu options
   * TODO: Change Add and Remove buttons to appear outside of the menu
   */
  public void initMenuBar() {
    final JMenu file = new JMenu("File");
    final JMenu help = new JMenu("Help");
    final JMenu edit = new JMenu("Edit");

    JMenuBar menuBar = new JMenuBar();
    menuBar.add(file);
    menuBar.add(help);

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

    JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
    flow.add(add);
    flow.add(remove);
    flow.add(edit);
    flow.add(addR);
    flow.add(removeR);
    content.add(flow, BorderLayout.NORTH);
  }

  /* void processBurronPressAdd ()
   * @param ActionEvent event : unused, but necessary for now
   * Prompts the user for a class name and builds a UML object that's added
   * Diagram is repainted
   */
  public void processButtonPressAdd(ActionEvent event) {
    // String name = JOptionPane.showInputDialog(null, "Enter a class name:");
    String name = getInputDialogBox("Add", "Add a class", "Enter a class name:");
    ShrugUMLClass add = new ShrugUMLClass(name);
    control.addClass(add);
    jgxAdapter.vertexAdded(
        new GraphVertexChangeEvent<ShrugUMLClass>(
            control.getGraph(), GraphVertexChangeEvent.VERTEX_ADDED, add));
    jgxAdapter.repaint();
    content.revalidate();
  }

  /* void processButtonPressRemove ()
   * @param ActionEvent event : unused, but necessary for now
   * Prompts the user for a class name and removes the object
   * Diagram is repainted
   */
  public void processButtonPressRemove(ActionEvent event) {
    String name = getInputDialogBox("Remove", "Remove a class", "Enter a class name:");
    ShrugUMLClass remove = control.getDiagram().findClass(name);
    control.removeClass(name);
    jgxAdapter.vertexRemoved(
        new GraphVertexChangeEvent<ShrugUMLClass>(
            control.getGraph(), GraphVertexChangeEvent.VERTEX_REMOVED, remove));
    jgxAdapter.repaint();
    content.revalidate();
  }

  /* void processButtonPressLoad ()
   * @param ActionEvent event : unused, but necessary for now
   * Prompts the user for a json file to load
   * Draws the diagram from the json file
   */
  public void processButtonPressLoad(ActionEvent event) {
    String load = getInputDialogBox("Load", "Load", "Enter a json file:");
    control = new Controller();
    control.load(load);
    jgxAdapter = new JGraphXAdapter<ShrugUMLClass, DefaultEdge>(control.getGraph());
    initGraphComponent();
    jgxAdapter.repaint();
    content.revalidate();
  }

  /* void processButtonPressSave ()
   * @param ActionEvent event : unused, but necessary for now
   * User is prompted for a json file to save to
   * TODO: basic handling for overwriting files
   */
  public void processButtonPressSave(ActionEvent event) {
    String save = getInputDialogBox("Save", "Save", "Enter a json file:");
    control.save(save);
  }

  /*
   * TODO
   */
  public void processButtonPressEdit(ActionEvent event) {
    String edit = getInputDialogBox("Edit", "Edit a Class", "Enter a class to edit:");
    ShrugUMLClass c = control.getDiagram().findClass(edit);

    String add =
        getInputDialogBox(
            "Edit", "Edit a class", "Enter attributes to add separated by whitespace:");
    ArrayList<String> addAttr = new ArrayList<String>(Arrays.asList(add.trim().split("\\s+")));
    control.addAttributes(c.getName(), addAttr);

    String remove =
        getInputDialogBox(
            "Edit", "Edit a class", "Enter attributes to add separated by whitespace:");
    ArrayList<String> removeAttr =
        new ArrayList<String>(Arrays.asList(remove.trim().split("\\s+")));
    control.removeAttributes(c.getName(), removeAttr);
    jgxAdapter.repaint();
    content.revalidate();
  }

  public void processButtonPressAddR(ActionEvent event) {
    String src = getInputDialogBox("Relationship", "Add a relation", "Enter source class:").trim();
    String dest =
        getInputDialogBox(
            "Relationship", "Add a relation", "Enter destination classes separated by whitespace:");
    ArrayList<String> destL = new ArrayList<String>(Arrays.asList(dest.trim().split("\\s+")));
    control.addRelationships(src, destL);
    for (String className : destL) {
      jgxAdapter.edgeAdded(
          new GraphEdgeChangeEvent<ShrugUMLClass, DefaultEdge>(
              control.getGraph(),
              GraphEdgeChangeEvent.EDGE_ADDED,
              control.getDiagram().getRelationship(src, className),
              control.getDiagram().findClass(src),
              control.getDiagram().findClass(className)));
    }
    jgxAdapter.repaint();
    content.revalidate();
  }

  public void processButtonPressRemoveR(ActionEvent event) {
    String src =
        getInputDialogBox("Relationship", "Remove a relation", "Enter source class:").trim();
    String dest =
        getInputDialogBox(
            "Relationship",
            "Remove a relation",
            "Enter destination classes separated by whitespace:");
    ArrayList<String> destL = new ArrayList<String>(Arrays.asList(dest.trim().split("\\s+")));
    for (String className : destL) {
      jgxAdapter.edgeRemoved(
          new GraphEdgeChangeEvent<ShrugUMLClass, DefaultEdge>(
              control.getGraph(),
              GraphEdgeChangeEvent.BEFORE_EDGE_REMOVED,
              control.getDiagram().getRelationship(src, className),
              control.getDiagram().findClass(src),
              control.getDiagram().findClass(className)));
    }
    control.removeRelationships(src, destL);
    jgxAdapter.repaint();
    content.revalidate();
  }

  public String getInputDialogBox(String title, String header, String content) {
    String result = JOptionPane.showInputDialog(frame, content, title, JOptionPane.PLAIN_MESSAGE);
    if (result == null) {
      return ".";
    }
    return result;
  }
}

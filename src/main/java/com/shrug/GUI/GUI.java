package GUI;

import Controller.*;
import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import org.jgrapht.*;
import org.jgrapht.event.*;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.*;
import shrugUML.*;

public class GUI extends JFrame {
  private JButton add, remove, edit, save, load;
  private Controller control = new Controller();
  private JGraphXAdapter<ShrugUMLClass, DefaultEdge> jgxAdapter =
      new JGraphXAdapter<ShrugUMLClass, DefaultEdge>(control.getGraph());

  private mxIGraphLayout layout = new mxCircleLayout(jgxAdapter);

  // private SwingNode diagramNode = new SwingNode();
  private JPanel panel = new JPanel(new BorderLayout());

  private JLabel diagramLabel = new JLabel();

  public GUI() {
    super("UML Editor");
    start();
  }

  public void start() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // initDiagram(borderLayout);
    // initMenuBar(borderPane);
    initButtons();

    panel.add(createSwingDiagram(), BorderLayout.CENTER);
    add(diagramLabel);
    setVisible(true);
  }

  /* SwingNode createSwingDiagram ()
   * This function adds an mxGraphComponent to the GUI using the JGraphXAdapter
   * The SwingNode returned is then added to the BorderPane
   */
  public JLabel createSwingDiagram() {
    diagramLabel.add(new mxGraphComponent(jgxAdapter));
    return diagramLabel;
  }

  /* void initButtons ()
   * Initializes Buttons and adds them to a FlowPane to be displayed at the top of the window
   * TODO: Use this for Add/Remove instead of the MenuBar
   */
  public void initButtons() {
    add = new JButton("Add");
    add.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            processButtonPressAdd();
          }
        });

    remove = new JButton("Remove");
    remove.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            processButtonPressRemove();
          }
        });
  }

  /* void processBurronPressAdd ()
   * @param ActionEvent event : unused, but necessary for now
   * Prompts the user for a class name and builds a UML object that's added
   * Diagram is repainted
   */
  public void processButtonPressAdd() {
    // String name = JOptionPane.showInputDialog(null, "Enter a class name:");
    String name = getInputDialogBox("Add", "Add a class", "Enter a class name:");
    ShrugUMLClass add = new ShrugUMLClass(name);
    control.addClass(add);
    jgxAdapter.vertexAdded(
        new GraphVertexChangeEvent<ShrugUMLClass>(
            control.getGraph(), GraphVertexChangeEvent.VERTEX_ADDED, add));
    jgxAdapter.repaint();
  }

  /* void processButtonPressRemove ()
   * @param ActionEvent event : unused, but necessary for now
   * Prompts the user for a class name and removes the object
   * Diagram is repainted
   */
  public void processButtonPressRemove() {
    String name = getInputDialogBox("Remove", "Remove a class", "Enter a class name:");
    ShrugUMLClass remove = control.getDiagram().findClass(name);
    control.removeClass(name);
    jgxAdapter.vertexRemoved(
        new GraphVertexChangeEvent<ShrugUMLClass>(
            control.getGraph(), GraphVertexChangeEvent.VERTEX_REMOVED, remove));
    jgxAdapter.repaint();
  }

  /* void processButtonPressLoad ()
   * @param ActionEvent event : unused, but necessary for now
   * Prompts the user for a json file to load
   * Draws the diagram from the json file
   */
  public void processButtonPressLoad() {
    String load = getInputDialogBox("Load", "Load", "Enter a json file:");
    control.load(load);
    jgxAdapter = new JGraphXAdapter<ShrugUMLClass, DefaultEdge>(control.getGraph());
    createSwingDiagram();
    jgxAdapter.repaint();
  }

  /* void processButtonPressSave ()
   * @param ActionEvent event : unused, but necessary for now
   * User is prompted for a json file to save to
   * TODO: basic handling for overwriting files
   */
  public void processButtonPressSave() {
    String save = getInputDialogBox("Save", "Save", "Enter a json file:");
    control.save(save);
  }

  /*
   * TODO
   */
  public void processButtonPressEdit() {}

  public String getInputDialogBox(String title, String header, String content) {
    JOptionPane dialog = new JOptionPane();
    String result = dialog.showInputDialog(content);
    return result;
  }
}

package GUI;

import Controller.*;
import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import java.util.*;
import javafx.application.Application;
import javafx.embed.swing.*;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javax.swing.*;
import org.jgrapht.*;
import org.jgrapht.event.*;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.*;
import shrugUML.*;

public class GUI extends Application {
  private Button add, remove, edit, save, load;
  private Controller control = new Controller();
  private JGraphXAdapter<ShrugUMLClass, DefaultEdge> jgxAdapter =
      new JGraphXAdapter<ShrugUMLClass, DefaultEdge>(control.getGraph());

  mxIGraphLayout layout = new mxCircleLayout(jgxAdapter);

  private SwingNode diagramNode = new SwingNode();
  private JPanel diagramFrame = new JPanel();

  public GUI() {}

  @Override
  public void start(Stage primaryStage) {

    primaryStage.setTitle("GUI");

    BorderPane borderPane = new BorderPane();

    Scene scene = new Scene(borderPane, 600, 600);

    initDiagram(borderPane);
    initMenuBar(borderPane);

    borderPane.setCenter(createSwingDiagram());

    borderPane.setMargin(borderPane.getCenter(), new Insets(30));

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /* SwingNode createSwingDiagram ()
   * This function adds an mxGraphComponent to the GUI using the JGraphXAdapter
   * The SwingNode returned is then added to the BorderPane
   */
  public SwingNode createSwingDiagram() {
    diagramFrame.removeAll();
    diagramFrame.add(new mxGraphComponent(jgxAdapter));
    diagramNode.setContent(diagramFrame);
    return diagramNode;
  }

  /* void initDiagram ()
   * @param BorderPane borderPane : The Pane that the diagram is in
   * Sets some styles for the diagram frame
   */
  public void initDiagram(BorderPane borderPane) {
    StackPane diagram = new StackPane();
    diagram.setStyle("-fx-background-color: antiquewhite;");
    diagram.setStyle("-fx-border-style: solid;");
    diagram.setStyle("-fx-border-color: black;");
  }

  /* void initMenuBar ()
   * Initializes the menu options
   * TODO: Change Add and Remove buttons to appear outside of the menu
   */
  public void initMenuBar(BorderPane borderPane) {
    final Menu file = new Menu("File");
    final Menu help = new Menu("Help");
    final Menu edit = new Menu("Edit");

    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(file);
    menuBar.getMenus().add(help);
    menuBar.getMenus().add(edit);

    // Set callback functions for each button
    MenuItem add = new MenuItem("Add");
    add.setOnAction(this::processButtonPressAdd);
    MenuItem remove = new MenuItem("Remove");
    remove.setOnAction(this::processButtonPressRemove);
    MenuItem save = new MenuItem("Save");
    save.setOnAction(this::processButtonPressSave);
    MenuItem load = new MenuItem("Load");
    load.setOnAction(this::processButtonPressLoad);

    file.getItems().add(add);
    file.getItems().add(remove);
    file.getItems().add(save);
    file.getItems().add(load);

    borderPane.setTop(menuBar);
  }

  /* void initButtons ()
   * Initializes Buttons and adds them to a FlowPane to be displayed at the top of the window
   * TODO: Use this for Add/Remove instead of the MenuBar
   */
  public void initButtons(BorderPane borderPane) {
    add = new Button("Add");
    add.setOnAction(this::processButtonPressAdd);

    remove = new Button("Remove");
    remove.setOnAction(this::processButtonPressRemove);

    load = new Button("Load");
    load.setOnAction(this::processButtonPressLoad);

    save = new Button("Save");
    save.setOnAction(this::processButtonPressSave);

    edit = new Button("Edit");
    edit.setOnAction(this::processButtonPressEdit);

    FlowPane options = new FlowPane(add, remove, load, save, edit);
    options.setAlignment(Pos.CENTER);
    options.setHgap(10);
    borderPane.setTop(options);
  }

  /* void processBurronPressAdd ()
   * @param ActionEvent event : unused, but necessary for now
   * Prompts the user for a class name and builds a UML object that's added
   * Diagram is repainted
   */
  public void processButtonPressAdd(ActionEvent event) {
    // String name = JOptionPane.showInputDialog(null, "Enter a class name:");
    String name;
    TextInputDialog dialog = new TextInputDialog("");
    dialog.setTitle("Class name");
    dialog.setContentText("Class name:");
    dialog.setHeaderText("Add Class");
    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
      name = result.get();
      ShrugUMLClass add = new ShrugUMLClass(name);
      control.addClass(add);
      jgxAdapter.vertexAdded(
          new GraphVertexChangeEvent<ShrugUMLClass>(
              control.getGraph(), GraphVertexChangeEvent.VERTEX_ADDED, add));
      layout.execute(jgxAdapter.getDefaultParent());
      jgxAdapter.repaint();
      diagramFrame.repaint();
      diagramFrame.revalidate();
    } else return;
  }

  /* void processButtonPressRemove ()
   * @param ActionEvent event : unused, but necessary for now
   * Prompts the user for a class name and removes the object
   * Diagram is repainted
   */
  public void processButtonPressRemove(ActionEvent event) {
    String name;
    TextInputDialog dialog = new TextInputDialog("");
    dialog.setTitle("Class name");
    dialog.setContentText("Class name:");
    dialog.setHeaderText("Remove Class");
    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
      name = result.get();
      ShrugUMLClass remove = control.getDiagram().findClass(name);
      control.removeClass(name);
      jgxAdapter.vertexRemoved(
          new GraphVertexChangeEvent<ShrugUMLClass>(
              control.getGraph(), GraphVertexChangeEvent.VERTEX_REMOVED, remove));
      layout.execute(jgxAdapter.getDefaultParent());
      jgxAdapter.repaint();
      diagramFrame.repaint();
      diagramFrame.revalidate();
    }
  }

  /* void processButtonPressLoad ()
   * @param ActionEvent event : unused, but necessary for now
   * Prompts the user for a json file to load
   * Draws the diagram from the json file
   */
  public void processButtonPressLoad(ActionEvent event) {
    String load;
    TextInputDialog dialog = new TextInputDialog("");
    dialog.setTitle(".json file name:");
    dialog.setContentText(".json file name:");
    dialog.setHeaderText("Load");
    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
      load = result.get();
      control.load(load);
      jgxAdapter = new JGraphXAdapter<ShrugUMLClass, DefaultEdge>(control.getGraph());
      createSwingDiagram();
      layout.execute(jgxAdapter.getDefaultParent());
      jgxAdapter.repaint();
      diagramFrame.repaint();
      diagramFrame.revalidate();
    }
  }

  /* void processButtonPressSave ()
   * @param ActionEvent event : unused, but necessary for now
   * User is prompted for a json file to save to
   * TODO: basic handling for overwriting files
   */
  public void processButtonPressSave(ActionEvent event) {
    String save;
    TextInputDialog dialog = new TextInputDialog("");
    dialog.setTitle(".json file name:");
    dialog.setContentText(".json file name:");
    dialog.setHeaderText("Save");
    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
      save = result.get();
      control.save(save);
    }
  }

  /*
   * TODO
   */
  public void processButtonPressEdit(ActionEvent event) {}
}

package GUI;

import Controller.*;
import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
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
  private JGraphXAdapter<ShrugUMLClass, DefaultEdge> jgxAdapter = new JGraphXAdapter<ShrugUMLClass, DefaultEdge>(control.getGraph());

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

  // function to print class names and attributes

  public SwingNode createSwingDiagram() {
    diagramFrame.add(new mxGraphComponent(jgxAdapter));
    diagramNode.setContent(diagramFrame);
    return diagramNode;
  }

  public void initDiagram(BorderPane borderPane) {
    StackPane diagram = new StackPane();
    diagram.setStyle("-fx-background-color: antiquewhite;");
    diagram.setStyle("-fx-border-style: solid;");
    diagram.setStyle("-fx-border-color: black;");
  }

  public void initMenuBar(BorderPane borderPane) {
    final Menu file = new Menu("File");
    final Menu help = new Menu("Help");
    final Menu edit = new Menu("Edit");

    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().add(file);
    menuBar.getMenus().add(help);
    menuBar.getMenus().add(edit);

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

  public void processButtonPressAdd(ActionEvent event) {
    ShrugUMLClass add = new ShrugUMLClass("a");
    control.addClass("a");
    jgxAdapter.vertexAdded(new GraphVertexChangeEvent<ShrugUMLClass>(control.getGraph(), GraphVertexChangeEvent.VERTEX_ADDED, add));
    layout.execute(jgxAdapter.getDefaultParent());
    diagramFrame.repaint();
    diagramFrame.revalidate();
    // call controller to add vertex, controller returns class, and we need controller to return
    // diagram from model
    // call adapter.vertexAdded()
  }

  public void processButtonPressRemove(ActionEvent event) {}

  public void processButtonPressLoad(ActionEvent event) {}

  public void processButtonPressSave(ActionEvent event) {}

  public void processButtonPressEdit(ActionEvent event) {}
}

/*public class GUI {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}*/

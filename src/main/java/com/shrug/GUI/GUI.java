package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import Controller.*;

public class GUI extends Application {
  private Button add, remove, edit, save, load;

  public GUI() {}

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("GUI");

    BorderPane borderPane = new BorderPane();

    Scene scene = new Scene(borderPane, 600, 600);

    initDiagram(borderPane);
    initButtons(borderPane);

    borderPane.setMargin(borderPane.getCenter(), new Insets(30));

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void initDiagram (BorderPane borderPane) {
    StackPane diagram = new StackPane();
    diagram.setStyle("-fx-background-color: antiquewhite;");
    diagram.setStyle("-fx-border-style: solid;");
    diagram.setStyle("-fx-border-color: black;");
    borderPane.setCenter(diagram);
  }

  public void initButtons (BorderPane borderPane) {
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

  public void processButtonPressAdd(ActionEvent event) {}

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

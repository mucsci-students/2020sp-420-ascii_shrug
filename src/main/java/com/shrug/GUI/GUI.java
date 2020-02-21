package GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class GUI extends Application {
  private Button add, remove, edit, save, load;

  public GUI() {}

  /*public static void main(String[] args) {
      launch(Args);
  }*/

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("GUI");

    BorderPane root = new BorderPane();
    Scene scene = new Scene(root, 600, 600);

    add = new Button("Add");
    remove = new Button("Remove");
    load = new Button("Load");
    save = new Button("Save");
    edit = new Button("Edit");

    FlowPane options = new FlowPane(add, remove, load, save, edit);
    options.setAlignment(Pos.CENTER);
    options.setHgap(10);

    root.setTop(options);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}

/*public class GUI {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}*/

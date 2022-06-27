package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.BillOfLading;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BillOfLading billOfLading = new BillOfLading();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("layout/authorization.fxml")));
        primaryStage.setTitle("Chef");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

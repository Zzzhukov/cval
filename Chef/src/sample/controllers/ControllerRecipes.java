package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerRecipes {
    DatabaseHandler databaseHandler = new DatabaseHandler();
    @FXML private Label recipeLabel;
    @FXML private Button dish1Button;
    @FXML private Button dish2Button;
    @FXML private Button dish3Button;
    @FXML private Button dish4Button;
    @FXML private Button dish5Button;
    @FXML private Button backButton;
    @FXML
    void initialize() {
        dish1Button.setOnAction(actionEvent -> {
            try {
                recipeLabel.setText(normalizationString(databaseHandler.returnRecipe("Борщ")));
            } catch (SQLException | ClassNotFoundException throwable) {
                throwable.printStackTrace();
            }
        });

        dish2Button.setOnAction(actionEvent -> {
            try {
                recipeLabel.setText(normalizationString(databaseHandler.returnRecipe("Плов")));
            } catch (SQLException | ClassNotFoundException throwable) {
                throwable.printStackTrace();
            }
        });

        dish3Button.setOnAction(actionEvent -> {
            try {
                recipeLabel.setText(normalizationString(databaseHandler.returnRecipe("Пельмени")));
            } catch (SQLException | ClassNotFoundException throwable) {
                throwable.printStackTrace();
            }
        });

        dish4Button.setOnAction(actionEvent -> {
            try {
                recipeLabel.setText(normalizationString(databaseHandler.returnRecipe("Венегрет")));
            } catch (SQLException | ClassNotFoundException throwable) {
                throwable.printStackTrace();
            }
        });

        dish5Button.setOnAction(actionEvent -> {
            try {
                recipeLabel.setText(normalizationString(databaseHandler.returnRecipe("Шаурма")));
            } catch (SQLException | ClassNotFoundException throwable) {
                throwable.printStackTrace();
            }
        });

        backButton.setOnAction(actionEvent -> openOtherWindow());
    }

    private void openOtherWindow(){
        backButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/layout/menu_formation.fxml"));
        try {
            loader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private String normalizationString(String recipe){
        String[] stringArray = recipe.split(" ");
        StringBuilder helpedString = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for (String s : stringArray) {
            helpedString.append(s).append(" ");
            if (helpedString.length() > 30) {
                result.append(helpedString).append("\n");
                helpedString = new StringBuilder();
            }
        }
        result.append(helpedString).append("\n");
        return result.toString();
    }
}

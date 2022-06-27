package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.database.User;

public class ControllerRegistration {
    DatabaseHandler databaseHandler = new DatabaseHandler();
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button buttonBack;
    @FXML private Button buttonRegistration;
    @FXML private Label errorLabel;
    @FXML private Label heading;
    @FXML private TextField login;
    @FXML private TextField password;
    @FXML private Label title;
    @FXML
    void initialize() {
        buttonBack.setOnAction(actionEvent -> {
            openAuthorizationWindow();
        });

        buttonRegistration.setOnAction(actionEvent -> {
            String loginText = login.getText().trim();
            String passwordText = password.getText().trim();

            if(!loginText.equals("") && !passwordText.equals("")){
                User.setUsername(loginText);
                User.setPassword(passwordText);
                User.setAdministrator(false);

                try {
                    databaseHandler.signUpUser();
                } catch (SQLException | ClassNotFoundException throwable) {
                    throwable.printStackTrace();
                }
                openAuthorizationWindow();
            }
            else {
                errorLabel.setText("Поле логина или пароля пустое");
            }
        });
    }

    private void openAuthorizationWindow(){
        buttonRegistration.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/layout/authorization.fxml"));
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
}

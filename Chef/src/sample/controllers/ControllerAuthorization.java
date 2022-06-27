package sample.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.database.BillOfLading;
import sample.database.DatabaseHandler;
import sample.database.User;

public class ControllerAuthorization {
    @FXML private Button buttonAuthorization;
    @FXML private Button buttonRegistration;
    @FXML private Label errorLabel;
    @FXML private TextField login;
    @FXML private TextField password;
    @FXML
    void initialize() {
        buttonRegistration.setOnAction(actionEvent -> openOtherWindow("/sample/layout/registration.fxml"));

        buttonAuthorization.setOnAction(actionEvent -> {
            String loginText = login.getText().trim();
            String passwordText = password.getText().trim();

            if(!loginText.equals("") && !passwordText.equals("")) {
                try {
                    if(loginUser(loginText, passwordText)) {
                        User.setAdministrator(new DatabaseHandler().checkingForAnAdministrator(User.getUsername()));
                        openOtherWindow("/sample/layout/menu_formation.fxml");
                    }
                    else {
                        errorLabel.setText("Логин или пароль неверен");
                    }
                } catch (SQLException | ClassNotFoundException throwable) {
                    throwable.printStackTrace();
                }
            }
            else {
                errorLabel.setText("Поле логина или пароля пустое");
            }
        });
    }

    private boolean loginUser(String login, String password) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        User.setUsername(login);
        User.setPassword(password);
        BillOfLading.menu = new StringBuilder();
        BillOfLading.setExpenses(0);
        ResultSet resultSet = databaseHandler.signInUser();
        return resultSet.next();
    }

    private void openOtherWindow(String window){
        BillOfLading.update();
        buttonRegistration.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
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

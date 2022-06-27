package sample.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.database.Const;
import sample.database.DatabaseHandler;
import sample.database.UsersData;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerAdministratorRoom {
    @FXML private Label exceptionLabel;
    @FXML private TextField searchTextField;

    @FXML private Button backButton;
    @FXML private Button updateButton;
    @FXML private Button searchButton;
    @FXML private Button reverseRoleButton;
    @FXML private Button banButton;
    @FXML private Button clearHistoryButton;

    @FXML private TableView<UsersData> usersTable;
    @FXML private TableColumn<UsersData, Integer> idColumn;
    @FXML private TableColumn<UsersData, String> usernameColumn;
    @FXML private TableColumn<UsersData, String> passwordColumn;
    @FXML private TableColumn<UsersData, Boolean> isAdministratorColumn;

    @FXML private TableView<UsersData> historyTable;
    @FXML private TableColumn<UsersData, String> historyUsername;
    @FXML private TableColumn<UsersData, String> historyMenu;
    @FXML private TableColumn<UsersData, Integer> historyExpenses;


    DatabaseHandler databaseHandler = new DatabaseHandler();

    @FXML void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        isAdministratorColumn.setCellValueFactory(new PropertyValueFactory<>("administrator"));

        historyUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        historyMenu.setCellValueFactory(new PropertyValueFactory<>("menu"));
        historyExpenses.setCellValueFactory(new PropertyValueFactory<>("expenses"));

        updateUsersTable();
        updateHistoryTable();

        clearHistoryButton.setOnAction(actionEvent -> {
            try {
                databaseHandler.clearHistoryTable();
                updateHistoryTable();
            } catch (SQLException | ClassNotFoundException throwable) {
                throwable.printStackTrace();
            }
        });

        reverseRoleButton.setOnAction(actionEvent -> {
            try {
                boolean role = databaseHandler.checkingForAnAdministrator(searchTextField.getText());
                databaseHandler.updateRole(searchTextField.getText(), role);
                usersTable.setItems(databaseHandler.returnUsers(
                        "SELECT * FROM " + Const.USER_TABLE +
                                " WHERE " + Const.USERNAMES + " = \"" + searchTextField.getText() + "\""));
            } catch (SQLException | ClassNotFoundException throwable) {
                exceptionLabel.setText("пользователь не найден");
            }
        });

        banButton.setOnAction(actionEvent -> {
            try {
                databaseHandler.deleteUser(searchTextField.getText());
                updateUsersTable();
            } catch (SQLException | ClassNotFoundException throwable) {
                exceptionLabel.setText("пользователь не найден");
            }
        });

        searchButton.setOnAction(
                actionEvent -> {
                    ObservableList<UsersData> user = null;
                    try {
                        user = databaseHandler.returnUsers(
                                "SELECT * FROM " + Const.USER_TABLE +
                                        " WHERE " + Const.USERNAMES + " = \"" + searchTextField.getText() + "\"");
                    } catch (SQLException | ClassNotFoundException throwable) {
                        throwable.printStackTrace();
                    }
                    assert user != null;
                    if(user.isEmpty()){
                        exceptionLabel.setText("пользователь не найден");
                    }
                    else {
                        usersTable.setItems(user);
                    }
                });

        updateButton.setOnAction(actionEvent -> updateUsersTable());

        backButton.setOnAction(actionEvent -> openOtherWindow());
    }

    private void updateUsersTable(){
        try {
            usersTable.setItems(databaseHandler.returnUsers("SELECT * FROM " + Const.USER_TABLE));
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    private void updateHistoryTable(){
        try {
            historyTable.setItems(databaseHandler.returnHistory("SELECT * FROM " + Const.HISTORY_TABLE));
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
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
}

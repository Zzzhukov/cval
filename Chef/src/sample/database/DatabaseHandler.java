package sample.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser()
            throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USERNAMES + "," + Const.PASSWORDS + "," + Const.ADMINISTRATORS + ") VALUES(?,?,?)";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, User.getUsername());
        preparedStatement.setString(2, User.getPassword());
        preparedStatement.setBoolean(3, User.isAdministrator());

        preparedStatement.executeUpdate();
    }

    public ResultSet signInUser() throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERNAMES + "=? AND " + Const.PASSWORDS + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setString(1, User.getUsername());
        preparedStatement.setString(2, User.getPassword());

        return preparedStatement.executeQuery();
    }

    public Boolean checkingForAnAdministrator(String username) throws SQLException, ClassNotFoundException {
        String select = "SELECT " + Const.ADMINISTRATORS + " FROM " + Const.USER_TABLE + " WHERE " + Const.USERNAMES + "=\"" + username + "\"";

        Statement statement = getDbConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(select);
        resultSet.next();

        return resultSet.getBoolean(1);
    }

    public void writeToHistory() throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.HISTORY_TABLE + "(" +
                Const.USERNAMES + "," + Const.MENU + "," + Const.EXPENSES +") VALUES(?,?,?)";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, User.getUsername());
        preparedStatement.setString(2, BillOfLading.menu.toString());
        preparedStatement.setInt(3, BillOfLading.getExpenses());

        preparedStatement.executeUpdate();
    }



    public void updateRole(String username, boolean role) throws SQLException, ClassNotFoundException {
        final int IDU = returnIdUser(username);
        int roleInt = role ? 0 : 1;

        String select = "UPDATE chef.users SET isadministrator = \"" + roleInt + "\" WHERE idusers = \"" + IDU + "\"";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.executeUpdate();
    }

    public void deleteUser(String username) throws SQLException, ClassNotFoundException {
        final int IDU = returnIdUser(username);
        String select = "DELETE FROM chef.users WHERE idusers = \"" + IDU + "\"";
        getDbConnection().prepareStatement(select).executeUpdate();
    }

    private int returnIdUser(String username) throws SQLException, ClassNotFoundException {
        String select = "SELECT " + Const.ID_USERS + " FROM " + Const.USER_TABLE + " WHERE " + Const.USERNAMES + "=\"" + username + "\"";
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(select);
        resultSet.next();
        return resultSet.getInt(1);
    }

    public ObservableList<UsersData> returnUsers(String select) throws SQLException, ClassNotFoundException {
        ObservableList<UsersData> userObservableList = FXCollections.observableArrayList();
        Statement statement = getDbConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(select);
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String username = resultSet.getString(2);
            String password = resultSet.getString(3);
            boolean isAdministrator = resultSet.getBoolean(4);

            userObservableList.add(new UsersData(id, username, password, isAdministrator));
        }
        return userObservableList;
    }

    public ObservableList<UsersData> returnHistory(String select) throws SQLException, ClassNotFoundException {
        ObservableList<UsersData> historyObservableList = FXCollections.observableArrayList();
        Statement statement = getDbConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(select);
        while (resultSet.next()){
            String username = resultSet.getString(2);
            String menu = resultSet.getString(3);
            int expenses = resultSet.getInt(4);

            historyObservableList.add(new UsersData(username, menu, expenses));
        }
        return historyObservableList;
    }

    public void clearHistoryTable() throws SQLException, ClassNotFoundException {
        getDbConnection().prepareStatement("TRUNCATE chef.story;").executeUpdate();
    }

    public String returnRecipe(String dish) throws SQLException, ClassNotFoundException {
        Statement statement = getDbConnection().createStatement();
        String select = "SELECT recipe FROM chef.dishes WHERE dish = \"" + dish + "\";";
        ResultSet resultSet = statement.executeQuery(select);
        resultSet.next();
        return resultSet.getString(1);
    }
}

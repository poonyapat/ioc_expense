package ku.cs.jdbc;

import ku.cs.models.Account;

import java.sql.*;
import java.text.ParseException;

public class DBConnector {
    private static DBConnector ourInstance = new DBConnector();
    private Connection connection;
    private DatabaseMetaData dm;
    private Statement statement;

    private DBConnector() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:se-expense.db";
            connection = DriverManager.getConnection(url);
            dm = connection.getMetaData();
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBConnector getInstance() {
        return ourInstance;
    }

    public Account loadAccount() {
        try {
            ResultSet resultSet = statement.executeQuery(DBQuery.findAllTransaction);
            Account account = new Account();
            while (resultSet.next()) {
                String date = resultSet.getString("Date");
                float amount = resultSet.getInt("Amount");
                String description = resultSet.getString("Description");
                if (amount > 0) {
                    account.deposit(amount, date, description);
                } else {
                    account.withdraw(amount, date, description);
                }
            }
            return account;
        } catch (SQLException | ParseException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void addTransaction(String date, float amount, String description){
        try {
            ResultSet resultSet = statement.executeQuery(DBQuery.addTransaction(date,amount,description));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void updateTransaction(String date, String column, String newValue){
        try {
            ResultSet resultSet = statement.executeQuery(DBQuery.updateTransaction(date, column, newValue));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

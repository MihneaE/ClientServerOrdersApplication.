package SQLDataBase;

import Model.*;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DeliveryAccountsDataBase implements Serializable{
    private transient Connection connection;
    private transient Statement statement;
    private transient ResultSet resultSet;

    public DeliveryAccountsDataBase() {
        this.connection = null;
        this.statement = null;
        this.resultSet = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:deliveryAccounts.db");
        }
        catch (Exception e)
        {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }

        try {
            System.out.println("Connected to: " + connection.getMetaData().getURL());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public Connection connect()
    {
        String url = new String("jdbc:sqlite:deliveryAccounts.db");
        Connection conn = null;

        try
        {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return  conn;
    }

    public void addAccountToDatabase(DeliveryAccount account)
    {
        String sql = "INSERT INTO Accounts (name, gmail, password) VALUES (?, ?, ?)";

        try(PreparedStatement preparedStatement = this.connect().prepareStatement(sql))
        {
            preparedStatement.setString(1, account.getName());
            preparedStatement.setString(2, account.getGmail());
            preparedStatement.setString(3, account.getPassword());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        try
        {
            if (connect() != null)
                connect().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DeliveryAccount> loadAccounts()
    {
        List<DeliveryAccount> accounts = new ArrayList<>();

        try
        {
            statement = connect().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Accounts");

            while (resultSet.next())
            {
                String name = resultSet.getString("name");
                String gmail = resultSet.getString("gmail");
                String passoword = resultSet.getString("password");

                DeliveryAccount account = new DeliveryAccount(name, gmail, passoword);

                accounts.add(account);
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getClass().getName() + " " + e.getMessage());
            System.exit(0);
        }

        try
        {
            if (connect() != null)
                connect().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return accounts;
    }

    public void close()
    {
        try
        {
            if (connection != null)
                connection.close();

            if (statement != null)
                statement.close();

            if (resultSet != null)
                resultSet.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

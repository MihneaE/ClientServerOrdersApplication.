package SQLDataBase;

import Model.Drink;
import Model.User;

import javax.crypto.SealedObject;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataBase implements Serializable {
    private transient Connection connection;
    private transient Statement statement;
    private transient ResultSet resultSet;

    public UserDataBase() throws SQLException {
        this.connection = null;
        this.statement = null;
        this.resultSet = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        }
        catch (Exception e)
        {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }

        System.out.println("Connected to: " + connection.getMetaData().getURL());
    }

    public Connection connect()
    {
        String url = new String("jdbc:sqlite:users.db");
        Connection conn = null;

        try
        {
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return conn;
    }

    public void addUserToDataBase(User user)
    {
        String sql = "INSERT INTO Users (id, name, password, firstName, lastName, age, town, country, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setInt(6, user.getAge());
            preparedStatement.setString(7, user.getTown());
            preparedStatement.setString(8, user.getCountry());
            preparedStatement.setString(9, user.getAddress());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        try
        {
            if (this.connect() != null)
                this.connect().close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteUserFromDataBase(String name)
    {
        String sql = "DELETE FROM Users WHERE name = ?";

        try
        {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        try
        {
            if (this.connect() != null)
                this.connect().close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateUserFromDataBase(String name, User user)
    {
        String sql = "UPDATE Users SET id = ?, name = ?, password = ?, firstName = ?, lastName = ?, age = ?, town = ?, country = ?, address = ? WHERE name = ?";

        try
        {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setInt(6, user.getAge());
            preparedStatement.setString(7, user.getTown());
            preparedStatement.setString(8, user.getCountry());
            preparedStatement.setString(9, user.getAddress());
            preparedStatement.setString(10, name);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        try
        {
            if (this.connect() != null)
                this.connect().close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public List<User> loadUsers()
    {
        List<User> users = new ArrayList<>();

        try
        {
            statement = connect().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Users");

            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                String town = resultSet.getString("town");
                String country = resultSet.getString("country");
                String address = resultSet.getString("address");

                User user = new User(id, name, password, firstName, lastName, age, town, country, address);
                users.add(user);
            }

        } catch (SQLException e)
        {
            System.err.println(e.getClass().getName() + " " + e.getMessage());
            System.exit(0);
        }

        try
        {
            if (this.connect() != null)
                this.connect().close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return users;
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

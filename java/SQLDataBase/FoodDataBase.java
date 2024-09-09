package SQLDataBase;

import Model.Food;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDataBase implements Serializable {
    private transient Connection connection;
    private transient Statement statement;
    private transient ResultSet resultSet;

    public FoodDataBase() throws SQLException {
        this.connection = null;
        this.statement = null;
        this.resultSet = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:foods.db");
        }
        catch (Exception e)
        {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }

        System.out.println("Connected to: " + connection.getMetaData().getURL());
    }

    public Connection connect()
    {
        String url = new String("jdbc:sqlite:foods.db");
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

    public void addFoodToDataBase(String table, Food food)
    {
        String sql = "INSERT INTO " + table + "(id, name, description, grams, price, rating, quantity) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = this.connect().prepareStatement(sql);)
        {
            preparedStatement.setInt(1, food.getId());
            preparedStatement.setString(2, food.getName());
            preparedStatement.setString(3, food.getDescription());
            preparedStatement.setInt(4, food.getGrams());
            preparedStatement.setInt(5, food.getPrice());
            preparedStatement.setDouble(6, food.getRating());
            preparedStatement.setInt(7, food.getQuantity());

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

    public void deleteFoodFromDataBase(String table, String name)
    {
        String sql = "DELETE FROM " + table + " WHERE name = ? ";

        try(PreparedStatement preparedStatement = this.connect().prepareStatement(sql))
        {
            preparedStatement.setString(1, name);
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

    public void updateFoodFromDataBase(String table, String name, Food food)
    {
        String sql = "UPDATE " + table + " SET id = ?, name = ?, description = ?, grams = ?, price = ?, rating = ?,quantity = ? WHERE name = ?";

        try(PreparedStatement preparedStatement = this.connect().prepareStatement(sql))
        {
            preparedStatement.setInt(1, food.getId());
            preparedStatement.setString(2, food.getName());
            preparedStatement.setString(3, food.getDescription());
            preparedStatement.setInt(4, food.getGrams());
            preparedStatement.setInt(5, food.getPrice());
            preparedStatement.setFloat(6, food.getRating());
            preparedStatement.setInt(7, food.getQuantity());
            preparedStatement.setString(8, name);

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

    public List<Food> loadFoods(String table)
    {
        List<Food> foods = new ArrayList<>();

        try
        {
            statement = connect().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + table);

            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int grams = resultSet.getInt("grams");
                int price = resultSet.getInt("price");
                float rating = resultSet.getFloat("rating");
                int quantity = resultSet.getInt("quantity");

                Food food = new Food(id, name, description, grams, price, rating, quantity);
                foods.add(food);
            }
        }
        catch (Exception e)
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

        return foods;
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

package SQLDataBase;

import Model.Drink;
import Model.Food;
import Model.Order;
import javafx.util.Pair;

import javax.crypto.SealedObject;
import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayOrdersDataBase implements Serializable {

    private transient Connection connection;
    private transient Statement statement;
    private transient ResultSet resultSet;

    public PayOrdersDataBase() throws SQLException {
        this.connection = null;
        this.statement = null;
        this.resultSet = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:payOrders.db");
        }
        catch (Exception e)
        {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }

        System.out.println("Connected to: " + connection.getMetaData().getURL());
    }

    public Connection connect()
    {
        String url = new String("jdbc:sqlite:payOrders.db");
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

    public void addDrinkToDataBase(Order order, Drink drink)
    {
        String sql = "INSERT INTO PayDrinks (OrderID, id, name, volume, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);


                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, drink.getId());
                preparedStatement.setString(3, drink.getName());
                preparedStatement.setDouble(4, drink.getVolume());
                preparedStatement.setInt(5, drink.getPrice());
                preparedStatement.setInt(6, drink.getQuantity());

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

    public void addFoodToDataBase(Order order, Food food)  {
        String sql = "INSERT INTO PayFood (OrderID, id, name, description, grams, price, rating, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);

                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, food.getId());
                preparedStatement.setString(3, food.getName());
                preparedStatement.setString(4, food.getDescription());
                preparedStatement.setInt(5, food.getGrams());
                preparedStatement.setInt(6, food.getPrice());
                preparedStatement.setFloat(7, food.getRating());
                preparedStatement.setInt(8, food.getQuantity());

                preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try
        {
            if (connect() != null)
                connect().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addOrderToDataBase(Order order)
    {
        String sql = "INSERT INTO Orders (OrderID, Name, Price, DateTime, TableNumber, PlaceName) VALUES (?, ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);

            preparedStatement.setInt(1, order.getId());
            preparedStatement.setString(2, order.getName());
            preparedStatement.setFloat(3, order.getPrice());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = order.getLocalDateTime().format(formatter);

            preparedStatement.setString(4, formattedDateTime);
            preparedStatement.setInt(5, order.getTableNumber());
            preparedStatement.setString(6, order.getPlaceName());

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePaidDrinkByNameFromdataBase(String name)
    {
        String sql = "DELETE FROM PayDrinks WHERE name = ?";

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePaidFoodByNameFromDataBase(String name)
    {
        String sql = "DELETE FROM PayFood WHERE name = ?";

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Drink> loadDrinks(int orderID) {
        List<Drink> drinks = new ArrayList<>();
        ResultSet localResultSet = null;

        try
        {
            String query = "SELECT * FROM PayDrinks WHERE OrderID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query))
            {
                preparedStatement.setInt(1, orderID);
                localResultSet = preparedStatement.executeQuery();

                while (localResultSet.next()) {
                    int drinkID = localResultSet.getInt("id");
                    String name = localResultSet.getString("name");
                    float volume = localResultSet.getFloat("volume");
                    int price = localResultSet.getInt("price");
                    int quantity = localResultSet.getInt("quantity");

                    Drink drink = new Drink(drinkID, name, volume, price, quantity);
                    drinks.add(drink);
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getClass().getName() + " " + e.getMessage());
        }
        finally
        {
            if (localResultSet != null) {
                try {
                    localResultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return drinks;
    }

    public List<Food> loadFood(int orderID) {
        List<Food> foods = new ArrayList<>();
        ResultSet localResultSet = null;

        try
        {
            String query = "SELECT * FROM PayFood WHERE OrderID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query))
            {
                preparedStatement.setInt(1, orderID);
                localResultSet = preparedStatement.executeQuery();

                while (localResultSet.next()) {
                    int foodId = localResultSet.getInt("id");
                    String name = localResultSet.getString("name");
                    String description = localResultSet.getString("description");
                    int grams = localResultSet.getInt("grams");
                    int price = localResultSet.getInt("price");
                    float rating = localResultSet.getFloat("rating");
                    int quantity = localResultSet.getInt("quantity");

                    Food food = new Food(foodId, name, description, grams, price, rating, quantity);
                    foods.add(food);
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getClass().getName() + " " + e.getMessage());
        }
        finally
        {
            if (localResultSet != null) {
                try {
                    localResultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return foods;
    }

    public Map<Pair<String, Integer>, List<Object>> loadPayOrdersToMap() {
        Map<Pair<String, Integer>, List<Object>> payOrdersMap = new HashMap<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Orders");

            while (resultSet.next()) {
                int orderId = resultSet.getInt("OrderID");
                int tableNumber = resultSet.getInt("TableNumber");
                String placeName = resultSet.getString("PlaceName");

                List<Drink> drinks = this.loadDrinks(orderId);
                List<Food> foods = this.loadFood(orderId);

                Pair<String, Integer> key = new Pair<>(placeName, tableNumber);

                payOrdersMap.putIfAbsent(key, new ArrayList<>());

                payOrdersMap.get(key).addAll(drinks);
                payOrdersMap.get(key).addAll(foods);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return payOrdersMap;
    }

    public boolean doesOrderExist(int orderId) {
        String sql = "SELECT COUNT(*) FROM Orders WHERE OrderID = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:payOrders.db");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
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

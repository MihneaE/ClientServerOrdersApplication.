package SQLDataBase;

import Model.Drink;
import Model.Food;
import Model.Order;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDataBase implements Serializable {
    private transient Connection connection;
    private transient Statement statement;
    private transient ResultSet resultSet;

    public OrderHistoryDataBase() throws SQLException {
        this.connection = null;
        this.statement = null;
        this.resultSet = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:orderHistory.db");
        }
        catch (Exception e)
        {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }

        System.out.println("Connected to: " + connection.getMetaData().getURL());
    }

    public Connection connect()
    {
        String url = new String("jdbc:sqlite:orderHistory.db");
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

    public void addOrderedDrinksToDataBase(List<Drink> drinks, Order order)  {
        String sql = "INSERT INTO OrderedDrinkHistory (OrderID, id, name, volume, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";


        try
        {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);

            for (Drink drink : drinks)
            {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, drink.getId());
                preparedStatement.setString(3, drink.getName());
                preparedStatement.setDouble(4, drink.getVolume());
                preparedStatement.setInt(5, drink.getPrice());
                preparedStatement.setInt(6, drink.getQuantity());

                preparedStatement.executeUpdate();
            }
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

    public void addOrderedFoodToDataBase(List<Food> foods, Order order)
    {
        String sql = "INSERT INTO OrderedFoodHistory (OrderID, id, name, description, grams, price, rating, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);

            for (Food food : foods)
            {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, food.getId());
                preparedStatement.setString(3, food.getName());
                preparedStatement.setString(4, food.getDescription());
                preparedStatement.setInt(5, food.getGrams());
                preparedStatement.setInt(6, food.getPrice());
                preparedStatement.setFloat(7, food.getRating());
                preparedStatement.setInt(8, food.getQuantity());

                preparedStatement.executeUpdate();
            }

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

    public void addSimpleDrinkToDataBase(Order order, Drink drink)
    {
        String sql = "INSERT INTO OrderedDrinkHistory (OrderID, id, name, volume, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";

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

    public void addSimpleFoodToDataBase(Order order, Food food)
    {
        String sql = "INSERT INTO OrderedFoodHistory (OrderID, id, name, description, grams, price, rating, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
        String sql = "INSERT INTO OrdersHistory (OrderID, Name, Price, DateTime, TableNumber, PlaceName, PayMethod) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
            preparedStatement.setString(7, order.getPayMethod());

            preparedStatement.executeUpdate();

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        this.addOrderedDrinksToDataBase(order.getOrderedDrink(), order);
        this.addOrderedFoodToDataBase(order.getOrderedFood(), order);

        try
        {
            if (this.connect() != null)
                this.connect().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOrderedDrinkByNameFromdataBase(String name)
    {
        String sql = "DELETE FROM OrderedDrinkHistory WHERE name = ?";

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

    public void deleteOrderedFoodByNameFromDataBase(String name)
    {
        String sql = "DELETE FROM OrderedFoodHistory WHERE name = ?";

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
            String query = "SELECT * FROM OrderedDrinkHistory WHERE OrderID = ?";

            try (PreparedStatement preparedStatement = connect().prepareStatement(query))
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

            try
            {
                if (this.connect() != null)
                    this.connect().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return drinks;
    }

    public List<Food> loadFood(int orderID) {
        List<Food> foods = new ArrayList<>();
        ResultSet localResultSet = null;

        try
        {
            String query = "SELECT * FROM OrderedFoodHistory WHERE OrderID = ?";

            try (PreparedStatement preparedStatement = connect().prepareStatement(query))
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

            try
            {
                if (this.connect() != null)
                    this.connect().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return foods;
    }

    public List<Order> loadOrders()
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            statement = connect().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM OrdersHistory");

            while (resultSet.next())
            {
                int OrderId = resultSet.getInt("OrderID");
                String name = resultSet.getString("Name");
                int price = resultSet.getInt("Price");
                String dateTimeStr = resultSet.getString("DateTime");
                int tableNumber = resultSet.getInt("TableNumber");
                String placeName = resultSet.getString("PlaceName");
                String payMethod = resultSet.getString("PayMethod");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

                List<Drink> drinks = this.loadDrinks(OrderId);
                List<Food> foods = this.loadFood(OrderId);

                Order order = new Order(OrderId, name, price, dateTime,drinks, foods);
                order.setTableNumber(tableNumber);
                order.setPlaceName(placeName);
                order.setPayMethod(payMethod);

                orders.add(order);
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getClass().getName() + " " + e.getMessage());
            System.exit(0);
        }

        try
        {
            if (this.connect() != null)
                this.connect().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orders;
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

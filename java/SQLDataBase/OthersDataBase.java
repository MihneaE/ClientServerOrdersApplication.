package SQLDataBase;

import Model.Drink;
import Model.Food;
import Model.Order;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OthersDataBase implements Serializable {
    private transient Connection connection;
    private transient Statement statement;
    private transient ResultSet resultSet;

    public OthersDataBase() throws SQLException {
        this.connection = null;
        this.statement = null;
        this.resultSet = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:others.db");
        }
        catch (Exception e)
        {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }

        System.out.println("Connected to: " + connection.getMetaData().getURL());
    }

    public Connection connect()
    {
        String url = new String("jdbc:sqlite:others.db");
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

    public void addOrderedDrinksToDataBase(List<Drink> drinks, Order order)  {
        String sql = "INSERT INTO RestoreDrinks (OrderID, id, name, volume, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";


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
        String sql = "INSERT INTO RestoreFood (OrderID, id, name, description, grams, price, rating, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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

    public void addOrderToDataBase(Order order)
    {
        String sql = "INSERT INTO RestoreOrders (OrderID, Name, Price, DateTime, TableNumber, PlaceName, payMethod) VALUES (?, ?, ?, ?, ?, ?, ?)";

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

    public void deleteOrderedDrinkFromDataBase(Order order)
    {
        String sql = "DELETE FROM RestoreDrinks WHERE OrderID = ?";

        try
        {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);

            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
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

    public void deleteOrderedFoodFromDataBase(Order order)
    {
        String sql = "DELETE FROM RestoreFood WHERE OrderID = ?";

        try
        {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);

            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
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

    public void deleteOrderFromDataBase(Order order)
    {
        String sql = "DELETE FROM RestoreOrders WHERE Name = ?";

        try
        {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);

            preparedStatement.setString(1, order.getName());
            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.deleteOrderedDrinkFromDataBase(order);
        this.deleteOrderedFoodFromDataBase(order);

        try
        {
            if (this.connect() != null)
                this.connect().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addThemeToDatabase(String themeName)
    {
        String sql = "INSERT INTO Themes (theme) VALUES (?)";

        try
        {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);

            preparedStatement.setString(1, themeName);

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

    public void deleteThemeFromDatabase(String themeName)
    {
        String sql = "DELETE FROM Themes WHERE theme = ?";

        try
        {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);

            preparedStatement.setString(1, themeName);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
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

    public List<Drink> loadDrinks(int orderID) {
        List<Drink> drinks = new ArrayList<>();
        ResultSet localResultSet = null;

        try
        {
            String query = "SELECT * FROM RestoreDrinks WHERE OrderID = ?";

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
            String query = "SELECT * FROM RestoreFood WHERE OrderID = ?";

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



    public List<Order> loadRestoreOrders()
    {
        List<Order> orders = new ArrayList<>();

        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM RestoreOrders");

            while (resultSet.next())
            {
                int OrderId = resultSet.getInt("OrderID");
                String name = resultSet.getString("Name");
                int price = resultSet.getInt("Price");
                String dateTimeStr = resultSet.getString("DateTime");
                int tableNumber = resultSet.getInt("TableNumber");
                String placeName = resultSet.getString("PlaceName");
                String payMethod = resultSet.getString("payMethod");

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

        return orders;
    }

    public List<String> loadThemes() {
        List<String> themes = new ArrayList<>();

        String query = "SELECT * FROM Themes";

        try (Statement statement = this.connect().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String themeName = resultSet.getString("theme");
                themes.add(themeName);
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }

        return themes;
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

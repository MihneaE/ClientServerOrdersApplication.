package SQLDataBase;

import Model.Drink;
import Model.Food;
import Model.Order;
import javafx.util.Pair;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDataBase implements Serializable {
    private transient Connection connection;
    private transient Statement statement;
    private transient ResultSet resultSet;
    public OrderDataBase() throws SQLException {
        this.connection = null;
        this.statement = null;
        this.resultSet = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:orders.db");
        }
        catch (Exception e)
        {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }

        System.out.println("Connected to: " + connection.getMetaData().getURL());
    }

    public Connection connect()
    {
        String url = new String("jdbc:sqlite:orders.db");
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
        String sql = "INSERT INTO OrderedDrink (OrderID, id, name, volume, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";


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
        String sql = "INSERT INTO OrderedFood (OrderID, id, name, description, grams, price, rating, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
        String sql = "INSERT INTO OrderedDrink (OrderID, id, name, volume, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";

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

    public void addSimpleNewDrinkToDataBase(Order order, Drink drink)
    {
        String sql = "INSERT INTO NewDrinks (OrderID, id, name, volume, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";

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
        String sql = "INSERT INTO OrderedFood (OrderID, id, name, description, grams, price, rating, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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

    public void addSimpleNewFoodToDataBase(Order order, Food food)
    {
        String sql = "INSERT INTO NewFoods (OrderID, id, name, description, grams, price, rating, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
        String sql = "INSERT INTO Orders (OrderID, Name, Price, DateTime, TableNumber, PlaceName, payMethod, Finished, Paid, Time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            preparedStatement.setBoolean(8, order.isFinished());
            preparedStatement.setBoolean(9, order.isPaid());

            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = order.getFinishedTime().format(formatter2);

            preparedStatement.setString(10, formattedTime);

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
        String sql = "DELETE FROM OrderedDrink WHERE OrderID = ?";

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

    public void deleteOrderedDrinkByNameFromdataBase(String name)
    {
        String sql = "DELETE FROM OrderedDrink WHERE name = ?";

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

    public void deleteNewDrinkByNameFromDataBase(String name)
    {
        String sql = "DELETE FROM NewDrinks WHERE name = ?";

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
        String sql = "DELETE FROM OrderedFood WHERE name = ?";

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

    public void deleteNewFoodByNameFromDataBase(String name)
    {
        String sql = "DELETE FROM NewFoods WHERE name = ?";

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

    public void deleteOrderedFoodFromDataBase(Order order)
    {
        String sql = "DELETE FROM OrderedFood WHERE OrderID = ?";

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
        String sql = "DELETE FROM Orders WHERE Name = ?";

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

    public void updateOrderedDrinkFromDataBase(int orderID, int drinkID, Drink drink)
    {
        String sql = "UPDATE OrderedDrink SET name = ?, volume = ?, price = ?, quantity = ? WHERE OrderID = ? AND id = ?";

        try
        {
            PreparedStatement preparedStatement = connect().prepareStatement(sql);

            preparedStatement.setString(1, drink.getName());
            preparedStatement.setDouble(2, drink.getVolume());
            preparedStatement.setInt(3, drink.getPrice());
            preparedStatement.setInt(4, drink.getQuantity());
            preparedStatement.setInt(5, orderID);
            preparedStatement.setInt(6, drinkID);

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

    public void updateOrderedFoodFromDataBase(int orderID, int foodID, Food food)
    {
        String sql = "UPDATE OrderedFood  SET  name = ? , description = ?, grams = ?, price = ?, rating = ?, quantity = ? WHERE OrderID = ? AND id = ?";

        try(PreparedStatement preparedStatement = connect().prepareStatement(sql))
        {
            preparedStatement.setString(1, food.getName());
            preparedStatement.setString(2, food.getDescription());
            preparedStatement.setInt(3, food.getGrams());
            preparedStatement.setFloat(4, food.getPrice());
            preparedStatement.setFloat(5, food.getRating());
            preparedStatement.setInt(6, food.getId());
            preparedStatement.setInt(7, orderID);
            preparedStatement.setInt(8, foodID);

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

    public void updateOrderFromDataBase(String name, Order order)
    {
        String sql = "UPDATE Orders SET OrderID = ?, Name = ?, Price = ?, Finished = ?, Paid = ?, Time = ?, Freeze = ? WHERE name = ?";

        try
        {
            PreparedStatement preparedStatement = connect().prepareStatement(sql);

            preparedStatement.setInt(1, order.getId());
            preparedStatement.setString(2, order.getName());
            preparedStatement.setFloat(3, order.getPrice());
            preparedStatement.setBoolean(4, order.isFinished());
            preparedStatement.setBoolean(5, order.isPaid());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = order.getFinishedTime().format(formatter);

            preparedStatement.setString(6, formattedTime);
            preparedStatement.setBoolean(7, order.isFreeze());
            preparedStatement.setString(8, name);

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

    public void updateSumOrderFromDataBase(String name, Order order)
    {
        String sql = "UPDATE Orders SET Price = ?, Paid = ? WHERE name = ?";

        try
        {
            PreparedStatement preparedStatement = connect().prepareStatement(sql);

            preparedStatement.setFloat(1, order.getPrice());
            preparedStatement.setBoolean(2, order.isPaid());
            preparedStatement.setString(3, name);

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
            String query = "SELECT * FROM OrderedDrink WHERE OrderID = ?";

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

    public List<Drink> loadNewDrinks(int orderID) {
        List<Drink> drinks = new ArrayList<>();
        ResultSet localResultSet = null;

        try
        {
            String query = "SELECT * FROM NewDrinks WHERE OrderID = ?";

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
            String query = "SELECT * FROM OrderedFood WHERE OrderID = ?";

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

    public List<Food> loadNewFoods(int orderID) {
        List<Food> foods = new ArrayList<>();
        ResultSet localResultSet = null;

        try
        {
            String query = "SELECT * FROM NewFoods WHERE OrderID = ?";

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
            resultSet = statement.executeQuery("SELECT * FROM Orders");

            while (resultSet.next())
            {
                int OrderId = resultSet.getInt("OrderID");
                String name = resultSet.getString("Name");
                int price = resultSet.getInt("Price");
                String dateTimeStr = resultSet.getString("DateTime");
                int tableNumber = resultSet.getInt("TableNumber");
                String placeName = resultSet.getString("PlaceName");
                String payMethod = resultSet.getString("payMethod");
                boolean finished = resultSet.getBoolean("Finished");
                boolean Paid = resultSet.getBoolean("Paid");
                String timeStr = resultSet.getString("Time");
                boolean freeze = resultSet.getBoolean("Freeze");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime time = LocalTime.parse(timeStr, formatter2);

                List<Drink> drinks = this.loadDrinks(OrderId);
                List<Food> foods = this.loadFood(OrderId);

                List<Drink> newDrinks = this.loadNewDrinks(OrderId);
                List<Food> newFoods = this.loadNewFoods(OrderId);

                Order order = new Order(OrderId, name, price, dateTime,drinks, foods);
                order.setTableNumber(tableNumber);
                order.setPlaceName(placeName);
                order.setPayMethod(payMethod);
                order.setFinished(finished);
                order.setPaid(Paid);
                order.setFinishedTime(time);
                order.setFreeze(freeze);

                order.setNewDrinks(newDrinks);
                order.setNewFoods(newFoods);

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

    public Map<Pair<String, Integer>, Order> loadOrdersToMap()
    {
        Map<Pair<String, Integer>, Order> ordersMap = new HashMap<>();

        try
        {
            statement = connect().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Orders");

            while (resultSet.next())
            {
                int OrderId = resultSet.getInt("OrderID");
                String name = resultSet.getString("Name");
                int price = resultSet.getInt("Price");
                String dateTimeStr = resultSet.getString("DateTime");
                int tableNumber = resultSet.getInt("TableNumber");
                String placeName = resultSet.getString("PlaceName");
                String payMethod = resultSet.getString("payMethod");
                boolean finished = resultSet.getBoolean("Finished");
                boolean Paid = resultSet.getBoolean("Paid");
                boolean freeze = resultSet.getBoolean("Freeze");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

                List<Drink> drinks = this.loadDrinks(OrderId);
                List<Food> foods = this.loadFood(OrderId);

                List<Drink> newDrinks = this.loadNewDrinks(OrderId);
                List<Food> newFoods = this.loadNewFoods(OrderId);

                Order order = new Order(OrderId, name, price, dateTime,drinks, foods);
                order.setTableNumber(tableNumber);
                order.setPlaceName(placeName);
                order.setPayMethod(payMethod);
                order.setFinished(finished);
                order.setPaid(Paid);
                order.setFreeze(freeze);

                order.setNewDrinks(newDrinks);
                order.setNewFoods(newFoods);

                Pair<String, Integer> key = new Pair<>(placeName, tableNumber);

                ordersMap.put(key, order);
            }

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


        return ordersMap;
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

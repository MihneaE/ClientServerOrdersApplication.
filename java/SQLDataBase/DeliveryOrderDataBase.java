package SQLDataBase;

import Model.DeliveryInfo;
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

public class DeliveryOrderDataBase implements Serializable{
    private transient Connection connection;
    private transient Statement statement;
    private transient ResultSet resultSet;

    public DeliveryOrderDataBase() {
        this.connection = null;
        this.statement = null;
        this.resultSet = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:deliveryOrders.db");
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
        String url = new String("jdbc:sqlite:deliveryOrders.db");
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
        String sql = "INSERT INTO DeliveryDrink (OrderID, id, name, volume, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";


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
        String sql = "INSERT INTO DeliveryFood (OrderID, id, name, description, grams, price, rating, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
        String sql = "INSERT INTO DeliveryDrink (OrderID, id, name, volume, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";

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
        String sql = "INSERT INTO DeliveryFood (OrderID, id, name, description, grams, price, rating, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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

    public void addDeliveryInfoToDataBase(Order order)
    {
        String sql = "INSERT INTO DeliveryInfo (OrderID, Phone, Address, Apartment, Floor, OtherDetails) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = this.connect().prepareStatement(sql))
        {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setString(2, order.getDeliveryInfo().getPhone());
            preparedStatement.setString(3, order.getDeliveryInfo().getAddress());
            preparedStatement.setString(4, order.getDeliveryInfo().getApartment());
            preparedStatement.setString(5, order.getDeliveryInfo().getFloor());
            preparedStatement.setString(6,order.getDeliveryInfo().getOtherDetails());

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

    public void addOrderToDataBase(Order order)
    {
        String sql = "INSERT INTO DeliveryOrders (OrderID, Name, Price, DateTime, PlaceName, payMethod, Paid, Time, Freeze) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);

            preparedStatement.setInt(1, order.getId());
            preparedStatement.setString(2, order.getName());
            preparedStatement.setFloat(3, order.getPrice());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = order.getLocalDateTime().format(formatter);

            preparedStatement.setString(4, formattedDateTime);
            preparedStatement.setString(5, order.getPlaceName());
            preparedStatement.setString(6, order.getPayMethod());
            preparedStatement.setBoolean(7, order.isPaid());

            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = order.getFinishedTime().format(formatter2);

            preparedStatement.setString(8, formattedTime);
            preparedStatement.setBoolean(9, order.isFreeze());

            preparedStatement.executeUpdate();

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        this.addOrderedDrinksToDataBase(order.getOrderedDrink(), order);
        this.addOrderedFoodToDataBase(order.getOrderedFood(), order);
        this.addDeliveryInfoToDataBase(order);

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
        String sql = "DELETE FROM DeliveryDrink WHERE OrderID = ?";

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

    public void deleteOrderedDrinkByNameFromDataBase(String name)
    {
        String sql = "DELETE FROM DeliveryDrink WHERE name = ?";

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
        String sql = "DELETE FROM DeliveryFood WHERE name = ?";

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
        String sql = "DELETE FROM DeliveryFood WHERE OrderID = ?";

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

    public void deleteDeliveryInfoFromDataBase(Order order)
    {
        String sql = "DELETE FROM DeliveryInfo WHERE OrderID = ?";

        try(PreparedStatement preparedStatement = this.connect().prepareStatement(sql))
        {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteOrderFromDataBase(Order order)
    {
        String sql = "DELETE FROM DeliveryOrders WHERE Name = ?";

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
        this.deleteDeliveryInfoFromDataBase(order);

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
        String sql = "UPDATE DeliveryOrders SET OrderID = ?, Name = ?, Price = ? , Paid = ?, Time = ?, Freeze = ? WHERE name = ?";

        try
        {
            PreparedStatement preparedStatement = connect().prepareStatement(sql);

            preparedStatement.setInt(1, order.getId());
            preparedStatement.setString(2, order.getName());
            preparedStatement.setFloat(3, order.getPrice());
            preparedStatement.setBoolean(4, order.isPaid());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = order.getFinishedTime().format(formatter);

            preparedStatement.setString(5, formattedTime);
            preparedStatement.setBoolean(6, order.isFreeze());
            preparedStatement.setString(7, name);

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

    public List<Drink> loadDeliveryDrinks(int orderID) {
        List<Drink> drinks = new ArrayList<>();
        ResultSet localResultSet = null;

        try
        {
            String query = "SELECT * FROM DeliveryDrink WHERE OrderID = ?";

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

    public List<Food> loadDeliveryFood(int orderID) {
        List<Food> foods = new ArrayList<>();
        ResultSet localResultSet = null;

        try
        {
            String query = "SELECT * FROM DeliveryFood WHERE OrderID = ?";

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

    public DeliveryInfo loadDeliveryInfo(int OrderID)
    {
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        ResultSet localResultSet = null;

        try
        {
            String query = "SELECT * FROM DeliveryInfo WHERE OrderID = ?";

            try(PreparedStatement preparedStatement = this.connect().prepareStatement(query))
            {
                preparedStatement.setInt(1, OrderID);
                localResultSet = preparedStatement.executeQuery();

                String phone = localResultSet.getString("Phone");
                String address = localResultSet.getString("Address");
                String apartment = localResultSet.getString("Apartment");
                String floor = localResultSet.getString("Floor");
                String otherDetails = localResultSet.getString("OtherDetails");

                deliveryInfo.setPhone(phone);
                deliveryInfo.setAddress(address);
                deliveryInfo.setApartment(apartment);
                deliveryInfo.setFloor(floor);
                deliveryInfo.setOtherDetails(otherDetails);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return deliveryInfo;
    }

    public List<Order> loadDeliveryOrders()
    {
        List<Order> deliveryOrders = new ArrayList<>();

        try
        {
            statement = connect().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM DeliveryOrders");

            while (resultSet.next())
            {
                int OrderId = resultSet.getInt("OrderID");
                String name = resultSet.getString("Name");
                int price = resultSet.getInt("Price");
                String dateTimeStr = resultSet.getString("DateTime");
                String placeName = resultSet.getString("PlaceName");
                String payMethod = resultSet.getString("payMethod");
                boolean paid = resultSet.getBoolean("Paid");
                String timeStr = resultSet.getString("Time");
                boolean freeze = resultSet.getBoolean("Freeze");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime time = LocalTime.parse(timeStr, formatter2);

                List<Drink> drinks = this.loadDeliveryDrinks(OrderId);
                List<Food> foods = this.loadDeliveryFood(OrderId);
                DeliveryInfo deliveryInfo = this.loadDeliveryInfo(OrderId);

                Order order = new Order(OrderId, name, price, dateTime,drinks, foods);
                order.setPlaceName(placeName);
                order.setDeliveryInfo(deliveryInfo);
                order.setPayMethod(payMethod);
                order.setPaid(paid);
                order.setLocalDateTime(dateTime);
                order.setFinishedTime(time);
                order.setFreeze(freeze);

                deliveryOrders.add(order);
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

        return deliveryOrders;
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

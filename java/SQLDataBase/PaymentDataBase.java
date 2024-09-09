package SQLDataBase;

import Model.Payment;

import javax.crypto.SealedObject;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDataBase implements Serializable {
    private transient Connection connection;
    private transient Statement statement;
    private transient ResultSet resultSet;

    public PaymentDataBase() throws SQLException {
        this.connection = null;
        this.statement = null;
        this.resultSet = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:payments.db");
        }
        catch (Exception e)
        {
            System.out.println(e.getClass().getName() + " " + e.getMessage());
        }

        System.out.println("Connected to: " + connection.getMetaData().getURL());
    }

    public Connection connect()
    {
        String url = new String("jdbc:sqlite:payments.db");
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

    public void addPaymentToDataBase(Payment payment)
    {
        String sql = "INSERT INTO Payments (orderName, price, payMethod) VALUES (?, ?, ?)";

        try(PreparedStatement preparedStatement = this.connect().prepareStatement(sql))
        {
            preparedStatement.setString(1, payment.getOrderName());
            preparedStatement.setFloat(2, payment.getPrice());
            preparedStatement.setString(3, payment.getPayMethod());

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

    public void deletePaymentFromDataBase(String name)
    {
        String sql = "DELETE FROM Payments WHERE OrderName = ?";

        try(PreparedStatement preparedStatement = this.connect().prepareStatement(sql))
        {
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

    public void updatePaymentFromDataBase(String name, Payment payment) {
        String sql = "UPDATE Payments SET orderName = ?, price = ? WHERE name = ?";

        try(PreparedStatement preparedStatement = this.connect().prepareStatement(sql))
        {
            preparedStatement.setString(1, payment.getOrderName());
            preparedStatement.setFloat(2, payment.getPrice());
            preparedStatement.setString(3, name);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Payment> loadPayments()
    {
        List<Payment> payments = new ArrayList<>();

        try
        {
            statement = connect().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Payments");

            while (resultSet.next())
            {
                String name = resultSet.getString("orderName");
                int price = resultSet.getInt("price");
                String payMethod = resultSet.getString("payMethod");

                Payment payment = new Payment(name, price, payMethod);
                payments.add(payment);
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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return payments;
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

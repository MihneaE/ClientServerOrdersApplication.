package Model;

import java.io.Serializable;
import java.util.Objects;

public class Payment implements Serializable {
    private String orderName;
    private float price;
    private String payMethod;

    public Payment() {}

    public Payment(String orderName, float price, String payMethod)
    {
        this.orderName = orderName;
        this.price = price;
        this.payMethod = payMethod;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrderName() {
        return orderName;
    }

    public float getPrice() {
        return price;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayMethod() {
        return payMethod;
    }

    @Override
    public String toString() {
        return orderName + ": " + price + " LEI - " + payMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return price == payment.price && Objects.equals(orderName, payment.orderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderName, price);
    }
}

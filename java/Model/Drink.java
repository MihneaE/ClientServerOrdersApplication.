package Model;

import java.io.Serializable;
import java.util.Objects;

public class Drink implements Cloneable, Serializable {
    private int id;
    private String name;
    private double volume;
    private int price;
    private int quantity;

    public Drink () {}

    public Drink(String name, double volume, int price, int quantity)
    {
        this.name = name;
        this.volume = volume;
        this.price = price;
        this.quantity = quantity;
    }

    public Drink(int id, String name, double volume, int price, int quantity)
    {
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.price = price;
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return id == drink.id && Double.compare(drink.volume, volume) == 0 && price == drink.price && quantity == drink.quantity && Objects.equals(name, drink.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, volume, price, quantity);
    }

    @Override
    public String toString() {
        return "Drink{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", volume=" + volume +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public Drink clone() {
        try {

            return (Drink) super.clone();
        } catch (CloneNotSupportedException e) {

            throw new RuntimeException("This class does not implement Cloneable", e);
        }
    }
}

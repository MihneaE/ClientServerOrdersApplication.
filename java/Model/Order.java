package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Order implements Cloneable, Serializable {
    private int id;
    private String name;
    private float price;
    private LocalDateTime localDateTime;
    private int tableNumber;
    private String placeName;
    private String payMethod;
    private List<Food> orderedFood;
    private List<Drink> orderedDrink;
    private List<Drink> newDrinks;
    private List<Food> newFoods;
    private boolean isFinished;
    private boolean isPaid;
    private DeliveryInfo deliveryInfo;
    private LocalTime finishedTime;
    private boolean freeze;

    public Order() {}

    public Order(int id, String name, float price, LocalDateTime localDateTime)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.localDateTime = localDateTime;
    }

    public Order(int id, String name, float price, LocalDateTime localDateTime, List<Drink> orderedDrink, List<Food> orderedFood)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.localDateTime = localDateTime;
        this.orderedDrink = orderedDrink;
        this.orderedFood = orderedFood;
        this.isFinished = false;
        finishedTime = LocalTime.MIDNIGHT;

        this.newDrinks = new ArrayList<>();
        this.newFoods = new ArrayList<>();
    }

    public Order(List<Drink> drinks, List<Food> foods)
    {
        this.orderedDrink = drinks;
        this.orderedFood = foods;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setOrderedDrink(List<Drink> orderedDrink) {
        this.orderedDrink = orderedDrink;
    }

    public void setOrderedFood(List<Food> orderedFood) {
        this.orderedFood = orderedFood;
    }

    public List<Food> getOrderedFood() {
        return orderedFood;
    }

    public List<Drink> getOrderedDrink() {
        return orderedDrink;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public LocalTime getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(LocalTime finishedTime) {
        this.finishedTime = finishedTime;
    }

    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }

    public boolean isFreeze() {
        return freeze;
    }

    public List<Drink> getNewDrinks() {
        return newDrinks;
    }

    public List<Food> getNewFoods() {
        return newFoods;
    }

    public void setNewDrinks(List<Drink> newDrinks) {
        this.newDrinks = newDrinks;
    }

    public void setNewFoods(List<Food> newFoods) {
        this.newFoods = newFoods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && price == order.price && Objects.equals(name, order.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", localDateTime=" + localDateTime +
                ", tableNumber=" + tableNumber +
                ", placeName='" + placeName + '\'' +
                ", payMethod='" + payMethod + '\'' +
                ", orderedFood=" + orderedFood +
                ", orderedDrink=" + orderedDrink +
                ", isFinished=" + isFinished +
                ", isPaid=" + isPaid +
                ", deliveryInfo=" + deliveryInfo +
                '}';
    }

    @Override
    public Order clone() {
        try {
            Order cloned = (Order) super.clone();

            if (this.orderedDrink != null) {
                cloned.orderedDrink = this.orderedDrink.stream()
                        .map(Drink::clone)
                        .collect(Collectors.toList());
            }
            if (this.orderedFood != null) {
                cloned.orderedFood = this.orderedFood.stream()
                        .map(Food::clone)
                        .collect(Collectors.toList());
            }

            cloned.deliveryInfo = (this.deliveryInfo != null) ? this.deliveryInfo.clone() : null;


            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

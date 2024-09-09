package Model;

import java.io.Serializable;
import java.util.Objects;

public class Food implements Cloneable, Serializable {
    private int id;
    private String name;
    private String description;
    private int grams;
    private int price;
    private float rating;
    private int quantity;
    private int noOfRatings = 100; //Rating is based on the average of 100 ratings
    private String foodType;

    public Food() {
    }

    public Food(int id, String name, String description, int grams, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.grams = grams;
        this.price = price;
    }

    public Food(int id, String name, String description, int grams, int price, float rating, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.grams = grams;
        this.price = price;
        this.rating = rating;
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getGrams() {
        return grams;
    }

    public float getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodType() {
        return foodType;
    }

    public void updateRating(float rating)
    {
        this.rating = ((this.rating * noOfRatings) + rating) / (noOfRatings + 1);
        noOfRatings++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return id == food.id && grams == food.grams && price == food.price && Float.compare(food.rating, rating) == 0 && quantity == food.quantity && Objects.equals(name, food.name) && Objects.equals(description, food.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, grams, price, rating, quantity);
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", grams=" + grams +
                ", price=" + price +
                ", rating=" + rating +
                '}';
    }

    @Override
    public Food clone() {
        try {

            return (Food) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("This class does not implement Cloneable");
        }
    }
}

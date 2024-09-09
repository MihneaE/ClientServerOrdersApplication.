package Networking.DTO;

import Enums.DessertFoodType;
import Model.Food;

import java.io.Serializable;

public class AddDessertFoodRequest implements Serializable {
    private String table;
    private DessertFoodType dessertFoodType;
    private Food food;

    public AddDessertFoodRequest(String table, DessertFoodType dessertFoodType, Food food) {
        this.table = table;
        this.dessertFoodType = dessertFoodType;
        this.food = food;
    }

    public String getTable() {
        return table;
    }

    public DessertFoodType getDessertFoodType() {
        return dessertFoodType;
    }

    public Food getFood() {
        return food;
    }
}

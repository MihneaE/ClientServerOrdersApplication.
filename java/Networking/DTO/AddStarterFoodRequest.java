package Networking.DTO;

import Enums.StarterFoodType;
import Model.Food;

import java.io.Serializable;

public class AddStarterFoodRequest implements Serializable {
    private String table;
    private StarterFoodType starterFoodType;
    private Food food;

    public AddStarterFoodRequest(String table, StarterFoodType starterFoodType, Food food) {
        this.table = table;
        this.starterFoodType = starterFoodType;
        this.food = food;
    }

    public String getTable() {
        return table;
    }

    public StarterFoodType getStarterFoodType() {
        return starterFoodType;
    }

    public Food getFood() {
        return food;
    }
}

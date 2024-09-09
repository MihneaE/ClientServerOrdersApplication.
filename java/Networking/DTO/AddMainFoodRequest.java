package Networking.DTO;

import Enums.MainFoodType;
import Model.Food;

import java.io.Serializable;

public class AddMainFoodRequest implements Serializable {
    private String table;
    private MainFoodType mainFoodType;
    private Food food;

    public AddMainFoodRequest(String table, MainFoodType mainFoodType, Food food) {
        this.table = table;
        this.mainFoodType = mainFoodType;
        this.food = food;
    }

    public String getTable() {
        return table;
    }

    public MainFoodType getMainFoodType() {
        return mainFoodType;
    }

    public Food getFood() {
        return food;
    }
}

package Networking.DTO;

import Enums.StarterFoodType;
import Model.Food;

import java.io.Serializable;

public class UpdateStarterFoodRequest implements Serializable {
    private String table;
    private StarterFoodType starterFoodType;
    private String oldName;
    private Food food;

    public UpdateStarterFoodRequest(String table, StarterFoodType starterFoodType, String oldName, Food food) {
        this.table = table;
        this.starterFoodType = starterFoodType;
        this.oldName = oldName;
        this.food = food;
    }

    public String getTable() { return table; }
    public StarterFoodType getStarterFoodType() { return starterFoodType; }
    public String getOldName() { return oldName; }

    public Food getFood() { return food; }
}

package Networking.DTO;

import Enums.MainFoodType;
import Model.Food;

import java.io.Serializable;

public class UpdateMainFoodRequest implements Serializable {
    private String table;
    private MainFoodType mainFoodType;
    private String oldName;
    private Food food;

    public UpdateMainFoodRequest(String table, MainFoodType mainFoodType, String oldName, Food food) {
        this.table = table;
        this.mainFoodType = mainFoodType;
        this.oldName = oldName;
        this.food = food;
    }

    public String getTable() { return table; }
    public MainFoodType getMainFoodType() { return mainFoodType; }
    public String getOldName() { return oldName; }

    public Food getFood() { return food; }
}

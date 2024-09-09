package Networking.DTO;

import Enums.DessertFoodType;
import Enums.MainFoodType;
import Model.Food;

import java.io.Serializable;

public class UpdateDessertFoodRequest implements Serializable {
    private String table;
    private DessertFoodType dessertFoodType;
    private String oldName;
    private Food food;

    public UpdateDessertFoodRequest(String table, DessertFoodType dessertFoodType, String oldName, Food food) {
        this.table = table;
        this.dessertFoodType = dessertFoodType;
        this.oldName = oldName;
        this.food = food;
    }

    public DessertFoodType getDessertFoodType() {
        return dessertFoodType;
    }

    public Food getFood() {
        return food;
    }

    public String getOldName() {
        return oldName;
    }

    public String getTable() {
        return table;
    }
}

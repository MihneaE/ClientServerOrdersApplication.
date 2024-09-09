package Networking.DTO;

import Enums.DessertFoodType;
import Enums.MainFoodType;

import java.io.Serializable;

public class RemoveDessertFoodRequest implements Serializable {
    private String table;
    private DessertFoodType dessertFoodType;
    private String name;

    public RemoveDessertFoodRequest(String table, DessertFoodType dessertFoodType, String name) {
        this.table = table;
        this.dessertFoodType = dessertFoodType;
        this.name = name;
    }

    public String getTable() {
        return table;
    }

    public DessertFoodType getDessertFoodType() {
        return dessertFoodType;
    }

    public String getName() {
        return name;
    }
}

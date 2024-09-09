package Networking.DTO;

import Enums.StarterFoodType;

import java.io.Serializable;

public class RemoveStarterFoodRequest implements Serializable {
    private String table;
    private StarterFoodType starterFoodType;
    private String name;

    public RemoveStarterFoodRequest(String table, StarterFoodType starterFoodType, String name) {
        this.table = table;
        this.starterFoodType = starterFoodType;
        this.name = name;
    }

    public String getTable() {
        return table;
    }

    public StarterFoodType getStarterFoodType() {
        return starterFoodType;
    }

    public String getName() {
        return name;
    }
}

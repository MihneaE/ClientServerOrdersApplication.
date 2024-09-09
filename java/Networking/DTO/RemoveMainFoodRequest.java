package Networking.DTO;

import Enums.MainFoodType;
import Enums.StarterFoodType;

import java.io.Serializable;

public class RemoveMainFoodRequest implements Serializable {
    private String table;
    private MainFoodType mainFoodType;
    private String name;

    public RemoveMainFoodRequest(String table, MainFoodType mainFoodType, String name) {
        this.table = table;
        this.mainFoodType = mainFoodType;
        this.name = name;
    }

    public String getTable() {
        return table;
    }

    public MainFoodType getMainFoodType() {
        return mainFoodType;
    }

    public String getName() {
        return name;
    }
}

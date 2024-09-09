package Networking.DTO;

import Enums.MainFoodType;

import java.io.Serializable;

public class FindMainFoodRequest implements Serializable {
    private MainFoodType type;
    private String name;

    public FindMainFoodRequest(MainFoodType type, String name) {
        this.type = type;
        this.name = name;
    }

    public MainFoodType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

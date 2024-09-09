package Networking.DTO;

import Enums.StarterFoodType;

import java.io.Serializable;

public class FindStarterFoodRequest implements Serializable {
    private StarterFoodType type;
    private String name;

    public FindStarterFoodRequest(StarterFoodType type, String name) {
        this.type = type;
        this.name = name;
    }

    public StarterFoodType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

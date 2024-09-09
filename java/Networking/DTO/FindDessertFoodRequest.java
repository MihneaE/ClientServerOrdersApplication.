package Networking.DTO;

import Enums.DessertFoodType;

import java.io.Serializable;

public class FindDessertFoodRequest implements Serializable {
    private DessertFoodType type;
    private String name;

    public FindDessertFoodRequest(DessertFoodType type, String name) {
        this.type = type;
        this.name = name;
    }

    public DessertFoodType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

package Networking.DTO;

import Enums.NonAlcoholicDrinkType;

import java.io.Serializable;

public class FindNonAlcoholicDrinkRequest implements Serializable {
    private NonAlcoholicDrinkType type;
    private String name;

    public FindNonAlcoholicDrinkRequest(NonAlcoholicDrinkType type, String name) {
        this.type = type;
        this.name = name;
    }

    public NonAlcoholicDrinkType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

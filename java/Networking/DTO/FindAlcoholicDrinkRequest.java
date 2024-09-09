package Networking.DTO;

import Enums.AlcoholicDrinkType;

import java.io.Serializable;

public class FindAlcoholicDrinkRequest implements Serializable {
    private AlcoholicDrinkType type;
    private String name;

    public FindAlcoholicDrinkRequest(AlcoholicDrinkType type, String name) {
        this.type = type;
        this.name = name;
    }

    public AlcoholicDrinkType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

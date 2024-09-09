package Networking.DTO;

import Enums.NonAlcoholicDrinkType;

import java.io.Serializable;

public class RemoveNonAlcoholicDrinkRequest implements Serializable {
    private String table;
    private NonAlcoholicDrinkType nonAlcoholicDrinkType;
    private String name;

    public RemoveNonAlcoholicDrinkRequest(String table, NonAlcoholicDrinkType nonAlcoholicDrinkType, String name) {
        this.table = table;
        this.nonAlcoholicDrinkType = nonAlcoholicDrinkType;
        this.name = name;
    }

    public String getTable() {
        return table;
    }

    public NonAlcoholicDrinkType getNonAlcoholicDrinkType() {
        return nonAlcoholicDrinkType;
    }

    public String getName() {
        return name;
    }
}

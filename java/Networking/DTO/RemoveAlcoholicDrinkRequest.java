package Networking.DTO;

import Enums.AlcoholicDrinkType;

import java.io.Serializable;

public class RemoveAlcoholicDrinkRequest implements Serializable {
    private String table;
    private AlcoholicDrinkType alcoholicDrinkType;
    private String name;

    public RemoveAlcoholicDrinkRequest(String table, AlcoholicDrinkType alcoholicDrinkType, String name) {
        this.table = table;
        this.alcoholicDrinkType = alcoholicDrinkType;
        this.name = name;
    }

    public String getTable() {
        return table;
    }

    public AlcoholicDrinkType getAlcoholicDrinkType() {
        return alcoholicDrinkType;
    }

    public String getName() {
        return name;
    }
}

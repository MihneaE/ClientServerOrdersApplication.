package Networking.DTO;

import Enums.NonAlcoholicDrinkType;
import Model.Drink;

import java.io.Serializable;

public class UpdateNonAlcoholicDrinkRequest implements Serializable {
    private String table;
    private NonAlcoholicDrinkType nonAlcoholicDrinkType;
    private String oldName;
    private Drink newDrink;

    public UpdateNonAlcoholicDrinkRequest(String table, NonAlcoholicDrinkType nonAlcoholicDrinkType, String oldName, Drink newDrink) {
        this.table = table;
        this.nonAlcoholicDrinkType = nonAlcoholicDrinkType;
        this.oldName = oldName;
        this.newDrink = newDrink;
    }

    public String getTable() {
        return table;
    }

    public NonAlcoholicDrinkType getNonAlcoholicDrinkType() {
        return nonAlcoholicDrinkType;
    }

    public String getOldName() {
        return oldName;
    }

    public Drink getNewDrink() {
        return newDrink;
    }
}

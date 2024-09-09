package Networking.DTO;

import Enums.AlcoholicDrinkType;
import Model.Drink;

import java.io.Serializable;

public class UpdateAlcoholicDrinkRequest implements Serializable {
    private String table;
    private AlcoholicDrinkType alcoholicDrinkType;
    private String oldName;
    private Drink newDrink;

    public UpdateAlcoholicDrinkRequest(String table, AlcoholicDrinkType alcoholicDrinkType, String oldName, Drink newDrink) {
        this.table = table;
        this.alcoholicDrinkType = alcoholicDrinkType;
        this.oldName = oldName;
        this.newDrink = newDrink;
    }

    public String getTable() {
        return table;
    }

    public AlcoholicDrinkType getAlcoholicDrinkType() {
        return alcoholicDrinkType;
    }

    public String getOldName() {
        return oldName;
    }

    public Drink getNewDrink() {
        return newDrink;
    }
}

package Networking.DTO;

import Enums.AlcoholicDrinkType;
import Model.Drink;

import java.io.Serializable;

public class AddAlcoholicDrinkRequest implements Serializable {
    private String table;
    private AlcoholicDrinkType alcoholicDrinkType;
    private Drink drink;

    public AddAlcoholicDrinkRequest(String table, AlcoholicDrinkType alcoholicDrinkType, Drink drink) {
        this.table = table;
        this.alcoholicDrinkType = alcoholicDrinkType;
        this.drink = drink;
    }

    public String getTable() {
        return table;
    }

    public AlcoholicDrinkType getAlcoholicDrinkType() {
        return alcoholicDrinkType;
    }

    public Drink getDrink() {
        return drink;
    }
}

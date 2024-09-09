package Networking.DTO;

import Enums.NonAlcoholicDrinkType;
import Model.Drink;

import java.io.Serializable;

public class AddNonAlcoholicDrinkRequest implements Serializable {
    private String table;
    private NonAlcoholicDrinkType nonAlcoholicDrinkType;
    private Drink drink;

    public AddNonAlcoholicDrinkRequest(String table, NonAlcoholicDrinkType nonAlcoholicDrinkType, Drink drink) {
        this.table = table;
        this.nonAlcoholicDrinkType = nonAlcoholicDrinkType;
        this.drink = drink;
    }

    public String getTable() {
        return table;
    }

    public NonAlcoholicDrinkType getNonAlcoholicDrinkType() {
        return nonAlcoholicDrinkType;
    }

    public Drink getDrink() {
        return drink;
    }
}

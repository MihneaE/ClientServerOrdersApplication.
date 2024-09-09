package Service;

import Enums.AlcoholicDrinkType;
import Enums.NonAlcoholicDrinkType;
import Model.Drink;
import Repository.AlcoholicDrinkRepository;
import Repository.NonAlcoholicDrinkRepository;
import SQLDataBase.DrinkDataBase;
import ServiceInteface.DrinkServiceI;

import java.util.List;

public class DrinkService implements DrinkServiceI {
    private AlcoholicDrinkRepository alcoholicDrinkRepository;
    private NonAlcoholicDrinkRepository nonAlcoholicDrinkRepository;
    private DrinkDataBase drinkDataBase;

    public DrinkService(AlcoholicDrinkRepository alcoholicDrinkRepository, NonAlcoholicDrinkRepository nonAlcoholicDrinkRepository, DrinkDataBase drinkDataBase)
    {
        this.alcoholicDrinkRepository = alcoholicDrinkRepository;
        this.nonAlcoholicDrinkRepository = nonAlcoholicDrinkRepository;
        this.drinkDataBase = drinkDataBase;
    }

    public void addAlcoholicDrink(String table, AlcoholicDrinkType alcoholicDrinkType, int id, String name, double volume, int price, int quantity)
    {
        Drink drink = new Drink(id, name, volume, price, quantity);

        alcoholicDrinkRepository.addDrink(alcoholicDrinkType, drink);
        drinkDataBase.addDrinkToDataBase(table, drink);
    }

    public void removeAlcoholicDrink(String table, AlcoholicDrinkType alcoholicDrinkType, String name)
    {
        alcoholicDrinkRepository.removeDrink(alcoholicDrinkType, name);
        drinkDataBase.deleteDrinkFromDataBase(table, name);
    }

    public void updateAlcoholicDrink(String table, AlcoholicDrinkType alcoholicDrinkType, String name, int new_id, String new_name, double new_volume, int new_price, int new_quantity)
    {
        Drink new_drink = new Drink(new_id, new_name, new_volume, new_price, new_quantity);

        alcoholicDrinkRepository.updateDrink(alcoholicDrinkType, name, new_drink);
        //drinkDataBase.updateDrinkFromDataBase(table, name, new_drink);
    }

    public Drink findAlcoholicDrink(AlcoholicDrinkType alcoholicDrinkType, String name)
    {
        return alcoholicDrinkRepository.findDrink(alcoholicDrinkType, name);
    }

    public void addNonAlcoholicDrink(String table, NonAlcoholicDrinkType nonAlcoholicDrinkType, int id, String name, double volume, int price, int quantity)
    {
        Drink drink = new Drink(id, name, volume, price, quantity);

        nonAlcoholicDrinkRepository.addDrink(nonAlcoholicDrinkType, drink);
        drinkDataBase.addDrinkToDataBase(table, drink);
    }

    public void removeNonAlcoholicDrink(String table, NonAlcoholicDrinkType nonAlcoholicDrinkType, String name)
    {
        nonAlcoholicDrinkRepository.removeDrink(nonAlcoholicDrinkType, name);
        drinkDataBase.deleteDrinkFromDataBase(table, name);
    }

    public void updateNonAlcoholicDrink(String table, NonAlcoholicDrinkType nonAlcoholicDrinkType, String name, int new_id, String new_name, double new_volume, int new_price, int new_quantity)
    {
        Drink new_drink = new Drink(new_id, new_name, new_volume, new_price, new_quantity);

        nonAlcoholicDrinkRepository.updateDrink(nonAlcoholicDrinkType, name, new_drink);
        drinkDataBase.updateDrinkFromDataBase(table, name, new_drink);
    }

    public Drink findNonAlcoholicDrink(NonAlcoholicDrinkType nonAlcoholicDrinkType, String name)
    {
        return nonAlcoholicDrinkRepository.findDrink(nonAlcoholicDrinkType, name);
    }

    public List<Drink> getBeers()
    {
        return alcoholicDrinkRepository.getBeers();
    }

    public List<Drink> getWines()
    {
        return alcoholicDrinkRepository.getWines();
    }

    public List<Drink> getCocktails()
    {
        return alcoholicDrinkRepository.getCocktails();
    }

    public List<Drink> getStrongDrinks()
    {
        return alcoholicDrinkRepository.getStrongDrinks();
    }

    public List<Drink> getCoffees()
    {
        return nonAlcoholicDrinkRepository.getCoffees();
    }

    public List<Drink> getTeas()
    {
        return nonAlcoholicDrinkRepository.getTeas();
    }

    public List<Drink> getJuices()
    {
        return nonAlcoholicDrinkRepository.getJuices();
    }

    public List<Drink> getSoftDrinks()
    {
        return nonAlcoholicDrinkRepository.getSoftDrinks();
    }
}

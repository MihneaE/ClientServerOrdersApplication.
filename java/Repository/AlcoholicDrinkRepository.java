package Repository;

import Enums.AlcoholicDrinkType;
import Model.Drink;
import Model.Food;
import javafx.scene.text.Font;

import java.util.Iterator;
import java.util.List;

public class AlcoholicDrinkRepository {
    private List<Drink> beers;
    private List<Drink> wines;
    private List<Drink> cocktails;
    private List<Drink> strongDrinks;

    public AlcoholicDrinkRepository(List<Drink> beers, List<Drink> wines, List<Drink> cocktails, List<Drink> strongDrinks)
    {
        this.beers = beers;
        this.wines = wines;
        this.cocktails = cocktails;
        this.strongDrinks = strongDrinks;
    }

    public void addDrink(AlcoholicDrinkType alcoholicDrinkType, Drink drink)
    {
        switch (alcoholicDrinkType)
        {
            case BEER:
                this.beers.add(drink);
                break;
            case WINE:
                this.wines.add(drink);
                break;
            case COCKTAIL:
                this.cocktails.add(drink);
                break;
            case STRONG_DRINK:
                this.strongDrinks.add(drink);
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");
        }
    }

    public void removeDrink(AlcoholicDrinkType alcoholicDrinkType, String name)
    {
        switch (alcoholicDrinkType)
        {
            case BEER:
                Iterator<Drink> iterator = beers.iterator();
                while (iterator.hasNext()) {
                    Drink drink = iterator.next();
                    if (drink.getName().equals(name)) {
                        iterator.remove();
                    }
                }
                break;
            case WINE:
                iterator = wines.iterator();
                while (iterator.hasNext()) {
                    Drink drink = iterator.next();
                    if (drink.getName().equals(name)) {
                        iterator.remove();
                    }
                }
                break;
            case COCKTAIL:
                iterator = cocktails.iterator();
                while (iterator.hasNext()) {
                    Drink drink = iterator.next();
                    if (drink.getName().equals(name)) {
                        iterator.remove();
                    }
                }
                break;
            case STRONG_DRINK:
                iterator = strongDrinks.iterator();
                while (iterator.hasNext()) {
                    Drink drink = iterator.next();
                    if (drink.getName().equals(name)) {
                        iterator.remove();
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");
        }
    }

    public void updateDrink(AlcoholicDrinkType alcoholicDrinkType, String name, Drink new_drink)
    {
        switch (alcoholicDrinkType)
        {
            case BEER:
                for (int i = 0; i < beers.size(); ++i)
                    if (beers.get(i).getName().equals(name))
                        beers.set(i, new_drink);
                break;
            case WINE:
                for (int i = 0; i < wines.size(); ++i)
                    if (wines.get(i).getName().equals(name))
                        wines.set(i, new_drink);
                break;
            case COCKTAIL:
                for (int i = 0; i < cocktails.size(); ++i)
                    if (cocktails.get(i).getName().equals(name))
                        cocktails.set(i, new_drink);
                break;
            case STRONG_DRINK:
                for (int i = 0; i < strongDrinks.size(); ++i)
                    if (strongDrinks.get(i).getName().equals(name))
                        strongDrinks.set(i, new_drink);
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");
        }
    }

    public Drink findDrink(AlcoholicDrinkType alcoholicDrinkType, String name)
    {
        Drink drink = new Drink();

        switch (alcoholicDrinkType)
        {
            case BEER:
                for (int i = 0; i < beers.size(); ++i)
                    if (beers.get(i).getName().equals(name))
                        drink = beers.get(i);
                break;
            case COCKTAIL:
                for (int i = 0; i < cocktails.size(); ++i)
                    if (cocktails.get(i).getName().equals(name))
                        drink = cocktails.get(i);
                break;
            case WINE:
                for (int i = 0; i < wines.size(); ++i)
                    if (wines.get(i).getName().equals(name))
                        drink = wines.get(i);
                break;
            case STRONG_DRINK:
                for (int i = 0; i < strongDrinks.size(); ++i)
                    if (strongDrinks.get(i).getName().equals(name))
                        drink = strongDrinks.get(i);
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");
        }

        return drink;
    }


    public List<Drink> getBeers() {
        return beers;
    }

    public List<Drink> getCocktails() {
        return cocktails;
    }

    public List<Drink> getStrongDrinks() {
        return strongDrinks;
    }

    public List<Drink> getWines() {
        return wines;
    }
}

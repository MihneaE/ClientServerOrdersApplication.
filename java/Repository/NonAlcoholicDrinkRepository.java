package Repository;

import Enums.AlcoholicDrinkType;
import Enums.NonAlcoholicDrinkType;
import Model.Drink;

import java.util.Iterator;
import java.util.List;

public class NonAlcoholicDrinkRepository {
    private List<Drink> coffees;
    private List<Drink> teas;
    private List<Drink> juices;
    private List<Drink> softDrinks;

    public NonAlcoholicDrinkRepository(List<Drink> coffees, List<Drink> teas, List<Drink> juices, List<Drink> softDrinks)
    {
        this.coffees = coffees;
        this.teas = teas;
        this.juices = juices;
        this.softDrinks = softDrinks;
    }

    public void addDrink(NonAlcoholicDrinkType nonAlcoholicDrinkType, Drink drink)
    {
        switch (nonAlcoholicDrinkType)
        {
            case COFFEE:
                this.coffees.add(drink);
                break;
            case TEA:
                this.teas.add(drink);
                break;
            case JUICE:
                this.juices.add(drink);
                break;
            case SOFT_DRINK:
                this.softDrinks.add(drink);
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");
        }
    }

    public void removeDrink(NonAlcoholicDrinkType nonAlcoholicDrinkType, String name)
    {
        switch (nonAlcoholicDrinkType)
        {
            case COFFEE:
                Iterator<Drink> iterator = coffees.iterator();
                while (iterator.hasNext()) {
                    Drink drink = iterator.next();
                    if (drink.getName().equals(name)) {
                        iterator.remove();
                    }
                }
                break;
            case TEA:
                iterator = teas.iterator();
                while (iterator.hasNext()) {
                    Drink drink = iterator.next();
                    if (drink.getName().equals(name)) {
                        iterator.remove();
                    }
                }
                break;
            case JUICE:
                iterator = juices.iterator();
                while (iterator.hasNext()) {
                    Drink drink = iterator.next();
                    if (drink.getName().equals(name)) {
                        iterator.remove();
                    }
                }
                break;
            case SOFT_DRINK:
                iterator = softDrinks.iterator();
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

    public void updateDrink(NonAlcoholicDrinkType nonAlcoholicDrinkType, String name, Drink new_drink)
    {
        switch (nonAlcoholicDrinkType)
        {
            case COFFEE:
                for (int i = 0; i < coffees.size(); ++i)
                    if (coffees.get(i).getName().equals(name))
                        coffees.set(i, new_drink);
                break;
            case TEA:
                for (int i = 0; i < teas.size(); ++i)
                    if (teas.get(i).getName().equals(name))
                        teas.set(i, new_drink);
                break;
            case JUICE:
                for (int i = 0; i < juices.size(); ++i)
                    if (juices.get(i).getName().equals(name))
                        juices.set(i, new_drink);
                break;
            case SOFT_DRINK:
                for (int i = 0; i < softDrinks.size(); ++i)
                    if (softDrinks.get(i).getName().equals(name))
                        softDrinks.set(i, new_drink);
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");
        }
    }

    public Drink findDrink(NonAlcoholicDrinkType nonAlcoholicDrinkType, String name)
    {
        Drink drink = new Drink();

        switch (nonAlcoholicDrinkType)
        {
            case COFFEE:
                for (int i = 0; i < coffees.size(); ++i)
                    if (coffees.get(i).getName().equals(name))
                        drink = coffees.get(i);
                break;
            case JUICE:
                for (int i = 0; i < juices.size(); ++i)
                    if (juices.get(i).getName().equals(name))
                        drink = juices.get(i);
                break;
            case TEA:
                for (int i = 0; i < teas.size(); ++i)
                    if (teas.get(i).getName().equals(name))
                        drink = teas.get(i);
                break;
            case SOFT_DRINK:
                for (int i = 0; i < softDrinks.size(); ++i)
                    if (softDrinks.get(i).getName().equals(name))
                        drink = softDrinks.get(i);
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");

        }

        return drink;
    }

    public List<Drink> getCoffees() {
        return coffees;
    }

    public List<Drink> getJuices() {
        return juices;
    }

    public List<Drink> getSoftDrinks() {
        return softDrinks;
    }

    public List<Drink> getTeas() {
        return teas;
    }
}

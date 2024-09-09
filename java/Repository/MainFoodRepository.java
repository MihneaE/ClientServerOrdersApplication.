package Repository;

import Enums.MainFoodType;
import Enums.StarterFoodType;
import Model.Food;

import java.util.Iterator;
import java.util.List;

public class MainFoodRepository {
    private List<Food> meat;
    private List<Food> pasta;
    private List<Food> seafood;
    private List<Food> pizza;

    public MainFoodRepository(List<Food> meat, List<Food> pasta, List<Food> seafood, List<Food> pizza)
    {
        this.meat = meat;
        this.pasta = pasta;
        this.seafood = seafood;
        this.pizza = pizza;
    }

    public void addFood(MainFoodType mainFoodType, Food food)
    {
        switch (mainFoodType)
        {
            case MEAT:
                meat.add(food);
                break;
            case PASTA:
                pasta.add(food);
                break;
            case SEAFOOD:
                seafood.add(food);
                break;
            case PIZZA:
                pizza.add(food);
                break;
            default:
                throw new IllegalArgumentException("Invalid food type");
        }
    }

    public void removeFood(MainFoodType mainFoodType, String name)
    {
        switch (mainFoodType)
        {
            case MEAT:
                Iterator<Food> iterator = meat.iterator();
                while (iterator.hasNext())
                {
                    Food food = iterator.next();
                    if (food.getName().equals(name))
                        iterator.remove();
                }
                break;
            case PASTA:
                iterator = pasta.iterator();
                while (iterator.hasNext())
                {
                    Food food = iterator.next();
                    if (food.getName().equals(name))
                        iterator.remove();
                }
                break;
            case SEAFOOD:
                iterator = seafood.iterator();
                while (iterator.hasNext())
                {
                    Food food = iterator.next();
                    if (food.getName().equals(name))
                        iterator.remove();
                }
                break;
            case PIZZA:
                iterator = pizza.iterator();
                while (iterator.hasNext())
                {
                    Food food = iterator.next();
                    if (food.getName().equals(name))
                        iterator.remove();
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid food type");

        }
    }

    public void updateFood(MainFoodType mainFoodType, String name, Food new_food)
    {
        switch (mainFoodType)
        {
            case MEAT:
                for (int i = 0; i < meat.size(); ++i)
                    if (meat.get(i).getName().equals(name))
                        meat.set(i, new_food);
                break;
            case PASTA:
                for (int i = 0; i < pasta.size(); ++i)
                    if (pasta.get(i).getName().equals(name))
                        pasta.set(i, new_food);
                break;
            case SEAFOOD:
                for (int i = 0; i < seafood.size(); ++i)
                    if (seafood.get(i).getName().equals(name))
                        seafood.set(i, new_food);
                break;
            case PIZZA:
                for (int i = 0; i < pizza.size(); ++i)
                    if (pizza.get(i).getName().equals(name))
                        pizza.set(i, new_food);
                break;
            default:
                throw new IllegalArgumentException("Invalid food type");
        }
    }

    public Food findFood(MainFoodType mainFoodType, String name)
    {
        Food food = new Food();

        switch (mainFoodType)
        {
            case MEAT:
                for (int i = 0; i < meat.size(); ++i)
                    if (meat.get(i).getName().equals(name))
                        food = meat.get(i);
                break;
            case PASTA:
                for (int i = 0; i < pasta.size(); ++i)
                    if (pasta.get(i).getName().equals(name))
                        food = pasta.get(i);
                break;
            case SEAFOOD:
                for (int i = 0; i < seafood.size(); ++i)
                    if (seafood.get(i).getName().equals(name))
                        food = seafood.get(i);
                break;
            case PIZZA:
                for (int i = 0; i < pizza.size(); ++i)
                    if (pizza.get(i).getName().equals(name))
                        food = pizza.get(i);
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");
        }

        return food;
    }

    public List<Food> getMeat() {
        return meat;
    }

    public List<Food> getPasta() {
        return pasta;
    }

    public List<Food> getPizza() {
        return pizza;
    }

    public List<Food> getSeafood() {
        return seafood;
    }
}

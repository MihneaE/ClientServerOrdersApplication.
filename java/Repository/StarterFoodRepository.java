package Repository;

import Enums.StarterFoodType;
import Model.Food;

import java.util.Iterator;
import java.util.List;

public class StarterFoodRepository {
    private List<Food> salads;
    private List<Food> soups;
    private List<Food> smallBites;

    public StarterFoodRepository(List<Food> salads, List<Food> soups, List<Food> smallBites)
    {
        this.salads = salads;
        this.soups = soups;
        this.smallBites = smallBites;
    }

    public void addFood(StarterFoodType starterFoodType, Food food)
    {
        switch(starterFoodType)
        {
            case SOUP:
                soups.add(food);
                break;
            case SALAD:
                salads.add(food);
                break;
            case SMALL_BITES:
                smallBites.add(food);
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");
        }
    }

    public void removeFood(StarterFoodType starterFoodType, String name)
    {
        switch (starterFoodType)
        {
            case SOUP:
                Iterator<Food> iterator = soups.iterator();
                while (iterator.hasNext())
                {
                    Food food = iterator.next();
                    if (food.getName().equals(name))
                        iterator.remove();
                }
                break;
            case SALAD:
                iterator = salads.iterator();
                while (iterator.hasNext())
                {
                    Food food = iterator.next();
                    if (food.getName().equals(name))
                        iterator.remove();
                }
                break;
            case SMALL_BITES:
                iterator = smallBites.iterator();
                while (iterator.hasNext())
                {
                    Food food = iterator.next();
                    if (food.getName().equals(name))
                        iterator.remove();
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");

        }
    }

    public void updateFood(StarterFoodType starterFoodType, String name, Food new_food)
    {
        switch (starterFoodType)
        {
            case SOUP:
                for (int i = 0; i < soups.size(); ++i)
                    if (soups.get(i).getName().equals(name))
                        soups.set(i, new_food);
                break;
            case SALAD:
                for (int i = 0; i < salads.size(); ++i)
                    if (salads.get(i).getName().equals(name))
                        salads.set(i, new_food);
                break;
            case SMALL_BITES:
                for (int i = 0; i < smallBites.size(); ++i)
                    if (smallBites.get(i).getName().equals(name))
                        smallBites.set(i, new_food);
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");
        }
    }

    public Food findFood(StarterFoodType starterFoodType, String name)
    {
        Food food = new Food();

        switch (starterFoodType)
        {
            case SALAD:
                for (int i = 0; i < salads.size(); ++i)
                    if (salads.get(i).getName().equals(name))
                        food = salads.get(i);
                break;
            case SOUP:
                for (int i = 0; i < soups.size(); ++i)
                    if (soups.get(i).getName().equals(name))
                        food = soups.get(i);
                break;
            case SMALL_BITES:
                for (int i = 0; i < smallBites.size(); ++i)
                    if (smallBites.get(i).getName().equals(name))
                        food = smallBites.get(i);
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");
        }

        return food;
    }

    public List<Food> getSalads() {
        return salads;
    }

    public List<Food> getSoups() {
        return soups;
    }

    public List<Food> getSmallBites() {
        return smallBites;
    }
}

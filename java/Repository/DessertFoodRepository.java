package Repository;

import Enums.DessertFoodType;
import Enums.StarterFoodType;
import Model.Food;

import java.util.Iterator;
import java.util.List;

import static Enums.DessertFoodType.*;

public class DessertFoodRepository {
    private List<Food> cakes;
    private List<Food> iceScream;
    private List<Food> otherDesserts;

    public DessertFoodRepository(List<Food> cakes, List<Food> iceScream, List<Food> otherDesserts)
    {
        this.cakes = cakes;
        this.iceScream = iceScream;
        this.otherDesserts = otherDesserts;
    }

    public void addFood(DessertFoodType dessertFoodType, Food food)
    {
        switch (dessertFoodType)
        {
            case CAKE:
                cakes.add(food);
                break;
            case ICE_SCREAM:
                iceScream.add(food);
                break;
            case OTHER_DESSERT:
                otherDesserts.add(food);
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");
        }
    }

    public void removeFood(DessertFoodType dessertFoodType, String name)
    {
        switch (dessertFoodType)
        {
            case CAKE:
                Iterator<Food> iterator = cakes.iterator();
                while (iterator.hasNext())
                {
                    Food food = iterator.next();
                    if (food.getName().equals(name))
                        iterator.remove();
                }
                break;
            case ICE_SCREAM:
                iterator = iceScream.iterator();
                while (iterator.hasNext())
                {
                    Food food = iterator.next();
                    if (food.getName().equals(name))
                        iterator.remove();
                }
                break;
            case OTHER_DESSERT:
                iterator = otherDesserts.iterator();
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

    public void updateFood(DessertFoodType dessertFoodType, String name, Food new_food)
    {
        switch (dessertFoodType)
        {
            case CAKE:
                for (int i = 0; i < cakes.size(); ++i)
                    if (cakes.get(i).getName().equals(name))
                        cakes.set(i, new_food);
                break;
            case ICE_SCREAM:
                for (int i = 0; i < iceScream.size(); ++i)
                    if (iceScream.get(i).getName().equals(name))
                        iceScream.set(i, new_food);
                break;
            case OTHER_DESSERT:
                for (int i = 0; i < otherDesserts.size(); ++i)
                    if (otherDesserts.get(i).getName().equals(name))
                        otherDesserts.set(i, new_food);
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");
        }
    }

    public Food findFood(DessertFoodType dessertFoodType, String name)
    {
        Food food = new Food();

        switch (dessertFoodType)
        {
            case CAKE:
                for (int i = 0; i < cakes.size(); ++i)
                    if (cakes.get(i).getName().equals(name))
                        food = cakes.get(i);
                break;
            case ICE_SCREAM:
                for (int i = 0; i < iceScream.size(); ++i)
                    if (iceScream.get(i).getName().equals(name))
                        food = iceScream.get(i);
                break;
            case OTHER_DESSERT:
                for (int i = 0; i < otherDesserts.size(); ++i)
                    if (otherDesserts.get(i).getName().equals(name))
                        food = otherDesserts.get(i);
                break;
            default:
                throw new IllegalArgumentException("Invalid drink type");
        }

        return food;
    }

    public List<Food> getCakes() {
        return cakes;
    }

    public List<Food> getIceScream() {
        return iceScream;
    }

    public List<Food> getOtherDesserts() {
        return otherDesserts;
    }
}

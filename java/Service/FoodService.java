package Service;

import Enums.DessertFoodType;
import Enums.MainFoodType;
import Enums.StarterFoodType;
import Model.Food;
import Repository.DessertFoodRepository;
import Repository.MainFoodRepository;
import Repository.StarterFoodRepository;
import SQLDataBase.FoodDataBase;
import ServiceInteface.FoodServiceI;

import java.util.List;

public class FoodService implements FoodServiceI {
    private StarterFoodRepository starterFoodRepository;
    private MainFoodRepository mainFoodRepository;
    private DessertFoodRepository dessertFoodRepository;
    private FoodDataBase foodDataBase;

    public FoodService(StarterFoodRepository starterFoodRepository, MainFoodRepository mainFoodRepository, DessertFoodRepository dessertFoodRepository, FoodDataBase foodDataBase)
    {
        this.starterFoodRepository = starterFoodRepository;
        this.mainFoodRepository = mainFoodRepository;
        this.dessertFoodRepository = dessertFoodRepository;
        this.foodDataBase = foodDataBase;
    }

    public void addStarterFood(String table, StarterFoodType starterFoodType, int id, String name, String description, int grams, int price, float rating, int quantity)
    {
        Food food = new Food(id, name, description, grams, price, rating, quantity);

        starterFoodRepository.addFood(starterFoodType, food);
        foodDataBase.addFoodToDataBase(table, food);
    }

    public void addMainFood(String table, MainFoodType mainFoodType, int id, String name, String description, int grams, int price, float rating, int quantity)
    {
        Food food = new Food(id, name, description, grams, price, rating, quantity);

        mainFoodRepository.addFood(mainFoodType, food);
        foodDataBase.addFoodToDataBase(table, food);
    }

    public void addDessertFood(String table, DessertFoodType dessertFoodType, int id, String name, String description, int grams, int price, float rating, int quantity)
    {
        Food food = new Food(id, name, description, grams, price, rating, quantity);

        dessertFoodRepository.addFood(dessertFoodType, food);
        foodDataBase.addFoodToDataBase(table, food);
    }

    public void removeStarterFood(String table, StarterFoodType starterFoodType, String name)
    {
        starterFoodRepository.removeFood(starterFoodType, name);
        foodDataBase.deleteFoodFromDataBase(table, name);
    }

    public void removeMainFood(String table, MainFoodType mainFoodType, String name)
    {
        mainFoodRepository.removeFood(mainFoodType, name);
        foodDataBase.deleteFoodFromDataBase(table, name);
    }

    public void removeDessertFood(String table, DessertFoodType dessertFoodType, String name)
    {
        dessertFoodRepository.removeFood(dessertFoodType, name);
        foodDataBase.deleteFoodFromDataBase(table, name);
    }

    public void updateStarterFood(String table, StarterFoodType starterFoodType, String old_name, int id, String name, String description, int grams, int price, float rating, int quantity)
    {
        Food food = new Food(id, name, description, grams, price, rating, quantity);

        starterFoodRepository.updateFood(starterFoodType, old_name, food);
        foodDataBase.updateFoodFromDataBase(table, old_name, food);
    }

    public void updateMainFood(String table, MainFoodType mainFoodType, String old_name, int id, String name, String description, int grams, int price, float rating, int quantity)
    {
        Food food = new Food(id, name, description, grams, price, rating, quantity);

        mainFoodRepository.updateFood(mainFoodType, old_name, food);
        foodDataBase.updateFoodFromDataBase(table, old_name, food);
    }

    public void updateDessertFood(String table, DessertFoodType dessertFoodType, String old_name, int id, String name, String description, int grams, int price, float rating, int quantity)
    {
        Food food = new Food(id, name, description, grams, price, rating, quantity);

        dessertFoodRepository.updateFood(dessertFoodType, old_name, food);
        foodDataBase.updateFoodFromDataBase(table, old_name, food);
    }

    public Food findStarterFood(StarterFoodType starterFoodType, String name)
    {
        return starterFoodRepository.findFood(starterFoodType, name);
    }

    public Food findMainFood(MainFoodType mainFoodType, String name)
    {
        return mainFoodRepository.findFood(mainFoodType, name);
    }

    public Food findDessertFood(DessertFoodType dessertFoodType, String name)
    {
        return dessertFoodRepository.findFood(dessertFoodType, name);
    }

    public List<Food> getSoups()
    {
        return starterFoodRepository.getSoups();
    }

    public List<Food> getSalads()
    {
        return starterFoodRepository.getSalads();
    }

    public List<Food> getSmallBites()
    {
        return starterFoodRepository.getSmallBites();
    }

    public List<Food> getMeat()
    {
        return mainFoodRepository.getMeat();
    }

    public List<Food> getPasta()
    {
        return mainFoodRepository.getPasta();
    }

    public List<Food> getSeafood()
    {
        return mainFoodRepository.getSeafood();
    }

    public List<Food> getPizza()
    {
        return mainFoodRepository.getPizza();
    }

    public List<Food> getCakes()
    {
        return dessertFoodRepository.getCakes();
    }

    public List<Food> getIceScream()
    {
        return dessertFoodRepository.getIceScream();
    }

    public List<Food> getOtherDesserts()
    {
        return dessertFoodRepository.getOtherDesserts();
    }
}

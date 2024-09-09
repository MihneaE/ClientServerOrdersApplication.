package ServiceInteface;

import Enums.DessertFoodType;
import Enums.MainFoodType;
import Enums.StarterFoodType;
import Model.Food;
import Service.ServiceException;

import java.util.List;

public interface FoodServiceI {
    void addStarterFood(String table, StarterFoodType starterFoodType, int id, String name, String description, int grams, int price, float rating, int quantity) throws ServiceException;


    void addMainFood(String table, MainFoodType mainFoodType, int id, String name, String description, int grams, int price, float rating, int quantity) throws ServiceException;


    void addDessertFood(String table, DessertFoodType dessertFoodType, int id, String name, String description, int grams, int price, float rating, int quantity) throws ServiceException;


    void removeStarterFood(String table, StarterFoodType starterFoodType, String name) throws ServiceException;


    void removeMainFood(String table, MainFoodType mainFoodType, String name) throws ServiceException;


    void removeDessertFood(String table, DessertFoodType dessertFoodType, String name) throws ServiceException;


    void updateStarterFood(String table, StarterFoodType starterFoodType, String old_name, int id, String name, String description, int grams, int price, float rating, int quantity) throws ServiceException;


    void updateMainFood(String table, MainFoodType mainFoodType, String old_name, int id, String name, String description, int grams, int price, float rating, int quantity) throws ServiceException;


    void updateDessertFood(String table, DessertFoodType dessertFoodType, String old_name, int id, String name, String description, int grams, int price, float rating, int quantity) throws ServiceException;


    Food findStarterFood(StarterFoodType starterFoodType, String name) throws ServiceException;


    Food findMainFood(MainFoodType mainFoodType, String name) throws ServiceException;


    Food findDessertFood(DessertFoodType dessertFoodType, String name) throws ServiceException;


    List<Food> getSoups() throws ServiceException;

    List<Food> getSalads() throws ServiceException;

    List<Food> getSmallBites() throws ServiceException;

    List<Food> getMeat() throws ServiceException;

    List<Food> getPasta() throws ServiceException;

    List<Food> getSeafood() throws ServiceException;

    List<Food> getPizza() throws ServiceException;

    List<Food> getCakes() throws ServiceException;

    List<Food> getIceScream() throws ServiceException;

    List<Food> getOtherDesserts() throws ServiceException;
}

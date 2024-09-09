package ServiceInteface;

import Enums.AlcoholicDrinkType;
import Enums.NonAlcoholicDrinkType;
import Model.Drink;
import Service.ServiceException;

import java.util.List;

public interface DrinkServiceI {
    void addAlcoholicDrink(String table, AlcoholicDrinkType alcoholicDrinkType, int id, String name, double volume, int price, int quantity) throws ServiceException;
    void removeAlcoholicDrink(String table, AlcoholicDrinkType alcoholicDrinkType, String name) throws ServiceException;
    void updateAlcoholicDrink(String table, AlcoholicDrinkType alcoholicDrinkType, String name, int new_id, String new_name, double new_volume, int new_price, int new_quantity) throws ServiceException;
    Drink findAlcoholicDrink(AlcoholicDrinkType alcoholicDrinkType, String name) throws ServiceException;
    void addNonAlcoholicDrink(String table, NonAlcoholicDrinkType nonAlcoholicDrinkType, int id, String name, double volume, int price, int quantity) throws ServiceException;
    void removeNonAlcoholicDrink(String table, NonAlcoholicDrinkType nonAlcoholicDrinkType, String name) throws ServiceException;
    void updateNonAlcoholicDrink(String table, NonAlcoholicDrinkType nonAlcoholicDrinkType, String name, int new_id, String new_name, double new_volume, int new_price, int new_quantity) throws ServiceException;
    Drink findNonAlcoholicDrink(NonAlcoholicDrinkType nonAlcoholicDrinkType, String name) throws ServiceException;
    List<Drink> getBeers() throws ServiceException;
    List<Drink> getWines() throws ServiceException;
    List<Drink> getCocktails() throws ServiceException;
    List<Drink> getStrongDrinks() throws ServiceException;
    List<Drink> getCoffees() throws ServiceException;
    List<Drink> getTeas() throws ServiceException;
    List<Drink> getJuices() throws ServiceException;
    List<Drink> getSoftDrinks() throws ServiceException;
}

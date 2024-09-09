package Networking.RpcProtocol;

import Enums.*;
import Model.*;
import Networking.DTO.*;
import SQLDataBase.*;
import Service.*;
import ServiceInteface.*;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServicesRpcProxy implements DrinkServiceI, FoodServiceI, PlaceOrderServiceI, IndexManagerI, UserServiceI {
    private String host;
    private int port;

    private Observer client;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> qresponses;
    private volatile boolean finished;

    public ServicesRpcProxy(String host, int port) {

        this.host = host;
        this.port = port;
        qresponses = new LinkedBlockingQueue<>();
    }

    @Override
    public List<Drink> getBeers() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_BEERS).build();
        sendRequest(request);

        System.out.println("x");

        Response response = readResponse();

        return (ArrayList<Drink>) response.getData();
    }

    @Override
    public List<Drink> getCocktails() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_COCKTAILS).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Drink>) response.getData();
    }

    @Override
    public List<Drink> getCoffees() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_COFFEES).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Drink>) response.getData();
    }

    @Override
    public List<Drink> getJuices() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_JUICES).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Drink>) response.getData();
    }

    @Override
    public List<Drink> getSoftDrinks() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_SOFT_DRINKS).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Drink>) response.getData();
    }

    @Override
    public List<Drink> getStrongDrinks() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_STRONG_DRINKS).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Drink>) response.getData();
    }

    @Override
    public List<Drink> getTeas() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_TEAS).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Drink>) response.getData();
    }

    @Override
    public List<Drink> getWines() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_WINES).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Drink>) response.getData();
    }

    @Override
    public List<Food> getCakes() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_CAKES).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Food>) response.getData();
    }

    @Override
    public List<Food> getIceScream() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_ICE_SCREAM).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Food>) response.getData();
    }

    @Override
    public List<Food> getMeat() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_MEAT).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Food>) response.getData();
    }

    @Override
    public List<Food> getOtherDesserts() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_OTHER_DESSERTS).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Food>) response.getData();
    }

    @Override
    public List<Food> getPasta() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_PASTA).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Food>) response.getData();
    }

    @Override
    public List<Food> getPizza() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_PIZZA).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Food>) response.getData();
    }

    @Override
    public List<Food> getSalads() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_SALAD).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Food>) response.getData();
    }

    @Override
    public List<Food> getSeafood() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_SEA_FOOD).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Food>) response.getData();
    }

    @Override
    public List<Food> getSmallBites() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_SMALL_BITES).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Food>) response.getData();
    }

    @Override
    public List<Food> getSoups() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_SOUP).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Food>) response.getData();
    }

    @Override
    public void addOrderedDrink(Drink drink) throws ServiceException {
        Request request = new Request.Builder().type(RequestType.ADD_ORDERED_DRINK).data(drink).build();
        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {

            String err = response.getData().toString();
            throw new ServiceException(err);
        }
    }

    @Override
    public void addOrderedFood(Food food) throws ServiceException {
        Request request = new Request.Builder().type(RequestType.ADD_ORDERED_FOOD).data(food).build();
        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {

            String err = response.getData().toString();
            throw new ServiceException(err);
        }
    }

    @Override
    public List<Food> getRateFood() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_RATE_FOOD).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Food>) response.getData();
    }

    @Override
    public void resetRateFood() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.RESET_RATE_FOOD).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to reset rate food: " + response.getData());
        }
    }

    @Override
    public List<Drink> getOrderedDrink() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_ORDERED_DRINK).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Drink>) response.getData();
    }

    @Override
    public List<Food> getOrderedFood() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_ORDERED_FOOD).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<Food>) response.getData();
    }

    @Override
    public void removeOrderedDrink(String name) throws ServiceException {
        Request request = new Request.Builder().type(RequestType.REMOVE_ORDERED_DRINK).data(name).build();
        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {

            String err = response.getData().toString();
            throw new ServiceException(err);
        }
    }

    @Override
    public void removeOrderedFood(String name) throws ServiceException {
        Request request = new Request.Builder().type(RequestType.REMOVE_ORDERED_FOOD).data(name).build();
        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {

            String err = response.getData().toString();
            throw new ServiceException(err);
        }
    }

    @Override
    public void addAlcoholicDrink(String table, AlcoholicDrinkType alcoholicDrinkType, int id, String name, double volume, int price, int quantity) throws ServiceException {
        Drink drink = new Drink(id, name, volume, price, quantity);
        AddAlcoholicDrinkRequest requestData = new AddAlcoholicDrinkRequest(table, alcoholicDrinkType, drink);

        Request request = new Request.Builder()
                .type(RequestType.ADD_ALCOHOLIC_DRINK)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void addNonAlcoholicDrink(String table, NonAlcoholicDrinkType nonAlcoholicDrinkType, int id, String name, double volume, int price, int quantity) throws ServiceException {
        Drink drink = new Drink(id, name, volume, price, quantity);
        AddNonAlcoholicDrinkRequest requestData = new AddNonAlcoholicDrinkRequest(table, nonAlcoholicDrinkType, drink);

        Request request = new Request.Builder()
                .type(RequestType.ADD_NON_ALCOHOLIC_DRINK)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void removeAlcoholicDrink(String table, AlcoholicDrinkType alcoholicDrinkType, String name) throws ServiceException {
        RemoveAlcoholicDrinkRequest requestData = new RemoveAlcoholicDrinkRequest(table, alcoholicDrinkType, name);

        Request request = new Request.Builder()
                .type(RequestType.REMOVE_ALCOHOLIC_DRINK)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void removeNonAlcoholicDrink(String table, NonAlcoholicDrinkType nonAlcoholicDrinkType, String name) throws ServiceException {
        RemoveNonAlcoholicDrinkRequest requestData = new RemoveNonAlcoholicDrinkRequest(table, nonAlcoholicDrinkType, name);

        Request request = new Request.Builder()
                .type(RequestType.REMOVE_NON_ALCOHOLIC_DRINK)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void updateAlcoholicDrink(String table, AlcoholicDrinkType alcoholicDrinkType, String name, int new_id, String new_name, double new_volume, int new_price, int new_quantity) throws ServiceException {
        Drink drink = new Drink(new_id, name, new_volume, new_price, new_quantity);
        UpdateAlcoholicDrinkRequest requestData = new UpdateAlcoholicDrinkRequest(table, alcoholicDrinkType, name, drink);

        Request request = new Request.Builder()
                .type(RequestType.UPDATE_ALCOHOLIC_DRINK)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void updateNonAlcoholicDrink(String table, NonAlcoholicDrinkType nonAlcoholicDrinkType, String name, int new_id, String new_name, double new_volume, int new_price, int new_quantity) throws ServiceException {
        Drink drink = new Drink(new_id, name, new_volume, new_price, new_quantity);
        UpdateNonAlcoholicDrinkRequest requestData = new UpdateNonAlcoholicDrinkRequest(table, nonAlcoholicDrinkType, name, drink);

        Request request = new Request.Builder()
                .type(RequestType.UPDATE_NON_ALCOHOLIC_DRINK)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void addDessertFood(String table, DessertFoodType dessertFoodType, int id, String name, String description, int grams, int price, float rating, int quantity) throws ServiceException {
        Food food = new Food(id, name, description, grams, price, rating, quantity);
        AddDessertFoodRequest requestData = new AddDessertFoodRequest(table, dessertFoodType, food);

        Request request = new Request.Builder()
                .type(RequestType.ADD_DESSERT_FOOD)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void addMainFood(String table, MainFoodType mainFoodType, int id, String name, String description, int grams, int price, float rating, int quantity) throws ServiceException {
        Food food = new Food(id, name, description, grams, price, rating, quantity);
        AddMainFoodRequest requestData = new AddMainFoodRequest(table, mainFoodType, food);

        Request request = new Request.Builder()
                .type(RequestType.ADD_MAIN_FOOD)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void addStarterFood(String table, StarterFoodType starterFoodType, int id, String name, String description, int grams, int price, float rating, int quantity) throws ServiceException {
        Food food = new Food(id, name, description, grams, price, rating, quantity);
        AddStarterFoodRequest requestData = new AddStarterFoodRequest(table, starterFoodType, food);

        Request request = new Request.Builder()
                .type(RequestType.ADD_STARER_FOOD)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void removeDessertFood(String table, DessertFoodType dessertFoodType, String name) throws ServiceException {
        RemoveDessertFoodRequest requestData = new RemoveDessertFoodRequest(table, dessertFoodType, name);

        Request request = new Request.Builder()
                .type(RequestType.REMOVE_DESSERT_FOOD)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void removeMainFood(String table, MainFoodType mainFoodType, String name) throws ServiceException {
        RemoveMainFoodRequest requestData = new RemoveMainFoodRequest(table, mainFoodType, name);

        Request request = new Request.Builder()
                .type(RequestType.REMOVE_MAIN_FOOD)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void removeStarterFood(String table, StarterFoodType starterFoodType, String name) throws ServiceException {
        RemoveStarterFoodRequest requestData = new RemoveStarterFoodRequest(table, starterFoodType, name);

        Request request = new Request.Builder()
                .type(RequestType.REMOVE_STARTER_FOOD)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void updateDessertFood(String table, DessertFoodType dessertFoodType, String old_name, int id, String name, String description, int grams, int price, float rating, int quantity) throws ServiceException {
        Food food = new Food(id, name, description, grams, price, rating, quantity);
        UpdateDessertFoodRequest requestData = new UpdateDessertFoodRequest(table, dessertFoodType, old_name, food);

        Request request = new Request.Builder()
                .type(RequestType.UPDATE_DESSERT_FOOD)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void updateMainFood(String table, MainFoodType mainFoodType, String old_name, int id, String name, String description, int grams, int price, float rating, int quantity) throws ServiceException {
        Food food = new Food(id, name, description, grams, price, rating, quantity);
        UpdateMainFoodRequest requestData = new UpdateMainFoodRequest(table, mainFoodType, old_name, food);

        Request request = new Request.Builder()
                .type(RequestType.UPDATE_MAIN_FOOD)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void updateStarterFood(String table, StarterFoodType starterFoodType, String old_name, int id, String name, String description, int grams, int price, float rating, int quantity) throws ServiceException {
        Food food = new Food(id, name, description, grams, price, rating, quantity);
        UpdateStarterFoodRequest requestData = new UpdateStarterFoodRequest(table, starterFoodType, old_name, food);

        Request request = new Request.Builder()
                .type(RequestType.UPDATE_STARTER_FOOD)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void addNormalOrderToOrders(Pair<String, Integer> key, Order order) throws ServiceException {
        AddNormalOrderRequest requestData = new AddNormalOrderRequest(key, order);

        Request request = new Request.Builder()
                .type(RequestType.ADD_ORDER_TO_ORDERS)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void addOrderToDeliveryOrders(Order order) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.ADD_ORDER_TO_ORDER_DELIVERY)
                .data(order)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public Order getOrderFromMap(Pair<String, Integer> key) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_ORDER_FROM_MAP)
                .data(key)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (Order) response.getData();
    }

    @Override
    public void addOrderToOrderHistory(Order order) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.ADD_ORDER_TO_ORDER_HISTORY)
                .data(order)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void increment() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.INCREMENT_INDEX)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to save index: " + response.getData());
        }
    }

    @Override
    public int getCurrentIndex() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_CURRENT_INDEX)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to get current index: " + response.getData());
        }

        return (Integer) response.getData();
    }

    @Override
    public void saveIndex() throws IOException, ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.SAVE_INDEX)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to save index: " + response.getData());
        }
    }

    @Override
    public List<User> getUsers() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_USERS).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<User>) response.getData();
    }

    @Override
    public void addUser(int id, String name, String password, String firstName, String lastName, int age, String town, String country, String address) throws ServiceException {
        User user = new User(id, name, password, firstName, lastName, age, town, country, address);

        Request request = new Request.Builder()
                .type(RequestType.ADD_USER)
                .data(user)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void removeUser(String name) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.REMOVE_USER)
                .data(name)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void updateUser(String old_name, int id, String name, String password, String firstName, String lastName, int age, String town, String country, String address) throws ServiceException {
        User user = new User(id, name, password, firstName, lastName, age, town, country, address);
        UpdateUserRequest requestData = new UpdateUserRequest(old_name, user);

        Request request = new Request.Builder()
                .type(RequestType.UPDATE_USER)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public User findUser(String name) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.FIND_USER)
                .data(name)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (User) response.getData();
    }

    @Override
    public Drink findAlcoholicDrink(AlcoholicDrinkType alcoholicDrinkType, String name) throws ServiceException {
        FindAlcoholicDrinkRequest requestData = new FindAlcoholicDrinkRequest(alcoholicDrinkType, name);

        Request request = new Request.Builder()
                .type(RequestType.FIND_ALCOHOLIC_DRINK)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (Drink) response.getData();
    }

    @Override
    public Drink findNonAlcoholicDrink(NonAlcoholicDrinkType nonAlcoholicDrinkType, String name) throws ServiceException {
        FindNonAlcoholicDrinkRequest requestData = new FindNonAlcoholicDrinkRequest(nonAlcoholicDrinkType, name);

        Request request = new Request.Builder()
                .type(RequestType.FIND_NON_ALCOHOLIC_DRINK)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (Drink) response.getData();
    }

    @Override
    public Food findDessertFood(DessertFoodType dessertFoodType, String name) throws ServiceException {
        FindDessertFoodRequest requestData = new FindDessertFoodRequest(dessertFoodType, name);

        Request request = new Request.Builder()
                .type(RequestType.FIND_DESSERT_FOOD)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (Food) response.getData();
    }

    @Override
    public Food findMainFood(MainFoodType mainFoodType, String name) throws ServiceException {
        FindMainFoodRequest requestData = new FindMainFoodRequest(mainFoodType, name);

        Request request = new Request.Builder()
                .type(RequestType.FIND_MAIN_FOOD)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (Food) response.getData();
    }

    @Override
    public Food findStarterFood(StarterFoodType starterFoodType, String name) throws ServiceException {
        FindStarterFoodRequest requestData = new FindStarterFoodRequest(starterFoodType, name);

        Request request = new Request.Builder()
                .type(RequestType.FIND_STARTER_FOOD)
                .data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (Food) response.getData();
    }

    @Override
    public List<Order> getOrdersHistory() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_ORDERS_HISTORY)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (List<Order>) response.getData();
    }

    @Override
    public List<Order> getDeliveryOrders() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_DELIVERY_ORDERS)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (List<Order>) response.getData();
    }


    @Override
    public List<Order> getOrders() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_ORDERS)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (List<Order>) response.getData();
    }


    @Override
    public List<DeliveryAccount> getDeliveryAccounts() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_DELIVERY_ACCOUNTS)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (List<DeliveryAccount>) response.getData();
    }

    @Override
    public DeliveryAccountsDataBase getDeliveryAccountsDataBase() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_DELIVERY_ACCOUNTS_DATABASE)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (DeliveryAccountsDataBase) response.getData();
    }

    @Override
    public Map<Pair<String, Integer>, Order> getOrdersMap() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_ORDERS_MAP)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (Map<Pair<String, Integer>, Order>) response.getData();
    }

    @Override
    public void removeNormalOrderFromOrders(Order order) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.REMOVE_ORDER).data(order)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void addNormalOrderWithoutPuttingInMap(Order order) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.ADD_ORDER_WITHOUT_MAP).data(order)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public OrderDataBase getOrderDataBase() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_ORDER_DATABASE)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return(OrderDataBase) response.getData();
    }

    @Override
    public DeliveryOrderDataBase getDeliveryOrderDataBase() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_DELIVERY_ORDER_DATABASE)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return(DeliveryOrderDataBase) response.getData();
    }

    @Override
    public OrderHistoryDataBase getOrderHistoryDataBase() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_ORDER_HISTORY_DATABASE)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return(OrderHistoryDataBase) response.getData();
    }

    @Override
    public OthersDataBase getOthersDataBase() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_OTHER_DATABASE)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return(OthersDataBase) response.getData();
    }

    @Override
    public PaymentDataBase getPaymentDataBase() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_PAYMENT_DATABASE)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return(PaymentDataBase) response.getData();
    }

    @Override
    public List<Payment> getPayments() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_PAYMENTS)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return(List<Payment>) response.getData();
    }

    @Override
    public List<Order> getRestoreOrderItems() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_RESTORE_ORDER_ITEMS)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return(List<Order>) response.getData();
    }

    @Override
    public void addOrderToRestoreOrderItems(Order order) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.ADD_TO_RESTORE_ORDER_ITEMS).data(order)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void removeOrderFromRestoreOrderItems(Order order) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.REMOVE_FROM_RESTORE_ORDER_ITEMS).data(order)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void addPaymentToPayments(Payment payment) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.ADD_PAYMENT).data(payment)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void removePaymentFromPayments(Payment payment) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.REMOVE_PAYMENT).data(payment)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void putOrderToMap(Pair<String, Integer> key, Order order) throws ServiceException {
        PutOrderInMapRequest requestData = new PutOrderInMapRequest(key, order);

        Request request = new Request.Builder()
                .type(RequestType.PUT_ORDER_IN_MAP).data(requestData)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public List<Drink> getAllDrinksToSearch() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_ALL_DRINKS_TO_SEARCH)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return(List<Drink>) response.getData();
    }

    @Override
    public List<Food> getAllFoodToSearch() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_ALL_FOOD_TO_SEARCH)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return(List<Food>) response.getData();
    }

    @Override
    public Map<Pair<String, Integer>, List<Object>> getPayObjectsMap() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_PAY_OBJECTS_MAP)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return(Map<Pair<String, Integer>, List<Object>>) response.getData();
    }

    @Override
    public PayOrdersDataBase getPayOrdersDataBase() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_PAY_ORDERS_DATABASE)
                .build();

        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return(PayOrdersDataBase) response.getData();
    }

    @Override
    public List<Object> getObjectFromMap(Pair<String, Integer> key) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_OBJECT_FROM_MAP)
                .data(key)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (List<Object>) response.getData();
    }

    @Override
    public void addAccountToAccounts(DeliveryAccount account) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.ADD_ACCOUNT)
                .data(account)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public UserDataBase getUserDataBase() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_USER_DATABASE)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (UserDataBase) response.getData();
    }

    @Override
    public DrinkDataBase getDrinkDataBase() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_DRINK_DATABASE)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (DrinkDataBase) response.getData();
    }

    @Override
    public FoodDataBase getFoodDataBase() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_FOOD_DATABASE)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (FoodDataBase) response.getData();
    }

    @Override
    public List<Drink> getOrderedDeliveryDrink() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_ORDERED_DELIVERY_DRINK)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (List<Drink>) response.getData();
    }

    @Override
    public List<Food> getOrderedDeliveryFood() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_ORDERED_DELIVERY_FOOD)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (List<Food>) response.getData();
    }

    @Override
    public void addDeliveryDrinkDToOrder(Drink drink) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.ADD_ORDERED_DELIVERY_DRINK)
                .data(drink)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void addDeliveryFoodToOrder(Food food) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.ADD_ORDERED_DELIVERY_FOOD)
                .data(food)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void removeDeliveryDrinkDToOrder(String name) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.REMOVE_ORDERED_DELIVERY_DRINK)
                .data(name)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void removeDeliveryFoodToOrder(String name) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.REMOVE_ORDERED_DELIVERY_FOOD)
                .data(name)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void addThemeToDatabase(String themeName) throws ServiceException {
        Request request = new Request.Builder().type(RequestType.ADD_THEME).data(themeName).build();
        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {

            String err = response.getData().toString();
            throw new ServiceException(err);
        }
    }

    @Override
    public void deleteThemeFromDatabase(String themeName) throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.DELETE_THEME)
                .data(themeName)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public List<String> getThemes() throws ServiceException {
        Request request = new Request.Builder().type(RequestType.GET_THEMES).build();
        sendRequest(request);

        Response response = readResponse();

        return (ArrayList<String>) response.getData();
    }

    @Override
    public void clearOrderItems() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.CLEAR_ORDER_ITEMS)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public void clearDeliveryOrderItems() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.CLEAR_DELIVERY_ORDER_ITEMS)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }
    }

    @Override
    public List<Drink> getNewDrinks() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_NEW_DRINKS)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (List<Drink>) response.getData();
    }

    @Override
    public List<Food> getNewFoods() throws ServiceException {
        Request request = new Request.Builder()
                .type(RequestType.GET_NEW_FOOD)
                .build();

        sendRequest(request);

        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            throw new ServiceException("Failed to update food: " + response.getData().toString());
        }

        return (List<Food>) response.getData();
    }

    @Override
    public void addNewDrink(Drink drink) throws ServiceException {
        Request request = new Request.Builder().type(RequestType.ADD_NEW_DRINKS).data(drink).build();
        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {

            String err = response.getData().toString();
            throw new ServiceException(err);
        }
    }

    @Override
    public void addNewFood(Food food) throws ServiceException {
        Request request = new Request.Builder().type(RequestType.ADD_NEW_FOOD).data(food).build();
        sendRequest(request);

        Response response = readResponse();

        if (response.getType() == ResponseType.ERROR) {

            String err = response.getData().toString();
            throw new ServiceException(err);
        }
    }

    public void initializeConnection() throws ServiceException {

        try{
            connection = new Socket(host, port);

            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();

            input = new ObjectInputStream(connection.getInputStream());

            finished = false;
            startReader();
        } catch (IOException e) {

            throw new ServiceException("Error initializing connection " + e);
        }
    }

    private void startReader() {

        Thread thread = new Thread(new ReaderThread());
        thread.start();
    }

    public void closeConnection() {

        finished = true;

        try{

            input.close();
            output.close();
            connection.close();
            client = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRequest(Request request) throws ServiceException {

        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {

            throw new ServiceException("Error sending object " + e);
        }
    }

    private Response readResponse() throws ServiceException {

        Response response = null;

        System.out.println("Qresponses size " + qresponses.size());

        try{
            response = qresponses.take();
        } catch (InterruptedException e) {

            throw new ServiceException("Error receiving response " + e);
        }
        return response;
    }

    private void handleUpdate() throws ServiceException, InterruptedException {

        client.update();
    }

    private boolean isUpdate(Response response) {

        return response.getType() == ResponseType.UPDATE;
    }

    private class ReaderThread implements Runnable {

        public void run() {

            while (!finished) {

                try{
                    Object response = input.readObject();
                    System.out.println("response received " + response);

                    if (isUpdate((Response) response)) {

                        try{
                            //qresponses.put((Response) response);
                            handleUpdate();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else

                        try {
                            qresponses.put((Response) response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                } catch (IOException | ClassNotFoundException | ServiceException e) {

                    System.out.println("Reading error " + e);
                }
            }
        }
    }
}

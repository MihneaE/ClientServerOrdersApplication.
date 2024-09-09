package Networking.RpcProtocol;

import Enums.*;
import Model.*;
import Networking.DTO.*;
import Repository.StarterFoodRepository;
import Repository.UserRepository;
import SQLDataBase.*;
import Service.ServiceException;
import Service.ServiceManager;
import ServiceInteface.Observer;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ClientRpcReflectionWorker implements Runnable, Observer {
    private ServiceManager server;

    private Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;

    public ClientRpcReflectionWorker(ServiceManager server, Socket connection) {

        this.server = server;
        this.connection = connection;
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(connected){
            try {
                Object request=input.readObject();
                Response response=handleRequest((Request)request);
                if (response!=null){
                    sendResponse(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }

    private static Response okResponse = new Response.Builder().type(ResponseType.OK).build();

    private Response handleRequest(Request request) {
        Response response = null;

        switch (request.getType()) {
            case GET_BEERS:
                response = handleGET_BEERS(request);
                break;
            case GET_COCKTAILS:
                response = handleGET_COCKTAILS(request);
                break;
            case GET_COFFEES:
                response = handleGET_COFFEES(request);
                break;
            case GET_JUICES:
                response = handleGET_JUICES(request);
                break;
            case GET_STRONG_DRINKS:
                response = handleGET_STRONG_DRINKS(request);
                break;
            case GET_TEAS:
                response = handleGET_TEAS(request);
                break;
            case GET_WINES:
                response = handleGET_WINES(request);
                break;
            case GET_SOFT_DRINKS:
                response = handleGET_SOFT_DRINKS(request);
                break;
            case GET_CAKES:
                response = handleGET_CAKES(request);
                break;
            case GET_MEAT:
                response = handleGET_MEAT(request);
                break;
            case GET_ICE_SCREAM:
                response = handleGET_ICE_SCREAM(request);
                break;
            case GET_PASTA:
                response = handleGET_PASTA(request);
                break;
            case GET_PIZZA:
                response = handleGET_PIZZA(request);
                break;
            case GET_SALAD:
                response = handleGET_SALAD(request);
                break;
            case GET_OTHER_DESSERTS:
                response = handleGET_OTHER_DESSERTS(request);
                break;
            case GET_SEA_FOOD:
                response = handleGET_SEA_FOOD(request);
                break;
            case GET_SMALL_BITES:
                response = handleGET_SMALL_BITES(request);
                break;
            case GET_SOUP:
                response = handleGET_SOUP(request);
                break;
            case ADD_ORDERED_DRINK:
                response = handleADD_ORDERED_DRINK(request);
                break;
            case ADD_ORDERED_FOOD:
                response = handleADD_ORDERED_FOOD(request);
                break;
            case GET_RATE_FOOD:
                response = handleGET_RATE_FOOD(request);
                break;
            case RESET_RATE_FOOD:
                response = handleRESET_RATE_FOOD(request);
                break;
            case REMOVE_ORDERED_DRINK:
                response = handleREMOVE_ORDERED_DRINK(request);
                break;
            case REMOVE_ORDERED_FOOD:
                response = handleREMOVE_ORDERED_FOOD(request);
                break;
            case ADD_DESSERT_FOOD:
                response = handleADD_DESSERT_FOOD(request);
                break;
            case ADD_MAIN_FOOD:
                response = handleADD_MAIN_FOOD(request);
                break;
            case ADD_STARER_FOOD:
                response = handleADD_STARTER_FOOD(request);
                break;
            case REMOVE_DESSERT_FOOD:
                response = handleREMOVE_DESSERT_FOOD(request);
                break;
            case REMOVE_MAIN_FOOD:
                response = handleREMOVE_MAIN_FOOD(request);
                break;
            case REMOVE_STARTER_FOOD:
                response = handleREMOVE_STARTER_FOOD(request);
                break;
            case UPDATE_DESSERT_FOOD:
                response = handleUPDATE_DESSERT_FOOD(request);
                break;
            case UPDATE_MAIN_FOOD:
                response = handleUPDATE_MAIN_FOOD(request);
                break;
            case UPDATE_STARTER_FOOD:
                response = handleUPDATE_STARTER_FOOD(request);
                break;
            case ADD_ALCOHOLIC_DRINK:
                response = handleADD_ALCOHOLIC_DRINK(request);
                break;
            case ADD_NON_ALCOHOLIC_DRINK:
                response = handleADD_NON_ALCOHOLIC_DRINK(request);
                break;
            case REMOVE_ALCOHOLIC_DRINK:
                response = handleREMOVE_ALCOHOLIC_DRINK(request);
                break;
            case REMOVE_NON_ALCOHOLIC_DRINK:
                response = handleREMOVE_NON_ALCOHOLIC_DRINK(request);
                break;
            case UPDATE_ALCOHOLIC_DRINK:
                response = handleUPDATE_ALCOHOLIC_DRINK(request);
                break;
            case UPDATE_NON_ALCOHOLIC_DRINK:
                response = handleUPDATE_NON_ALCOHOLIC_DRINK(request);
                break;
            case INCREMENT_INDEX:
                response = handleINCREMENT_INDEX(request);
                break;
            case SAVE_INDEX:
                response = handleSAVE_INDEX(request);
                break;
            case GET_CURRENT_INDEX:
                response = handleGET_CURRENT_INDEX(request);
                break;
            case GET_ORDERED_DRINK:
                response = handleGET_ORDERED_DRINK(request);
                break;
            case GET_ORDERED_FOOD:
                response = handleGET_ORDERED_FOOD(request);
                break;
            case ADD_ORDER_TO_ORDERS:
                response = handleADD_ORDER_TO_ORDERS(request);
                break;
            case ADD_ORDER_TO_ORDER_HISTORY:
                response = handleADD_ORDER_TO_ORDER_HISTORY(request);
                break;
            case ADD_ORDER_TO_ORDER_DELIVERY:
                response = handleADD_ORDER_TO_ORDER_DELIVERY(request);
                break;
            case GET_ORDER_FROM_MAP:
                response = handleGET_ORDER_FROM_MAP(request);
                break;
            case ADD_USER:
                response = handleADD_USER(request);
                break;
            case REMOVE_USER:
                response = handleREMOVE_USER(request);
                break;
            case UPDATE_USER:
                response = handleUPDATE_USER(request);
                break;
            case GET_USERS:
                response = handleGET_USERS(request);
                break;
            case FIND_USER:
                response = handleFIND_USER(request);
                break;
            case FIND_ALCOHOLIC_DRINK:
                response = handleFIND_ALCOHOLIC_DRINK(request);
                break;
            case FIND_NON_ALCOHOLIC_DRINK:
                response = handleFIND_NON_ALCOHOLIC_DRINK(request);
                break;
            case FIND_DESSERT_FOOD:
                response = handleFIND_DESSERT_FOOD(request);
                break;
            case FIND_STARTER_FOOD:
                response = handleFIND_STARTER_FOOD(request);
                break;
            case FIND_MAIN_FOOD:
                response = handleFIND_MAIN_FOOD(request);
                break;
            case GET_ORDERS:
                response = handle_GET_ORDERS(request);
                break;
            case GET_DELIVERY_ORDERS:
                response = handle_GET_ORDERS_DELIVERY(request);
                break;
            case GET_ORDERS_HISTORY:
                response = handle_GET_ORDERS_HISTORY(request);
                break;
            case GET_ORDERS_MAP:
                response = handle_GET_ORDERS_MAP(request);
                break;
            case REMOVE_ORDER:
                response = handleREMOVE_ORDER(request);
                break;
            case ADD_ORDER_WITHOUT_MAP:
                response = handleADD_ORDER_WITHOUT_MAP(request);
                break;
            case GET_ORDER_DATABASE:
                response = handleGET_ORDER_DATABASE(request);
                break;
            case GET_DELIVERY_ORDER_DATABASE:
                response = handleGET_DELIVERY_ORDER_DATABASE(request);
                break;
            case GET_ORDER_HISTORY_DATABASE:
                response = handleGET_ORDER_HISTORY_DATABASE(request);
                break;
            case GET_OTHER_DATABASE:
                response = handleGET_OTHER_DATABASE(request);
                break;
            case GET_PAYMENT_DATABASE:
                response = handleGET_PAYMENT_DATABASE(request);
                break;
            case GET_PAYMENTS:
                response = handleGET_PAYMENTS(request);
                break;
            case ADD_TO_RESTORE_ORDER_ITEMS:
                response = handleADD_ORDER_TO_RESTORE_ORDER_ITEMS(request);
                break;
            case REMOVE_FROM_RESTORE_ORDER_ITEMS:
                response = handleREMOVE_ORDER_FROM_RESTORE_ORDER_ITEMS(request);
                break;
            case GET_RESTORE_ORDER_ITEMS:
                response = handleGET_RESTORE_ORDER_ITEMS(request);
                break;
            case ADD_PAYMENT:
                response = handleADD_PAYMENT(request);
                break;
            case REMOVE_PAYMENT:
                response = handleREMOVE_PAYMENT(request);
                break;
            case PUT_ORDER_IN_MAP:
                response = handlePUT_ORDER_TO_MAP(request);
                break;
            case GET_ALL_DRINKS_TO_SEARCH:
                response = handleGET_ALL_DRINKS_TO_SEARCH(request);
                break;
            case GET_ALL_FOOD_TO_SEARCH:
                response = handleGET_ALL_FOOD_TO_SEARCH(request);
                break;
            case GET_PAY_OBJECTS_MAP:
                response = handleGET_PAY_OBJECTS_MAP(request);
                break;
            case GET_PAY_ORDERS_DATABASE:
                response = handleGET_PAY_ORDERS_DATABASE(request);
                break;
            case GET_OBJECT_FROM_MAP:
                response = handleGET_OBJECT_FROM_MAP(request);
                break;
            case GET_DELIVERY_ACCOUNTS:
                response = handle_GET_DELIVERY_ACCOUNTS(request);
                break;
            case GET_DELIVERY_ACCOUNTS_DATABASE:
                response = handle_GET_DELIVERY_ACCOUNTS_DATABASE(request);
                break;
            case ADD_ACCOUNT:
                response = handleADD_ACCOUNT(request);
                break;
            case GET_USER_DATABASE:
                response = handleGET_USER_DATABASE(request);
                break;
            case GET_DRINK_DATABASE:
                response = handleGET_DRINK_DATABASE(request);
                break;
            case GET_FOOD_DATABASE:
                response = handleGET_FOOD_DATABASE(request);
                break;
            case ADD_ORDERED_DELIVERY_DRINK:
                response = handleADD_ORDERED_DELIVERY_DRINK(request);
                break;
            case ADD_ORDERED_DELIVERY_FOOD:
                response = handleADD_ORDERED_DELIVERY_FOOD(request);
                break;
            case GET_ORDERED_DELIVERY_DRINK:
                response = handleGET_ORDERED_DELIVERY_DRINK(request);
                break;
            case GET_ORDERED_DELIVERY_FOOD:
                response = handleGET_ORDERED_DELIVERY_FOOD(request);
                break;
            case REMOVE_ORDERED_DELIVERY_DRINK:
                response = handleREMOVE_ORDERED_DELIVERY_DRINK(request);
                break;
            case REMOVE_ORDERED_DELIVERY_FOOD:
                response = handleREMOVE_ORDERED_DELIVERY_FOOD(request);
                break;
            case ADD_THEME:
                response = handleADD_THEME(request);
                break;
            case DELETE_THEME:
                response = handleDELETE_THEME(request);
                break;
            case GET_THEMES:
                response = handleGET_THEMES(request);
                break;
            case CLEAR_ORDER_ITEMS:
                response = handleCLEAR_ORDER_ITEMS(request);
                break;
            case CLEAR_DELIVERY_ORDER_ITEMS:
                response = handleCLEAR_DELIVERY_ORDER_ITEMS(request);
                break;
            case GET_NEW_DRINKS:
                response = handleGET_NEW_DRINK(request);
                break;
            case GET_NEW_FOOD:
                response = handleGET_NEW_FOOD(request);
                break;
            case ADD_NEW_DRINKS:
                response = handleADD_NEW_DRINK(request);
                break;
            case ADD_NEW_FOOD:
                response = handleADD_NEW_FOOD(request);
                break;
            default:
                System.out.println("Unhandled request type: " + request.getType());
                response = new Response.Builder().type(ResponseType.ERROR).data("Unhandled request").build();
        }

        System.out.println("Method for " + request.getType() + " invoked");
        return response;
    }

    public Response handleGET_BEERS(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Drink> beers = server.getDrinkService().getBeers();
            return new Response.Builder().type(ResponseType.OK).data(beers).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_COCKTAILS(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Drink> cocktails = server.getDrinkService().getCocktails();
            return new Response.Builder().type(ResponseType.OK).data(cocktails).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_COFFEES(Request request)
    {
        System.out.println("Get all coffees request..." + request.getType());

        try
        {
            List<Drink> coffees = server.getDrinkService().getCoffees();
            return new Response.Builder().type(ResponseType.OK).data(coffees).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_JUICES(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Drink> juices = server.getDrinkService().getJuices();
            return new Response.Builder().type(ResponseType.OK).data(juices).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_STRONG_DRINKS(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Drink> strongDrinks = server.getDrinkService().getStrongDrinks();
            return new Response.Builder().type(ResponseType.OK).data(strongDrinks).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_TEAS(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Drink> teas = server.getDrinkService().getTeas();
            return new Response.Builder().type(ResponseType.OK).data(teas).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_WINES(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Drink> wines = server.getDrinkService().getWines();
            return new Response.Builder().type(ResponseType.OK).data(wines).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_SOFT_DRINKS(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Drink> softDrinks = server.getDrinkService().getSoftDrinks();
            return new Response.Builder().type(ResponseType.OK).data(softDrinks).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_CAKES(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Food> cakes = server.getFoodService().getCakes();
            return new Response.Builder().type(ResponseType.OK).data(cakes).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_SOUP(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Food> soup = server.getFoodService().getSoups();
            return new Response.Builder().type(ResponseType.OK).data(soup).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_ICE_SCREAM(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Food> iceScream = server.getFoodService().getIceScream();
            return new Response.Builder().type(ResponseType.OK).data(iceScream).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_MEAT(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Food> meat = server.getFoodService().getMeat();
            return new Response.Builder().type(ResponseType.OK).data(meat).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_OTHER_DESSERTS(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Food> otherDesserts = server.getFoodService().getOtherDesserts();
            return new Response.Builder().type(ResponseType.OK).data(otherDesserts).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_PASTA(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Food> pasta = server.getFoodService().getPasta();
            return new Response.Builder().type(ResponseType.OK).data(pasta).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_PIZZA(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Food> pizza = server.getFoodService().getPizza();
            return new Response.Builder().type(ResponseType.OK).data(pizza).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_SALAD(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Food> salad = server.getFoodService().getSalads();
            return new Response.Builder().type(ResponseType.OK).data(salad).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_SEA_FOOD(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Food> seaFood = server.getFoodService().getSeafood();
            return new Response.Builder().type(ResponseType.OK).data(seaFood).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_SMALL_BITES(Request request)
    {
        System.out.println("Get all beers request..." + request.getType());

        try
        {
            List<Food> smallBites = server.getFoodService().getSmallBites();
            return new Response.Builder().type(ResponseType.OK).data(smallBites).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_ORDERED_DRINK(Request request)
    {
        System.out.println("Add food to order request" + request.getType());
        Drink drink = (Drink) request.getData();

        try
        {
            server.getPlaceOrderManagerService().addOrderedDrink(drink);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_ORDERED_FOOD(Request request)
    {
        System.out.println("Add drink to order request" + request.getType());
        Food food = (Food) request.getData();

        try
        {
            server.getPlaceOrderManagerService().addOrderedFood(food);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_RATE_FOOD(Request request)
    {
        System.out.println("Get rate food request..." + request.getType());

        try
        {
            List<Food> rateFood = server.getPlaceOrderManagerService().getRateFood();
            return new Response.Builder().type(ResponseType.OK).data(rateFood).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleRESET_RATE_FOOD(Request request)
    {
        System.out.println("Reset rate food request..." + request.getType());

        try
        {
            server.getPlaceOrderManagerService().resetRateFood();
            return new Response.Builder().type(ResponseType.OK).build();
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleGET_ORDERED_DRINK(Request request)
    {
        System.out.println("Get ordered drink request..." + request.getType());

        try
        {
            List<Drink> ordered_drink = server.getPlaceOrderManagerService().getOrderedDrink();
            return new Response.Builder().type(ResponseType.OK).data(ordered_drink).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleGET_ORDERED_FOOD(Request request)
    {
        System.out.println("Get ordered food request..." + request.getType());

        try
        {
            List<Food> ordered_food = server.getPlaceOrderManagerService().getOrderedFood();
            return new Response.Builder().type(ResponseType.OK).data(ordered_food).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleREMOVE_ORDERED_DRINK(Request request)
    {
        System.out.println("Remove ordered drink request" + request.getType());
        String name = (String) request.getData();

        try
        {
            server.getPlaceOrderManagerService().removeOrderedDrink(name);
            return okResponse;
        } catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleREMOVE_ORDERED_FOOD(Request request)
    {
        System.out.println("Remove ordered food request" + request.getType());
        String name = (String) request.getData();

        try
        {
            server.getPlaceOrderManagerService().removeOrderedFood(name);
            return okResponse;
        } catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_DESSERT_FOOD(Request request)
    {
        System.out.println("Add dessert food request" + request.getType());

        AddDessertFoodRequest requestData = (AddDessertFoodRequest) request.getData();

        String table = requestData.getTable();
        DessertFoodType dessertFoodType = requestData.getDessertFoodType();
        Food food = requestData.getFood();

        try
        {
            server.getFoodService().addDessertFood(table, dessertFoodType,  food.getId(), food.getName(), food.getDescription(), food.getGrams(), food.getPrice(), food.getRating(), food.getQuantity());
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_MAIN_FOOD(Request request)
    {
        System.out.println("Add main food request" + request.getType());

        AddMainFoodRequest requestData = (AddMainFoodRequest) request.getData();

        String table = requestData.getTable();
        MainFoodType mainFoodType = requestData.getMainFoodType();
        Food food = requestData.getFood();

        try
        {
            server.getFoodService().addMainFood(table, mainFoodType,  food.getId(), food.getName(), food.getDescription(), food.getGrams(), food.getPrice(), food.getRating(), food.getQuantity());
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_STARTER_FOOD(Request request)
    {
        System.out.println("Add starter food request" + request.getType());

        AddStarterFoodRequest requestData = (AddStarterFoodRequest) request.getData();

        String table = requestData.getTable();
        StarterFoodType starterFoodType = requestData.getStarterFoodType();
        Food food = requestData.getFood();

        try
        {
            server.getFoodService().addStarterFood(table, starterFoodType,  food.getId(), food.getName(), food.getDescription(), food.getGrams(), food.getPrice(), food.getRating(), food.getQuantity());
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleREMOVE_DESSERT_FOOD(Request request)
    {
        System.out.println("Remove dessert food request" + request.getType());

        RemoveDessertFoodRequest requestData = (RemoveDessertFoodRequest) request.getData();

        String table = requestData.getTable();
        DessertFoodType dessertFoodType = requestData.getDessertFoodType();
        String name = requestData.getName();

        try
        {
            server.getFoodService().removeDessertFood(table, dessertFoodType,  name);
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleREMOVE_MAIN_FOOD(Request request)
    {
        System.out.println("Remove main food request" + request.getType());

        RemoveMainFoodRequest requestData = (RemoveMainFoodRequest) request.getData();

        String table = requestData.getTable();
        MainFoodType mainFoodType = requestData.getMainFoodType();
        String name = requestData.getName();

        try
        {
            server.getFoodService().removeMainFood(table, mainFoodType,  name);
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleREMOVE_STARTER_FOOD(Request request)
    {
        System.out.println("Remove starter food request" + request.getType());

        RemoveStarterFoodRequest requestData = (RemoveStarterFoodRequest) request.getData();

        String table = requestData.getTable();
        StarterFoodType starterFoodType = requestData.getStarterFoodType();
        String name = requestData.getName();

        try
        {
            server.getFoodService().removeStarterFood(table, starterFoodType,  name);
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleUPDATE_DESSERT_FOOD(Request request)
    {
        System.out.println("Update dessert food request" + request.getType());

        UpdateDessertFoodRequest requestData = (UpdateDessertFoodRequest) request.getData();

        String table = requestData.getTable();
        DessertFoodType dessertFoodType = requestData.getDessertFoodType();
        String oldName = requestData.getOldName();
        Food food = requestData.getFood();

        try
        {
            server.getFoodService().updateDessertFood(table, dessertFoodType, oldName, food.getId(), food.getName(), food.getDescription(), food.getGrams(), food.getPrice(), food.getRating(), food.getQuantity());
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleUPDATE_MAIN_FOOD(Request request)
    {
        System.out.println("Update main food request" + request.getType());

        UpdateMainFoodRequest requestData = (UpdateMainFoodRequest) request.getData();

        String table = requestData.getTable();
        MainFoodType mainFoodType = requestData.getMainFoodType();
        String oldName = requestData.getOldName();
        Food food = requestData.getFood();

        try
        {
            server.getFoodService().updateMainFood(table, mainFoodType, oldName, food.getId(), food.getName(), food.getDescription(), food.getGrams(), food.getPrice(), food.getRating(), food.getQuantity());
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleUPDATE_STARTER_FOOD(Request request)
    {
        System.out.println("Update starer food request" + request.getType());

        UpdateStarterFoodRequest requestData = (UpdateStarterFoodRequest) request.getData();

        String table = requestData.getTable();
        StarterFoodType starterFoodType = requestData.getStarterFoodType();
        String oldName = requestData.getOldName();
        Food food = requestData.getFood();

        try
        {
            server.getFoodService().updateStarterFood(table, starterFoodType, oldName, food.getId(), food.getName(), food.getDescription(), food.getGrams(), food.getPrice(), food.getRating(), food.getQuantity());
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_ALCOHOLIC_DRINK(Request request)
    {
        System.out.println("Add alcoholic drink request" + request.getType());

        AddAlcoholicDrinkRequest requestData = (AddAlcoholicDrinkRequest) request.getData();

        String table = requestData.getTable();
        AlcoholicDrinkType alcoholicDrinkType = requestData.getAlcoholicDrinkType();
        Drink drink = requestData.getDrink();

        try
        {
            server.getDrinkService().addAlcoholicDrink(table, alcoholicDrinkType, drink.getId(), drink.getName(), drink.getVolume(), drink.getPrice(), drink.getQuantity());
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_NON_ALCOHOLIC_DRINK(Request request)
    {
        System.out.println("Add non alcoholic drink request" + request.getType());

        AddNonAlcoholicDrinkRequest requestData = (AddNonAlcoholicDrinkRequest) request.getData();

        String table = requestData.getTable();
        NonAlcoholicDrinkType nonAlcoholicDrinkType = requestData.getNonAlcoholicDrinkType();
        Drink drink = requestData.getDrink();

        try
        {
            server.getDrinkService().addNonAlcoholicDrink(table, nonAlcoholicDrinkType, drink.getId(), drink.getName(), drink.getVolume(), drink.getPrice(), drink.getQuantity());
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleREMOVE_ALCOHOLIC_DRINK(Request request)
    {
        System.out.println("Remove alcoholic drink request" + request.getType());

        RemoveAlcoholicDrinkRequest requestData = (RemoveAlcoholicDrinkRequest) request.getData();

        String table = requestData.getTable();
        AlcoholicDrinkType alcoholicDrinkType = requestData.getAlcoholicDrinkType();
        String name = requestData.getName();

        try
        {
            server.getDrinkService().removeAlcoholicDrink(table, alcoholicDrinkType, name);
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleREMOVE_NON_ALCOHOLIC_DRINK(Request request)
    {
        System.out.println("Remove non alcoholic drink request" + request.getType());

        RemoveNonAlcoholicDrinkRequest requestData = (RemoveNonAlcoholicDrinkRequest) request.getData();

        String table = requestData.getTable();
        NonAlcoholicDrinkType nonAlcoholicDrinkType = requestData.getNonAlcoholicDrinkType();
        String name = requestData.getName();

        try
        {
            server.getDrinkService().removeNonAlcoholicDrink(table, nonAlcoholicDrinkType, name);
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleUPDATE_ALCOHOLIC_DRINK(Request request)
    {
        System.out.println("Update alcoholic drink request" + request.getType());

        UpdateAlcoholicDrinkRequest requestData = (UpdateAlcoholicDrinkRequest) request.getData();

        String table = requestData.getTable();
        AlcoholicDrinkType alcoholicDrinkType = requestData.getAlcoholicDrinkType();
        String name = requestData.getOldName();
        Drink drink = requestData.getNewDrink();

        try
        {
            server.getDrinkService().updateAlcoholicDrink(table, alcoholicDrinkType, name, drink.getId(), drink.getName(), drink.getVolume(), drink.getPrice(), drink.getQuantity());
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleUPDATE_NON_ALCOHOLIC_DRINK(Request request)
    {
        System.out.println("Update non alcoholic drink request" + request.getType());

        UpdateNonAlcoholicDrinkRequest requestData = (UpdateNonAlcoholicDrinkRequest) request.getData();

        String table = requestData.getTable();
        NonAlcoholicDrinkType nonAlcoholicDrinkType = requestData.getNonAlcoholicDrinkType();
        String name = requestData.getOldName();
        Drink drink = requestData.getNewDrink();

        try
        {
            server.getDrinkService().updateNonAlcoholicDrink(table, nonAlcoholicDrinkType, name, drink.getId(), drink.getName(), drink.getVolume(), drink.getPrice(), drink.getQuantity());
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleINCREMENT_INDEX(Request request)
    {
        System.out.println("Increment index request" + request.getType());

        try
        {
            server.getIndexManager().increment();
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleSAVE_INDEX(Request request)
    {
        System.out.println("Save index request" + request.getType());

        try
        {
            server.getIndexManager().saveIndex();
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Response handleGET_CURRENT_INDEX(Request request)
    {
        System.out.println("Get current index request" + request.getType());

        try
        {
            int index = server.getIndexManager().getCurrentIndex();
            return new Response.Builder().type(ResponseType.OK).data(index).build();
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_ORDER_TO_ORDERS(Request request)
    {
        System.out.println("Add order to orders request" + request.getType());
        AddNormalOrderRequest requestData = (AddNormalOrderRequest) request.getData();

        Pair<String, Integer> key = requestData.getKey();
        Order order = requestData.getOrder();

        try
        {
            server.getPlaceOrderManagerService().addNormalOrderToOrders(key, order);
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_ORDER_TO_ORDER_HISTORY(Request request)
    {
        System.out.println("Add order to order history request" + request.getType());

        Order order = (Order) request.getData();

        try
        {
            server.getPlaceOrderManagerService().addOrderToOrderHistory(order);
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_ORDER_TO_ORDER_DELIVERY(Request request)
    {
        System.out.println("Add order to order delivery request" + request.getType());
        Order order = (Order) request.getData();

        try
        {
            server.getPlaceOrderManagerService().addOrderToDeliveryOrders(order);
            return okResponse;
        }
        catch (ServiceException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_ORDER_FROM_MAP(Request request)
    {
        System.out.println("Get order from map request..." + request.getType());

        Pair<String, Integer> key = (Pair<String, Integer>) request.getData();

        try
        {
            Order order = server.getPlaceOrderManagerService().getOrderFromMap(key);
            return new Response.Builder().type(ResponseType.OK).data(order).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_USERS(Request request)
    {
        System.out.println("Get users request..." + request.getType());

        try
        {
            List<User> users = server.getUserService().getUsers();
            return new Response.Builder().type(ResponseType.OK).data(users).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_USER(Request request)
    {
        System.out.println("Get users request..." + request.getType());
        User user = (User) request.getData();

        try
        {
            server.getUserService().addUser(user.getId(), user.getName(),user.getPassword(), user.getFirstName(), user.getLastName(), user.getAge(), user.getTown(), user.getCountry(), user.getAddress());
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleREMOVE_USER(Request request)
    {
        System.out.println("Get users request..." + request.getType());
        String name = (String) request.getData();

        try
        {
            server.getUserService().removeUser(name);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleUPDATE_USER(Request request)
    {
        System.out.println("Get users request..." + request.getType());
        UpdateUserRequest requestData = (UpdateUserRequest) request.getData();
        User user = requestData.getUser();
        String old_name = requestData.getOldName();

        try
        {
            server.getUserService().updateUser(old_name, user.getId(), user.getName(),user.getPassword(), user.getFirstName(), user.getLastName(), user.getAge(), user.getTown(), user.getCountry(), user.getAddress());
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleFIND_USER(Request request)
    {
        System.out.println("Find user request..." + request.getType());

        String name = (String) request.getData();

        try
        {
            User user = server.getUserService().findUser(name);
            return new Response.Builder().type(ResponseType.OK).data(user).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleFIND_ALCOHOLIC_DRINK(Request request)
    {
        System.out.println("Find alcoholic drink request..." + request.getType());

        FindAlcoholicDrinkRequest requestData = (FindAlcoholicDrinkRequest) request.getData();
        AlcoholicDrinkType alcoholicDrinkType = requestData.getType();
        String name = requestData.getName();

        try
        {
            Drink drink = server.getDrinkService().findAlcoholicDrink(alcoholicDrinkType, name);
            return new Response.Builder().type(ResponseType.OK).data(drink).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleFIND_NON_ALCOHOLIC_DRINK(Request request)
    {
        System.out.println("Find NON alcoholic drink request..." + request.getType());

        FindNonAlcoholicDrinkRequest requestData = (FindNonAlcoholicDrinkRequest) request.getData();
        NonAlcoholicDrinkType nonAlcoholicDrinkType = requestData.getType();
        String name = requestData.getName();

        try
        {
            Drink drink = server.getDrinkService().findNonAlcoholicDrink(nonAlcoholicDrinkType, name);
            return new Response.Builder().type(ResponseType.OK).data(drink).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleFIND_STARTER_FOOD(Request request)
    {
        System.out.println("Find starter food request..." + request.getType());

        FindStarterFoodRequest requestData = (FindStarterFoodRequest) request.getData();
        StarterFoodType starterFoodType = requestData.getType();
        String name = requestData.getName();

        try
        {
            Food food = server.getFoodService().findStarterFood(starterFoodType, name);
            return new Response.Builder().type(ResponseType.OK).data(food).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleFIND_MAIN_FOOD(Request request)
    {
        System.out.println("Find main food request..." + request.getType());

        FindMainFoodRequest requestData = (FindMainFoodRequest) request.getData();
        MainFoodType mainFoodType = requestData.getType();
        String name = requestData.getName();

        try
        {
            Food food = server.getFoodService().findMainFood(mainFoodType, name);
            return new Response.Builder().type(ResponseType.OK).data(food).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleFIND_DESSERT_FOOD(Request request)
    {
        System.out.println("Find main food request..." + request.getType());

        FindDessertFoodRequest requestData = (FindDessertFoodRequest) request.getData();
        DessertFoodType dessertFoodType = requestData.getType();
        String name = requestData.getName();

        try
        {
            Food food = server.getFoodService().findDessertFood(dessertFoodType, name);
            return new Response.Builder().type(ResponseType.OK).data(food).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handle_GET_ORDERS(Request request)
    {
        System.out.println("Get orders request..." + request.getType());

        try
        {
            List<Order> orders = server.getPlaceOrderManagerService().getOrders();
            return new Response.Builder().type(ResponseType.OK).data(orders).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handle_GET_DELIVERY_ACCOUNTS(Request request)
    {
        System.out.println("Get accounts request..." + request.getType());

        try
        {
            List<DeliveryAccount> accounts = server.getPlaceOrderManagerService().getDeliveryAccounts();
            return new Response.Builder().type(ResponseType.OK).data(accounts).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handle_GET_DELIVERY_ACCOUNTS_DATABASE(Request request)
    {
        System.out.println("Get delivery accounts database request..." + request.getType());

        try
        {
            DeliveryAccountsDataBase deliveryAccountsDataBase = server.getPlaceOrderManagerService().getDeliveryAccountsDataBase();
            return new Response.Builder().type(ResponseType.OK).data(deliveryAccountsDataBase).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handle_GET_ORDERS_HISTORY(Request request)
    {
        System.out.println("Get orders history request..." + request.getType());

        try
        {
            List<Order> ordersHistory = server.getPlaceOrderManagerService().getOrdersHistory();
            return new Response.Builder().type(ResponseType.OK).data(ordersHistory).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handle_GET_ORDERS_DELIVERY(Request request)
    {
        System.out.println("Get orders delivery request..." + request.getType());

        try
        {
            List<Order> ordersDelivery = server.getPlaceOrderManagerService().getDeliveryOrders();
            return new Response.Builder().type(ResponseType.OK).data(ordersDelivery).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handle_GET_ORDERS_MAP(Request request)
    {
        System.out.println("Get orders map request..." + request.getType());

        try
        {
            Map<Pair<String, Integer>, Order> ordersMap = server.getPlaceOrderManagerService().getOrdersMap();
            return new Response.Builder().type(ResponseType.OK).data(ordersMap).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleREMOVE_ORDER(Request request) {
        System.out.println("Remove order request..." + request.getType());
        Order order = (Order) request.getData();

        try
        {
            server.getPlaceOrderManagerService().removeNormalOrderFromOrders(order);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_ORDER_WITHOUT_MAP(Request request) {
        System.out.println("Remove order request..." + request.getType());
        Order order = (Order) request.getData();

        try
        {
            server.getPlaceOrderManagerService().addNormalOrderWithoutPuttingInMap(order);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_ORDER_DATABASE(Request request)
    {
        System.out.println("Get order database request..." + request.getType());

        try
        {
            OrderDataBase orderDataBase = server.getPlaceOrderManagerService().getOrderDataBase();
            return new Response.Builder().type(ResponseType.OK).data(orderDataBase).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_DELIVERY_ORDER_DATABASE(Request request)
    {
        System.out.println("Get delivery order database request..." + request.getType());

        try
        {
            DeliveryOrderDataBase deliveryOrderDataBase = server.getPlaceOrderManagerService().getDeliveryOrderDataBase();
            return new Response.Builder().type(ResponseType.OK).data(deliveryOrderDataBase).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_ORDER_HISTORY_DATABASE(Request request)
    {
        System.out.println("Get order history database request..." + request.getType());

        try
        {
            OrderHistoryDataBase orderHistoryDataBase = server.getPlaceOrderManagerService().getOrderHistoryDataBase();
            return new Response.Builder().type(ResponseType.OK).data(orderHistoryDataBase).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_OTHER_DATABASE(Request request)
    {
        System.out.println("Get other database request..." + request.getType());

        try
        {
            OthersDataBase othersDataBase = server.getPlaceOrderManagerService().getOthersDataBase();
            return new Response.Builder().type(ResponseType.OK).data(othersDataBase).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_PAYMENT_DATABASE(Request request)
    {
        System.out.println("Get payment database request..." + request.getType());

        try
        {
            PaymentDataBase paymentDataBase = server.getPlaceOrderManagerService().getPaymentDataBase();
            return new Response.Builder().type(ResponseType.OK).data(paymentDataBase).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_PAYMENTS(Request request)
    {
        System.out.println("Get payments request..." + request.getType());

        try
        {
            List<Payment> payments = server.getPlaceOrderManagerService().getPayments();
            return new Response.Builder().type(ResponseType.OK).data(payments).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_PAYMENT(Request request)
    {
        System.out.println("Add payments request..." + request.getType());
        Payment payment = (Payment) request.getData();

        try
        {
            server.getPlaceOrderManagerService().addPaymentToPayments(payment);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleREMOVE_PAYMENT(Request request)
    {
        System.out.println("Remove payment request..." + request.getType());
        Payment payment = (Payment) request.getData();

        try
        {
            server.getPlaceOrderManagerService().removePaymentFromPayments(payment);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_ORDER_TO_RESTORE_ORDER_ITEMS(Request request)
    {
        System.out.println("Remove from order items request..." + request.getType());
        Order order = (Order) request.getData();

        try
        {
            server.getPlaceOrderManagerService().addOrderToRestoreOrderItems(order);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleREMOVE_ORDER_FROM_RESTORE_ORDER_ITEMS(Request request)
    {
        System.out.println("Add to order items request..." + request.getType());
        Order order = (Order) request.getData();

        try
        {
            server.getPlaceOrderManagerService().removeOrderFromRestoreOrderItems(order);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_RESTORE_ORDER_ITEMS(Request request)
    {
        System.out.println("Get restore order items request..." + request.getType());

        try
        {
            List<Order> restoreOrderItems = server.getPlaceOrderManagerService().getRestoreOrderItems();
            return new Response.Builder().type(ResponseType.OK).data(restoreOrderItems).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handlePUT_ORDER_TO_MAP(Request request)
    {
        System.out.println("Get restore order items request..." + request.getType());
        PutOrderInMapRequest requestData = (PutOrderInMapRequest) request.getData();

        Pair<String, Integer> key = requestData.getKeyPair();
        Order order = requestData.getOrder();

        try
        {
            server.getPlaceOrderManagerService().putOrderToMap(key, order);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_ALL_DRINKS_TO_SEARCH(Request request)
    {
        System.out.println("Get all drinks to search request..." + request.getType());

        try
        {
            List<Drink> allDrinksToSearch = server.getPlaceOrderManagerService().getAllDrinksToSearch();
            return new Response.Builder().type(ResponseType.OK).data(allDrinksToSearch).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_ALL_FOOD_TO_SEARCH(Request request)
    {
        System.out.println("Get all food to search request..." + request.getType());

        try
        {
            List<Food> allFoodToSearch = server.getPlaceOrderManagerService().getAllFoodToSearch();
            return new Response.Builder().type(ResponseType.OK).data(allFoodToSearch).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_PAY_OBJECTS_MAP(Request request)
    {
        System.out.println("Get pay objects map request..." + request.getType());

        try
        {
            Map<Pair<String, Integer>, List<Object>> payObjectsMap = server.getPlaceOrderManagerService().getPayObjectsMap();
            return new Response.Builder().type(ResponseType.OK).data(payObjectsMap).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_PAY_ORDERS_DATABASE(Request request)
    {
        System.out.println("Get pay orders database request..." + request.getType());

        try
        {
            PayOrdersDataBase payOrdersDataBase = server.getPlaceOrderManagerService().getPayOrdersDataBase();
            return new Response.Builder().type(ResponseType.OK).data(payOrdersDataBase).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_OBJECT_FROM_MAP(Request request)
    {
        System.out.println("Get object from map request..." + request.getType());

        Pair<String, Integer> key = (Pair<String, Integer>) request.getData();

        try
        {
            List<Object> objects = server.getPlaceOrderManagerService().getObjectFromMap(key);
            return new Response.Builder().type(ResponseType.OK).data(objects).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_ACCOUNT(Request request)
    {
        System.out.println("Get object from map request..." + request.getType());

        DeliveryAccount account = (DeliveryAccount) request.getData();

        try
        {
            server.getPlaceOrderManagerService().addAccountToAccounts(account);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_USER_DATABASE(Request request)
    {
        System.out.println("Get user database request..." + request.getType());

        try
        {
            UserDataBase userDataBase = server.getPlaceOrderManagerService().getUserDataBase();
            return new Response.Builder().type(ResponseType.OK).data(userDataBase).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_DRINK_DATABASE(Request request)
    {
        System.out.println("Get drink database request..." + request.getType());

        try
        {
            DrinkDataBase drinkDataBase = server.getPlaceOrderManagerService().getDrinkDataBase();
            return new Response.Builder().type(ResponseType.OK).data(drinkDataBase).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_FOOD_DATABASE(Request request)
    {
        System.out.println("Get food database request..." + request.getType());

        try
        {
            FoodDataBase foodDataBase = server.getPlaceOrderManagerService().getFoodDataBase();
            return new Response.Builder().type(ResponseType.OK).data(foodDataBase).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_ORDERED_DELIVERY_DRINK(Request request)
    {
        System.out.println("Add ordered delivery drink request..." + request.getType());
        Drink drink = (Drink) request.getData();

        try
        {
            server.getPlaceOrderManagerService().addDeliveryDrinkDToOrder(drink);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_ORDERED_DELIVERY_FOOD(Request request)
    {
        System.out.println("Add ordered delivery food request..." + request.getType());
        Food food = (Food) request.getData();

        try
        {
            server.getPlaceOrderManagerService().addDeliveryFoodToOrder(food);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleREMOVE_ORDERED_DELIVERY_DRINK(Request request)
    {
        System.out.println("Remove ordered delivery drink request..." + request.getType());
        String name = (String) request.getData();

        try
        {
            server.getPlaceOrderManagerService().removeDeliveryDrinkDToOrder(name);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleREMOVE_ORDERED_DELIVERY_FOOD(Request request)
    {
        System.out.println("Remove ordered delivery food request..." + request.getType());
        String name = (String) request.getData();

        try
        {
            server.getPlaceOrderManagerService().removeDeliveryFoodToOrder(name);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_ORDERED_DELIVERY_DRINK(Request request)
    {
        System.out.println("Get ordered delivery drink request..." + request.getType());

        try
        {
            List<Drink> orderedDeliveryDrink = server.getPlaceOrderManagerService().getOrderedDeliveryDrink();
            return new Response.Builder().type(ResponseType.OK).data(orderedDeliveryDrink).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_ORDERED_DELIVERY_FOOD(Request request)
    {
        System.out.println("Get ordered delivery food request..." + request.getType());

        try
        {
            List<Food> orderedDeliveryFood = server.getPlaceOrderManagerService().getOrderedDeliveryFood();
            return new Response.Builder().type(ResponseType.OK).data(orderedDeliveryFood).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_THEME(Request request)
    {
        System.out.println("Add theme request" + request.getType());
        String theme = (String) request.getData();

        try
        {
            server.getPlaceOrderManagerService().addThemeToDatabase(theme);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleDELETE_THEME(Request request)
    {
        System.out.println("Delete theme request" + request.getType());
        String theme = (String) request.getData();

        try
        {
            server.getPlaceOrderManagerService().deleteThemeFromDatabase(theme);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_THEMES(Request request)
    {
        System.out.println("Get themes request..." + request.getType());

        try
        {
            List<String> themes = server.getPlaceOrderManagerService().getThemes();
            return new Response.Builder().type(ResponseType.OK).data(themes).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleCLEAR_ORDER_ITEMS(Request request)
    {
        System.out.println("Clear order items request..." + request.getType());

        try
        {
            server.getPlaceOrderManagerService().clearOrderItems();
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleCLEAR_DELIVERY_ORDER_ITEMS(Request request)
    {
        System.out.println("Clear delivery order request..." + request.getType());

        try
        {
            server.getPlaceOrderManagerService().clearDeliveryOrderItems();
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_NEW_DRINK(Request request)
    {
        System.out.println("Add new drink request" + request.getType());
        Drink drink = (Drink) request.getData();

        try
        {
            server.getPlaceOrderManagerService().addNewDrink(drink);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleADD_NEW_FOOD(Request request)
    {
        System.out.println("Add new food request" + request.getType());
        Food food = (Food) request.getData();

        try
        {
            server.getPlaceOrderManagerService().addNewFood(food);
            return okResponse;
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_NEW_DRINK(Request request)
    {
        System.out.println("Get new drink request..." + request.getType());

        try
        {
            List<Drink> drinks = server.getPlaceOrderManagerService().getNewDrinks();
            return new Response.Builder().type(ResponseType.OK).data(drinks).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    public Response handleGET_NEW_FOOD(Request request)
    {
        System.out.println("Get new food request..." + request.getType());

        try
        {
            List<Food> foods = server.getPlaceOrderManagerService().getNewFoods();
            return new Response.Builder().type(ResponseType.OK).data(foods).build();
        }
        catch (ServiceException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    @Override
    public void update() {

    }

    private void sendResponse(Response response) throws IOException {

        System.out.println("sending response " + response);

        synchronized (output) {
            output.writeObject(response);
            output.flush();
        }
    }
}

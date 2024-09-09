package Server;

import Model.*;
import Networking.Server.AbstractServer;
import Networking.Server.RpcConcurrentServer;
import Networking.Server.ServerException;
import Repository.*;
import SQLDataBase.*;
import Service.*;
import ServiceInteface.DrinkServiceI;
import ServiceInteface.FoodServiceI;
import javafx.util.Pair;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class StartServer {

    private static int defaultPort = 12345;

    public static void main(String[] args) throws SQLException {

        Properties serverProperties = new Properties();

        try
        {
            serverProperties.load(StartServer.class.getResourceAsStream("/server.properties"));
            System.out.println("Server properties set. ");
            serverProperties.list(System.out);

        }
        catch (IOException e)
        {
            System.err.println("Cannot find server.properties! " + e);
            return;
        }

        DrinkDataBase drinksDataBase = new DrinkDataBase();

        List<Drink> beers = drinksDataBase.loadDrinks("Beers");
        List<Drink> cocktails = drinksDataBase.loadDrinks("Cocktails");
        List<Drink> coffees = drinksDataBase.loadDrinks("Coffees");
        List<Drink> juices = drinksDataBase.loadDrinks("Juices");
        List<Drink> soft_drinks = drinksDataBase.loadDrinks("SoftDrinks");
        List<Drink> teas = drinksDataBase.loadDrinks("Teas");
        List<Drink> wines = drinksDataBase.loadDrinks("Wines");
        List<Drink> strong_drinks = drinksDataBase.loadDrinks("StrongDrinks");

        drinksDataBase.close();

        FoodDataBase foodDataBase = new FoodDataBase();

        List<Food> salads =foodDataBase.loadFoods("Salad");
        List<Food> soups = foodDataBase.loadFoods("Soup");
        List<Food> smallBites = foodDataBase.loadFoods("SmallBites");
        List<Food> meat = foodDataBase.loadFoods("Meat");
        List<Food> pasta = foodDataBase.loadFoods("Pasta");
        List<Food> pizza = foodDataBase.loadFoods("Pizza");
        List<Food> seafood = foodDataBase.loadFoods("Seafood");
        List<Food> cakes = foodDataBase.loadFoods("Cakes");
        List<Food> iceScreams = foodDataBase.loadFoods("IceScream");
        List<Food> otherDesserts = foodDataBase.loadFoods("OtherDesserts");

        foodDataBase.close();

        UserDataBase userDataBase = new UserDataBase();

        List<User> users = userDataBase.loadUsers();

        userDataBase.close();

        OrderDataBase orderDataBase = new OrderDataBase();

        List<Drink> orderedDrink = new ArrayList<>();
        List<Food> orderedFood = new ArrayList<>();
        List<Drink> newDrinks = new ArrayList<>();
        List<Food> newFoods = new ArrayList<>();
        List<Food> rateFood = new ArrayList<>();

        List<Drink> orderedDeliveryDrink = new ArrayList<>();
        List<Food> orderedDeliveryFood = new ArrayList<>();

        List<Order> orders = orderDataBase.loadOrders();

        Map<Pair<String, Integer>, Order> ordersMap = orderDataBase.loadOrdersToMap();

        for (int i = 1; i <= 19; i++) {
            Pair<String, Integer> insideKey = new Pair<>("inside", i);

            if (!ordersMap.containsKey(insideKey)) {
                Order order = new Order();
                order.setOrderedDrink(new ArrayList<>());
                order.setOrderedFood(new ArrayList<>());
                ordersMap.put(insideKey, order);
            }
        }

        for (int i = 1; i <= 16; i++) {
            Pair<String, Integer> terraceKey = new Pair<>("terrace", i);

            if (!ordersMap.containsKey(terraceKey)) {
                Order order = new Order();
                order.setOrderedDrink(new ArrayList<>());
                order.setOrderedFood(new ArrayList<>());
                ordersMap.put(terraceKey, order);
            }
        }

        orderDataBase.close();

        PaymentDataBase paymentDataBase = new PaymentDataBase();

        List<Payment> payments = paymentDataBase.loadPayments();

        paymentDataBase.close();

        OrderHistoryDataBase orderHistoryDataBase = new OrderHistoryDataBase();

        List<Order> ordersHistory = orderHistoryDataBase.loadOrders();

        orderHistoryDataBase.close();

        PayOrdersDataBase payOrdersDataBase = new PayOrdersDataBase();

        Map<Pair<String, Integer>, List<Object>> payObjectsMap = payOrdersDataBase.loadPayOrdersToMap();

        payOrdersDataBase.close();

        DeliveryOrderDataBase deliveryOrderDataBase = new DeliveryOrderDataBase();

        List<Order> deliveryOrders = deliveryOrderDataBase.loadDeliveryOrders();

        deliveryOrderDataBase.close();

        OthersDataBase othersDataBase = new OthersDataBase();

        List<Order> restoreOrderItems = othersDataBase.loadRestoreOrders();

        othersDataBase.close();

        DeliveryAccountsDataBase deliveryAccountsDataBase = new DeliveryAccountsDataBase();

        List<DeliveryAccount> accounts = deliveryAccountsDataBase.loadAccounts();

        deliveryAccountsDataBase.close();

        for (int i = 1; i <= 19; i++) {
            Pair<String, Integer> insideKey = new Pair<>("inside", i);
            if (!payObjectsMap.containsKey(insideKey))
                payObjectsMap.put(insideKey, new ArrayList<>());
        }

        for (int i = 1; i <= 18; i++) {
            Pair<String, Integer> terraceKey = new Pair<>("terrace", i);
            if (!payObjectsMap.containsKey(terraceKey))
                payObjectsMap.put(terraceKey, new ArrayList<>());
        }

        List<Drink> allDrinksToSearch = new ArrayList<>();

        allDrinksToSearch.addAll(coffees);
        allDrinksToSearch.addAll(beers);
        allDrinksToSearch.addAll(juices);
        allDrinksToSearch.addAll(soft_drinks);
        allDrinksToSearch.addAll(strong_drinks);
        allDrinksToSearch.addAll(teas);
        allDrinksToSearch.addAll(wines);
        allDrinksToSearch.addAll(cocktails);

        List<Food> allFoodToSearch = new ArrayList<>();

        allFoodToSearch.addAll(salads);
        allFoodToSearch.addAll(soups);
        allFoodToSearch.addAll(smallBites);
        allFoodToSearch.addAll(meat);
        allFoodToSearch.addAll(pasta);
        allFoodToSearch.addAll(seafood);
        allFoodToSearch.addAll(pizza);
        allFoodToSearch.addAll(cakes);
        allFoodToSearch.addAll(iceScreams);
        allFoodToSearch.addAll(otherDesserts);

        IndexManager indexManager = new IndexManager();

        List<String> themes = othersDataBase.loadThemes();

        AlcoholicDrinkRepository alcoholicDrinkRepository = new AlcoholicDrinkRepository(beers, wines, cocktails, strong_drinks);
        NonAlcoholicDrinkRepository nonAlcoholicDrinkRepository = new NonAlcoholicDrinkRepository(coffees, teas, juices, soft_drinks);
        DessertFoodRepository dessertFoodRepository = new DessertFoodRepository(cakes, iceScreams, otherDesserts);
        MainFoodRepository mainFoodRepository = new MainFoodRepository(meat, pasta, seafood, pizza);
        StarterFoodRepository starterFoodRepository = new StarterFoodRepository(salads, soups, smallBites);
        UserRepository userRepository = new UserRepository(users);

        PlaceOrderManagerRepository placeOrderManagerRepository = new PlaceOrderManagerRepository(orderedDrink, orderedFood, newDrinks, newFoods, orderedDeliveryDrink, orderedDeliveryFood, rateFood, orders, ordersHistory, deliveryOrders, restoreOrderItems, payments, ordersMap, allDrinksToSearch, allFoodToSearch, payObjectsMap, accounts, themes);

        DrinkService drinkService = new DrinkService(alcoholicDrinkRepository, nonAlcoholicDrinkRepository, drinksDataBase);
        FoodService foodService = new FoodService(starterFoodRepository, mainFoodRepository, dessertFoodRepository, foodDataBase);
        UserService userService = new UserService(userRepository, userDataBase);
        PlaceOrderManagerService placeOrderManagerService = new PlaceOrderManagerService(placeOrderManagerRepository, orderDataBase, orderHistoryDataBase, deliveryOrderDataBase,othersDataBase, paymentDataBase, payOrdersDataBase, deliveryAccountsDataBase, userDataBase, drinksDataBase, foodDataBase);

        ServiceManager serviceManager = new ServiceManager(drinkService, foodService, userService, placeOrderManagerService, indexManager);

        int serverPort = defaultPort;

        try
        {
            serverPort = Integer.parseInt(serverProperties.getProperty("server.port"));
        }
        catch (NumberFormatException e)
        {
            System.err.println("Wrong Port Number " + e.getMessage());
            System.err.println("Using default port " + defaultPort);
        }

        System.out.println("Starting server on port: " + serverPort);
        AbstractServer server = new RpcConcurrentServer(serverPort, serviceManager);

        try {
            server.start();
        }
        catch (ServerException e)
        {
            System.err.println("Error starting the server " + e.getMessage());
        }
        finally
        {
            try
            {
                server.stop();
            }
            catch (ServerException e)
            {
                System.err.println("Error stopping server" + e.getMessage());
            }
        }

    }
}

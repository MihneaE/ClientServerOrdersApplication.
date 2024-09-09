package Service;

import Model.*;
import Repository.PlaceOrderManagerRepository;
import SQLDataBase.*;
import ServiceInteface.PlaceOrderServiceI;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class PlaceOrderManagerService implements PlaceOrderServiceI {
    private PlaceOrderManagerRepository placeOrderManagerRepository;
    private DrinkDataBase drinkDataBase;
    private FoodDataBase foodDataBase;
    private OrderDataBase orderDataBase;
    private OrderHistoryDataBase orderHistoryDataBase;
    private DeliveryOrderDataBase deliveryOrderDataBase;
    private OthersDataBase othersDataBase;
    private PaymentDataBase paymentDataBase;
    private PayOrdersDataBase payOrdersDataBase;
    private DeliveryAccountsDataBase deliveryAccountsDataBase;
    private UserDataBase userDataBase;

    public PlaceOrderManagerService(PlaceOrderManagerRepository placeOrderManagerRepository, OrderDataBase orderDataBase, OrderHistoryDataBase orderHistoryDataBase, DeliveryOrderDataBase deliveryOrderDataBase, OthersDataBase othersDataBase, PaymentDataBase paymentDataBase, PayOrdersDataBase payOrdersDataBase, DeliveryAccountsDataBase deliveryAccountsDataBase, UserDataBase userDataBase, DrinkDataBase drinkDataBase, FoodDataBase foodDataBase)
    {
        this.placeOrderManagerRepository = placeOrderManagerRepository;
        this.orderDataBase = orderDataBase;
        this.orderHistoryDataBase = orderHistoryDataBase;
        this.deliveryOrderDataBase = deliveryOrderDataBase;
        this.othersDataBase = othersDataBase;
        this.paymentDataBase = paymentDataBase;
        this.payOrdersDataBase = payOrdersDataBase;
        this.deliveryAccountsDataBase = deliveryAccountsDataBase;
        this.userDataBase = userDataBase;
        this.drinkDataBase = drinkDataBase;
        this.foodDataBase = foodDataBase;
    }

    public void addOrderedDrink(Drink drink)
    {
        placeOrderManagerRepository.addDrinkToOder(drink);
    }

    public void addOrderedFood(Food food)
    {
        placeOrderManagerRepository.addFoodToOrder(food);
        placeOrderManagerRepository.addRateFood(food);
    }

    public void removeOrderedDrink(String name)
    {
        placeOrderManagerRepository.removeDrinkFromOrder(name);
    }

    public void removeOrderedFood(String name)
    {
        placeOrderManagerRepository.removeFoodFromOrder(name);
        placeOrderManagerRepository.removeRateFood(name);
    }

    public void addOrderToOrderHistory(Order order)
    {
        placeOrderManagerRepository.addOrderToOrderHistory(order);
        orderHistoryDataBase.addOrderToDataBase(order);
    }

    public void addOrderToDeliveryOrders(Order order)
    {
        placeOrderManagerRepository.addOrderToDeliveryOrders(order);
        deliveryOrderDataBase.addOrderToDataBase(order);
    }

    public void addNormalOrderToOrders(Pair<String, Integer> key, Order order)
    {
        placeOrderManagerRepository.addOrderToOrders(order);
        placeOrderManagerRepository.putOrderInMap(key, order);
        orderDataBase.addOrderToDataBase(order);
    }

    public void addNormalOrderWithoutPuttingInMap(Order order)
    {
        placeOrderManagerRepository.addOrderToOrders(order);
        orderDataBase.addOrderToDataBase(order);
    }

    public void removeNormalOrderFromOrders(Order order)
    {
        placeOrderManagerRepository.removeOrder(order);
        orderDataBase.deleteOrderFromDataBase(order);
    }

    public Order getOrderFromMap(Pair<String, Integer> key)
    {
        return placeOrderManagerRepository.getOrdersMap().get(key);
    }

    public List<Object> getObjectFromMap(Pair<String, Integer> key)
    {
        return placeOrderManagerRepository.getPayObjectsMap().get(key);
    }

    public void putOrderToMap(Pair<String, Integer> key, Order order)
    {
        placeOrderManagerRepository.putOrderInMap(key, order);
    }

    public void addAccountToAccounts(DeliveryAccount account)
    {
        placeOrderManagerRepository.addAccount(account);
    }

    public void resetRateFood() {
        placeOrderManagerRepository.getRateFood().clear();
    }

    public List<Food> getOrderedFood()
    {
        return placeOrderManagerRepository.getOrderedFood();
    }

    public List<Drink> getOrderedDrink()
    {
        return placeOrderManagerRepository.getOrderedDrink();
    }

    public List<Food> getRateFood()
    {
        return placeOrderManagerRepository.getRateFood();
    }

    public List<Order> getOrders()
    {
       return placeOrderManagerRepository.getOrders();
    }
    public List<Order> getDeliveryOrders()
    {
        return placeOrderManagerRepository.getDeliveryOrders();
    }

    public void addOrderToRestoreOrderItems(Order order)
    {
        placeOrderManagerRepository.addToRestoreOrderItems(order);
    }

    public void removeOrderFromRestoreOrderItems(Order order)
    {
        placeOrderManagerRepository.removeFromRestoreOrderItems(order);
    }

    public void addPaymentToPayments(Payment payment)
    {
        placeOrderManagerRepository.addPayment(payment);
    }

    public void removePaymentFromPayments(Payment payment)
    {
        placeOrderManagerRepository.removePayment(payment);
    }

    @Override
    public List<Order> getOrdersHistory() throws ServiceException {
        return placeOrderManagerRepository.getOrdersHistory();
    }

    @Override
    public Map<Pair<String, Integer>, Order> getOrdersMap() throws ServiceException {
        return placeOrderManagerRepository.getOrdersMap();
    }

    public OrderDataBase getOrderDataBase() {
        return orderDataBase;
    }

    public OrderHistoryDataBase getOrderHistoryDataBase() {
        return orderHistoryDataBase;
    }

    public DeliveryOrderDataBase getDeliveryOrderDataBase() {
        return deliveryOrderDataBase;
    }

    public OthersDataBase getOthersDataBase() {
        return othersDataBase;
    }

    public PaymentDataBase getPaymentDataBase() {
        return paymentDataBase;
    }

    public List<Payment> getPayments()
    {
        return placeOrderManagerRepository.getPayments();
    }

    public List<Order> getRestoreOrderItems()
    {
        return placeOrderManagerRepository.getRestoreOrderItems();
    }

    public PayOrdersDataBase getPayOrdersDataBase() {
        return payOrdersDataBase;
    }

    public List<Drink> getAllDrinksToSearch()
    {
        return placeOrderManagerRepository.getAllDrinksToSearch();
    }

    public List<Food> getAllFoodToSearch()
    {
        return placeOrderManagerRepository.getAllFoodToSearch();
    }

    public Map<Pair<String, Integer>, List<Object>> getPayObjectsMap() throws ServiceException {
        return placeOrderManagerRepository.getPayObjectsMap();
    }

    public List<DeliveryAccount> getDeliveryAccounts()
    {
        return placeOrderManagerRepository.getAccounts();
    }

    public DeliveryAccountsDataBase getDeliveryAccountsDataBase() {
        return deliveryAccountsDataBase;
    }

    public UserDataBase getUserDataBase() {
        return userDataBase;
    }

    public DrinkDataBase getDrinkDataBase() {
        return drinkDataBase;
    }

    public FoodDataBase getFoodDataBase() {
        return foodDataBase;
    }

    public void addDeliveryDrinkDToOrder(Drink drink)
    {
        placeOrderManagerRepository.addDeliveryDrinkToOrderR(drink);
    }

    public void addDeliveryFoodToOrder(Food food)
    {
        placeOrderManagerRepository.addDeliveryFoodToOrderR(food);
    }

    public void removeDeliveryDrinkDToOrder(String name)
    {
        placeOrderManagerRepository.removeDeliveryDrinkToOrderR(name);
    }

    public void removeDeliveryFoodToOrder(String name)
    {
        placeOrderManagerRepository.removeDeliveryFoodToOrderR(name);
    }

    public List<Drink> getOrderedDeliveryDrink()
    {
        return placeOrderManagerRepository.getOrderedDeliveryDrink();
    }

    public List<Food> getOrderedDeliveryFood()
    {
        return placeOrderManagerRepository.getOrderedDeliveryFood();
    }

    public void addThemeToDatabase(String themeName)
    {
        placeOrderManagerRepository.getThemes().add(themeName);
        othersDataBase.addThemeToDatabase(themeName);
    }

    public void deleteThemeFromDatabase(String themeName)
    {
        placeOrderManagerRepository.getThemes().remove(themeName);
        othersDataBase.deleteThemeFromDatabase(themeName);
    }

    public List<String> getThemes()
    {
        return placeOrderManagerRepository.getThemes();
    }

    public void clearOrderItems()
    {
        placeOrderManagerRepository.clearOrderItems();
    }

    public void clearDeliveryOrderItems()
    {
        placeOrderManagerRepository.clearDeliveryOrderItems();
    }

    public List<Drink> getNewDrinks(){
        return placeOrderManagerRepository.getNewDrinks();
    }
    public List<Food> getNewFoods(){
        return placeOrderManagerRepository.getNewFoods();
    }

    public void addNewDrink(Drink drink)
    {
        placeOrderManagerRepository.getNewDrinks().add(drink);
    }

    public void addNewFood(Food food)
    {
        placeOrderManagerRepository.getNewFoods().add(food);
    }
}

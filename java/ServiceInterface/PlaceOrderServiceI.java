package ServiceInteface;

import Model.*;
import SQLDataBase.*;
import Service.ServiceException;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public interface PlaceOrderServiceI {

    void addOrderedDrink(Drink drink) throws ServiceException;
    void addOrderedFood(Food food) throws ServiceException;
    void removeOrderedDrink(String name) throws ServiceException;
    void removeOrderedFood(String name) throws ServiceException;
    void resetRateFood() throws ServiceException;
    void addOrderToOrderHistory(Order order) throws ServiceException;
    void addOrderToDeliveryOrders(Order order) throws ServiceException;
    void addNormalOrderToOrders(Pair<String, Integer> key, Order order) throws ServiceException;
    Order getOrderFromMap(Pair<String, Integer> key) throws ServiceException;

    List<Food> getRateFood() throws ServiceException;
    List<Food> getOrderedFood() throws ServiceException;
    List<Drink> getOrderedDrink() throws ServiceException;
    List<Order> getOrders() throws ServiceException;
    List<Order> getDeliveryOrders() throws ServiceException;
    List<Order> getOrdersHistory() throws ServiceException;
    void removeNormalOrderFromOrders(Order order) throws ServiceException;
    void addNormalOrderWithoutPuttingInMap(Order order) throws ServiceException;

    Map<Pair<String, Integer>, Order> getOrdersMap() throws ServiceException;

    OrderDataBase getOrderDataBase() throws ServiceException;

    OrderHistoryDataBase getOrderHistoryDataBase()throws ServiceException;

    DeliveryOrderDataBase getDeliveryOrderDataBase()throws ServiceException;

    OthersDataBase getOthersDataBase()throws ServiceException;

    PaymentDataBase getPaymentDataBase() throws ServiceException;

    List<Payment> getPayments()throws ServiceException;
    List<Order> getRestoreOrderItems() throws ServiceException;
    void addOrderToRestoreOrderItems(Order order)throws ServiceException;

    void removeOrderFromRestoreOrderItems(Order order)throws ServiceException;

    void addPaymentToPayments(Payment payment)throws ServiceException;

    void removePaymentFromPayments(Payment payment) throws ServiceException;
    void putOrderToMap(Pair<String, Integer> key, Order order)throws ServiceException;

    PayOrdersDataBase getPayOrdersDataBase() throws ServiceException;

    List<Drink> getAllDrinksToSearch() throws ServiceException;

    List<Food> getAllFoodToSearch() throws ServiceException;

    Map<Pair<String, Integer>, List<Object>> getPayObjectsMap() throws ServiceException;
    List<Object> getObjectFromMap(Pair<String, Integer> key) throws ServiceException;
    List<DeliveryAccount> getDeliveryAccounts()throws ServiceException;
    DeliveryAccountsDataBase getDeliveryAccountsDataBase() throws ServiceException;
    void addAccountToAccounts(DeliveryAccount account) throws ServiceException;

    UserDataBase getUserDataBase() throws ServiceException;
    DrinkDataBase getDrinkDataBase() throws ServiceException;
    FoodDataBase getFoodDataBase() throws ServiceException;

    void addDeliveryDrinkDToOrder(Drink drink) throws ServiceException;

    void addDeliveryFoodToOrder(Food food) throws ServiceException;

    List<Drink> getOrderedDeliveryDrink() throws ServiceException;

    List<Food> getOrderedDeliveryFood() throws ServiceException;
    void removeDeliveryDrinkDToOrder(String name) throws ServiceException;
    void removeDeliveryFoodToOrder(String name) throws ServiceException;
    void addThemeToDatabase(String themeName) throws ServiceException;
    void deleteThemeFromDatabase(String themeName) throws ServiceException;
    List<String> getThemes() throws ServiceException;
    void clearOrderItems() throws ServiceException;
    void clearDeliveryOrderItems() throws ServiceException;

    List<Drink> getNewDrinks() throws ServiceException;
    List<Food> getNewFoods() throws ServiceException;

    void addNewDrink(Drink drink) throws ServiceException;

    void addNewFood(Food food) throws ServiceException;
}

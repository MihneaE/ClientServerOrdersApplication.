package Repository;

import Model.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlaceOrderManagerRepository {
    private List<Drink> orderedDrink;
    private List<Food> orderedFood;
    private List<Drink> newDrinks;
    private List<Food> newFoods;
    private List<Drink> orderedDeliveryDrink;
    private List<Food> orderedDeliveryFood;
    private List<Food> rateFood;
    private List<Order> orders;
    private List<Order> ordersHistory;
    private List<Order> deliveryOrders;
    private List<Order> restoreOrderItems;
    private List<Payment> payments;
    private Map<Pair<String, Integer>, Order> ordersMap;
    private Map<Pair<String, Integer>, List<Object>> payObjectsMap;
    private List<Drink> allDrinksToSearch;
    private List<Food> allFoodToSearch;
    private List<DeliveryAccount> accounts;
    private List<String> themes;

    public PlaceOrderManagerRepository(List<Drink> orderedDrink, List<Food> orderedFood, List<Drink> newDrinks, List<Food> newFoods, List<Drink> orderedDeliveryDrink, List<Food> orderedDeliveryFood, List<Food> rateFood ,List<Order> orders, List<Order> ordersHistory, List<Order> deliveryOrders, List<Order> restoreOrderItems, List<Payment> payments, Map<Pair<String, Integer>, Order> ordersMap, List<Drink> allDrinksToSearch, List<Food> allFoodToSearch, Map<Pair<String, Integer>, List<Object>> payObjectsMap, List<DeliveryAccount> accounts, List<String> themes)
    {
        this.orderedDrink = orderedDrink;
        this.orderedFood = orderedFood;
        this.newDrinks = newDrinks;
        this.newFoods = newFoods;
        this.orderedDeliveryDrink = orderedDeliveryDrink;
        this.orderedDeliveryFood = orderedDeliveryFood;
        this.rateFood = rateFood;
        this.orders = orders;
        this.ordersHistory = ordersHistory;
        this.deliveryOrders = deliveryOrders;
        this.ordersMap = ordersMap;
        this.restoreOrderItems = restoreOrderItems;
        this.payments = payments;
        this.allDrinksToSearch = allDrinksToSearch;
        this.allFoodToSearch = allFoodToSearch;
        this.payObjectsMap = payObjectsMap;
        this.accounts = accounts;
        this.themes = themes;
    }

    public void addDrinkToOder(Drink drink)
    {
        orderedDrink.add(drink);
    }

    public void addFoodToOrder(Food food)
    {
        orderedFood.add(food);
    }

    public void addDeliveryDrinkToOrderR(Drink drink)
    {
        orderedDeliveryDrink.add(drink);
    }

    public void addDeliveryFoodToOrderR(Food food)
    {
        orderedDeliveryFood.add(food);
    }

    public void removeDeliveryDrinkToOrderR(String name)
    {
        for (Drink drink : orderedDeliveryDrink)
        {
            if (drink.getName().equals(name))
            {
                orderedDeliveryDrink.remove(drink);
                break;
            }
        }
    }

    public void removeDeliveryFoodToOrderR(String name)
    {
        for (Food food : orderedDeliveryFood)
            if (food.getName().equals(name))
            {
                orderedDeliveryFood.remove(food);
                break;
            }
    }

    public void addRateFood(Food food)
    {
        rateFood.add(food);
    }

    public void removeDrinkFromOrder(String name)
    {
        for (Drink drink : orderedDrink)
        {
            if (drink.getName().equals(name))
            {
                orderedDrink.remove(drink);
                break;
            }
        }
    }

    public void removeFoodFromOrder(String name)
    {
        for (Food food : rateFood)
            if (food.getName().equals(name))
            {
                rateFood.remove(food);
                break;
            }
    }

    public void addAccount(DeliveryAccount account)
    {
        accounts.add(account);
    }

    public void addOrderToOrders(Order order)
    {
        orders.add(order);
    }

    public void addOrderToOrderHistory(Order order)
    {
        ordersHistory.add(order);
    }

    public void addOrderToDeliveryOrders(Order order)
    {
        deliveryOrders.add(order);
    }

    public void putOrderInMap(Pair<String, Integer> key, Order order)
    {
        ordersMap.put(key, order);
    }

    public void removeRateFood(String name)
    {
        for (Food food : rateFood)
            if (food.getName().equals(name))
            {
                rateFood.remove(food);
                break;
            }
    }

    public void addToRestoreOrderItems(Order order)
    {
        restoreOrderItems.add(order);
    }

    public void removeFromRestoreOrderItems(Order order)
    {
        restoreOrderItems.remove(order);
    }

    public void addPayment(Payment payment)
    {
        payments.add(payment);
    }

    public void removePayment(Payment payment)
    {
        payments.remove(payment);
    }

    public void removeOrder(Order order)
    {
        orders.remove(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Drink> getOrderedDrink() {
        return orderedDrink;
    }

    public List<Food> getOrderedFood() {
        return orderedFood;
    }

    public List<Order> getOrdersHistory() {
        return ordersHistory;
    }

    public List<Order> getDeliveryOrders() {
        return deliveryOrders;
    }

    public Map<Pair<String, Integer>, Order> getOrdersMap() {
        return ordersMap;
    }

    public List<Food> getRateFood() {
        return rateFood;
    }

    public List<Order> getRestoreOrderItems() {
        return restoreOrderItems;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public List<Drink> getAllDrinksToSearch() {
        return allDrinksToSearch;
    }

    public List<Food> getAllFoodToSearch() {
        return allFoodToSearch;
    }

    public Map<Pair<String, Integer>, List<Object>> getPayObjectsMap() {
        return payObjectsMap;
    }

    public List<DeliveryAccount> getAccounts() {
        return accounts;
    }

    public List<Drink> getOrderedDeliveryDrink() {
        return orderedDeliveryDrink;
    }

    public List<Food> getOrderedDeliveryFood() {
        return orderedDeliveryFood;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void clearOrderItems()
    {
        orderedDrink.clear();
        orderedFood.clear();
    }

    public void clearDeliveryOrderItems()
    {
        orderedDeliveryDrink.clear();
        orderedDeliveryFood.clear();
    }

    public List<Drink> getNewDrinks() {
        return newDrinks;
    }

    public List<Food> getNewFoods() {
        return newFoods;
    }
}

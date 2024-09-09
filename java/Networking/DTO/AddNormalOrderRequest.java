package Networking.DTO;

import Model.Order;
import javafx.util.Pair;

import java.io.Serializable;

public class AddNormalOrderRequest implements Serializable {
    private Pair<String, Integer> key;
    private Order order;

    public AddNormalOrderRequest(Pair<String, Integer> key, Order order)
    {
        this.key = key;
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public Pair<String, Integer> getKey() {
        return key;
    }
}

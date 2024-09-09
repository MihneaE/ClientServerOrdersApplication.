package Networking.DTO;

import Model.Order;
import javafx.util.Pair;

import java.io.Serializable;

public class PutOrderInMapRequest implements Serializable {
    private Pair<String, Integer> keyPair;
    private Order order;

    public PutOrderInMapRequest(Pair<String, Integer> keyPair, Order order) {
        this.keyPair = keyPair;
        this.order = order;
    }


    public Pair<String, Integer> getKeyPair() {
        return keyPair;
    }


    public Order getOrder() {
        return order;
    }
}

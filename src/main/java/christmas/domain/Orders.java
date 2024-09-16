package christmas.domain;

import java.util.List;

public class Orders {
    private final List<OrderProduct> orderProducts;

    public Orders(List<OrderProduct> orderProducts){
        this.orderProducts = orderProducts;
    }


}

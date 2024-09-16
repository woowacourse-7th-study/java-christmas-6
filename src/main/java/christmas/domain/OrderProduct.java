package christmas.domain;

import christmas.domain.vo.Product;
import java.util.Objects;

public class OrderProduct {
    private final Product product;
    private final int count;

    public OrderProduct(Product product, int count){
        this.product = product;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return product == that.product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

}

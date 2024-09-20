package christmas.domain.discount;

import christmas.domain.OrderProduct;
import christmas.domain.vo.Product;
import org.junit.jupiter.api.Order;

public class Gift {
    private static final String DEFAULT_GIFT_QUANTITY = "1개";
    private final boolean isGiftAvailable;

    public Gift(boolean isGiftAvailable) {
        this.isGiftAvailable = isGiftAvailable;
    }

    public String getGiftMessage() {
        if (isGiftAvailable) {
            return Product.CHAMPAGNE.getName() + " " + DEFAULT_GIFT_QUANTITY;
        }
        return Product.NONE.getName(); // "없음" 반환
    }
}

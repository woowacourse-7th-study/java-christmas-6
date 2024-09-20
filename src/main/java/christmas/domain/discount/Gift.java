package christmas.domain.discount;

import christmas.domain.OrderProduct;
import christmas.domain.Orders;
import christmas.domain.vo.Product;
import org.junit.jupiter.api.Order;

public class Gift implements Discount {
    private static final String DEFAULT_GIFT_QUANTITY = "1개";
    private static final String GIFT_DISCOUNT_STRING = "증정 이벤트";
    private final boolean isGiftAvailable;

    public Gift(boolean isGiftAvailable) {
        this.isGiftAvailable = isGiftAvailable;
    }

    @Override
    public int applyDiscount(Orders orders) {
        if (isGiftAvailable) {
            return Product.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    @Override
    public String getDiscountName() {
        return GIFT_DISCOUNT_STRING;
    }

    public String getGiftMessage() {
        if (isGiftAvailable) {
            return Product.CHAMPAGNE.getName() + " " + DEFAULT_GIFT_QUANTITY;
        }
        return Product.NONE.getName(); // "없음" 반환
    }
}

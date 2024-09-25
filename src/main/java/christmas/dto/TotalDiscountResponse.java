package christmas.dto;

public record TotalDiscountResponse(int totalDiscount) {
    public static TotalDiscountResponse of(int totalDiscount) {
        return new TotalDiscountResponse(totalDiscount);
    }
}

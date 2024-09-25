package christmas.dto;

public record PaymentResponse(int payment) {
    public static PaymentResponse of(int payment) {
        return new PaymentResponse(payment);
    }
}

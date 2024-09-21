package christmas.view;

import christmas.dto.OrderResponse;
import christmas.dto.VisitDayResponse;

import static christmas.constants.ViewMessage.GREETING_MESSAGE;
import static christmas.constants.ViewMessage.ORDER_HEADER;
import static christmas.constants.ViewMessage.ORDER_INFORMATION;
import static christmas.constants.ViewMessage.PRINT_DAY;

public class OutputView {
    private OutputView() {
    }

    public static void printGreetingMessage() {
        System.out.println(GREETING_MESSAGE);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printDay(VisitDayResponse visitDayResponse) {
        String dayMessage = String.format(PRINT_DAY, visitDayResponse.day());
        System.out.println(dayMessage);
    }

    public static void printOrderHeader() {
        System.out.println();
        System.out.println(ORDER_HEADER);
    }

    public static void printOrderInformation(OrderResponse orderResponse) {
        for (int i = 0; i < orderResponse.size(); i++) {
            String orderInformation = String.format(ORDER_INFORMATION, orderResponse.getName(i), orderResponse.getQuantity(i));
            System.out.println(orderInformation);
        }
    }
}

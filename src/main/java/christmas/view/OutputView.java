package christmas.view;

import christmas.constants.Menu;
import christmas.dto.OrderResponse;
import christmas.dto.PriceBeforeDiscountResponse;
import christmas.dto.VisitDayResponse;

import static christmas.constants.ViewMessage.GREETING_MESSAGE;
import static christmas.constants.ViewMessage.GiveawayMenuHeader;
import static christmas.constants.ViewMessage.NONE;
import static christmas.constants.ViewMessage.ORDER_HEADER;
import static christmas.constants.ViewMessage.ORDER_INFORMATION;
import static christmas.constants.ViewMessage.PRINT_DAY;
import static christmas.constants.ViewMessage.TOTAL_PRICE_BEFORE_DISCOUNT;
import static christmas.constants.ViewMessage.TOTAL_PRICE_BEFORE_DISCOUNT_HEADER;

public class OutputView {
    private OutputView() {
    }

    public static void printNewLine() {
        System.out.println();
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
        printNewLine();
        System.out.println(ORDER_HEADER);
    }

    public static void printOrderInformation(OrderResponse orderResponse) {
        for (int i = 0; i < orderResponse.size(); i++) {
            String orderInformation = String.format(ORDER_INFORMATION, orderResponse.getName(i), orderResponse.getQuantity(i));
            System.out.println(orderInformation);
        }
    }

    public static void printTotalPriceBeforeDiscountHeader() {
        printNewLine();
        System.out.println(TOTAL_PRICE_BEFORE_DISCOUNT_HEADER);
    }

    public static void printTotalPriceBeforeDiscount(PriceBeforeDiscountResponse priceBeforeDiscountResponse) {
        String priceBeforeDiscount = String.format(TOTAL_PRICE_BEFORE_DISCOUNT, priceBeforeDiscountResponse.priceBeforeDiscount());
        System.out.println(priceBeforeDiscount);
    }

    public static void printGiveawayMenuHeader() {
        printNewLine();
        System.out.println(GiveawayMenuHeader);
    }

    public static void printGiveawayMenu(Menu menu) {
        System.out.println(menu.getName() + " 1개");
    }

    public static void printGiveawayMenu() {
        System.out.println(NONE);
    }
}

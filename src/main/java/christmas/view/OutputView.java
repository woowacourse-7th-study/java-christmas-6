package christmas.view;

import christmas.constants.Badge;
import christmas.constants.Menu;
import christmas.constants.ViewMessage;
import christmas.dto.OrderResponse;
import christmas.dto.PriceBeforeDiscountResponse;
import christmas.dto.VisitDayResponse;
import christmas.model.Discount;

import static christmas.constants.Event.GIVEAWAY;
import static christmas.constants.Event.SPECIAL;
import static christmas.constants.Event.WEEKDAYS;
import static christmas.constants.Event.WEEKEND;
import static christmas.constants.Event.XMAS;
import static christmas.constants.Menu.CHAMPAGNE;
import static christmas.constants.ViewMessage.DISCOUNT_DETAILS;
import static christmas.constants.ViewMessage.DISCOUNT_DETAILS_HEADER;
import static christmas.constants.ViewMessage.GIVEAWAY_MENU_HEADER;
import static christmas.constants.ViewMessage.GREETING_MESSAGE;
import static christmas.constants.ViewMessage.NONE;
import static christmas.constants.ViewMessage.ORDER_INFORMATION;
import static christmas.constants.ViewMessage.ORDER_INFORMATION_HEADER;
import static christmas.constants.ViewMessage.PAYMENT;
import static christmas.constants.ViewMessage.PAYMENT_HEADER;
import static christmas.constants.ViewMessage.PRINT_DAY;
import static christmas.constants.ViewMessage.TOTAL_DISCOUNT;
import static christmas.constants.ViewMessage.TOTAL_DISCOUNT_HEADER;
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
        System.out.println(ORDER_INFORMATION_HEADER);
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
        System.out.println(GIVEAWAY_MENU_HEADER);
    }

    public static void printGiveawayMenu(Menu menu) {
        System.out.println(menu.getName() + " 1개");
    }

    public static void printGiveawayMenu() {
        System.out.println(NONE);
    }

    public static void printDiscountDetailsHeader() {
        printNewLine();
        System.out.println(DISCOUNT_DETAILS_HEADER);
    }

    public static void printDiscountDetails(Discount discount, boolean giveaway) {
        int xmasDiscount = discount.calculateXmasDiscount();
        if (xmasDiscount != 0) {
            String discountDetailsFormat = String.format(DISCOUNT_DETAILS, XMAS, xmasDiscount);
            System.out.println(discountDetailsFormat);
        }

        int weekdaysDiscount = discount.calculateWeekdaysDiscount();
        if (weekdaysDiscount != 0) {
            String discountDetailsFormat = String.format(DISCOUNT_DETAILS, WEEKDAYS, weekdaysDiscount);
            System.out.println(discountDetailsFormat);
        }

        int weekendDiscount = discount.calculateWeekendDiscount();
        if (weekendDiscount != 0) {
            String discountDetailsFormat = String.format(DISCOUNT_DETAILS, WEEKEND, weekendDiscount);
            System.out.println(discountDetailsFormat);
        }

        int specialDiscount = discount.calculateSpecialDiscount();
        if (specialDiscount != 0) {
            String discountDetailsFormat = String.format(DISCOUNT_DETAILS, SPECIAL, specialDiscount);
            System.out.println(discountDetailsFormat);
        }

        if (giveaway) {
            String discountDetailsFormat = String.format(DISCOUNT_DETAILS, GIVEAWAY, CHAMPAGNE.getPrice());
            System.out.println(discountDetailsFormat);
        }
    }

    public static void printTotalDiscountHeader() {
        printNewLine();
        System.out.println(TOTAL_DISCOUNT_HEADER);
    }

    public static void printTotalDiscount(int totalDiscount) {
        String totalDiscountMessage = String.format(TOTAL_DISCOUNT, totalDiscount);
        System.out.println(totalDiscountMessage);
    }

    public static void printPaymentHeader() {
        printNewLine();
        System.out.println(PAYMENT_HEADER);
    }

    public static void printPayment(int payment) {
        String paymentMessage = String.format(PAYMENT, payment);
        System.out.println(paymentMessage);
    }

    public static void printBadgeHeader() {
        printNewLine();
        System.out.println(ViewMessage.BADGE_HEADER);
    }

    public static void printBadge(Badge badge) {
        System.out.println(badge);
    }
}

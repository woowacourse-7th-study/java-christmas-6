package christmas.view;

import christmas.constants.Event;
import christmas.constants.Menu;
import christmas.constants.ViewMessage;
import christmas.dto.BadgeResponse;
import christmas.dto.DiscountDetailsResponse;
import christmas.dto.OrderResponse;
import christmas.dto.PaymentResponse;
import christmas.dto.PriceBeforeDiscountResponse;
import christmas.dto.TotalDiscountResponse;
import christmas.dto.VisitDayResponse;
import christmas.model.Discount;
import christmas.model.Giveaway;

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
import static christmas.constants.ViewMessage.PAYMENT_HEADER;
import static christmas.constants.ViewMessage.PRICE_FORMAT;
import static christmas.constants.ViewMessage.PRINT_DAY;
import static christmas.constants.ViewMessage.TOTAL_DISCOUNT;
import static christmas.constants.ViewMessage.TOTAL_DISCOUNT_HEADER;
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
        int priceBeforeDiscount = priceBeforeDiscountResponse.priceBeforeDiscount();
        String priceBeforeDiscountMessage = String.format(PRICE_FORMAT, priceBeforeDiscount);
        System.out.println(priceBeforeDiscountMessage);
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

    public static void printDiscountDetails(DiscountDetailsResponse discountDetailsResponse) {
        Discount discount = discountDetailsResponse.discount();
        Giveaway giveaway = discountDetailsResponse.giveaway();
        boolean isEventUser = discountDetailsResponse.isEventUser();

        if (isEventUser) {
            printDiscount(discount, giveaway);
            return;
        }
        System.out.println(NONE);
    }

    private static void printDiscount(Discount discount, Giveaway giveaway) {
        printDiscountIfNotZero(XMAS, discount.calculateXmasDiscount());
        printDiscountIfNotZero(WEEKDAYS, discount.calculateWeekdaysDiscount());
        printDiscountIfNotZero(WEEKEND, discount.calculateWeekendDiscount());
        printDiscountIfNotZero(SPECIAL, discount.calculateSpecialDiscount());
        printGiveawayDiscount(giveaway);
    }

    private static void printDiscountIfNotZero(Event eventType, int discount) {
        if (discount == 0) {
            return;
        }
        String discountDetails = String.format(DISCOUNT_DETAILS, eventType, discount);
        System.out.println(discountDetails);
    }

    private static void printGiveawayDiscount(Giveaway giveaway) {
        if (giveaway.getGiveawayStatus()) {
            String discountDetailsFormat = String.format(DISCOUNT_DETAILS, GIVEAWAY, CHAMPAGNE.getPrice());
            System.out.println(discountDetailsFormat);
        }
    }

    public static void printTotalDiscountHeader() {
        printNewLine();
        System.out.println(TOTAL_DISCOUNT_HEADER);
    }

    public static void printTotalDiscount(TotalDiscountResponse totalDiscountResponse) {
        int totalDiscount = totalDiscountResponse.totalDiscount();
        String totalDiscountMessage = String.format(TOTAL_DISCOUNT, totalDiscount);
        if (totalDiscount == 0) {
            totalDiscountMessage = String.format(PRICE_FORMAT, totalDiscount);
        }
        System.out.println(totalDiscountMessage);
    }

    public static void printPaymentHeader() {
        printNewLine();
        System.out.println(PAYMENT_HEADER);
    }

    public static void printPayment(PaymentResponse paymentResponse) {
        int payment = paymentResponse.payment();
        String paymentMessage = String.format(PRICE_FORMAT, payment);
        System.out.println(paymentMessage);
    }

    public static void printBadgeHeader() {
        printNewLine();
        System.out.println(ViewMessage.BADGE_HEADER);
    }

    public static void printBadge(BadgeResponse badgeResponse) {
        String badgeName = badgeResponse.badgeName();
        System.out.println(badgeName);
    }
}

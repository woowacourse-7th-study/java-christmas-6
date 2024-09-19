package christmas.view;

import static christmas.constants.ViewMessage.ANNOUNCE_EVENT_BENEFITS;
import static christmas.constants.ViewMessage.EVENT_PLANNER_NOTICE;
import static christmas.constants.ViewMessage.NOTICE_MENU;

import christmas.dto.OrdersDto;

public class OutputView {
    public void printHeaderNotice() {
        System.out.println(EVENT_PLANNER_NOTICE);
    }

    public void printHeaderEventBenefits(){
        System.out.println(ANNOUNCE_EVENT_BENEFITS);
        printWhiteSpace();
    }
    public void printResult(String result) {
        System.out.println(result);
    }

    public void printOrderDetails(OrdersDto ordersDto) {
        System.out.println(NOTICE_MENU);
        ordersDto.orders().getOrderProducts().stream()
            .forEach(orderProduct ->
                System.out.println(orderProduct.getProductName() + " " + orderProduct.getCount() + "개")
            );
    }

    private static void printWhiteSpace() {
        System.out.println();
    }
}
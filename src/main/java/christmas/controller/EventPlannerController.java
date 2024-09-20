package christmas.controller;

import christmas.constants.exception.InputException;
import christmas.dto.BenefitDto;
import christmas.dto.OrdersDto;
import christmas.dto.VisitDateDto;
import christmas.service.CalculateService;
import christmas.service.ConvertService;
import christmas.util.DateParserUtil;
import christmas.util.OrderParserUtil;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CalculateService calculateService;
    private final ConvertService convertService;

    private VisitDateDto visitDateDto;
    private BenefitDto benefitDto;
    private OrdersDto ordersDto;

    public EventPlannerController(InputView inputView, OutputView outputView, CalculateService calculateService, ConvertService convertService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.calculateService = calculateService;
        this.convertService = convertService;
    }

    public void run() {
        outputEventPlannerGuide();
        runUntilNoException(inputVisitDate());
        runUntilNoException(inputOrder());
        outputEventBenefits();
        outputPreTotalPrice();
        outputGiftAvailable();
    }

    private void runUntilNoException(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                break;
            } catch (InputException e) {
                outputView.printResult(e.getMessage());  // 예외 메시지 출력
            }
        }
    }

    private void outputEventPlannerGuide() { // 안내 문구 출력
        outputView.printHeaderNotice();
    }

    private Runnable inputVisitDate() { // 방문 날짜를 입력 받는다.
        return () -> {
            String date = inputView.inputVisitDate();
            visitDateDto = DateParserUtil.parseVisitDate(date);
        };
    }

    private Runnable inputOrder(){ // 주문 메뉴와 개수를 입력 받는다.
        return () ->{
            String inputOrder = inputView.inputOrder();
            ordersDto = OrderParserUtil.parseOrder(inputOrder);
        };
    }

    private void outputEventBenefits(){ // 혜택 출력
        outputView.printHeaderEventBenefits(); // 안내 문구 출력
        outputView.printOrderDetails(ordersDto); // 주문 메뉴 출력
    }

    private void outputPreTotalPrice(){ // 할인 전 총주문 금액 출력
        benefitDto = calculateService.calculateBenefits(ordersDto);
        outputView.printPreTotalPrice(benefitDto.preTotalPrice());
    }

    private void outputGiftAvailable(){
        String giftMessage = convertService.priceToGift(benefitDto);
        outputView.printIsGiftAvailable(giftMessage);
    }
}

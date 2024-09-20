package christmas;

import christmas.controller.EventPlannerController;
import christmas.service.CalculateService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        CalculateService calculateService = new CalculateService();

        new EventPlannerController(inputView,outputView,calculateService).run();
    }
}

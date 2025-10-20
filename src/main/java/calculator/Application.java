package calculator;

public class Application {
    public static void main(String[] args) {
        CalculatorController controller = new CalculatorController(
                new InputView(),
                new OutputView(),
                new StringCalculator(),
                new DelimiterParser()
        );
        controller.run();  // 프로그램 흐름 시작
    }
}
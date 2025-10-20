package calculator;

public class CalculatorController {
    private final InputView in;
    private final OutputView out;
    private final StringCalculator calc;
    private final DelimiterParser parser;

    public CalculatorController(InputView in, OutputView out, StringCalculator calc, DelimiterParser parser) {
        this.in = in;
        this.out = out;
        this.calc = calc;
        this.parser = parser;
    }

    public void run() {
        System.out.println("쉼표(,) 또는 콜론(:)으로 구분하여 숫자의 합을 하는 계산기 입니다.");

        String input = in.read();
    }
}
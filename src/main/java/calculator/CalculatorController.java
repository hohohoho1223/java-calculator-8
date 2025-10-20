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

        // 빈 문자열: 정상 케이스 → 0 출력 후 종료
        if (input.isEmpty()) {
            out.print(0);
            return;
        }

        try {
            final String delimiter;
            final String numbers;

            if (input.startsWith("//")) {
                // 첫 줄 헤더만 보고 구분자 결정
                delimiter = parser.resolveDelimiter(input); // "//<문자>"
                // 둘째 줄에서 숫자 읽기
                numbers = in.read();
            } else {
                // 기본 구분자 케이스는 한 줄 입력
                delimiter = parser.resolveDelimiter(input); // "[,:]"
                numbers   = parser.extractNumbers(input);   // == input
            }

            int result = calc.add(numbers, delimiter);
            out.print(result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e; // 테스트가 예외를 감지하도록 재던지기
        }
    }
}

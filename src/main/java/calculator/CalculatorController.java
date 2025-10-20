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
            // 구분자 결정
            String delimiter = parser.resolveDelimiter(input); // 기본 [,:] 또는 커스텀 한 글자
            String numbers   = parser.extractNumbers(input);   // 커스텀이면 \n 뒤, 아니면 전체

            // 합산
            int result = calc.add(numbers, delimiter);

            // 출력
            out.print(result);
        } catch (IllegalArgumentException e) {
            // 요구사항: 예외 발생 후 애플리케이션 종료(별도 System.exit() 금지)
            System.out.println(e.getMessage());
        }
    }
}
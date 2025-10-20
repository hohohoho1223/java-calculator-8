package calculator;

public class StringCalculator {

    public int add(String numbers, String delimiter) {
        int sum = 0;

        String[] tokens = numbers.split(delimiter);

        for (String token : tokens) {
            if (token.isEmpty()) {
                // 정책: 빈 토큰은 건너뜀 (원하면 여기서 예외로 바꿀 수 있음)
                continue;
            }

            final int n;
            try {
                n = Integer.parseInt(token);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되었습니다: " + token);
            }

            if (n < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + n);
            }

            sum += n;
        }

        return sum;
    }
}
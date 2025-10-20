package calculator;

public class StringCalculator {

    public int add(String numbers, String delimiter) {
        int sum = 0;

        String[] tokens = numbers.split(delimiter);

        for (String token : tokens) {
            if (token.isEmpty()) continue;
            try {
                int n = Integer.parseInt(token);
                if (n < 0) {
                    throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + n);
                }
                sum += n;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되었습니다: " + token);
            }
        }
        return sum;
    }
}
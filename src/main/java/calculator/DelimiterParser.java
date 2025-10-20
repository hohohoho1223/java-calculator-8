package calculator;

public class DelimiterParser {
    private static final String DEFAULT = "[,:]";

    // 기본([,:]) 또는 커스텀(//<d>\n...) 구분자를 정의
    public String resolveDelimiter(String input) {
        if (!input.startsWith("//")) return DEFAULT;

        // readLine() 결과엔 보통 '\n'이 없으므로, 있으면 거기까지 / 없으면 라인 끝까지
        int nl = input.indexOf('\n');
        int cut = (nl == -1) ? input.length() : nl;

        String customDelimiter = input.substring(2, cut);

        if (customDelimiter.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자가 비어 있습니다.");
        }
        if (customDelimiter.length() != 1) {
            throw new IllegalArgumentException("커스텀 구분자는 한 글자여야 합니다.");
        }
        return java.util.regex.Pattern.quote(customDelimiter);
    }

    // 숫자 부분을 추출(커스텀이면 \n 뒤, 아니면 전체)

    // extractNumbers(String input)는 기본 구분자 케이스에서만 사용
    public String extractNumbers(String input) {
        return input; // 기본 입력은 한 줄로 들어오므로 그대로 반환
    }
}
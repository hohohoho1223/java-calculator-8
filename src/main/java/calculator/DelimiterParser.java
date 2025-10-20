package calculator;

import java.util.regex.Pattern;

public class DelimiterParser {
    private static final String DEFAULT = "[,:]";

    // 기본([,:]) 또는 커스텀(//<d>\n...) 구분자를 정의
    public String resolveDelimiter(String input) {
        if (!input.startsWith("//")) return DEFAULT;

        int idx = input.indexOf("\n");
        if (idx == -1) {
            throw new IllegalArgumentException("커스텀 구분자 형식 오류: //과 \n 사이에 구분자, 이후 숫자");
        }

        String custom = input.substring(2, idx);

        if (custom.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자가 비어 있습니다.");
        }
        if (custom.length() != 1) {
            throw new IllegalArgumentException("커스텀 구분자는 한 글자여야 합니다.");
        }

        // 정규식 메타문자 안전 처리
        return Pattern.quote(custom);
    }
}
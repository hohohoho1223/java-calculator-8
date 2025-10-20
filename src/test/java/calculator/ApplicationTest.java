package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {

    /* 1) 빈 문자열이면 0 */
    @Test
    void 빈_문자열이면0_반환() {
        assertSimpleTest(() -> {
            run(""); // readLine() -> ""
            assertThat(output()).contains("결과 : 0");
        });
    }

    /* 2) 기본 구분자(,) */
    @Test
    void 기본_구분자_쉼표_합산() {
        assertSimpleTest(() -> {
            run("1,2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    /* 3) 기본 구분자(:) */
    @Test
    void 기본_구분자_콜론_합산() {
        assertSimpleTest(() -> {
            run("2:3:4");
            assertThat(output()).contains("결과 : 9");
        });
    }

    /* 4) 기본 구분자 혼용(, :) */
    @Test
    void 기본_구분자_혼용_합산() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    /* 5) 커스텀 구분자(//<문자>\n...) - 세미콜론 */
    @Test
    void 커스텀_구분자_세미콜론_합산() {
        assertSimpleTest(() -> {
            run("//;\n1;2;3"); // 문자열 리터럴에서 \n은 \\n으로 작성
            assertThat(output()).contains("결과 : 6");
        });
    }

    /* 6) 커스텀 구분자에 정규식 메타문자(*)가 와도 동작 (Pattern.quote 적용) */
    @Test
    void 커스텀_구분자_메타문자_별_합산() {
        assertSimpleTest(() -> {
            run("//*\n1*2*3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    /* 7) 커스텀 구분자 형식 오류 - 개행(\n) 누락 */
    @Test
    void 커스텀_구분자_개행_누락_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    /* 8) 커스텀 구분자 비어있음 */
    @Test
    void 커스텀_구분자_비어있음_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//\\n1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    /* 9) 커스텀 구분자 여러 문자(한 글자 규칙 위반) */
    @Test
    void 커스텀_구분자_여러_문자_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;;\\n1;;2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    /* 10) 음수 입력 시 예외 */
    @Test
    void 음수_입력_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    /* 11) 숫자 아님 입력 시 예외 */
    @Test
    void 숫자아님_입력_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,a"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    /* 12) 결과 출력 형식 확인 */
    @Test
    void 결과_출력_형식() {
        assertSimpleTest(() -> {
            run("4,5");
            assertThat(output()).contains("결과 : 9");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}

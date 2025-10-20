package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.NoSuchElementException;

public class InputView {
    public String read() {
        try {
            String s = Console.readLine();
            return (s == null) ? "" : s;   // null 방어
        } catch (NoSuchElementException e) {
            return "";
        }
    }
}
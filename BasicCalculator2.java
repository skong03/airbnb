import java.util.*;

public class BasicCalculator2 {
    public static void main(String[] args) {
        Calculator2 h = new Calculator2();
        int result = h.calculate(" 3+5 / 2 ");

        System.out.println(result);
    }

}

// Input: " 3+5 / 2 "
// Output: 5
class Calculator2 {
    public int calculate(String s) {
        Stack<Integer> nStack = new Stack<Integer>();
        char sign = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int temp = (int) (c - '0');
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    temp = temp * 10 + (int) (s.charAt(i + 1) - '0');
                    i++;
                }
                if (sign == '+' || sign == '-') {
                    nStack.add(sign == '+' ? temp : -temp);
                } else {
                    if (sign == '*') {
                        nStack.add(nStack.pop() * temp);
                    } else if (sign == '/') {
                        nStack.add(nStack.pop() / temp);
                    }
                }

            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                sign = c;
            }

        }

        int sum = 0;
        for (int i : nStack) {
            sum += i;
        }
        return sum;
    }
}
import AdditionalExceptions.ExpressionFormatException;
import AdditionalExceptions.NumberExceedsTheLimitException;
import AdditionalExceptions.WrongNumberFormatException;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
            Calculator calculator = Calculator.getCalculator();
        try {
            calculator.enterExcpression();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        } catch (NumberExceedsTheLimitException e) {
            System.out.println("Числа должны быть от 1 до 10 включительно");
            return;
        } catch (WrongNumberFormatException e) {
            System.out.println("Оба числа должны быть либо арабскими, либо римскими");
            return;
        } catch (ExpressionFormatException e) {
            System.out.println("выражение должно быть в следующем формате:\r\n1 + 1");
            return;
        }
            System.out.println(calculator.evaluateExpression());
    }
}

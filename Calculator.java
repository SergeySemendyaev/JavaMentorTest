import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import AdditionalExceptions.*;

public class Calculator {
    private static Calculator calculator;
    //private String firstNumber;
    //private String secondNumber;
    int a;
    int b;
    private String operator;
    private boolean romanNumbers;

    private Calculator() {
    }

    public static Calculator getCalculator() {
        if (calculator == null) {
            calculator = new Calculator();
        }
        return calculator;
    }

    public void enterExcpression() throws NumberExceedsTheLimitException, WrongNumberFormatException, ExpressionFormatException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String expression = reader.readLine();
        parseExpression(expression);
    }

    //создать подходящий вид исключения
    private void parseExpression(String expression) throws NumberExceedsTheLimitException, WrongNumberFormatException, ExpressionFormatException {
        //цифры должны быть от 1 до 10 либо арабские, либо римские
        String[] lines = expression.split("[+-/*//]");
        if (lines.length != 2) {
            throw new ExpressionFormatException();
        }
        String firstNumber = lines[0].trim();
        String secondNumber = lines[1].trim();
        operator = String.valueOf(expression.charAt(lines[0].length()));
        if (!numbersFormatIsCorrect(firstNumber, secondNumber) || !operatorIsCorrect()) {
            clearData();
            throw new WrongNumberFormatException();
        }
        if (romanNumbers) {
            try {
                a = IntRomanConverter.convertToInt(firstNumber);
                b = IntRomanConverter.convertToInt(secondNumber);
            } catch (NullPointerException e) {
                throw new NumberExceedsTheLimitException();
            }
        }
        else {
            a = Integer.parseInt(firstNumber);
            b = Integer.parseInt(secondNumber);
        }
        if (a > 10 || b > 10 || a < 1 || b < 1) {
            clearData();
            throw new NumberExceedsTheLimitException(); //вид исключения
        }
    }

    private boolean numbersFormatIsCorrect(String firstNumber, String secondNumber) {
        boolean result = false;
        Pattern arabicDigits = Pattern.compile("\\D");
        Pattern romanDigits = Pattern.compile("[^ivxmcIVXMC]");
        if (bothNumbersHasTheSameFormat(firstNumber, secondNumber, arabicDigits))
            result = true;
        else if (bothNumbersHasTheSameFormat(firstNumber, secondNumber, romanDigits)) {
            romanNumbers = true;
            result = true;
        }
        return result;
    }

    private boolean bothNumbersHasTheSameFormat(String firstNumber, String secondNumber, Pattern pattern) {
        Matcher firstNumberMatcher = pattern.matcher(firstNumber);
        Matcher secondNumberMatcher = pattern.matcher(secondNumber);
        return (firstNumberMatcher.find() || secondNumberMatcher.find()) ? false : true;
    }

    private boolean operatorIsCorrect() {
        return (operator.equals("+") || operator.equals("-") || operator.equals("/") || operator.equals("*"));
    }

    private void clearData() {
        a = 0;
        b = 0;
        operator = null;
        romanNumbers = false;
    }

    public String evaluateExpression() {
        int result = 0;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/": 
                try {
                    result = a / b;//обработать деление на 0
                }
                catch (Exception e) {
                    e.printStackTrace();    
                }
        }
        if (romanNumbers)
            return IntRomanConverter.convertToRoman(result);
        else
            return "" +  result;
    }
}

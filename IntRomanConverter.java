import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntRomanConverter {

    private static Map<String, Integer> romanMap = new HashMap<>();
    private static String[][] romanDigits = {{"I", "V", "X"}, {"X", "L", "C"}, {"C", "D", "M"}};
    static {
        romanMap.put("I",    1);
        romanMap.put("II",   2);
        romanMap.put("III",  3);
        romanMap.put("IV",   4);
        romanMap.put("V",    5);
        romanMap.put("VI",   6);
        romanMap.put("VII",  7);
        romanMap.put("VIII", 8);
        romanMap.put("IX",   9);
        romanMap.put("X",   10);
    }

    public static int convertToInt(String romanNumber) {
        return romanMap.get(romanNumber.toUpperCase());
    }

    public static String convertToRoman(int arabicNumber) {
        StringBuilder builder = new StringBuilder();
        if (arabicNumber < 0) {
            builder.append("-");
            arabicNumber = -arabicNumber;
        }
        List<Integer> digits = splitNumber(arabicNumber);
        int index = digits.size() - 1;
        for(int i = digits.size() - 1; i >= 0; i--){
            int digit = digits.get(i);
            if (digit == 0) {
            }
            else if (digit < 4) {
                for (int j = 0; j < digit; j++) {
                    builder.append(romanDigits[index][0]);
                }
            }
            else if (digit == 4) {
                builder.append(romanDigits[index][0]);
                builder.append(romanDigits[index][1]);
            }
            else if (digit < 9) {
                builder.append(romanDigits[index][1]);
                for (int j = 5; j < digit; j++) {
                    builder.append(romanDigits[index][0]);
                }
            }
            else {
                builder.append(romanDigits[index][0]);
                builder.append(romanDigits[index][2]);
            }
            index--;
            arabicNumber = (arabicNumber - digit) / 10;
        }
        return builder.toString();
    }

    private static List<Integer> splitNumber(int arabicNumber) {
        List<Integer> result = new ArrayList<>();
        while (arabicNumber != 0) {
            int digit = arabicNumber % 10;
            result.add(digit);
            arabicNumber = (arabicNumber - digit) / 10;
        }
        return result;
    }
}

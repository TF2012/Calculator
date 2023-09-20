package calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String expression = reader.readLine();                                         // получаем входные данные
            String[] splitString = expression.split(" ");
            String operation = splitString[1];
            var number1 = splitString[0];
            var number2 = splitString[2];
            String[] romeNum = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            List<String> romeNumbers = new ArrayList<>(Arrays.asList(romeNum));
            List<String> arabNumbers = new ArrayList<>();
            for (int i = 0; i < 10; i++) arabNumbers.add(String.valueOf(i));

            if (romeNumbers.contains(number1) && romeNumbers.contains(number2) && (splitString.length < 4)) {
                int num1 = NumeralConverter.fromRomanNumeral(splitString[0]);
                int num2 = NumeralConverter.fromRomanNumeral(splitString[2]);
                int result = calc(num1, num2, operation);
                if ((result < 1)) throw new Exception();
                String answer = NumeralConverter.toRomanNumeral(result);
                System.out.println(answer);
            } else if (arabNumbers.contains(number1) && arabNumbers.contains(number2) && (splitString.length < 4)) {
                System.out.println(calc(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[2]), operation));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("throws Exception");
        }
    }

    public static int calc(int num1, int num2, String operation) throws Exception {   // метод математических операций
        return switch (operation) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new Exception();
        };
    }

    static class NumeralConverter {
        // Конвертация арабских цифр в римские и наоборот
        private final static String[] ROMAN_NUMERALS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        private final static int[] DECIMAL_VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        public static String toRomanNumeral(int number) {  // из арабских цифр в римские
            var result = new StringBuilder();
            int remaining = number;
            for (int i = 0; i < ROMAN_NUMERALS.length; i++) {
                int decimalValue = DECIMAL_VALUES[i];
                String romanNumeral = ROMAN_NUMERALS[i];
                while (remaining >= decimalValue) {
                    result.append(romanNumeral);
                    remaining -= decimalValue;
                }
            }
            return result.toString();
        }

        public static int fromRomanNumeral(String romanNumeral) {  // из римских цифр в арабские
            int result = 0;
            var input = romanNumeral.toUpperCase();
            for (int i = 0; i < ROMAN_NUMERALS.length; i++) {
                var romanNumeralToCheck = ROMAN_NUMERALS[i];
                int decimalValue = DECIMAL_VALUES[i];
                while (input.startsWith(romanNumeralToCheck)) {
                    result += decimalValue;
                    input = input.substring(romanNumeralToCheck.length());
                }
            }
            return result;
        }
    }
}
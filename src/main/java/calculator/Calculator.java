package calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
    /*
    Решил сделать два класса:
     Первый собственно калькулятор состоящий из двух методов - main и метода математических
    операций с арабскими цифрами;
     Второй вспомогательный, вложенный - конвертирующий римские цифры в арабские и наоборот;

     Программа выполняет одну операцию, после чего завершается. Так как указаний на этот счет в требованиях не было,
     я ничего с этим делать и не стал.
     */

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String expression = reader.readLine();                                         // получаем входные данные
            String[] splitString = expression.split(" ");
            List<String> romeNumbers = new ArrayList<>();
            romeNumbers.add("I");
            romeNumbers.add("II");
            romeNumbers.add("III");
            romeNumbers.add("IV");
            romeNumbers.add("V");
            romeNumbers.add("VI");
            romeNumbers.add("VII");
            romeNumbers.add("VIII");
            romeNumbers.add("IX");
            romeNumbers.add("X");
            List<String> arabNumbers = new ArrayList<>();
            for (int i = 0; i < 11; i++) arabNumbers.add(String.valueOf(i));
            int firstRomeNumber;
            int secondRomeNumber;




             if (romeNumbers.contains(splitString[0]) && romeNumbers.contains(splitString[2])) {
                firstRomeNumber = NumeralConverter.fromRomanNumeral(splitString[0]);
                secondRomeNumber = NumeralConverter.fromRomanNumeral(splitString[2]);
                String[] romeValues = {String.valueOf(firstRomeNumber),splitString[1], String.valueOf(secondRomeNumber)};
                int arabicSumma = arabicOperation(romeValues);
                if ((arabicSumma < 1)) {
                    System.out.println("throws Exception");
                    throw new Exception();
                    // System.out.println("throw Exception // т.к. в римской системе нет отрицательных чисел");
                }
                String answer = NumeralConverter.toRomanNumeral(arabicSumma);
                System.out.println(answer);
            } else if (arabNumbers.contains(splitString[0]) && arabNumbers.contains(splitString[2]) && (splitString.length < 4)) {
                System.out.println(arabicOperation(splitString));
            } else {
                System.out.println("throws Exception");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("throws Exception");
        }
    }

    public static Integer arabicOperation(String[] string) throws Exception {   // метод математических операций
        int result = 0;
        int first = Integer.parseInt(string[0]);
        int second = Integer.parseInt(string[2]);

        switch (string[1]) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "*":
                result = first * second;
                break;
            case "/":
                result = first / second;
                break;
            default:
                System.out.println("throws Exception");
                throw new Exception();
        }
        return result;
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

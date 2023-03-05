import java.util.Scanner;

public class Main {
    static class Calc {
        public static void main(String[] args) throws Exception {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите выражение: ");
            String expression = scanner.nextLine();
            System.out.println(parse(expression));
        }
        public static String parse(String expression) throws Exception {
            int number1;
            int number2;
            String matchOperation;
            String result;
            boolean isRoman;
            String[] operands = expression.split("[+\\-*/]");
            if (operands.length != 2) throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            matchOperation = definitionOperation(expression);
            if (matchOperation == null) throw new Exception("т.к. строка не является математической операцией");
            if (Converter.Roman.isRoman(operands[0]) && Converter.Roman.isRoman(operands[1])) {
                number1 = Converter.Roman.convertToArabian(operands[0]);
                number2 = Converter.Roman.convertToArabian(operands[1]);
                isRoman = true;
            } else if (!Converter.Roman.isRoman(operands[0]) && !Converter.Roman.isRoman(operands[1])) {
                number1 = Integer.parseInt(operands[0]);
                number2 = Integer.parseInt(operands[1]);
                isRoman = false;
            } else {
                throw new Exception("т.к. используются одновременно разные системы счисления");
            }
            if (number1 > 10 || number2 > 10) {
                throw new Exception("Числа должны быть от 1 до 10");
            }
            int arabian = calculation(number1, number2, matchOperation);
            if (isRoman) {
                if (arabian <= 0) {
                    throw new Exception("т.к. в римской системе нет отрицательных чисел");
                }
                result = Converter.Roman.convertToRoman(arabian);
            } else {
                result = String.valueOf(arabian);
            }
            return result;
        }

        static String definitionOperation(String expression) {
            if (expression.contains("+")) return "+";
            else if (expression.contains("-")) return "-";
            else if (expression.contains("*")) return "*";
            else if (expression.contains("/")) return "/";
            else return null;
        }

        static int calculation(int a, int b, String matchOperation) {

            switch (matchOperation) {
                case "+":
                    return a + b;
                case "-":
                    return a - b;
                case "*":
                    return a * b;
                default:
                    return a / b;
            }
        }

    }
}


package iphone11.etc;

public class Test {
        public static void main(String[] args) {
            String expression = "300+10*0";
            String[] tokens = expression.split("(?=[\\+\\-\\*/])|(?<=[\\+\\-\\*/])");

            int result = Integer.parseInt(tokens[0]);

            for (int i = 1; i < tokens.length; i += 2) {
                String operator = tokens[i];
                int operand = Integer.parseInt(tokens[i + 1]);

                switch (operator) {
                    case "+":
                        result += operand;
                        break;
                    case "-":
                        result -= operand;
                        break;
                    case "*":
                        result *= operand;
                        break;
                    case "/":
                        result /= operand;
                        break;
                }
            }

            System.out.println("결과: " + result);
        }
    }


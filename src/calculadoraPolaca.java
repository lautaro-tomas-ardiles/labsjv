import java.util.*;

class resolvePolish {
    private final Stack<Character> operators = new Stack<>();
    private final Queue<String> output = new LinkedList<>();
    private final List<String> tokens = new ArrayList<>();

    private final static String operatorsCharList = "+-*/^√";

    private final HashMap<Character, Integer> precedence = new HashMap<>();
    //* se inicializa el hash map
    {
        precedence.put('+', 10);
        precedence.put('-', 10);

        precedence.put('*', 20);
        precedence.put('/', 20);

        precedence.put('^', 30);
        precedence.put('√', 30);
    }

    private Boolean isNumeric(String o) {
        if (o == null || o.isEmpty()) return false;

        try {
            Double.parseDouble(o);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Boolean precedenceReview(char top, char current){
        if (top == '(') return false;

        return precedence.get(top) >= precedence.get(current);
    }

    private void addNumber(String value) {
        if (!value.isEmpty()) {
            tokens.add(value);
        }
    }

    public void convertStringToTokens(String expression) {
        StringBuilder number = new StringBuilder();
        String previosNumber = null;
        String operatorsList = "()+-*/^√";

        char[] finalExpression = expression.replace(" ", "").toCharArray();

        for (char item : finalExpression){
            boolean isFloat = item == '.' && (!number.isEmpty());
            boolean isNegative = item == '-' &&
                            (previosNumber == null || (!isNumeric(previosNumber) && !previosNumber.equals(")")) );

            if (Character.isDigit(item) || isNegative || isFloat) {
                number.append(item);
            } else if (operatorsList.indexOf(item) != -1) {
                addNumber(number.toString());
                number = new StringBuilder();

                tokens.add(String.valueOf(item));
            }
            previosNumber = String.valueOf(item);
        }
    }

    public Queue<String> convertInfixToPolacaInversa() {
        for (String token : tokens) {
            if (isNumeric(token)) output.add(token);

            if (operatorsCharList.contains(token)) {
                while (!operators.isEmpty() && precedenceReview(operators.peek(), token.charAt(0))) {
                    output.add(operators.pop().toString());
                }

                operators.add(token.charAt(0));
            }

            if (token.equals("(")) operators.add('(');

            if (token.equals(")")) {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    output.add(operators.pop().toString());
                }

                if (operators.peek() == '(') operators.pop();
            }
        }

        while (!operators.isEmpty()) {
            output.add(operators.pop().toString());
        }

        return output;
    }

    public Double resolvePolacaInversa(){
        Stack<Double> doubleStack = new Stack<>();

        for (String element: output) {
            if (isNumeric(element)){
                doubleStack.push(Double.parseDouble(element));
            }

            if (operatorsCharList.contains(element)) {
                if (element.equals("√")) {
                    Double a = doubleStack.pop();
                    doubleStack.push(Math.sqrt(a));
                } else {

                    double numA = doubleStack.pop();
                    double numB = doubleStack.pop();

                    switch (element) {
                        case "+" -> doubleStack.push(numB + numA);
                        case "-" -> doubleStack.push(numB - numA);
                        case "*" -> doubleStack.push(numB * numA);
                        case "/" -> doubleStack.push(numB / numA);
                        case "^" -> doubleStack.push(Math.pow(numB, numA));
                    }
                }
            }

        }

        return doubleStack.isEmpty() ? Double.NaN : doubleStack.pop();
    }
}
/*
    if (operatorsCharList.contains(element)) {
        switch (element) {
                    case "-" -> {
                        numberA = numberA - numberB;
                        numberB = null;
                    }
                    case "+" -> {
                        numberA = numberA + numberB;
                        numberB = null;
                    }
                    case "*" -> {
                        numberA = numberA * numberB;
                        numberB = null;
                    }
                    case "/" -> {
                        numberA = numberA / numberB;
                        numberB = null;
                    }
                }
            } else if (numberA != null && numberB != null) {
                numberA = numberB;
                numberB = null;
            }

            if (isNumeric(element) && numberA == null) {
                numberA = Double.parseDouble(element);
            } else if (isNumeric(element) && numberA != null) {
                numberB = Double.parseDouble(element);
            }
 */
public class calculadoraPolaca {
    public static void main(String[] args) {
        resolvePolish expressionPolaca = new resolvePolish();

        String operacion = "(-7) * (4 + (2 ^ 3)) - 15 / (-8 + 5) + √(16) ^ (1 + 1)";

        expressionPolaca.convertStringToTokens(operacion);

        System.out.println(expressionPolaca.convertInfixToPolacaInversa());

        System.out.println(expressionPolaca.resolvePolacaInversa());

    }
}

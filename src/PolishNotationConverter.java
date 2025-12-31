import java.util.*;

class ExpressionPolaca {
    private final Stack<Character> operators = new Stack<>();
    private final Queue<String> output = new LinkedList<>();

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

    public Queue<String> convertInfixToPolacaInversa(String[] tokens) {
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
}

public class PolishNotationConverter {
    public static void main(String[] args) {
        ExpressionPolaca expressionPolaca = new ExpressionPolaca();

        String[] operacion = {"-7", "*", "(", "4", "+", "5", ")", "-", "15", "/", "(", "-8", "+", "5", ")"};

        System.out.println(expressionPolaca.convertInfixToPolacaInversa(operacion));

    }
}

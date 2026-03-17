

public class FunctionalEvaluator {
    static class EvalResult{
        final int value;
        final int nextIdx;

        public EvalResult(int value, int nextIdx) {
            this.value = value;
            this.nextIdx = nextIdx;
        }
    }

    public static int evaluatePrefix(String prefixExp){
        String cleanStr = prefixExp.replace("(", "( ").replace(")", " )").trim();
        String[] tokens = cleanStr.split("\\s+");
        return evaluationRecursive(tokens, 0).value;
    }

    private static EvalResult evaluationRecursive(String[] tokens, int idx){
        String token = tokens[idx];

        if (token.equals("(")) {
            String operator = tokens[idx + 1];
            EvalResult left = evaluationRecursive(tokens, idx + 2);
            EvalResult right = evaluationRecursive(tokens, left.nextIdx);
            int result = 0;
            switch (operator) {
                case "+":
                    result =  left.value + right.value;
                    break;
                case "-":
                    result =  left.value - right.value;
                    break;
                case "*":
                    result =  left.value * right.value;
                    break;
                case "/":
                    if (right.value == 0) {
                        throw new ArithmeticException("Division by zero!");
                    }else{
                        result = left.value / right.value;
                    }
                break;
                default:
                    throw new AssertionError();
               
            }
            return new EvalResult(result, right.nextIdx + 1);
        }else{
            return new EvalResult(Integer.parseInt(token), idx + 1);
        }
    }
}

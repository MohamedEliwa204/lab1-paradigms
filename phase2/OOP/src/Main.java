
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("hello in my calcuator!");
        System.out.println("Type 'Q' or ' 'q' to exit");
        System.out.println("enter expression:");

        while (true) { 
            System.out.println("calc> ");
            String input = scanner.nextLine().trim();
            if (input.equals("Q") || input.equals("q")) {
                System.out.println("Goodbye!");
                break;   
            }
            if (input.isEmpty()) {
                continue;
            }
            try {
                Parser parser= new Parser(input);
                ASTNode root = parser.parse();
                System.out.println("phase 2 AST Tree: \n");
                root.print("");
                System.out.println("\nResult: " + root.evaluate());
                System.out.println("\nphase 3 prefix evaluation: \n");
                String prefixForm = root.toPrefix();
                System.out.println("prefix form:\n" + prefixForm);
                System.out.println("result from prefix:\n" + FunctionalEvaluator.evaluatePrefix(prefixForm));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            }
        }
        scanner.close();
    }
}

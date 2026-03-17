public class BinOpAST extends ASTNode{
    char opertaor;
    ASTNode left;
    ASTNode right;

    public BinOpAST(char op, ASTNode left, ASTNode right){
        this.opertaor = op;
        this.left = left;
        this.right = right;
    }

    public int evaluate(){
        switch (opertaor) {
            case '+':
                return left.evaluate() + right.evaluate();
            case '-':
                return left.evaluate() - right.evaluate();
            case '*':
                return left.evaluate() * right.evaluate();
            case '/':
                if (right.evaluate() == 0) {
                    throw new ArithmeticException("Division by zero!");
                }else{
                    return left.evaluate() / right.evaluate();
                }
            default:
                throw new AssertionError();
        }
    }

    public void print(String prefix){
        System.out.println(opertaor);
        System.out.print(prefix + "|--");
        left.print(prefix + "|  ");
        System.out.print(prefix + "\\--");
        right.print(prefix + "  ");
    }

    @Override
    public String toPrefix(){
        return "(" + opertaor + " " + left.toPrefix() + " " + right.toPrefix() + ")";
    }
}

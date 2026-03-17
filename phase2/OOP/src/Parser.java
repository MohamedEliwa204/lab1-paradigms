public class Parser {
    private String[] tokens;
    private int pos = 0;
    public Parser(String input){
        this.tokens = input.trim().split("\\s+");
    }

    public ASTNode parse(){
        return parseExp();
    }

    private ASTNode parseExp(){
        ASTNode node = parseTerm();
        while(pos < tokens.length && (tokens[pos].equals("+") || tokens[pos].equals("-"))){
            char opertaor = tokens[pos].charAt(0);
            pos++;
            ASTNode right = parseTerm();
            node = new BinOpAST(opertaor, node, right);
            
        }
        return node;
    }
    //for * and /
    private ASTNode parseTerm(){
        ASTNode node = parseFactor();
        while(pos < tokens.length && (tokens[pos].equals("*") || tokens[pos].equals("/"))){
            char opertaor = tokens[pos].charAt(0);
            pos++;
            ASTNode right = parseFactor();
            node = new BinOpAST(opertaor, node, right);
            
        }
        return node;

    }

    //for raw numbers
    private ASTNode parseFactor(){
        if (pos < tokens.length) {
            int value = Integer.parseInt(tokens[pos]);
            pos++;
            return new NumberNode(value);
            
        }
        return null;
    }
}

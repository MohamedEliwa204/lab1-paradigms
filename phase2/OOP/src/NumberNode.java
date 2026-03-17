public class NumberNode extends ASTNode{
    int value;

    public NumberNode(int value){
        this.value = value;
    }

    @Override
    public int evaluate(){
        return this.value;
    }

    @Override
    public void print(String prefix){
        System.out.println(value);
    }

    @Override
    public String toPrefix(){
        return String.valueOf(value);
    }
}

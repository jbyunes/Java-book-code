package chapter.novelties.pattern;

public sealed interface Tree {
    record Operator(Tree left, char operator, Tree right) implements Tree {}
    record Constant(int value) implements Tree {}
    public static Tree of(Tree left, char operator, Tree right) {
        return new Operator(left,operator,right);
    }
    public static Tree of(int value) { return new Constant(value); }
    public default int evaluate() {
        return switch(this) {
            case Operator(Tree l, char o, Tree r) when o=='+'
                 -> l.evaluate()+r.evaluate();
            case Operator(Tree l, char o, Tree r) when o=='-'
                 -> l.evaluate()-r.evaluate();
            case Operator o
                 -> throw new RuntimeException("unknown operator "+o);
            case Constant(int v) -> v;
        };
    }
    public default String print() {
        return switch(this) {
            case Operator(Tree l, char o, Tree r)
              -> "("+l.print()+o+r.print()+")";
            case Constant(int v) -> Integer.toString(v);
        };         
    } 
    public static void main(String[] args) {
        var three = Tree.of(3);
        var four = Tree.of(4);
        var five = Tree.of(5);
        var threePlusFour = Tree.of(three, '+',four);
        var two = Tree.of(threePlusFour, '-', five);
        
        System.out.println(two.print()+"  ==>  "+ two.evaluate());    
    }
}

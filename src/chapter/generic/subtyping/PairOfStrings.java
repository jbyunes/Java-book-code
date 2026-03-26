package chapter.generic.subtyping;

public record PairOfStrings(String first, String second) implements Pair<String,String> {
    @Override
    public String getFirst() { return first; }
    @Override
    public String getSecond() { return second; }   
}

package chapter.threads.asynchronous;

public record Price(int price, String item, Provider provider) {
    @Override public String toString() {
        return "Price for "+item+" is $"+price+" on "+provider;
    }
}

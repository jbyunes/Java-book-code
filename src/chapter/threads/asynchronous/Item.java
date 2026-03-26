package chapter.threads.asynchronous;

import static java.util.Comparator.comparing;
import static chapter.threads.threads.Utils.*;
import java.util.function.Supplier;

public record Item(String source, int price) implements Comparable<Item> {
    @Override public String toString() {
        return "@"+source+" for $"+price;
    }
    public static Supplier<Item> createItemTask(String from) {
        return () -> {
            delay();
            int price = random.nextInt(10,20);
            var item = new Item(from,price);
            System.out.println("Item found: "+item);
            return item;
        };
    }
    public static Item cheapest(Item i1,Item i2) {
        return i1.compareTo(i2)<=0 ? i1 : i2;
    }
    @Override public int compareTo(Item o) {
        return comparing(Item::price).compare(this, o);
    }

}

package chapter.threads.asynchronous;

import static chapter.threads.threads.Utils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UI {
    private List<Price> prices =
            Collections.synchronizedList(new ArrayList<>());
    public void add(Price price) {
        print("Price on "+price.provider()+" added to UI");
        prices.add(price);
    }
    public synchronized void show() {
        print("+--UI---");
        prices.forEach(p -> print("| "+p.toString()));
        print("+--UI END---");
    }
}
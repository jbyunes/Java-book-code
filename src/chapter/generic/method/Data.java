package chapter.generic.method;

import java.util.Map;

public class Data<T> {
    private T t;
    public Data(T t) { this.t = t; }
    public <U> Map<U,T> makeMap(U u) { return Map.of(u,t); }
}

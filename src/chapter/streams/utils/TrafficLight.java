package chapter.streams.utils;

import java.util.function.Consumer;

public enum TrafficLight {
    GREEN(4), ORANGE(1), RED(5);
    private final int delay;
    private TrafficLight(int delay) { this.delay = delay; }
    public void expand(Consumer<? super TrafficLight> c) {
        for (int i=0; i<delay; i++) c.accept(this);
    }
    public TrafficLight next() {
        int n = (ordinal()+1)%values().length;
        return values()[n];
    }
}

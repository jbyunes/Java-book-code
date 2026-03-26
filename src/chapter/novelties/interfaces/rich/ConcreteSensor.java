package chapter.novelties.interfaces.rich;

public class ConcreteSensor implements TemperatureSensor {
    @Override
    public double celsiusValue() {
        return Math.random() * 100;
    }

}

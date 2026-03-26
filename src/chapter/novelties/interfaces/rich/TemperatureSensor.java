package chapter.novelties.interfaces.rich;

/*
public interface TemperatureSensor {
    public double celsiusValue();
    public double fahrenheitValue();
    public double kelvinValue();
}
*/
public interface TemperatureSensor {
    public double celsiusValue();
    default public double fahrenheitValue() {
        return celsiusValue() * 9.0 / 5.0 + 32.0;
    }

    default public double kelvinValue() {
        return celsiusValue() + 273.15;
    }
}



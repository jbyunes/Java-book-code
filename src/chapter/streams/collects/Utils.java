package chapter.streams.collects;

import java.util.Arrays;
import java.util.Objects;
import static java.util.stream.Collectors.*;

public class Utils {
    public static <T> String toString(T [] array) {
        Objects.requireNonNull(array);
        return Arrays.stream(array)
            .map(Object::toString)
            .collect(joining(", ","[","]"));
    }
}

package chapter.streams.intro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UnixExample {
    public static void main(String[] args) throws IOException {
        try (var lines = Files.lines(Paths.get("fichier.txt"))) {
            var n = lines
                .map(String::toLowerCase)
                .filter(line -> line.contains("bonjour"))
                .count();
            System.out.println("#lines containing 'bonjour' : " + n);
        }
    }
}

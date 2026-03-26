package chapter.streams.ops;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class TestOnClose {
    public static Stream<MyPrintStream> create(String... filenames) throws Exception {
        var builder = Stream.<MyPrintStream>builder();
        var printStreams = new ArrayList<MyPrintStream>();
        var tmpDir = new File("/tmp");
        for (var filename: filenames) {
            var file = File.createTempFile(filename, ".txt", tmpDir);
            var printStream = new MyPrintStream(file);
            builder.accept(printStream);
            printStreams.add(printStream);
        }
        var theStream = builder.build();
        theStream.onClose(() -> printStreams
            .reversed()
            .forEach(MyPrintStream::close));
        return theStream;
    }
    public static void main(String[] args) throws Exception {
        try (var ls = Files.lines(Paths.get("fichier"))) {
            ls.forEach(System.out::println);
        } catch(Exception e) {
            System.out.println("File not found");
        }
        
        try (var s = create("file1","file2")) {
            s.forEach(ps -> ps.print("Hello\n"));
        }
    }
}

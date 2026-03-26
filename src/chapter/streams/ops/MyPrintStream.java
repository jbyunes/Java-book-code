package chapter.streams.ops;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class MyPrintStream extends OutputStream implements AutoCloseable {
    private boolean closed = false;
    private String name;
    private PrintStream p;
    public MyPrintStream(File file) throws FileNotFoundException {
        this.name = file.getName();
        System.out.println("opening "+name);
        p = new PrintStream(file);
    }
    @Override
    public void close() {
        if (closed) return; // idempotent
        System.out.println("closing "+name);
        p.close();
        closed = true;
    }
    @Override
    public void write(int b) throws IOException {
        p.write(b);
    }
    public void print(String string) {
        p.print(string);
    }
}


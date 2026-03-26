package chapter.threads.virtual;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Executors;

public class EchoClientTest {
    private static final String MSG = "hello";
    public static void createClient(int id) {
        System.out.println("Client "+id+" started");
        try (var client = new Socket("localhost", 8888)) {
            var in = client.getInputStream();
            var out = client.getOutputStream();
            var printer = new java.io.PrintWriter(out);
            var reader = new BufferedReader(new InputStreamReader(in));
            printer.println(MSG+" "+id);
            printer.flush();
            String line = reader.readLine();
            System.out.println("Client "+id+" received: "+line);
        } catch (Exception e) {
            System.out.println("Can't start client");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) { 
        try (var service = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i=0; i<1_000; i++) {
                int id = i;
                service.submit(() -> createClient(id));
            }
        } catch (Exception e) {
            System.out.println("Client problem");
            e.printStackTrace();
        }
        System.out.println("Clients ended");
    }

}

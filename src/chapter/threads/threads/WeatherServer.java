package chapter.threads.threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;
import static chapter.threads.threads.Utils.*;

public class WeatherServer {
    public final static int PORT = 8888;
    // Set it to true to test with
    // BadConcurrentAccessExample
    // ConcurrentStructureExample
    // and SynchronizedAdapterExample
    // Paris and Tokyo with the same delay (testing purposes)
    private static boolean FAST = false;
    public static final String LOCAL_URL = "http://localhost:8888";
    public static final String WTTRIN_URL = "https://wttr.in";
    private final static Pattern cityPattern = Pattern.compile("/([^/?\\\\\\s]+)");
    public static void main(String []args) throws Exception {
        try (var server = new ServerSocket(PORT)) {
            while(true) {
                print("Accepting");
                var client = server.accept();
                Utils.print("Server accepting a new client");
                new Thread(() -> handle(client)).start();
            }
        }
    }
    private static void handle(Socket client) {
        try (var bis = new BufferedReader(new InputStreamReader(client.getInputStream()));
             var ps = new PrintStream(client.getOutputStream())) {
            Utils.print("Client waiting for a request");
            String req = bis.readLine();
            Utils.print("Client sent "+req);
            var m = cityPattern.matcher(req);
            String city = "";
            if (m.find()) {
                city = m.group(1);
                print(city);
            }

            String temp = city+":+"+switch(city.toLowerCase()) {
                case "paris" -> {
                    if (FAST) Utils.delay(1546);
                    else Utils.delay();
                    yield Utils.random.nextInt(10,15);
                }
                case "tokyo" -> {
                    if (FAST) Utils.delay(1546);
                    else Utils.delay();
                    yield Utils.random.nextInt(20,30);
                }
                case "rio" -> {
                    Utils.delay();
                    yield Utils.random.nextInt(30,40);
                }
                default -> "bad";
            }+"°C";
            ps.println("HTTP/1.1 200 OK");
            ps.println("Content-Type: text/plain; charset=utf-8");
            ps.println("Content-Length: "+temp.getBytes("utf-8").length);
            ps.println("Connection: close");
            ps.println();
            ps.println(temp);
            ps.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    public static String getTemperature(String city) {
//        try (var socket = new Socket("localhost",PORT);
//             var ps = new PrintStream(socket.getOutputStream());
//             var bis = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
////            Utils.print("Client sending "+city);
//            ps.println(city);
//            ps.flush();
//            return bis.readLine();
//        } catch (Exception e) {
//            return city+":"+e.getMessage();
//        }     
//    }
}
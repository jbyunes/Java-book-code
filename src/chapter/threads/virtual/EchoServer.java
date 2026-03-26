package chapter.threads.virtual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.Executors;

import chapter.threads.threads.Utils;

public class EchoServer {
    private final ServerSocket serverSocket;
    private int i=0;
    public void handleClient(Socket socket) {
        System.out.println("Handling client "+i++);
        try (socket) {
            var in = socket.getInputStream();
            var out = socket.getOutputStream();
            var reader = new BufferedReader(new InputStreamReader(in));
            var writer = new PrintWriter(out);
            String line = reader.readLine();
            if (line != null) {
                if (line.equals("/stop")) {
                    stop();
                    return;
                }
                Utils.delay(2000);
                writer.println(line.toUpperCase());
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public EchoServer(int port) throws Exception {
        serverSocket = new ServerSocket(port);
    }
    public void stop() throws IOException {
        serverSocket.close();
    }
    public void start() {
        System.out.println("Server started on port "
            +serverSocket.getLocalPort());
        try (serverSocket;
             var service = Executors.newVirtualThreadPerTaskExecutor()) {
            while (true) {
                var socket = serverSocket.accept();
                System.out.println("Client connected from "
                    +socket.getRemoteSocketAddress());
                Runnable handler = () -> handleClient(socket);
                service.submit(handler);
            }
        } catch(SocketException se) {
            System.out.println("Server socket closed");
        } catch (Throwable e) {
            System.out.println("Server problem, stopped.");
            e.printStackTrace();
        }
    }
}

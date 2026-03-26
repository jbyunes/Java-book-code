package chapter.threads.virtual;

public class EchoServerTest {
    public static void main(String[] args) {
        try {
            var server = new EchoServer(8888);
            server.start();
        } catch (Exception e) {
            System.out.println("Can't start server");
        }
        System.out.println("Main ended");
    }
}

package chapter.threads.asynchronous;

import static java.util.concurrent.CompletableFuture.*;

public class Test {
    public static void main(String[] args) {
        Throwable t = new Exception("Test Exception");
        var f = completedFuture(t);
        try { System.out.println(f.get()); }
        catch (Exception e) {
            System.out.println("erreur"); e.printStackTrace();
        }
        System.out.println("----");
        var f2 = failedFuture(t);
        try { System.out.println(f2.get()); }
        catch (Exception e) {
            System.out.println("erreur"); e.printStackTrace();
        }
    }
}

package chapter.novelties.trywithresources;

public class AutoCloseableExample {
    public static void main(String[] args) {
        try (AutoCloseable a = new MyAutoCloseable("1");
             AutoCloseable b = new MyAutoCloseable("2")) {
            System.out.println("do something with "+a+" and "+b);
            throw new Exception("Oh no...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        AutoCloseable a = new MyAutoCloseable("1");
        AutoCloseable b = new MyAutoCloseable("2");
        try (a; b) {
            System.out.println("do something");
            throw new Exception("Oh no...");
        } catch (Exception e) {
            System.out.println("catch: "+a+" "+b);
            System.out.println(e);
            for (Throwable t: e.getSuppressed()) {
                System.out.println(t);
            }
        }
    }
}

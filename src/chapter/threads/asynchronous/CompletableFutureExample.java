package chapter.threads.asynchronous;

import static java.util.concurrent.CompletableFuture.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static CompletableFuture<Image> getImage(String name) {
        if (name.contains("Dog")) {
            return failedFuture(new Exception("Wrong (getting "+name+")"));
        }
        return completedFuture(new Image(name));
    }
    
    public static CompletableFuture<Image> filterImage(Image image) {
        if (image.name().contains("Croco")) {
            return failedFuture(new Exception("Wrong (filtering "+image+")"));
        }
        return completedFuture(new Image("filtered "+image.name()));
    }

    public static CompletableFuture<Image> rotateImage(Image image) {
        return completedFuture(new Image("rotated "+image.name()));
    }
    public static void test(String name) {
        var r = getImage(name)
                .thenCompose(CompletableFutureExample::filterImage)
                .thenCompose(CompletableFutureExample::rotateImage);
        try {
            System.out.println(r.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
    }


    public static void main(String[] args) {
        test("Cat.jpeg");
        test("Dog.jpeg");
        test("Crocodile.jpeg");
    }

}

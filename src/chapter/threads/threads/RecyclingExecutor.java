package chapter.threads.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import static chapter.threads.threads.Utils.*;

public class RecyclingExecutor implements Executor {
    private BlockingQueue<Runnable> queue= new LinkedBlockingQueue<>(); 
    private boolean stop;
    public RecyclingExecutor() {
        this.stop = false;
        print("Service started");
        new Thread(() -> {
            while (!stop) {
                try {
                    print("Dequeueing a task and running it");
                    queue.take().run();
                }
                catch (InterruptedException e) {}
            }
            print("Service stopped");
        }).start();
    }
    public void stop() {
        print("Stop requested");
        execute(() -> { stop = true;}); // unblock loop...
    }
    @Override
    public void execute(Runnable r) {
        print("Enqueuing a task");
        queue.add(r);
    }
}


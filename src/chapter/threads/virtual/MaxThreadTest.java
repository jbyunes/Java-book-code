package chapter.threads.virtual;

import static chapter.threads.threads.Utils.*;


public class MaxThreadTest {
    public static void createThread(int n) {
        new Thread(() -> {
            for (int i=0; i<1000; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"#"+n).start();
    }
    public static void main(String[] args) {
        printInfos();
        int i=0;
        try {
            while (true) {
                createThread(++i);
            }
        } catch (Throwable e) {}
        delay(1000000);
    }
}

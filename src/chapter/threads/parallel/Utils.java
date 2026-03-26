package chapter.threads.parallel;

import static java.lang.System.getProperty;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Utils {
    public static long tid() {
        return Thread.currentThread().threadId();
    }
    public static void printInfos() {
        System.out.println(getProperty("os.name")
            +", "+getProperty("os.version")
            +", "+getProperty("os.arch"));
        System.out.println(System.getProperty("java.vendor")
            +", "+getProperty("java.version"));
        System.out.println("Available processing units: "
            +Runtime.getRuntime().availableProcessors());
        System.out.println("Thread pool size: "
            +ForkJoinPool.getCommonPoolParallelism());
    }
}

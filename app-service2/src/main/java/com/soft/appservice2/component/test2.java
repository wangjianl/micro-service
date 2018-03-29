package com.soft.appservice2.component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class test2 {
    private static void runTest1() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });

        executorService.shutdown();
    }

    public static void main(String []args) throws InterruptedException {
        System.out.println("1");
        runTest1();
        System.out.println("2");

        System.out.println("Done");

    }


}

package com.soft.appservice2.component;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class test1 {
    private static void runTest1() throws InterruptedException {
        //Thread.sleep(10000);
        Runnable task = ()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello "+ threadName);
        };

        Thread thread = new Thread(task);
        thread.start();
    }

    public static void main(String []args) throws InterruptedException {
        System.out.println("1");
        runTest1();
        System.out.println("2");

        System.out.println("Done");

    }


}

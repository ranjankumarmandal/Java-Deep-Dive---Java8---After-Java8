package org.example.java_concurrency.multithreading.c_multithreadingwith_executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadingwithExecutorService {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);

        Runnable t1 = () -> System.out.println("Thread 1");
        Runnable t2 = () -> System.out.println("Thread 2");

        es.submit(t1);
        es.submit(t2);

        es.shutdown();
    }
}

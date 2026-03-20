package org.example.java_concurrency.multithreading.c_multithreadingwith_executorservice;

import java.util.concurrent.*;
import java.util.*;

class OrderProcessor {


    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<String>> tasks = new ArrayList<>();

        int orderId = 101;


        try {
            List<Future<String>> results = executor.invokeAll(tasks);

            for (Future<String> future : results) {
                System.out.println(future.get());
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}


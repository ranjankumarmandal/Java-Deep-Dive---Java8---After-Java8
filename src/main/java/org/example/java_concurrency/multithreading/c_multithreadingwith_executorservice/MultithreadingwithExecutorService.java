package org.example.java_concurrency.multithreading.c_multithreadingwith_executorservice;

import java.util.concurrent.*;
import java.util.*;

class OrderProcessor {

    public static String processPayment(int orderId) throws InterruptedException {
        Thread.sleep(1000); // simulate delay
        return "Payment processed for Order " + orderId;
    }

    public static String updateInventory(int orderId) throws InterruptedException {
        Thread.sleep(800);
        return "Inventory updated for Order " + orderId;
    }

    public static String sendEmail(int orderId) throws InterruptedException {
        Thread.sleep(500);
        return "Email sent for Order " + orderId;
    }

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<String>> tasks = new ArrayList<>();

        int orderId = 101;

        tasks.add(() -> processPayment(orderId));
        tasks.add(() -> updateInventory(orderId));
        tasks.add(() -> sendEmail(orderId));

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


package org.example.java_concurrency.multithreading.c_multithreadingwith_executorservice;

import java.util.concurrent.*;
import java.util.*;

class OrderProcessorAdvanced {

    enum OrderStatus {
        CREATED, PAYMENT_FAILED, COMPLETED
    }

    static class Order {
        int orderId;
        OrderStatus status;

        Order(int orderId) {
            this.orderId = orderId;
            this.status = OrderStatus.CREATED;
        }
    }

    public static boolean processPayment(Order order) throws InterruptedException {
        Thread.sleep(1000);
        if (new Random().nextBoolean()) {
            System.out.println("Payment success for Order " + order.orderId);
            return true;
        } else {
            System.out.println("Payment failed for Order " + order.orderId);
            return false;
        }
    }

    public static void updateInventory(Order order) throws InterruptedException {
        Thread.sleep(800);
        System.out.println("Inventory updated for Order " + order.orderId);
    }

    public static void sendEmail(Order order) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Email sent for Order " + order.orderId);
    }

    public static boolean retryPayment(Order order, int maxRetries) throws InterruptedException {
        for (int i = 1; i <= maxRetries; i++) {
            System.out.println("Retrying payment (" + i + ") for Order " + order.orderId);
            if (processPayment(order)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Order> orders = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            orders.add(new Order(i));
        }

        List<Future<?>> futures = new ArrayList<>();

        for (Order order : orders) {

            Future<?> future = executor.submit(() -> {
                try {
                    boolean paymentSuccess = processPayment(order);

                    if (!paymentSuccess) {
                        paymentSuccess = retryPayment(order, 2);
                    }

                    if (!paymentSuccess) {
                        order.status = OrderStatus.PAYMENT_FAILED;
                        System.out.println("Order " + order.orderId + " failed completely");
                        return;
                    }

                    Callable<Void> inventoryTask = () -> {
                        updateInventory(order);
                        return null;
                    };


            });

            futures.add(future);
        }

        for (Future<?> f : futures) {
            try {
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        System.out.println("\nFINAL ORDER STATUS:");
        for (Order order : orders) {
            System.out.println("Order " + order.orderId + " -> " + order.status);
        }
    }
}
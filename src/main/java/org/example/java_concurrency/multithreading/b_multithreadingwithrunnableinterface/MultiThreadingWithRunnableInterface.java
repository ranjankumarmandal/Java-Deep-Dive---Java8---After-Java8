package org.example.java_concurrency.multithreading.b_multithreadingwithrunnableinterface;

class OrderProcessor implements Runnable {
    private int orderId;

    public OrderProcessor(int orderId) {
        this.orderId = orderId;
    }

    public void run() {
        System.out.println("Processing Order: " + orderId +
                " by " + Thread.currentThread().getName());

        try {
            Thread.sleep(2000); // simulate processing time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed Order: " + orderId);
    }
}

public class MultiThreadingWithRunnableInterface {
    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {
            Thread t = new Thread(new OrderProcessor(i));
            t.start();
        }
    }
}

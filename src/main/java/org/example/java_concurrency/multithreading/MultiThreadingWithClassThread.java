package org.example.java_concurrency.multithreading;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;

class SharedResource {

    private final Queue<String> buffer = new LinkedList<>();
    private final int MAX_SIZE = 5;

    private volatile boolean shutdown = false;

    // Consumer method
    public synchronized String consumeData() {
        while (buffer.isEmpty() && !shutdown) {
            try {
                log(Thread.currentThread().getName() + " waiting for data...");
                wait(3000); // timeout wait
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log(Thread.currentThread().getName() + " interrupted while waiting");
                return null;
            }
        }

        if (shutdown && buffer.isEmpty()) {
            return null;
        }

        String data = buffer.poll();
        log(Thread.currentThread().getName() + " consumed: " + data);

        notifyAll(); // notify producers
        return data;
    }

    // Producer method
    public synchronized void produceData(String data) {
        while (buffer.size() == MAX_SIZE && !shutdown) {
            try {
                log(Thread.currentThread().getName() + " waiting, buffer full...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log(Thread.currentThread().getName() + " interrupted while producing");
                return;
            }
        }

        if (shutdown) return;

        buffer.offer(data);
        log(Thread.currentThread().getName() + " produced: " + data);

        notifyAll(); // notify consumers
    }

    public synchronized void shutdown() {
        shutdown = true;
        notifyAll();
        log("Shutdown initiated. Notifying all threads...");
    }

    private void log(String message) {
        System.out.println(LocalTime.now() + " | " + message);
    }
}

public class MultiThreadingWithClassThread {

    public static void main(String[] args) throws Exception {

        SharedResource resource = new SharedResource();

        // Multiple consumers & producers
        Thread1 consumer1 = new Thread1(resource, "Consumer-1");
        Thread1 consumer2 = new Thread1(resource, "Consumer-2");

        Thread2 producer1 = new Thread2(resource, "Producer-1");
        Thread2 producer2 = new Thread2(resource, "Producer-2");

        consumer1.start();
        consumer2.start();

        Thread.sleep(1000); // ensure consumers start waiting

        producer1.start();
        producer2.start();

        // Let system run for some time
        Thread.sleep(8000);

        // Graceful shutdown
        resource.shutdown();

        consumer1.join();
        consumer2.join();
        producer1.join();
        producer2.join();

        System.out.println("Main thread finished execution");
    }
}
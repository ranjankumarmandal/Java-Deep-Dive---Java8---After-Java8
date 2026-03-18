package org.example.java_concurrency.multithreading;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;


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
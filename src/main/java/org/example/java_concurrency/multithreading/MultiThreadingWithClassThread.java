package org.example.java_concurrency.multithreading;

class SharedResource {

    private boolean dataReady = false;

    public synchronized void waitForData() {
        try {
            System.out.println(Thread.currentThread().getName() + " waiting for data...");

            while (!dataReady) {
                wait();   // releases lock and waits
            }

            System.out.println(Thread.currentThread().getName() + " received notification and continues work");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void produceData() {

        try {
            System.out.println(Thread.currentThread().getName() + " producing data...");
            Thread.sleep(2000); // simulate processing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dataReady = true;

        notify(); // notify waiting thread
        System.out.println(Thread.currentThread().getName() + " notified waiting thread");
    }
}

class Thread1 extends Thread {

    SharedResource resource;

    Thread1(SharedResource resource) {
        this.resource = resource;
    }

    public void run() {

        resource.waitForData();

        for (int i = 1; i <= 5; i++) {
            try {
                System.out.println(getName() + " processing step " + i);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Thread2 extends Thread {

    SharedResource resource;

    Thread2(SharedResource resource) {
        this.resource = resource;
    }

    public void run() {

        resource.produceData();

        for (int i = 1; i <= 5; i++) {
            try {
                System.out.println(getName() + " working step " + i);
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class MultiThreadingWithClassThread {

    public static void main(String[] args) throws Exception {

        SharedResource resource = new SharedResource();

        Thread1 t1 = new Thread1(resource);
        Thread2 t2 = new Thread2(resource);

        t1.setName("Thread1");
        t2.setName("Thread2");

        t1.start();

        Thread.sleep(1000); // ensures Thread1 starts waiting - sleep

        t2.start();

        t1.join();
        t2.join();

        System.out.println("Main thread finished execution");
    }
}

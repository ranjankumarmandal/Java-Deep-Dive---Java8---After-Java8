package org.example.java_concurrency.multithreading;

class Thread1 extends Thread {

    public void run() {
        System.out.println("Thread1");
    }
}

class Thread2 extends Thread {
    public void run() {
        System.out.println("Thread2");
    }
}

public class MultiThreadingWithClassThread {
}

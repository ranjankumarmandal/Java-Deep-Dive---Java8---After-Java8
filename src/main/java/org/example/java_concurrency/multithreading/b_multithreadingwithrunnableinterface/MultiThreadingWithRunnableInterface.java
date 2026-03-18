package org.example.java_concurrency.multithreading.b_multithreadingwithrunnableinterface;

class Thread1 implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread1");
    }
}

class Thread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread2");
    }
}

public class MultiThreadingWithRunnableInterface {
}

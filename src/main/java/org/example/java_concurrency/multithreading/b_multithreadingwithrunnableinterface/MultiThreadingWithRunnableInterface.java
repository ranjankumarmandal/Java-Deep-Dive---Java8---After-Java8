package org.example.java_concurrency.multithreading.b_multithreadingwithrunnableinterface;

class MyTask implements Runnable {
    public void run() {
        System.out.println("Thread is running");
    }
}

public class MultiThreadingWithRunnableInterface {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread t1 = new Thread(task);
        t1.start(); // starts a new thread
    }
}

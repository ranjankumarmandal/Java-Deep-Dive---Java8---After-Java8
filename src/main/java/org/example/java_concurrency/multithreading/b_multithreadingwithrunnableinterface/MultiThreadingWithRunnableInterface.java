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
    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();

        t1.run();
        t2.run();
    }
}

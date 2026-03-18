package org.example.java_concurrency.multithreading.b_multithreadingwithrunnableinterface;

class BankAccount {
    private int balance = 1000;

    public synchronized void withdraw(int amount, String user) {
        System.out.println(user + " is trying to withdraw " + amount);

        if (balance >= amount) {
            System.out.println(user + " proceeding with withdrawal...");

            balance -= amount;
            System.out.println(user + " completed withdrawal. Remaining balance: " + balance);
        } else {
            System.out.println(user + " insufficient balance!");
        }
    }
}

public class MultiThreadingWithRunnableInterface {
    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        Thread user1 = new Thread(new WithdrawTask(account, "User-1", 700));
        Thread user2 = new Thread(new WithdrawTask(account, "User-2", 700));

        user1.start();
        user2.start();
    }
}

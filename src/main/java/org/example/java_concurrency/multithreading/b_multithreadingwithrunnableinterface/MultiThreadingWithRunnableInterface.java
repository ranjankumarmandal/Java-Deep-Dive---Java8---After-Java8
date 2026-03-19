package org.example.java_concurrency.multithreading.b_multithreadingwithrunnableinterface;

class BankAccount {
    private int balance = 1000;

    public synchronized void withdraw(int amount, String user) {
        System.out.println(user + " is trying to withdraw " + amount);

        if (balance >= amount) {
            System.out.println(user + " proceeding with withdrawal...");
            try {
                Thread.sleep(1000); // simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            balance -= amount;
            System.out.println(user + " completed withdrawal. Remaining balance: " + balance);
        } else {
            System.out.println(user + " insufficient balance!");
        }
    }
}

// Runnable task
class WithdrawTask implements Runnable {
    private BankAccount account;
    private String user;
    private int amount;

    public WithdrawTask(BankAccount account, String user, int amount) {
        this.account = account;
        this.user = user;
        this.amount = amount;
    }

    public void run() {
        account.withdraw(amount, user);
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

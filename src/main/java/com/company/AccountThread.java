package com.company;

public class AccountThread implements Runnable{

    private Account account;

    public AccountThread(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        synchronized (account){
            account.deposit(500);
            account.withDraw(500);
            System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
        }
    }

    public static void main(String[] args) {
        Account account = new Account("Zhang", 10000);
        AccountThread accountThread = new AccountThread(account);
        final int THREAD_NUM = 5;
        Thread[] threads = new Thread[THREAD_NUM];
        for (Thread thread: threads) {
            thread = new Thread(accountThread, "Thread" + 1);
            thread.start();
        }
    }
}

class Account{
    String name;
    float amount;

    public Account(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }

    public void deposit(float amt){
        amount += amt;
        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void withDraw(float amt){
        amount -= amt;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public float getBalance(){
        return amount;
    }
}

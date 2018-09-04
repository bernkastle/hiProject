package com.company;

public class SyncDemo {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new SyncThread(), "A");
        Thread thread2 = new Thread(new SyncThread(), "B");
        thread1.start();
        thread2.start();
    }
}

class SyncThread implements Runnable{
    private static Integer count;

    public SyncThread() {
        count = 0;
    }

    public void countAdd(){
        synchronized (this){
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printCount(){
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + "count:" + count);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        if (threadName.equals("A")){
            countAdd();
        }else if (threadName.equals("B")){
            printCount();
        }
    }



}
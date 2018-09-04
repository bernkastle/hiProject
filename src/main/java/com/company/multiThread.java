package com.company;


public class multiThread {
            
    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        ThreadWait threadWait1 = new ThreadWait("A", c, a);
        ThreadWait threadWait2 = new ThreadWait("B", a, b);
        ThreadWait threadWait3 = new ThreadWait("C", b, c);

        new Thread(threadWait1).start();
        Thread.sleep(100);
        new Thread(threadWait2).start();
        Thread.sleep(100);
        new Thread(threadWait3).start();
        Thread.sleep(100);
    }
}

class ThreadExtend extends Thread{
    private String name;

    public ThreadExtend(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i=0; i<5; i++){
            System.out.println(name + " running: " + i);
            try {
                sleep((long) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadRunnable implements Runnable{
    private String name;

    public ThreadRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " Running: " + i);
            try {
                Thread.sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadYield implements Runnable{

    private String name;

    public ThreadYield(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 50; i++) {
            System.out.println("" + Thread.currentThread().getName() + "-----" + i);
            if (i == 30){
                Thread.yield();
            }
        }
    }
}


//class ThreadYield extends Thread {
//    public ThreadYield(String name) {
//        super(name);
//    }
//
//    @Override
//    public void run() {
//        for (int i = 1; i <= 50; i++) {
//            System.out.println("" + this.getName() + "-----" + i);
//            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
//            if (i == 30) {
//                this.yield();
//            }
//        }
//
//    }
//}

class ThreadInterrupt extends Thread{
    public ThreadInterrupt(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            int i =0;
            while (!interrupted()){
                Thread.sleep(100);
                i++;
                System.out.println(Thread.currentThread().getName()+" ("+this.getState()+") loop " + i);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() +" ("+this.getState()+") catch InterruptedException.");
        }
    }
}

class ThreadWait implements Runnable{
    private String name;
    private Object prev, self;

    public ThreadWait(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count>0){
            synchronized (prev){
                synchronized (self){
                    System.out.print(name);
                    count--;
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
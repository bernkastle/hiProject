package com.company;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockTest test = new LockTest();
        MyThread thread1 = new MyThread(test);
        MyThread thread2 = new MyThread(test);

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

//    public void insert(Thread thread){
//        if (lock.tryLock()){
//            try {
//                System.out.println(thread.getName()+"得到了锁");
//                for(int i=0;i<5;i++) {
//                    arrayList.add(i);
//                }
//                Thread.sleep(100);
//            } catch (Exception e) {
//                // TODO: handle exception
//            }finally {
//                System.out.println(thread.getName()+"释放了锁");
//                lock.unlock();
//            }
//        }else {
//            System.out.println(thread.getName()+"获取锁失败");
//        }
//    }

    public void insert(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            System.out.println(thread.getName() + "得到了锁");
            long startTime = System.currentTimeMillis();
            while (true){
                if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE){
                    break;
                }
            }
        }finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }
    }
}

class MyThread extends Thread{
    private LockTest test = null;
    public MyThread(LockTest test){
        this.test = test;
    }

    @Override
    public void run() {
        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断");
        }
    }
}

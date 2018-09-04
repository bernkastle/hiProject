package com.company;

import java.util.concurrent.*;

public class CallableTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();

        try{
            while (!executor.awaitTermination(1, TimeUnit.SECONDS)){
                System.out.println("主线程正在执行");
            };
            System.out.println("task运行结果是：" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("主线程正在执行任务");
//
//        try {
//            System.out.println("task运行结果" + result.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("所有任务执行完毕");
    }

}


class Task implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}
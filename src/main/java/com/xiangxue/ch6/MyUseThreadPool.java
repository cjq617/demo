package com.xiangxue.ch6;

import com.xiangxue.tools.SleepTools;

import java.util.Random;
import java.util.concurrent.*;

public class MyUseThreadPool {

    private static class Worker implements Runnable {
        private String taskName;
        private Random r = new Random();

        public Worker(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " process task " + taskName);
            SleepTools.ms(r.nextInt(100) * 5);
        }
    }

    private static class CallWorker implements Callable {
        private String taskName;
        private Random r = new Random();

        public CallWorker(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public Object call() throws Exception {
            String name = Thread.currentThread().getName();
            System.out.println(name + " process task " + taskName);
            return Thread.currentThread().getName()+":"+ r.nextInt(100) * 5;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //ThreadPoolExecutor pool = new ThreadPoolExecutor(2,4,3,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10),new ThreadPoolExecutor.AbortPolicy());
        ExecutorService pool = Executors.newCachedThreadPool();
        for(int i=0;i<6;i++) {
            Worker worker = new Worker("worker");
            pool.execute(worker);
        }

        for(int i=0;i<6;i++) {
            CallWorker callWorker = new CallWorker("callWorker");
            Future<String> rs = pool.submit(callWorker);
            System.out.println(rs.get());
        }
        pool.shutdown();
    }
}

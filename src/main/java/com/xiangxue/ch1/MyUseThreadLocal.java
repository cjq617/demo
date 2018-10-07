package com.xiangxue.ch1;

public class MyUseThreadLocal {

    static ThreadLocal<Integer> a = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    public void startThreadArray() {
        Thread[] threads = new Thread[3];
        for(int i=0;i<threads.length;i++) {
            threads[i] = new Thread(new TestThread(i));
        }

        for(int i=0;i<threads.length;i++) {
            threads[i].start();
        }
    }

    private static class TestThread implements Runnable{
        private int id;

        public TestThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + "start:");
            Integer s = a.get();
            s = s + id;
            a.set(s);
            System.out.println(name + "=" + a.get());
        }
    }

    public static void main(String[] args) {
        MyUseThreadLocal useThreadLocal = new MyUseThreadLocal();
        useThreadLocal.startThreadArray();
    }
}

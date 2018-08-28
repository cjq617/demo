package com.cjq.thread;

public class NormalDeadLock {

    private static Object first = new Object();
    private static Object second = new Object();

    private static void firstSecond() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (first) {
            System.out.println(threadName + " get first");
            Thread.sleep(100);
            synchronized (second) {
                System.out.println(threadName + " get second");
            }
        }
    }

    private static void secondFirst() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (first) {
            System.out.println(threadName + " get first");
            Thread.sleep(100);
            synchronized (second) {
                System.out.println(threadName + " get second");
            }
        }
        /*synchronized (second) {
            System.out.println(threadName + " get second");
            Thread.sleep(100);
            synchronized (first) {
                System.out.println(threadName + " get first");
            }
        }*/
    }

    private static class StubThread extends Thread {
        private String name;

        public StubThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);
            try {
                secondFirst();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("mainThread");
        StubThread stubThread = new StubThread("stubThread");
        stubThread.start();
        firstSecond();
    }
}

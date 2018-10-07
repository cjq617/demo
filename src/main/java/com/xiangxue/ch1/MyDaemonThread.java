package com.xiangxue.ch1;

import com.xiangxue.tools.SleepTools;

public class MyDaemonThread {

    private static class UserThread extends Thread {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            try {
                while (!isInterrupted()) {
                    System.out.println("I am extend Thread:" + name);
                }
                System.out.println("the interrupted flag is ：" + isInterrupted());
            }finally {
                System.out.println("finally......");
            }
        }
    }

    public static void main(String[] args) {
        UserThread userThread = new UserThread();
        userThread.setDaemon(true);
        userThread.start();
        UserThread userThread2 = new UserThread();
        userThread2.start();
        SleepTools.second(1);
        userThread.interrupt();
    }
}

package com.xiangxue.ch1.vola;

import com.xiangxue.tools.SleepTools;

public class MyVolatileUnSafe {

    private static class VolatileVar implements Runnable {
        private volatile int a = 0;
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            a = a + 1;
            System.out.println(name + ":=" + a);
            SleepTools.ms(100);
            a = a + 1;
            System.out.println(name + ":=" + a);
        }
    }

    public static void main(String[] args) {
        VolatileVar v = new VolatileVar();

        Thread t1 = new Thread(v);
        Thread t2 = new Thread(v);
        Thread t3 = new Thread(v);
        Thread t4 = new Thread(v);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

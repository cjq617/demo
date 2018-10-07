package com.xiangxue.ch1.syn;

import com.xiangxue.tools.SleepTools;

public class MySynClzAndInst {

    private static class SynClass extends Thread {
        @Override
        public void run() {
            System.out.println("Test class is running...");
            synClass();
        }
    }

    private static class SynInstance implements Runnable {

        private MySynClzAndInst mySynClzAndInst;

        public SynInstance(MySynClzAndInst mySynClzAndInst) {
            this.mySynClzAndInst = mySynClzAndInst;
        }
        @Override
        public void run() {
            System.out.println("Test instance is running..." + mySynClzAndInst);
            mySynClzAndInst.synInstance();
        }
    }

    private static class Syn2Instance implements Runnable {

        private MySynClzAndInst mySynClzAndInst;

        public Syn2Instance(MySynClzAndInst mySynClzAndInst) {
            this.mySynClzAndInst = mySynClzAndInst;
        }
        @Override
        public void run() {
            System.out.println("Test instance2 is running..." + mySynClzAndInst);
            mySynClzAndInst.syn2Instance();
        }
    }

    public static synchronized void synClass() {
        SleepTools.second(3);
        System.out.println("synclass going...");
        SleepTools.second(3);
        System.out.println("sunclass end...");
    }

    public synchronized void synInstance() {
        SleepTools.second(3);
        System.out.println("synInstance going..." + this);
        SleepTools.second(3);
        System.out.println("synInstance end..." + this);
    }

    public synchronized void syn2Instance() {
        SleepTools.second(3);
        System.out.println("syn2Instance going..." + this);
        SleepTools.second(3);
        System.out.println("syn2Instance end..." + this);
    }

    public static void main(String[] args) {
        MySynClzAndInst mySynClzAndInst = new MySynClzAndInst();
        SynInstance synInstance = new SynInstance(mySynClzAndInst);

        MySynClzAndInst mySyn2ClzAndInst = new MySynClzAndInst();
        Syn2Instance syn2Instance = new Syn2Instance(mySynClzAndInst);

        Thread thread = new Thread(synInstance);
        Thread thread2 = new Thread(syn2Instance);
        thread.start();
        //thread2.start();

        SynClass synClass = new SynClass();
        synClass.start();

        SleepTools.second(1);
    }
}

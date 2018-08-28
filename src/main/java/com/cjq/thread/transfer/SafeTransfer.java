package com.cjq.thread.transfer;

public class SafeTransfer implements ITransfer {

    private static Object tieLock = new Object();//加时赛锁

    @Override
    public void transfer(UserAccount from, UserAccount to, int amount) throws InterruptedException {
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        String threadName = Thread.currentThread().getName();
        if(fromHash < toHash) {
            synchronized (from) {
                System.out.println(threadName + " get" + from.getName());
                Thread.sleep(100);
                synchronized (to) {
                    System.out.println(threadName + " get" + to.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                    System.out.println(from);
                    System.out.println(to);
                }
            }
        }else if(fromHash > toHash) {
            synchronized (to) {
                System.out.println(threadName + " get" + to.getName());
                Thread.sleep(100);
                synchronized (from) {
                    System.out.println(threadName + " get" + from.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                    System.out.println(from);
                    System.out.println(to);
                }
            }
        }else {
            synchronized (tieLock) {
                synchronized (from) {
                    System.out.println(threadName + " get" + from.getName());
                    Thread.sleep(100);
                    synchronized (to) {
                        System.out.println(threadName + " get" + to.getName());
                        from.flyMoney(amount);
                        to.addMoney(amount);
                    }
                }
            }
        }
    }
}

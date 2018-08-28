package com.cjq.thread.transfer;

public class NormalTransfer implements ITransfer {
    @Override
    public void transfer(UserAccount from, UserAccount to, int amount) throws InterruptedException {
        String threadName = Thread.currentThread().getName();
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

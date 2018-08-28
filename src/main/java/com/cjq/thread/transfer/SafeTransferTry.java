package com.cjq.thread.transfer;

import java.util.Random;

public class SafeTransferTry implements ITransfer {

    @Override
    public void transfer(UserAccount from, UserAccount to, int amount) throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        Random random = new Random();
        while (true) {
            if(from.getLock().tryLock()) {
                System.out.println(threadName + " get" + from.getName());
                try{
                    if(to.getLock().tryLock()) {
                        System.out.println(threadName + " get" + to.getName());
                        try {
                            from.flyMoney(amount);
                            to.addMoney(amount);
                            System.out.println(from);
                            System.out.println(to);
                            break;
                        }finally {
                            to.getLock().unlock();
                        }
                    }
                }finally {
                    from.getLock().unlock();
                }
            }
            //Thread.sleep(random.nextInt(10));
        }
    }
}

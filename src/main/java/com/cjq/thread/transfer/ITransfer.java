package com.cjq.thread.transfer;

public interface ITransfer {

    void transfer(UserAccount from, UserAccount to, int amount) throws InterruptedException;
}

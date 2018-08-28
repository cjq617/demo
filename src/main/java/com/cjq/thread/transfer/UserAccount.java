package com.cjq.thread.transfer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserAccount {

    private String name;
    private int money;

    private Lock lock = new ReentrantLock();

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public UserAccount(String name, int money) {
        this.name = name;
        this.money = money;
    }

    @Override
    public String toString() {
        return "name:" + name + ",money:" + money;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void flyMoney(int amount) {
        money -= amount;
    }
}

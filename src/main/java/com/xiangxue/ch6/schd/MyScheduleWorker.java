package com.xiangxue.ch6.schd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyScheduleWorker implements Runnable {

    public static final int NORMAL = 0;
    public static final int HASEXCEPTION = -1;
    public static final int EXCEPTIONPROCESSOR = 1;
    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public int taskType;

    public MyScheduleWorker(int taskType) {
        this.taskType = taskType;
    }

    @Override
    public void run() {
        if(taskType == NORMAL) {
            System.out.println("Normal " + format.format(new Date()));
        }else if(taskType == HASEXCEPTION) {
            System.out.println("HASEXCEPTION " + format.format(new Date()));
            throw new RuntimeException("exception happen");
        }else {
            try {
                System.out.println("EXCEPTIONPROCESSOR " + format.format(new Date()));
                throw new RuntimeException("exception happen");
            }catch (Exception e) {
                System.out.println("catch exception");
            }
        }
    }

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(new MyScheduleWorker(NORMAL), 1000, 3000, TimeUnit.MILLISECONDS);

        executor.scheduleAtFixedRate(new MyScheduleWorker(EXCEPTIONPROCESSOR), 1000, 3000, TimeUnit.MILLISECONDS);
    }
}

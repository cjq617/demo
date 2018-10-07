package com.xiangxue.ch2.forkjoin.sum;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MySumArray {

    private static class MySumTask extends RecursiveTask<Integer> {

        private static final int HOLD = MakeArray.ARRAY_LENGTH/10;
        private int[] src;
        private int fromIndex;
        private int toIndex;

        public MySumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {

            if(toIndex - fromIndex < HOLD) {
                int count =0;
                for(int i=fromIndex;i<toIndex;i++) {
                    count += src[i];
                }
                return count;
            }else {
                int mid = (fromIndex + toIndex)/2;
                MySumTask left = new MySumTask(src, fromIndex, mid);
                MySumTask right = new MySumTask(src, mid + 1, toIndex);
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int src[] = MakeArray.makeArray();
        MySumTask sumTask = new MySumTask(src, 0, src.length - 1);

        long start = System.currentTimeMillis();

        pool.invoke(sumTask);

        System.out.println("Task in running...");
        System.out.println("The count is " + sumTask.join() + ";spend " + (System.currentTimeMillis() - start) + "ms");

    }
}

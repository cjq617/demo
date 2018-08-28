package com.cjq.thread.transfer;

public class PayCompany {

    private static class TransferThread extends Thread {
        private String name;
        private UserAccount from;
        private UserAccount to;
        private int amount;
        private ITransfer transfer;

        private TransferThread(String name, UserAccount from, UserAccount to, int amount, ITransfer transfer) {
            this.name = name;
            this.from = from;
            this.to = to;
            this.amount = amount;
            this.transfer = transfer;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);
            try {
                transfer.transfer(from, to, amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PayCompany payCompany = new PayCompany();
        UserAccount zhangsan = new UserAccount("zhangsan", 2000);
        UserAccount lisi = new UserAccount("lisi", 2000);
        //ITransfer transfer = new NormalTransfer();
        //ITransfer transfer = new SafeTransfer();
        ITransfer transfer = new SafeTransferTry();
        TransferThread zhangtoli = new TransferThread("zhangtoli", zhangsan, lisi, 200, transfer);
        TransferThread litozhang = new TransferThread("litozhang", lisi, zhangsan, 400, transfer);
        zhangtoli.start();
        litozhang.start();
    }
}

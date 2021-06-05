package pt.ual.pp.lab3;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DepositThread implements Runnable{
    private final double goal = 50.0;
    private final double MIN_AMOUNT = 1.0;
    private final double MAX_AMOUNT = 10.0;
    private final int minWaitTime = 1;
    private final int maxWaitTime = 3;
    private BankAccount bankAccount;
    private Random random;
    private double depositAmount = 0.0;

    public DepositThread(BankAccount bankAccount, Random random){
        this.bankAccount = bankAccount;
        this.random = random;
    }

    public void run(){
        while(depositAmount < goal){
            double amount = Math.min(MAX_AMOUNT, (random.nextDouble() * MAX_AMOUNT) + MIN_AMOUNT);
            int waitTime = Math.min(maxWaitTime, random.nextInt(maxWaitTime) + minWaitTime);
            bankAccount.deposit(amount);
            depositAmount += amount;
            System.out.println(Thread.currentThread().getId() + " [D] " + " Balance: "+ bankAccount.getBalance() + " " + " AmountDeposited: " + amount);

            if(depositAmount < goal){
                try {
                    TimeUnit.SECONDS.sleep(waitTime);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
    }

}


package pt.ual.pp.lab3;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WithdrawThread implements Runnable{
    private final int goal = 4;
    private final double MIN_AMOUNT = 5.0;
    private final double MAX_AMOUNT = 20.0;
    private final int minWaitTime = 2;
    private final int maxWaitTime = 5;
    private BankAccount bankAccount;
    private Random random;
    private int numWithdraws = 0;

    public WithdrawThread(BankAccount bankAccount, Random random){
        this.bankAccount = bankAccount;
        this.random = random;
    }

    public void run(){
        while(numWithdraws < goal){
            double amount = Math.min(MAX_AMOUNT, (random.nextDouble()*MAX_AMOUNT) + MIN_AMOUNT);
            var waitTime = Math.min(maxWaitTime, random.nextInt(maxWaitTime) + minWaitTime);

            bankAccount.withdraw(amount);
            numWithdraws++;
            System.out.println(Thread.currentThread().getId() + " [W] " + " Balance: " + bankAccount.getBalance() + " " + " NumWithdraws: " + numWithdraws);

            if(numWithdraws < goal){
                try{
                    TimeUnit.SECONDS.sleep(waitTime);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
    }

}

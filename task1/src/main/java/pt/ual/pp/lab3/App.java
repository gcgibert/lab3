package pt.ual.pp.lab3;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App
{
    public static void main( String[] args ) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        Random random = new Random();

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0; i<3; i++) {
            executorService.submit(new WithdrawThread(bankAccount, random));
            executorService.submit(new DepositThread(bankAccount, random));
        }
        executorService.awaitTermination(1, TimeUnit.MINUTES);


    }
}

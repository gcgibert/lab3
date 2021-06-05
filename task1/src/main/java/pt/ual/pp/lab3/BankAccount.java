package pt.ual.pp.lab3;

public class BankAccount {
    private double balance;

    public BankAccount(){
        this.balance = 0.0;
    }

    public double getBalance(){
        return this.balance;
    }

    public synchronized void deposit(double amount){
        this.balance += amount;
        notifyAll();
    }

    public synchronized void withdraw(double amount){
        while(amount > this.balance ){
            try{
                wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        this.balance -= amount;
        notifyAll();
    }

}

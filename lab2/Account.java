import java.util.Random;
import java.util.concurrent.*; 
import java.util.concurrent.locks.*;
import java.util.*;
public class Account{
    /*
        The Account Class defines the structure of an user account, it hosts information such as account number, balance 
        It also contains thread safe methods deposit() and withdraw() implemented with locks
    */
    public String accountNumberFrom;
    public int accountBalance = 0;
    public ReadWriteLock readWriteLock ;
    public static int accountBalanceRange = 2000;

    public Account(int branchFrom){
        // Constructor function which generates a random account number and a random account balance
        this.accountNumberFrom = (char)(branchFrom+'0') + generateRandomAccountNumber(9);
        this.accountBalance = (int)(Math.random() * accountBalanceRange);
        readWriteLock = new ReentrantReadWriteLock();
    }
    public  void deposit(int amount){
        // Thread safe deposit function which uses locks to deposit to bank account
        readWriteLock.readLock().lock();
        int balance = this.accountBalance;
        readWriteLock.readLock().unlock();
        readWriteLock.writeLock().lock();
        this.accountBalance = balance + amount;
        readWriteLock.writeLock().unlock();
        
    }
    public  void withdraw(int amount){
        // Thread safe withdraw function which uses locks to withdraw from bank account
        readWriteLock.readLock().lock();
        int balance = this.accountBalance;
        readWriteLock.readLock().unlock();
        if(amount > balance){
           return ;
        }
        readWriteLock.writeLock().lock();
        this.accountBalance = balance - amount;
        
        readWriteLock.writeLock().unlock();
    }
    private String generateRandomAccountNumber(int size) {
        // Generates a random account number string of length <size>
        String CHARS = "0123456789";
        StringBuilder randomAccountNumber = new StringBuilder();
        Random rnd = new Random();
        for(int i=0; i<size;i++) { // length of the random string.
            int index = (int) (rnd.nextFloat() * CHARS.length());
            randomAccountNumber.append(CHARS.charAt(index));
        }
        String numberString = randomAccountNumber.toString();
        return numberString;

    }
   
}
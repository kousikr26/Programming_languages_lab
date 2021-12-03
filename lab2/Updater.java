import java.util.Random;
import java.util.concurrent.*; 
import java.util.concurrent.locks.*;
import java.util.*;

public class Updater implements Runnable{
    /*
        The Updater class is what actually performs the operations in a thread safe manner. 
        It implements a single method run() which in this case is programmed to perform operationsPerThread number of random operations from 
            1. Deposit
            2. Withdraw
            3. Transfer money
            4. Add account
            5. Delete account 
            6. Transfer account
    */
    public String accountNumberFrom,accountNumberTo; 
    public int branchFrom, branchTo, amount;
    public static Random rand = new Random();
    public static int numOperations = Bank.operationsPerThread;
    public static int amountRange = 10; // The range i.e [0,amountRange] to pick from randomly to deposit/withdraw/transfer money
    public static int numBranches = Bank.numBranches; // The number of branches in the bank
    public Updater(int branchFrom){
        this.branchFrom = branchFrom;
    }
    
    public void run(){
        
        Account randomAccounts [] = new Account[numOperations]; // Array which will hold random accounts to perform operations on 
        
        Bank.locks[branchFrom].readLock().lock();
            // Get numOperations random Accounts and store to array to minimise the number of locks
            int branchSize = Bank.branches.get(branchFrom).size()-1;
            for(int i=0;i<numOperations;i++){
                int randIndex = rand.nextInt(branchSize);
                Account accountFrom=Bank.branches.get(branchFrom).get(randIndex); // Expensive operation as get() is O(n) and total time taken is N*O(n) where n is numOperations
                randomAccounts[i] = accountFrom;
            }
        Bank.locks[branchFrom].readLock().unlock();

        for(int i=0;i<numOperations;i++){
           
                
            Account accountFrom = randomAccounts[i]; // Random Account to deposit / withdraw / transfer from/ delete / transfer account from
            accountNumberFrom = accountFrom.accountNumberFrom; // Corresponding account number

            Account accountTo = accountFrom; // Will be used in case of transfer
             
            

            amount = rand.nextInt(amountRange); // Generate a random amount to deposit/withdraw
            
            float choice = rand.nextFloat();
                
            
            if(choice < 0.33){
                // 1. Deposit
                accountFrom.deposit(amount);
            }
            else if(choice<0.66){
                // 2. Withdraw
                accountFrom.withdraw(amount);
            }
            else if(choice <0.99){
                // 3. Transfer money
                branchTo = rand.nextInt(numBranches); // Choose random branch to transfer to 

                Bank.locks[branchTo].readLock().lock(); // Perform a read lock on the linked list to avoid linked list modification
                    int y = rand.nextInt(Bank.branches.get(branchTo).size()); // Choose a random account from the chosen branch to transfer to 
                    accountTo = Bank.branches.get(branchTo).get(y);
                    accountNumberTo = accountTo.accountNumberFrom;
                Bank.locks[branchTo].readLock().unlock();

                

                accountFrom.withdraw(amount); // Withdraw from accountFrom
                accountTo.deposit(amount);  // Deposit to accountTo
            }
            else if (choice < 0.993){
                // 4. Add new account
                Account newAccount = new Account(branchFrom);

                Bank.locks[branchFrom].writeLock().lock(); // Write lock while adding new account to the linked list
                    Bank.branches.get(branchFrom).add(newAccount); // Add a new account to branch
                Bank.locks[branchFrom].writeLock().unlock();
            }
            else if(choice < 0.996){
                // 5. Delete account
                Bank.locks[branchFrom].writeLock().lock(); // Write lock while deleting an account from the linked list
                    int randIndex = rand.nextInt(Bank.branches.get(branchFrom).size()-1); // Choose a random index to delete from 
                    Bank.branches.get(branchFrom).remove(randIndex); // Delete that index
                Bank.locks[branchFrom].writeLock().unlock();
            }
            else{
                // 6. Transfer account

                // Delete account from old branch
                Bank.locks[branchFrom].writeLock().lock();
                int randIndex = rand.nextInt(Bank.branches.get(branchFrom).size()-1);
                accountTo = Bank.branches.get(branchFrom).remove(randIndex);
                Bank.locks[branchFrom].writeLock().unlock();
                
                Account newAccount = new Account(branchTo);

                // Add account to new branch
                Bank.locks[branchFrom].writeLock().lock();
                    Bank.branches.get(branchFrom).add(newAccount);
                    newAccount.accountBalance = accountTo.accountBalance;
                Bank.locks[branchFrom].writeLock().unlock();
            }   
        }
    }
}

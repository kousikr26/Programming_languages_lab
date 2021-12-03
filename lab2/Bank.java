import java.util.Random;
import java.util.concurrent.*; 
import java.util.concurrent.locks.*;
import java.util.*;




public class Bank{
    /* 
        The Bank Class has all the Account information of users and updaters required to perform operations
        It schedules operations to the Updater Class
    */
    public static HashMap<Integer,BranchList> branches = new HashMap<Integer,BranchList>(); //HashMap of linkedlists containing branches
    public static ExecutorService [] allExecutors = new ExecutorService[10]; // Initialising Executor for each branch
    public static ReadWriteLock[] locks = new  ReentrantReadWriteLock[10]; // Initialising a read-write lock for each branch linkedlist
    public static final int numBranches = 10; 
    public static final int updatersPerBranch = 10;
    public static final int initialAccounts = 10000;
    public static int operationsPerThread = 100000;
    
    public static void main(String []args){
        long startTime = System.nanoTime();
        
        
        if(args.length == 0 ){
            System.out.println("Arguments not specified \n Correct usage : java Bank <operations per updater>");
            System.out.println("Program is now defaulting to 10^5 operations per updater");
        }
        else operationsPerThread =Integer.parseInt(args[0]); 
        System.out.println("Executing Program...");

        for(int i =0;i<numBranches;i++){ 
            BranchList accounts=  new BranchList();  // Create a LinkedList for each branch 
            branches.put(i,accounts); // Add it to hash map 
            locks[i] = new ReentrantReadWriteLock(); // Create a read-write lock for each branch
            allExecutors[i] = Executors.newFixedThreadPool(updatersPerBranch); // Creating 10 threads/updaters in a thread pool for each branch 
        }
        System.out.println("Created Threads");
        for(int i=0;i<numBranches;i++){
            for(int j=0;j<initialAccounts;j++){
                
                Account newAccount = new Account(i);
                Bank.branches.get(i).add(newAccount); // Initialising accounts and adding to linked list
            } 
        }
        System.out.println("Initialised 10^4 accounts per branch");
        System.out.println("Running "+ operationsPerThread+" operations per updater....");
        for(int i=0;i<numBranches;i++){
            for(int j=0;j<updatersPerBranch;j++){
                Updater newUpdater = new Updater(i);
                allExecutors[i].execute(newUpdater); //Initialise the updater
            }
        }
        
        for(int i=0;i<numBranches;i++){
            // Wait and Shutdown threads
            allExecutors[i].shutdown();
            try {
                allExecutors[i].awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                // Force shutdown in case of exception
                System.out.println("Failed to shutdown");
                allExecutors[i].shutdownNow();
            }
            
        }
		long stopTime = System.nanoTime();
		System.out.println("Total Execution time = " + (double)(stopTime - startTime)/1000000000.0 + " Seconds");
	}
}

class BranchList extends LinkedList<Account> {} // Create a Generic class for linkedlist of Accounts
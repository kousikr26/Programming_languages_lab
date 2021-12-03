import java.util.Random;
import java.util.ArrayList;


public class Matrix implements Runnable{
    public final static int N = 1000;
    public static int numThreads;
    // Initialise matrices
    public static int[][] A = new int[N][N];
    public static int[][] B = new int[N][N];
    public static int[][] C = new int[N][N];
    
    public boolean initialiseMatrix;
    private int threadId;
   
	
	public static void main(String []args){
        
        if(args.length == 0 ){
            System.out.println("Arguments not specified \n Correct usage : java Matrix <numThreads>");
            System.exit(1);
        }
       
        // Initialise variables and start clock
		long startTime = System.nanoTime();	
        
        numThreads =Integer.parseInt(args[0]); 
        
        // Initialise Thread pool
        Thread threadPool[] = new Thread[numThreads];

        

       // First loop is to initialise the matrices with random integers
        for(int i =0;i<numThreads;i++){
            
            Runnable r = new Matrix(true,i);
            threadPool[i] = new Thread(r);
            threadPool[i].start();
        }
        // Wait for initialising threads to complete
        waitForThreads(threadPool);
        
        // Second loop is to initialise threads that will do the calculation - indicated by setting initialiseMatrices = false
        for(int i =0;i<numThreads;i++){
            
            Runnable r = new Matrix(false,i);
            threadPool[i] = new Thread(r);
            threadPool[i].start();
        }
        // Wait for calculating threads to complete
        waitForThreads(threadPool);

        // Optional : print matrices to verify if N is small
        // printMatrix(A);
        // printMatrix(B);
        // printMatrix(C);
        
        // Calculate total execution time
        long stopTime = System.nanoTime();
        System.out.println("Succesfully calculated Matrix product");
        System.out.println("Total Execution time = " + (double)(stopTime - startTime)/1000000000.0 + " Seconds");
        
		
    }
    public Matrix(boolean initialiseMatrix, int threadId){
        this.initialiseMatrix = initialiseMatrix;
        this.threadId = threadId;
    }
    public void run(){
        // Thread run function
        if(initialiseMatrix){ // If thread is initialising the matrix
            
            Random rand = new Random();
            for(int row = threadId;row<N;row+=numThreads){
                for(int i = 0;i<N;i++){
                    A[row][i] = rand.nextInt(11) ;
                    B[row][i] = rand.nextInt(11) ;
                }
            }
            
        }
        else{ // If thread is calculating the matrix product
            for(int row = threadId;row<N;row+=numThreads){
                for(int j = 0;j<N;j++){
                    int sum = 0;
                    for (int k = 0;k<N;k++){
                        sum+=A[row][k]*B[k][j];
                    }
                    C[row][j] = sum;
                }
            }
            
        }    
    }
    

    public static void waitForThreads(Thread[] threadPool){
        // Function that waits for threads in threadpool given as argument to complete
        for(int i =0;i<numThreads;i++){
            if(threadPool[i] == null) continue;
            try{
                threadPool[i].join();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
	

    public static void printMatrix(int [][] matrix){
        // Utility function to print the matrix
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
}


import java.util.Random;


public class Pi extends Thread{
	public final static int numPoints = 1000000;
	public volatile static int mainCounter = 0; // Static counter storing total count 
	private volatile int counter = 0; // Private counter for each thread
	private int n = 0;
	
	public Pi(int n){
		this.n = n;
	}
	public static void main(String []args){
		if(args.length == 0 ){
            System.out.println("Arguments not specified \n Correct usage : java Pi <numThreads>");
            System.exit(1);
        }

		// Initialise variables and clock
		mainCounter = 0;
		long startTime = System.nanoTime();	
		final int numThreads =Integer.parseInt(args[0]); 
		
		
		int div = numPoints/numThreads;
		int rem = numPoints%numThreads;
		// Initialising threads
		Pi threadpool[] = new Pi[numThreads];
		for(int i = 0;i<numThreads;i++){
			if(i == numThreads - 1){
				threadpool[i] = new Pi(div + rem);	
			}
			else threadpool[i] = new Pi(div);
			threadpool[i].start();
		}

		long threadCreationTime = System.nanoTime();
		// Wait for threads to complete and get counts
		for(int i = 0;i<numThreads;i++){
			try{  
				threadpool[i].join();  
				mainCounter+=threadpool[i].getCount();
			}
			catch(Exception e){
				System.out.println(e);
			}  

		}
		System.out.println("Estimated Pi = " + 4 * (double) mainCounter / numPoints);		
		long stopTime = System.nanoTime();
		// System.out.println("Thread Creation and Initialisation time : " + (double)(threadCreationTime - startTime)/1000000000.0 + " Seconds");
		System.out.println("Total Execution time = " + (double)(stopTime - startTime)/1000000000.0 + " Seconds");
		
		
	}
	
	public void run(){
		Random rand = new Random();
		double x,y,z;
		
		counter = 0; 
		for (int i = 0;i < n; i++){
			// Generate random point
			x = rand.nextDouble();
			y = rand.nextDouble();
			z = x*x + y*y;
			// Check if it lies within the circle
			if (z < 1.0) {
				counter+=1;
			}
		}
		
		
	}
	public int getCount(){
		return counter;
	}
	
}


import java.util.Random;
import java.lang.Math; 


public class Simpson extends Thread{
    final static int lowerBound = -1;
    final static int upperBound = 1;
    private int startPoint,endPoint;
    private double value;
    static double integralValue;
    final static int numPoints = 1000000;


    public Simpson(int startPoint, int endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
    
    public static void main(String []args){
        if(args.length == 0 ){
            System.out.println("Arguments not specified \n Correct usage : java Simpson <numThreads>");
            System.exit(1);
        }

        // Initialise variables and clock
        long startTime = System.nanoTime();	
    
        final int numThreads = Integer.parseInt(args[0]); 
        
        
        Simpson threadPool[] = new Simpson[numThreads];
		 
        integralValue = 0.0;
        // Initialise threads
        
        int div = (numPoints+1)/numThreads;
        int left,right;
		for(int i = 0;i<numThreads;i++){
            left = div*i;
            right=left+div;
            if(i==numThreads-1) {
                right = numPoints+1;
            }
			threadPool[i] = new Simpson(left,right);
            threadPool[i].start();
            left=right;
        }
        

        long threadCreationTime = System.nanoTime();
        


        // Wait for threads to complete and get value
		for(int i = 0;i<numThreads;i++){
			try{  
                threadPool[i].join();  
                integralValue+=threadPool[i].getValue();
			}
			catch(Exception e){
				System.out.println(e);
			}  

		}
		System.out.println("Estimated Integral = " + ((double)(upperBound - lowerBound)/(3.0*(double)(numPoints)))*integralValue);		
		long stopTime = System.nanoTime();
		// System.out.println("Thread Creation and Initialisation time : " + (double)(threadCreationTime - startTime)/1000000000.0 + " Seconds");
        System.out.println("Total Execution time = " + (double)(stopTime - startTime)/1000000000.0 + " Seconds");
        
       
	}
	
	public void run(){
        // Use Simpson's formula
        value = 0;
        for(int i=startPoint;i<endPoint;i++){
            double x = (i*(double)(upperBound - lowerBound)/(double)(numPoints)) + (double)lowerBound;
            if(i == 0 || i == numPoints){
                value=value+func(x);
            }
            else{
                value=value +func(x)*2*(1+(i%2));
            }
            
        }
		
    }
    public double func(double x){
        // Function we wish to integrate
        return (Math.exp((-1*x*x)/2.0))/(Math.sqrt(2*Math.PI));
        
    }
    public double getValue(){
        return value;
    }
    
}


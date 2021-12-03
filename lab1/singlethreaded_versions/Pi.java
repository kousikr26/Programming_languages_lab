import java.util.Random;

public class Pi{
	
	public static void main(String []args){
		long startTime = System.nanoTime();	
		final int POINTS = 1000000;
		
		Random rand = new Random();
		double x,y,z;
		
		int counter = 0; 
		for (int i = 0;i < POINTS; i++){
			
			x = rand.nextDouble();
			y = rand.nextDouble();
			z = x*x + y*y;
			if (z < 1.0) {

				counter+=1;
			}
		}

		System.out.println("Estimated Pi = " + 4 * (double) counter / POINTS);		
		long stopTime = System.nanoTime();
		System.out.println("Execution time : " + (double)(stopTime - startTime)/1000000000.0 + " Seconds");
	}
}


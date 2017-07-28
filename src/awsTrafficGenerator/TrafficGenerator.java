package awsTrafficGenerator;

import java.util.Random;

public class TrafficGenerator {
	
	public static void main(String[] args ) throws InterruptedException {
		// TODO Auto-generated method stub
		Random sleepy = new Random();
		double sendingRate = 1;
		Long sleepTime;
		
		while(true) {
			sleepTime = Math.round(1000*getExpRandom(sleepy, sendingRate));
			Thread.sleep(sleepTime);
			System.out.println("slept for: " + sleepTime+ "\n" );
			new AWSClient().start();
		}
	}
	
    public static double getExpRandom(Random r, double p) { 
        return -Math.log((1-r.nextDouble())) / p; 
    }
	
}

package awsTrafficGenerator;

import java.util.Random;

import constants.connectionConstants;

public class TrafficGenerator {
	
	public static void main(String[] args ) throws InterruptedException {
		// TODO Auto-generated method stub
		Random sleepy = new Random();
		double sendingRate = 1;
		Long sleepTime;
		
		while(true) {
			//sleepTime = Math.round(getSleepMultiplier()*getExpRandom(sleepy, sendingRate));
			//sleepTime = Math.round(connectionConstants.sleepMulitplier*getExpRandom(sleepy, sendingRate));
			sleepTime = (long) connectionConstants.sleepMulitplier;
			Thread.sleep(sleepTime); 
			System.out.println("slept for: " + sleepTime+ "\n" );
			new AWSClient().start();
		}
	}
	
    public static double getExpRandom(Random r, double p) { 
        return -Math.log((1-r.nextDouble())) / p; 
    }
    
    public static double getSleepMultiplier() {
    	long currentTime = System.currentTimeMillis() - connectionConstants.startTime;
    	return	(
    				Math.tanh(
    					2*Math.E*(
    						(currentTime/(Math.E*connectionConstants.sleepMulitplierShiftPeriod))
    						- 0.5
    					)
    				)
    				* (connectionConstants.sleepMulitplierEnd - connectionConstants.sleepMulitplierStart)/2
    			)
				+ (connectionConstants.sleepMulitplierEnd + connectionConstants.sleepMulitplierStart)/2;
    }
	
}

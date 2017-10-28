package awsTrafficGenerator;

import java.util.Random;

import constants.connectionConstants;

public class TrafficGenerator {
	
	public static void main(String[] args ) throws InterruptedException {
		// TODO Auto-generated method stub
		Random sleepy = new Random();
		double sendingRate = 3/connectionConstants.sleepMulitplierOneMachineLoad;
		Long sleepTime;
		int counter = 1;
		LogWriter logWriter = new LogWriter("TrafficGenerator."+System.currentTimeMillis()+".log");
		logWriter.write("client_num,time_stamp,event,server_id,avg_thread_count\n");
		
		while(true) {
			//sleepTime = Math.round(getTanhSleepMultiplier()*getExpRandom(sleepy, sendingRate));
			//sleepTime = Math.round(getLinearSleepMultiplier()*getExpRandom(sleepy, sendingRate));
			//sleepTime = Math.round(connectionConstants.sleepMulitplier*getExpRandom(sleepy, sendingRate));
			sleepTime = Math.round(getExpRandom(sleepy, sendingRate));
			//sleepTime = (long) getLinearSleepMultiplier();
			//sleepTime = (long) getLinearFrequencySleepMultiplier();
			//sleepTime = (long) connectionConstants.sleepMulitplier;
			
			Thread.sleep(sleepTime); 
			System.out.println("slept for: " + sleepTime+ "\n" );
			new AWSClient(logWriter, counter++).start();
		}
	}
	
    public static double getExpRandom(Random r, double p) { 
        return -Math.log((1-r.nextDouble())) / p; 
    }
    
    public static double getTanhSleepMultiplier() {
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
    
    public static double getLinearSleepMultiplier() {
    	long currentTime = System.currentTimeMillis() - connectionConstants.startTime;
    	return (
    		(connectionConstants.sleepMulitplierEnd - connectionConstants.sleepMulitplierStart)
    		/ (double) connectionConstants.sleepMulitplierShiftPeriod
    	) * currentTime + connectionConstants.sleepMulitplierStart;
    }
    
    public static double getLinearFrequencySleepMultiplier() {
    	int maxMachines = connectionConstants.sleepMultiplierMaxMachines;
    	long currentTime = System.currentTimeMillis() - connectionConstants.startTime;
    	double machineNum = ((double) currentTime
        		/ (connectionConstants.sleepMultiplierMachineOpenTime * 4))
    			+1 ;
    	return (
    		connectionConstants.sleepMulitplierOneMachineLoad
    		/ (double) ( machineNum<maxMachines ? machineNum : maxMachines )
    	);
    }
}

package awsTrafficGenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;

import constants.connectionConstants;

public class TrafficGenerator {
	
	public static void main(String[] args ) throws InterruptedException {
		// TODO Auto-generated method stub
		Random sleepy = new Random();
		double sendingRate = 1;
		Long sleepTime;
		
		try {
			Socket socket = new Socket(connectionConstants.host, connectionConstants.configPort);
			BufferedWriter buf = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			buf.write(Integer.toString(connectionConstants.serverIterations) + "\n");
			buf.flush();
			buf.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			sleepTime = Math.round(100*getExpRandom(sleepy, sendingRate));
			Thread.sleep(sleepTime);
			System.out.println("slept for: " + sleepTime+ "\n" );
			new AWSClient().start();
		}
	}
	
    public static double getExpRandom(Random r, double p) { 
        return -Math.log((1-r.nextDouble())) / p; 
    }
	
}

package awsTrafficGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import constants.connectionConstants;

public class AWSClient extends Thread{
	private Socket socket = null;
	private BufferedReader buff = null;
	
	public AWSClient() {
		
	}
	@Override
	public void run() {
		
		super.run();
		RttTimer timer = new RttTimer();
		timer.start();
		try {
			socket = new Socket(connectionConstants.host, connectionConstants.port);
			buff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String reply = buff.readLine();
			timer.stop();
			System.out.println(reply + ": RTT = " + timer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}

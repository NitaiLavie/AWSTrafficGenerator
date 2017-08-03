package awsTrafficGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import constants.connectionConstants;

public class AWSClient extends Thread{
	private Socket socket;
	private BufferedReader buff;
	
	public AWSClient() {
		
	}
	@Override
	public void run() {
		
		super.run();
		RttTimer timer = new RttTimer();
		timer.start();
		try {
			socket = new Socket(connectionConstants.host, connectionConstants.port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			buff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String reply = buff.readLine();
			timer.stop();
			System.out.println(reply + ": RTT = " + timer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

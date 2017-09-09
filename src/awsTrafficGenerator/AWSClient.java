package awsTrafficGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import constants.connectionConstants;

public class AWSClient extends Thread{
	private Socket socket = null;
	private BufferedReader buffRead = null;
	private BufferedWriter buffWrite = null;
	
	public AWSClient() {
		
	}
	@Override
	public void run() {
		
		super.run();
		RttTimer timer = new RttTimer();
		timer.start();
		try {
			socket = new Socket(connectionConstants.host, connectionConstants.port);
			buffRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffWrite = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			buffWrite.write(connectionConstants.cores+"\n"); // number of cores
			buffWrite.write(connectionConstants.timeout+"\n"); // timeout in seconds
			buffWrite.write(connectionConstants.iterations+"\n"); // number of iterations
			buffWrite.write(connectionConstants.update_interval+"\n"); // what is the metric update interval in millis
			buffWrite.flush();
			String reply = buffRead.readLine(); // waiting for an answer from the server
			timer.stop();
			System.out.println(reply + ": RTT = " + timer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				buffRead.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}

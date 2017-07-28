package awsTrafficGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import constants.connectionConstants;

public class AWSClient extends Thread{
	private Socket socket;
	private InputStream inputStream;
	private BufferedReader buff;
	
	public AWSClient() {
		
	}
	@Override
	public void run() {
		
		super.run();
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
			buff.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

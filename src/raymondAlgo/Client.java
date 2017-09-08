package raymondAlgo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	String host;
	int port;
	static int count=0;
	String message;
	Client(Node output){
		this.host=output.getHost();
		this.port= output.getPort();
	}
	public void send(String message){
		try {
			Socket clientSocket = new Socket(host,port);
			OutputStream os = clientSocket.getOutputStream();
			StreamUtil.writeLine(message, os);
			count++;
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

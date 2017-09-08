package raymondAlgo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	Node input;
	Server(Node input){
		this.input = input;
	}
	public void startServer(){
		Thread t = new Thread(new MyRunnable());
		t.start();
	}
	
	public class MyRunnable implements Runnable{

		@Override
		public void run() {
			try {
				ServerSocket serverSocket = new ServerSocket(input.getPort()); //opens server connection
				System.out.println("Server of node "+input.getName()+" started");
				while(true){
					Socket clientSocket = serverSocket.accept(); 	//waits for the client to connect to server
					Thread t1 = new Thread(new Runner(clientSocket));
					t1.start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public class Runner implements Runnable{
		Socket clientSocket;
		Runner(Socket cs){
			clientSocket = cs;
		}
		@Override
		public void run() {
			InputStream is;
			try {
				is = clientSocket.getInputStream();
				String message = StreamUtil.readLine(is);
				if(message.contains("REQ_TOKEN")){
					input.pushToQueue(Integer.parseInt(message.split(":")[0]));
					Raymond.requestToken(input);			//request token
				}
				else if(message.contains("GIVING_TOKEN")){
					Raymond.recievedToken(input);			// accepting token
				}
				clientSocket.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}

}

package raymondAlgo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class RaymondMain {
	public static long startT,endT;
	public static void main(String[] args){
		int name = Integer.parseInt(args[0]);
		ReadInput read = new ReadInput();
		Node input = read.reader(name);
		//print(input);
		Server server = new Server(input);
		server.startServer();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		startT = System.currentTimeMillis();
		fun(input);
	}
	
	
	public static void print(Node node){
		System.out.println("Name: "+node.getName());
		System.out.println("Host: "+node.getHost());
		System.out.println("port: "+node.getPort());
		System.out.println("delay: "+node.getDelay());
		System.out.println("Cs Time: "+node.getCsTime());
		System.out.println("No Times: "+node.getNoRequests());
		System.out.println("parent: "+node.getParent().getName());
		System.out.print("Neighbours are: ");
	}
	
	public static void fun(Node input){
		long responseTime = 0;
		long throughput =0;
		for(int i=0;i<input.getNoRequests();i++){
			try {
				long startTime = System.currentTimeMillis();
				Raymond.csEnter(input);
				String message ="Entering cs by node "+input.getName();
				FileUtil.writeData(message);
				Thread.sleep(expRandom(input.getCsTime()));
				message ="Leaving cs by node "+input.getName();
				FileUtil.writeData(message);
				long endTime = System.currentTimeMillis();
				long diff = endTime - startTime;
				responseTime += diff;
				Raymond.csExit(input);
				Thread.sleep(expRandom(input.getDelay()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		endT = System.currentTimeMillis();
		System.out.println("done");
		System.out.println("message complexity:"+Client.count);
		responseTime /= input.getNoRequests();
		System.out.println("Response Time = "+ responseTime+" milliSec");
		long diff = (endT-startT)/1000;
		throughput = Client.count/diff;
		System.out.println("ThroughPut = "+throughput+" messages per second");
		
	}
	
	public static int expRandom(int mean){
		double lampda = 1.0/mean; 
		Random rand= new Random(); 
		double exp = Math.log(1-rand.nextDouble())/(-lampda);
		return (int) Math.ceil(exp);
	}
	
	
		
	    

}

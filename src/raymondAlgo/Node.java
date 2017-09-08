package raymondAlgo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Node {
	private int name;
	private int delay;
	private int csTime;
	private int noRequests;
	private Node parent;
	private LinkedList<Node> neighbours =new LinkedList<Node>();
	private String host;
	private int port;
	private BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
	private boolean executingCs= false;
	
	public boolean isExecutingCs(){
		return executingCs;
	}
	
	public void setCs(boolean val){
		executingCs = val;
	}
	
	public void pushToQueue(int n){
		queue.add(n);
	}
	
	public int popQueue(){
		return queue.poll();
	}
	
	public int peekQueue(){
		return queue.peek();
	}
	public Queue getQueue(){
		return queue;
	}
	public boolean nodeInQueue(){
		return queue.contains(name);
	}
	public void removeNode(){
		queue.remove(name);
	}
	public boolean isQueueEmpty(){
		return queue.isEmpty();
	}
	public void addNeighbour(Node n){
		neighbours.add(n);
	}
	 public Node getNeighbour(int n){
		 Node result = null;
		 for(Node out:neighbours){
			 if(out.getName()==n){
				 result = out;
				 break;
			 }
		 }
		 return result;
	 }
	
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public int getCsTime() {
		return csTime;
	}
	public void setCsTime(int csTime) {
		this.csTime = csTime;
	}
	public int getNoRequests() {
		return noRequests;
	}
	public void setNoRequests(int noRequests) {
		this.noRequests = noRequests;
	}
	
}

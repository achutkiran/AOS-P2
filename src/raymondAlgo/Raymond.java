package raymondAlgo;

import java.util.concurrent.Semaphore;

public class Raymond {
	static Semaphore s = new Semaphore(1);
	static boolean req_sent = false;
	static boolean token_busy = false;
	static boolean sem = false;
	
	public static void csEnter(Node n){
		n.pushToQueue(n.getName());
		acessPrivilages(n);
		sendRequest(n);
		//FileUtil.writeData("Trying to acquire semaphore by node "+n.getName());
		try {
			s.acquire();
			//FileUtil.writeData("acquired semaphore by node "+n.getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static  void csExit(Node n){
		token_busy = false;
		acessPrivilages(n);
		s.release();
		sendRequest(n);
	}
	
	public static synchronized void acessPrivilages(Node n){
		if(n.getParent()==n && !n.isQueueEmpty() && !token_busy){
			int neigh;
//			if(n.nodeInQueue()){	//if-else for greedy
//				neigh =n.getName();
//				n.removeNode();
//			}
//			else{
//				neigh = n.popQueue();
//			}
			neigh = n.popQueue(); //not greedy
			if(n.getName()==neigh){
				token_busy=true;
				if(sem){
					s.release();
					sem= false;
				}
				return;
			}
			Node parent =n.getNeighbour(neigh);
			Client c = new Client(parent);
			String message ="GIVING_TOKEN";
			c.send(message);
			n.setParent(parent);
		}
	}
	public static synchronized void sendRequest(Node n){
		if(n.getParent()!=n && !n.isQueueEmpty() && !req_sent){
			req_sent = true;
			Client c = new Client(n.getParent());
			String message = n.getName()+":REQ_TOKEN";
			c.send(message);
			try {
				if(!sem){
					s.acquire();
					sem=true;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void recievedToken(Node n){
		n.setParent(n);
		acessPrivilages(n);
		req_sent = false;
		sendRequest(n);
	}
	
	public static void requestToken(Node n){
		acessPrivilages(n);
		sendRequest(n);
	}

}

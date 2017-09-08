package raymondAlgo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ReadInput {							//reads configuration of nodes from config.txt
	ArrayList<Node> input = new ArrayList<Node>() ;
	public Node reader(int name){
		BufferedReader br;
		Node output = null;
		
		int n=0,delay=0,csTime=0,noRequests=0;
		try {
			br = new BufferedReader(new FileReader("config.txt"));
			String line = null;
			while ((line = br.readLine()) != null) {
				if((!line.contains("#")) && (!line.equalsIgnoreCase(""))){
					if(n==0){
						String[] dura = line.split(" ");
						n = Integer.parseInt(dura[0]);
						delay = Integer.parseInt(dura[1]);
						csTime = Integer.parseInt(dura[2]);
						noRequests = Integer.parseInt(dura[3]);
						for(int i=0;i<n;i++){
							Node node = new Node();
							node.setCsTime(csTime);
							node.setDelay(delay);
							node.setNoRequests(noRequests);
							input.add(node);
						}
					}
					else{
						String[] splits = line.split(" ");
						int index = Integer.parseInt(splits[0]);
						Node in = input.get(index);
						in.setName(index);
						String  host = splits[1];
						in.setHost(host+".utdallas.edu");
						String port = splits[2];
						in.setPort(Integer.parseInt(port));
						Node parent = input.get(Integer.parseInt(splits[3]));
						in.setParent(parent);
						addNeighbour(in,parent);
						if(index==name){
							output = in;
						}
					}
				}
			}
		 
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return output;
		
	}
	
	public void addNeighbour(Node node1, Node node2){
		if(node1==node2){
			return;
		}
		node1.addNeighbour(node2);
		node2.addNeighbour(node1);
	}
	
}

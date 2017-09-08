package raymondScript;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Script {		//Script to read log file
	
	static class RaymondError extends Exception{
		
	}
	
	public static void main(String[] args){
			boolean enterCs=false;
			int node=-1;
			try {
				File file = new File("outputLog.txt");
				FileReader fr;
				fr = new FileReader(file);
				BufferedReader buffer = new BufferedReader(fr);
				String line;
				while((line=buffer.readLine())!=null){
					if(line.contains("Entering")){
						if(!enterCs){
							node = Character.getNumericValue(line.charAt(line.length()-1));
							enterCs=true;
						}
						else{
							throw new RaymondError();
						}
					}
					else{
						if(node!=Character.getNumericValue(line.charAt(line.length()-1))){
							throw new RaymondError();
						}
						enterCs= false;
					}
				}
				System.out.println("Raymond Sucess");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch(RaymondError e){
				System.out.println("Raymond failed");
			}
			
	}
	

}

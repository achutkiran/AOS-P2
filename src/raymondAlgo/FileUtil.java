package raymondAlgo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtil {
	public static void writeData(String message) //used to write data to log file
	{
		File file = new File("outputLog.txt");
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file,true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(message);
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

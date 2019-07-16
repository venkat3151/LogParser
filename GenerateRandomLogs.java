

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Generate Logs for a particular day with 1000 servers and each server having 2 CPU's
 * The CPU's will be with the ID 0 or 1.
 * For Querying purposes, each server will have a specific folder associated with it which in turn will have 2 .txt files(one for each CPU)
 * 			
 * @author Venkatesh Viswanathan
 *
 */
public class GenerateRandomLogs {
	
	private String dir;
	
	/**
	 * Initialize the class with the directory name, where the logs will be stored.
	 * @param dir the directory name for storing logs.
	 */
	public GenerateRandomLogs(String dir) {
		this.dir = dir;
	}
	
	
	/**
	 * A method to generate random IP addresses for a specific number of server, all begin with 192.168.*.*.
	 * 
	 * @param serverNum the number of servers we are going to simulate
	 * @return A set of randomly generated IP addresses.
	 */	
	private Set<String> generateIP(int serverNum) {
		Set<String> serverIPs = new HashSet<>(); // use set instead of list to avoid duplicates
		Random r = new Random();
		String IP = "";
		while(serverIPs.size() < serverNum) {
			IP = 192 + "." + 168 + "." + r.nextInt(256) + "." + r.nextInt(256);
			serverIPs.add(IP);
		}
		return serverIPs;
	}
	
	
	/**
	 * 
	 * @param Date a date that indicate the day for simulation. in formate of: yyyy-mm-dd
	 * @return A list of time stamps per minute during the day.
	 * @throws ParseException If the date format is incorrect.
	 */
	private List<Long> generateTimestamp(String date) throws ParseException {
		List<Long> timestamps = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date tmpDate = format.parse(date + " 00:00:00");
		long timestamp = tmpDate.getTime()/1000; // convert ms to s;
		long tmpStamp = timestamp;
		while(tmpStamp < timestamp + 60*60*24) {
			timestamps.add(tmpStamp);
			tmpStamp += 60;
		}
		return timestamps;
	}
	
	/**
	 * A method that write logs for a single file, the file name is the "DATA_PATH/IP_ADDRESS/CPU_ID.txt".
	 * This is to increase the speed of querying.
	 * @param IP 
	 * @param cpu_id
	 * @param timestamps
	 * @throws IOException An exception happens if file name is incorrect.
	 */
	private void writeLogFile(String IP, int cpu_id, List<Long> timestamps) throws IOException {
		Random cpu_usage = new Random();
		File filePath = new File(dir + "/"+ IP);
		filePath.mkdirs();
		File file = new File(filePath + "/" + String.valueOf(cpu_id) + ".txt");
		FileOutputStream outStream = new FileOutputStream(file);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outStream));
		for(long timestamp : timestamps) {
			bw.write(timestamp + " " + IP + " " + cpu_id + " " + cpu_usage.nextInt(100));
			bw.newLine();
		}
		bw.close();
	}
	
	/**
	 * Method that generate Random logs for 1000 servers and for a particular date with 2 CPU's each
	 * @throws IOException
	 * @throws ParseException
	 */
	public void simulate() throws IOException, ParseException {
		Set<String> serverIPs = generateIP(1000);
		List<Long> timestamps = generateTimestamp("2014-10-31");
		for(String IP : serverIPs) {
			for(int i = 0; i < 2; i++) {
				writeLogFile(IP,i , timestamps);
			}
		}	
	}
}



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class fetches the results of the queries given by the user
 * @author Venkatesh Viswanathan
 *
 */
public class QueryHandler {
	private String dir;
	
	/**
	 * Constructor; Initialize the class with the target directory where logs were stored.
	 * @param dir the directory name where the logs are stored.
	 */
	public QueryHandler(String dir) {
		this.dir = dir;
	}

	/**
	 * This method searches the data according to the query given by the user.
	 * 
	 * @param arguments An array of arguments from the query sentence a user typed in.
	 * @return A String the represents the response of each each query.
	 * @throws IOException An exception happens if there is no such a file that related to an IP address.
	 * @throws ParseException An exception happens if the input data format is not correct.
	 */
	public String handleQuery(String[] arguments) throws IOException, ParseException {		
		String response = "";
		System.out.println("Entered Query Handler");
		// parse the arguments;
		String IP = arguments[2];
		String cpu_id = arguments[3];
		String startDataTime = arguments[4] + " " + arguments[5];
		String endDateTime = arguments[6] + " " + arguments[7];
		
		// parse the time section to get logs.
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date startTime = format.parse(startDataTime);
		Date endTime = format.parse(endDateTime);
		long startTimestamp = startTime.getTime()/1000; 
		long endTimestamp = endTime.getTime()/1000;

		// get the path of the target file.
		File file = new File(dir + "/" + IP + "/"+ cpu_id + ".txt");
		BufferedReader br = new BufferedReader(new FileReader(file));		
		String line = br.readLine();
		
		// scan the file, filter out the logs.
		while(line != null){			
			String[] log = line.split(" ");
			long timestamp = Long.parseLong(log[0]);
			
			if( (startTimestamp == endTimestamp && startTimestamp == timestamp)||startTimestamp <= timestamp &&  timestamp < endTimestamp){
				String cpu_usage = log[3];
				Date date = new Date(timestamp*1000); 
				String validLog = "(" + format.format(date) + ", " + cpu_usage +"%" + ")";
				response += validLog + ", ";
			} else if(timestamp > endTimestamp) break;
			line = br.readLine();
		}
		
		br.close();
		if(response.length() <3) return "no result!";
		return response.substring(0, response.length()-2);
	}
}

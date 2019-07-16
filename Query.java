

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Determines the Type of Command and calls for the Query Handler if the command is Query.
 * @author Venkatesh Viswanathan
 *
 */
public class Query {
	
	public static void main(String[] args) throws IOException, ParseException {
		QueryHandler queryHandler = new QueryHandler(args[0]);
		System.out.println("Entered Query");
	        args[1] = args[1].trim();
	    	if(args[1].equals("QUERY")) {
	    		System.out.println("The command is Query is QUERY");
		    	String response = queryHandler.handleQuery(args);
		    	System.out.println("CPU" + args[3] + " usage on " + args[2] + ":");
		    	System.out.println(response);
		    }
	    	else if(args[1].equals("EXIT")) {
	    	System.out.println("The command is Query is EXIT");
	    	System.exit(0);
	    }else {
	    	System.out.println("The command is Query is Invalid. Kindly check for the case as only Upper case command are accepted.");
	    }
    	
	}

}

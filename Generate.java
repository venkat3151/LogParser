

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * A class that acts as a mediator between the .sh file and the Java File.
 * Get Arguments from the .sh file and passes onto the Generate Random Logs file.
 * 
 * @author Venkatesh Viswanathan
 *
 */
public class Generate {
	
	public static void main(String[] args) {
		GenerateRandomLogs randomLogGenerator = new GenerateRandomLogs(args[0]);		
		try {
			randomLogGenerator.simulate();
		} catch (Exception e) {
			System.out.println("Please start the program again, and run it with a correct directory name!");
		} 
		System.out.println("Logs are generated under the folder " + args[0]);
	}
}

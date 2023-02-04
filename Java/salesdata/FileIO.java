package salesdata;

// Import Java libraries
import java.io.*;
import java.util.*;

// Define class FileIO
public class FileIO {
	// The path and name of the file to be read
	private String fname = null;
	// Set DEBUG to true to enable debug mode
	private boolean DEBUG = true;

	public FileIO(String fname) {
		this.fname = fname;
	}

	// Reads data from a file and returns a Franchise object
	public Franchise readData() {
		Franchise a1 = null;
		int counter = 0;
		try {
			// Create a FileReader object and pass in the file name
			FileReader file = new FileReader(fname);
			// Create a BufferedReader object to read the file
			BufferedReader buff = new BufferedReader(file);
			String temp;
			boolean eof = false;
			// Loop through each line of the file
			while (!eof) {
				String line = buff.readLine();
				counter++;
				// Check if we have reached the end of the file
				if (line == null)
					eof = true;
				else {
					if (DEBUG)
						System.out.println("Reading" + line);
					// If it is the first line of the file, create a new Franchise object with the number of stores passed in as the parameter
					if (counter == 1) {
						temp = line;
						a1 = new Franchise(Integer.parseInt(temp));
						if (DEBUG)
							System.out.println("d  " + a1.numberOfStores());
					}
					// If it is the second line, do nothing
					if (counter == 2);
					// For all subsequent lines, build a Store object and add it to the Franchise object
					if (counter > 2) {
						// Call the "buildStore" method to create a new Store object and add it to the Franchise object
						int x = buildStore(a1, (counter-3), line);
						if (DEBUG)
							System.out.println("Reading Store # "+(counter-2)+" Number of weeks read =  " +  x);
						if (DEBUG)
						{	
							System.out.println("Data read:");
							a1.getStores(counter-3).printData();
						}
					}
				}
			}
			// Close the BufferedReader object
			buff.close();
		} catch (Exception e) {
			// If an exception occurs, print out the error message
			System.out.println("Error -- " + e.toString());
		}
		// Return the Franchise object
		return a1;
	}

	// Method to build a Store object and add it to the Franchise object
	public int buildStore(Franchise a1, int counter, String temp) {
		// Declare and initialize the necessary variables
		Store tstore = new Store();
		String s1  =  "";
		float sale = 0.0f;
		int week = 0;
		int day = 0;
		StringTokenizer st = new StringTokenizer(temp);
		while (st.hasMoreTokens()) {
			for(day=0;day<7;day++)
			{
				s1 = st.nextToken();
				sale = Float.parseFloat(s1);
				tstore.setSaleForWeekdayIntersection(week, day, sale);
			}
			week++;
		}
		// Add the newly created Store object to the Franchise object
		a1.setStores(tstore, counter);
		// Return the number of weeks that were read from the file
		return week; 
	}
}

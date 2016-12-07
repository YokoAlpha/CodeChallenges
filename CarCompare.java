/*
 * Model Java solution 
 * CarCompare
 */

import java.util.*;
import java.io.*;

public class CarCompare 
{
	public static void main (String args[]) throws IOException
	{
		Scanner fileNameScanner= new Scanner (System.in);
		System.out.println("Enter name of your file that you want to create e.g. example.csv ");			
		String filename = fileNameScanner.nextLine();
		System.out.println("Enter how many car marque and prices do you want to enter? ");			
		int entriesNumber = fileNameScanner.nextInt();

		//getting car marque and price and storing into array list
		Scanner readText= new Scanner (System.in);
		ArrayList<String> carInformationArrayList  = new ArrayList<String>(); 
		for (int i = 0; i < entriesNumber; i++)
		{
			System.out.println("Enter car marque and price");
			carInformationArrayList.add(readText.nextLine());
		}

		//calling method that creates .csv file and writers data from array list into it
		createAndWriteFile(filename, carInformationArrayList);

		//setup scanners
		Scanner csvScanner = new Scanner (new File (filename));

		//assigning the retrieved lines to an array list
		ArrayList<String> linesArrayList  = retrieveLinesFromScanner(csvScanner);

		//create array variables to hold marques and prices
		String carMarques[] = new String[linesArrayList.size()];
		double carPrices[] = new double[linesArrayList.size()];

		//calling method that will split line string into array
		splitLines(linesArrayList.size(), linesArrayList, carMarques, carPrices);

		//calling method that will sort cars by their highest price
		sortCarsByPrices(linesArrayList.size(), carMarques, carPrices);          

		//calling method that will display sorted list
		displaySortedList(linesArrayList.size(), carMarques, carPrices);
	}
	
	/*
	 * This method creates .csv file and writers data from array list into it
	 */
	private static void createAndWriteFile(String filename,ArrayList<String> carInformationArrayList) throws IOException 
	{
		//creates file
		File file = new File(filename);
		// if file doesnt exists, then create it
		if (!file.exists()) 
		{
			file.createNewFile();
		}
		//Using constructor FileWriter(String filename, boolean append)
		//that can instruct the file to be opened in append mode

		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		for (String content: carInformationArrayList)
		{
			bw.write(content);
			bw.newLine();
		}
		bw.close();
	}
	
	/*
	 * This method retrieves lines from scanner
	 */
	private static ArrayList<String> retrieveLinesFromScanner(Scanner csvScanner)
	{
		ArrayList<String> linesArrayList = new ArrayList<String>();
		while (csvScanner.hasNextLine()) 
		{
			linesArrayList.add(csvScanner.nextLine());
		}
		return linesArrayList;
	}
	
	/*
	 * This method will split line string into array
	 */
	private static void splitLines(int lineCount, ArrayList<String> linesArrayList, String[] carMarques, double[] carPrices) 
	{
		for (int i = 0; i < lineCount; i++)
		{
			String[] splitString = linesArrayList.get(i).split(",");
			carMarques[i] = splitString[0];
			carPrices[i] = Double.parseDouble(splitString[1]); 
		}
	}
	
	/*
	 * This method will sort cars by their highest price
	 */
	private static void sortCarsByPrices(int lineCount, String[] carMarques, double[] carPrices) 
	{
		for (int i = 0; i < lineCount; i++)  
		{
			for(int j = 0; j < lineCount-1; j ++)  
			{
				if( carPrices[j] < carPrices[i] )
				{
					String marque = carMarques[j];
					double price = carPrices[j];
					carMarques[j]= carMarques[i];
					carPrices[j] = carPrices[i];
					carMarques[i] = marque;
					carPrices[i] = price;
				} 
			}
		}
	}
	
	/*
	 * This method will display sorted list 
	 */
	private static void displaySortedList(int itemCount, String[] cars, double[] prices) 
	{
		System.out.println(" \n ");
		System.out.println("Displaying car marques and prices starting from most expensive:");
		System.out.println();
		System.out.printf("%-20s %-10s \n", "Car", "Price");
		for (int i = 0; i < itemCount; i++)
		{
			System.out.printf("%-20s £%-10s\n", cars[i], prices[i]);
		}
	}
}
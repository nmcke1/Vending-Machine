package part01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class Item {
	private static ArrayList<String[]> rows = new ArrayList<String[]>();
	private static String[] row = new String[5];
	private static String itemPath = "src/part01/Items.csv";

	// Niall's Variables
	private static String[] rowA = new String[5];
	private static String[] rowB = new String[5];
	private static String[] rowC = new String[5];
	private static String[] rowD = new String[5];
	private static String[] rowE = new String[5];

	/**
	 * 
	 * 1: this item takes the CSV items, splits them by the ","s and reads them into
	 * an arraylist. 2: It takes the User input(this input will already be validated
	 * by the class that uses this method) - the input is then split. The char value
	 * is converted to a number for ease of use, and the number half is used as an
	 * index. 3: We use these values (letterToNumber + number) to retrieve the exact
	 * item information contained in the corresponding CSV file. 4: These items are
	 * returned if they exist. If not, "" is returned.
	 **/

	/**
	 *  Loads all CSV data into an arraylist named rows
	 */
	public static void loadItems() {
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(itemPath));

			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				rows.add(values);// ENTIRE ROW OF ALL ITEMS
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static String readItems(String input) {
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(itemPath));

			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				rows.add(values);//ENTIRE ROW OF ALL ITEMS
			}
			// Get stuff based by the number
			int number = Integer.parseInt(input.substring(1));
			row = rows.get(number - 1);
			
			//Get stuff based by the letter
			int letterToNumber = (input.charAt(0) - 65); // ASCII chars have numbers assigned, A becomes 0 when you take
			// away 65 for example
			// means that we don't need a big switch statement

			String selectedItem = row[letterToNumber].toString();
			br.close();
			return selectedItem;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	

	/* 1: We take
	 * a string, itemDetails. The format is (item;quantity;price). We split it by
	 * the ; and take the price, converting it to a double value. 2: This value is
	 * returned.
	 */

	public static double getPrice(String itemDetails) {
		String details[] = itemDetails.split(";");
		// Convert price to int
		double price = Double.parseDouble(details[2]);
		return price;
	}

	/** 1:
	 * itemDetails is passed in (name;quantity;price) 2: We split the details by the
	 * ;, and take the name 3: We return the name
	 */

	public static String getName(String itemDetails) {
		String details[] = itemDetails.split(";");
		// Convert name to String
		String name = details[0].toString();
		name = name.trim(); // Remove Leading Space
		return name;
	}

	/**
	 *  1:
	 * itemDetails is passed in (name;quantity;price) 2: We split the details by the
	 * ;, and take the quantity 3: We return the quantity
	 */

	public static int getQuantity(String itemDetails) {
		String details[] = itemDetails.split(";");
		// Convert name to String
		int quantity = Integer.parseInt(details[1]);
		return quantity;
	}
	
	/*
	 * will split the csv values of the item and return the type of the product
	 */
	public static String getType(String itemDetails) {
		String details[] = itemDetails.split(";");
		// Convert name to String
		String type = details[3];
		return type;
	}

	

	private static String[] itemsToStringArray() {
		rowA = rows.get(0);
		rowB = rows.get(1);
		rowC = rows.get(2);
		rowD = rows.get(3);
		rowE = rows.get(4);

		String aString = (rowA[0] + ", " + rowA[1] + ", " + rowA[2] + ", " + rowA[3] + ", " + rowA[4]);
		String bString = (rowB[0] + ", " + rowB[1] + ", " + rowB[2] + ", " + rowB[3] + ", " + rowB[4]);
		String cString = (rowC[0] + ", " + rowC[1] + ", " + rowC[2] + ", " + rowC[3] + ", " + rowC[4]);
		String dString = (rowD[0] + ", " + rowD[1] + ", " + rowD[2] + ", " + rowD[3] + ", " + rowD[4]);
		String eString = (rowE[0] + ", " + rowE[1] + ", " + rowE[2] + ", " + rowE[3] + ", " + rowE[4]);
		String[] allItems = { aString, bString, cString, dString, eString };

		return allItems;
	}


	public static void displayItems() {
		rowA = rows.get(0);
		rowB = rows.get(1);
		rowC = rows.get(2);
		rowD = rows.get(3);
		rowE = rows.get(4);

		System.out.print("\nA)\t");
		for (String items : rowA) {
			String[] item = items.split(";");
			System.out.print(item[0] + " Price: £" + item[2]);
		}
		System.out.print("\nB)\t");
		for (String items : rowB) {
			String[] item = items.split(";");
			System.out.print(item[0] + " Price: £" + item[2]);
		}
		System.out.print("\nC)\t");
		for (String items : rowC) {
			String[] item = items.split(";");
			System.out.print(item[0] + " Price: £" + item[2]);
		}
		System.out.print("\nD)\t");
		for (String items : rowD) {
			String[] item = items.split(";");
			System.out.print(item[0] + " Price: £" + item[2]);
		}
		System.out.print("\nE)\t");
		for (String items : rowE) {
			String[] item = items.split(";");
			System.out.print(item[0] + " Price: £" + item[2]);
		}
		System.out.print("\n");
	}

	
	public static void updateCSV(String userInput) {

		String name = getName(readItems(userInput));
		int quantity = getQuantity(readItems(userInput));
		double price = getPrice(readItems(userInput));
		String type = getType(readItems(userInput));
		String updatedItem = (name + ";" + (quantity - 1) + ";" + price + ";" + type);

		// get letter + number index, eg get A + 1
		int letterToNumber = (userInput.charAt(0) - 65);
		int number = Integer.parseInt(userInput.substring(1));

		// change the rows to the new item
		rows.get(letterToNumber)[number - 1] = updatedItem;

		saveItems();
	}

	
	public static void saveItems() {
		try {
			FileWriter fw = new FileWriter(itemPath, false);
			PrintWriter pw = new PrintWriter(fw);
			for (String row : itemsToStringArray()) {
				pw.println(row);
			}
			fw.close();
			pw.close();
			// System.out.println("Data successfully saved");
		} catch (IOException e) {
			System.out.println("Cannot save Vending Machine Data");
		}
	}

	/**etches the
	 * item's information - price, name, quantity - using the other methods 2: if
	 * the quantity is bigger than 0, the updateCSV method is called, which
	 * subtracts 1 from quantity 3: This method will likely be modified and called
	 * from a different class. It's just printing stuff for testing purposes now
	 */

	public static void buyItem(String userInput) {

		double price = getPrice(readItems(userInput));
		String name = getName(readItems(userInput));

		System.out.println("The price of " + name + " is " + price);

		// Please enter money first
		System.out.println("Buying...");

		int quantity = getQuantity(readItems(userInput));

		if (quantity > 0) {
			updateCSV(userInput);
			System.out.println("*releases drink*");
		} else {
			System.out.println("Sorry, there are no drinks in this section. Please enter another row.");
		}
	}

	public static boolean showPrice(String userInput) {
		Currency pound = new Currency(0);
		double price = pound.getValue(getPrice(readItems(userInput))) ;
		String name = getName(readItems(userInput));

		DecimalFormat twoPlaces = new DecimalFormat("0.00");

		String money = twoPlaces.format(price);

		int quantity = getQuantity(readItems(userInput));

		if (quantity > 0) {
			System.out.println("\n\nThe price of " + name + " is " + pound.getSymbol() + money);
			return true;
		} else {
			System.out.println("Sorry, there are no items in this section. Please enter another row.");
			return false;
		}

		// System.out.println(twoPlaces.format(amount));

		// System.out.println("\n\nThe price of " + name + " is £" + money);
	}

	
	public static void changeData(String input, String change, int changeType) {
		String item = readItems(input);
		String[] split = item.split(";");
		split[changeType] = change;
		String updatedItem = (split[0] + ";" + split[1]);

		int letterToNumber = (input.charAt(0) - 65);
		// ASCII chars have numbers assigned, A becomes 0 when you take away 65
		int number = Integer.parseInt(input.substring(1));
		rows.get(letterToNumber)[number - 1] = updatedItem;
	}

}
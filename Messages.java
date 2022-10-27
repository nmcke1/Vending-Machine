package part01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Messages {

	/*
	 * Niall's Method
	 * Is called after an item is bought and used to check if the quantity is below 5
	 * and if so outputs a message to a text file stating the item the quantity and the date that the message was sent
	 */
	public static boolean lowStock(String input) {
		String item = Item.readItems(input);
		String[] split = item.split(";");
		int quantity = Integer.parseInt(split[1]);
		Date date = new Date();
		if (quantity <= 5) {
			String msg = ("Item: " + split[0] + " is low on stock. Stock Remaining: " + quantity + ". Date: " + date);
			try {
				FileWriter fw = new FileWriter("src/part01/StockMessages.txt", true);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(msg);
				fw.close();
				pw.close();
				return true;
			} catch (IOException e) {
				System.out.println("Couldn't send low stock alert");
				return false;
			}
		}
		return false;
	}
	
	
	/*
	 * Niall's Method
	 * Will put the location, date, item and type into a message which is stored in a text file 
	 */
	public static boolean purchaseLog(String input) {
		String item = Item.readItems(input);
		String[] split = item.split(";");
		Date date = new Date();
		vendingLocation location = new vendingLocation("Queen's University Belfast", 54.584, -5.933);

		String msg = ("Item: " + split[0] + " Product Type: " + split[3] + " Location: " + location.toString() + " Date: " + date);
		try {
			FileWriter fw = new FileWriter("src/part01/PurchaseLog.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(msg);
			fw.close();
			pw.close();
			sendLog();
			lowStock(input);
			return true;
		} catch (IOException e) {
			System.out.println("Couldn't update purchase log");
			return false;
		}
	}
	
	/*
	 * Niall's Code
	 * Is used to check if the purchase log has 10 purchases yet
	 * if so the purchase log is "sent" to a server and the local copy is cleared
	 * so that another 10 purchases can be added
	 */
	public static boolean sendLog() {
		String line = "";
		int count = 0;
		try {
			FileReader fr = new FileReader("src/part01/PurchaseLog.txt");
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				count++;
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			return false;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		if (count > 10) {
			System.out.println("Console: Ten transactions completed, transaction log has been uploaded and cleared \n");
			try {
				PrintWriter pw = new PrintWriter("src/part01/PurchaseLog.txt");
				pw.close();
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

}

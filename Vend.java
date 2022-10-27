package part01;

import java.util.InputMismatchException;

/**
 * @author Thomas Keenan
 */

import java.util.Scanner;

import part01.Biometrics.Password;

public class Vend {
	static Scanner input = new Scanner(System.in);
	static final String PROMPT = "--->";
	static final String SPACES = "    ";
	static final String ERROR = "ERROR: Not a Valid option. Please try again. ";
	private static Biometrics bio = new Biometrics();
	public static void main(String[] args) {
		String options[] = { "Buy An Item", "Check All Items", "Admin Mode", "Exit" };
		Menu myMenu = new Menu("Vending Machine Options: ", options);
		Item.loadItems();

		boolean finished = false;
		do {
			int option = myMenu.getUserChoice();
			switch (option) {
			case 1:
				buyItem();
				break;
			case 2:
				 Item.displayItems();
				break;
			case 3:
				// adminMode();
				break;
			case 4:
				finished = true;
				Item.saveItems();
				break;
			default:
				System.out.println(ERROR);
			}
		} while (!finished);
	}

	public static void buyItem() {
		//Chooses the biometric option required. Can be changed by changing parameter
		bio.bioChooser(1);
		// Get Letter
		System.out.println("Please enter the Row Letter (A-G)");

		String letterString = input.nextLine().toUpperCase();
		System.out.println(letterString);

		Character letter = letterString.charAt(0);
		// Set letter to uppercase and then pass into validation method

		if (checkLetter(letter)) { // If letter is valid
			System.out.println("Please enter the Column number (1-5)");

			// Get Column Number with checkNumber method
			int number = checkNumber();
			// If checkNumber is invalid it returns 0, so you can't get through to the next
			// stage.
			if (number != 0) {

				// Make string to pass into the Item class's buy method
				String userInput = makeString(letter, number);
				// The Actual Buying starts here:
				
				//showPrice checks is the item available, then shows its price if it is
				if (Item.showPrice(userInput)) {
					
					//If the item is available, inputMoney is used for the user to enter coins to pay for it
					if (inputMoney(userInput)) {

						//Update CSV
						Item.updateCSV(userInput);
						//Using the code to get the product type then displaying a message depending on the product type
						System.out.println("**" + Products.chooseProduct(Item.getType(Item.readItems(userInput))) +"** \n");
						//creates a purchase log message about the current item and adds it tp the text file
						Messages.purchaseLog(userInput);
					}
				}
			}

			// userInput = input.nextInt();

		}
	}


	
	/**
	 * Thomas's method public static boolean checkLetter(String input)
	 * 
	 * This method validates whether the letter the user typed was between A-G or
	 * not.
	 * 
	 * 1: the user input is passed as a parameter in char form. 2: If the value is
	 * within the correct range, the method returns true. If not, the method returns
	 * false with an error message.
	 */

	public static boolean checkLetter(char letter) {
		Character minValue = 'A';
		Character maxValue = 'G';
		// convert first letter to char
		// Character letter = input.charAt(0);
		if (letter >= minValue && letter <= maxValue) {
			// System.out.println("Ok " + letter);
			return true;
		} else {
			System.out.println("\nSorry, your input is not acceptable. Please try again. \n\n");
			return false;

		}
	}

	/**
	 * public static boolean checkNumber(int input) { 
	 * 
	 * this method checks if the number the user typed is between the range 1-5. 1:
	 * The user input is passed as a parameter in int form. 2: If the number is
	 * smaller than one, OR larger than 5 an error message is returned. If the
	 * number is valid, the method returns true.
	 */

	public static int checkNumber() {

		try {
			//
			int number = input.nextInt();

			if (number < 1 || number > 5) {
				System.out.println("\nSorry, you typed the wrong number. Please try again. \n\n");
				input.nextLine(); // Clear Scanner
				return 0;

			} else {
				return number;
			}

		} catch (InputMismatchException ex) {
			System.out.println("Sorry, that input is not accepted. Please try again. \n\n");
			input.nextLine(); // Clear Scanner
			return 0;
		}

		/*
		 * int number = input.nextInt();
		 * 
		 * if (number < 1 || number > 5) { System.out.
		 * println("\nSorry, your typed the wrong number. Please try again. \n\n");
		 * return false; } else { return true; }
		 */

	}

	/**
	 * public static String makeString(char letter, int number) { Thomas's method
	 * This method takes the char and number the user typed and concatenates them
	 * into a string that can be used for other methods.
	 */

	public static String makeString(char letter, int number) {
		String userInput = "";
		userInput += letter;
		userInput += number;
		return userInput;
	}

	/**
	 * 
	 */

	public static boolean inputMoney(String userInput) {
		Currency pound = new Currency(0);
		double price = pound.getValue(Item.getPrice(Item.readItems(userInput)));

		while (price >= 0) {
			double number = isCoin();
			double change = number - price;

			if (number == 999) {
				input.nextLine(); // Clear Scanner
				System.out.println("Refunding...");
				return false;
			}

			price = price - number;

			if (price <= 0) {
				input.nextLine(); // Clear Scanner
				System.out.println("\n\nPurchase Complete!\n");
				if (price < 0) {
					System.out.println("You have " + Money.FormatMoney(change) + " change.\n");
				}
				return true;
			}

			System.out.println("You have " + Money.FormatMoney(price) + " left.\n\n");

		}

		input.nextLine(); // Clear Scanner
		return false;

	}

	/**
	 * public static double isCoin() { 
	 * 
	 * This method validates that the user is indeed entering a coin into the
	 * vending machine. 1: There is a try catch block to catch inputmismatch
	 * exceptions. For example, if the user incorrectly enters a string such as "a",
	 * an error message will be thrown. The method will return 0. 2: When the method
	 * returns 0, the "buy method" will allow this method to be called again so that
	 * the user has infinite attempts to enter valid coins. 3: If the price is not
	 * within the valid values (1.00, 0.50 etc), 0 is returned. 4: If the price is
	 * valid, it is returned.
	 */

	public static double isCoin() {

		try {
			//
			System.out.println("\nPlease enter your money below. \n" + "If you wish to cancel the purchase, enter 999");
			double price = input.nextDouble();

			if (price == 0.01 || price == 0.002 || price == 0.05 || price == 0.1 || price == 0.2 || price == 0.5
					|| price == 1 || price == 2 || price == 999) {
				return price;
			} else {
				System.out.println(
						"\n\nThat payment type is not accepted. This machine only accepts 1p, 2p, 5p, 10p, 20p, 50p, £1 or £2 coins.");
				input.nextLine(); // Clear Scanner
				return 0;
			}

		} catch (InputMismatchException ex) {
			System.out.println("\n\n\nThat input type is not accepted. Please try again.");
			input.nextLine(); // Clear Scanner

			return 0;
		}
	}
}
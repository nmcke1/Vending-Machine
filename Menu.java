package part01;

import java.util.Scanner;
/**
 * 
 * 
 * 
 * A Menu to use for the user to interact with the Vending Machine.
 */
public class Menu {
	
	private String items[];
	private String title;
	private Scanner input;

	public Menu(String title, String data[]) {
		this.title = title;
		this.items = data;
		this.input = new Scanner(System.in);
	}

	private void display() {
		System.out.println(title);
		for(int count=0;count<title.length();count++) {
			System.out.print("+");
		}
		System.out.println();
		for(int option=1; option<=items.length; option++) {
			System.out.println(option + ". " + items[option-1] );
		}
		System.out.println();
	}

	/**
	 * public int getUserChoice() is used to validate what the user has typed into the menu.
	 * This method checks if the user's input is in the range of possible choices.
	 * If not, the user will be given an error message and be asked to try again.
	 * If the user types in inappropriate data, such as a letter, they will get a different error message.
	 * If the menu's list of choices is somehow null, 0 will be returned.
	 */
	
	public int getUserChoice() {
		
		boolean choice = false;
		int userInput = 0;
		display();
		
		if ( items == null || items.length == 0 ) {
			return 0;
		}

		do {
			System.out.print("Enter Selection: ");
			try {
				userInput = input.nextInt();
				if ( (userInput > 0 && userInput <= items.length) ) {
					choice = true;
				}
				else {
					System.out.println("Incorrect Input. Please enter a value between 1 and " + items.length);
				}
			}
			catch(Exception ex) {
				System.out.println("ERROR. Input not accepted Please try again.");
				input.nextLine();
			}
		} while (!choice);
		
		return userInput;
	}
	
	public void displaybyTitle() {
		System.out.println(title);
		for(int count=0;count<title.length();count++) {
			System.out.print("+");
		}
		System.out.println();
		for(int option=1; option<=items.length; option++) {
			System.out.println(items[option-1] );
		}
		System.out.println();
	}
	
}
 
package part01;

public class Products implements IProduct {

	/*
	 * Niall's Code 
	 * A default useProduct method message
	 */

	@Override
	public String useProduct() {
		String message = "You take your product and use it.";
		return message;
	}

	/*
	 * Nialls Code
	 * Method which will call the useProduct method depending on the given product type
	 * if no product type found or an invalid one sent then default message is used
	 */
	public static String chooseProduct(String product) {
		switch (product) {
		case "drink":
			newProduct = new Drink();
			break;
		case "crisps":
			newProduct = new Crisps();
			break;
		case "choc":
			newProduct = new ChocBar();
			break;
		default:
			newProduct = new Products();
		}
		return newProduct.useProduct();
	}

	/*
	 * Niall's code
	 * Child Drink class
	 */
	public static class Drink extends Products implements IProduct {
		public String useProduct() {
			String message = "You open your drink and drink it";
			return message;
		}
	}
	
	/*
	 * Niall's code
	 * Child Chocbar class
	 */
	public static class ChocBar extends Products implements IProduct {
		public String useProduct() {
			String message = "You open your chocolate bar and take a bite";
			return message;
		}
	}

	/*
	 * Niall's code
	 * Child Crisps class
	 */
	public static class Crisps extends Products implements IProduct {
		public String useProduct() {
			String message = "You open your crisps, take one out and eat it";
			return message;
		}
	}

	//Uninstantiated Products object which is later instantiated to whatever child object is needed
	private static Products newProduct;
}

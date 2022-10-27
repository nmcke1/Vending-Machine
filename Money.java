package part01;

import java.text.DecimalFormat;

public class Money {

	public static String FormatMoney(double price) {
		Currency pound = new Currency(0);
		DecimalFormat twoPlaces = new DecimalFormat("0.00");
		String money = pound.getSymbol() + twoPlaces.format(pound.getValue(price));
		return money;
	}
	
}
package part01;


public class Currency {

	private static String[] symbolArr = new String[] {"£", "$", "€", "¥"};
	private static double[] doubleArray = new double[] { 1, 1.31, 1.20, 161.88}; 
	private int classPosition = 0;
	
	private void setClassPosition(int position) {
		this.classPosition = position;
	}
	
	private int getClassPosition() {
	return this.classPosition;
	}

	public Currency(int position) {
		setClassPosition(position);
	}	
	public double getValue(double inCost) {
		double out = inCost*doubleArray[getClassPosition()];
		return out;
	}	
	
	 public String getSymbol() {
			return symbolArr[getClassPosition()];
		}


}



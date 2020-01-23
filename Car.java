//Raj Mistry 500896324 CPS 209 Assignment 1

public class Car extends Vehicle implements Comparable<Car> {
	protected int model;
	protected int maxRange;
	protected double safetyRating;
	protected boolean AWD;
	protected double price;
	public static final int SEDAN = 0;
	public static final int SUV = 1;
	public static final int SPORTS = 2;
	public static final int MINIVAN = 3;
	
	//GET METHODS
	public int getmodel() {
		return model;
	}
	public int getmaxRange() {
		return maxRange;
	}
	public double getsafetyRating() {
		return safetyRating;
	}
	public boolean getAWD() {
		return AWD;
	}
	public double getprice() {
		return price;
	}
	public int getVIN() {
		int VIN = super.getVIN();
		return VIN;
	}

	
	//SET METHODS
	public void setmodel(int model1) {
		model = model1;
	}
	public void setmaxRange(int maxRange1) {
		maxRange = maxRange1;
	}
	public void setsafetyRating(double safetyRating1) {
		safetyRating = safetyRating1;
	}
	public void setAWD(boolean AWD1) {
		AWD = AWD1;
	}
	public void setprice(double price1) {
		price = price1;
	}
	//String color1, String mfr1, int power1, int numWheels1, String model1, int maxRange1, double safetyRating1, boolean AWD1, double price1
	
	//constructor method for a car
	public Car(String mfr1, String color1, int model1, int power1, double safetyRating1,int maxRange1,boolean AWD1,double price1) {
		super(mfr1, color1, power1, 4);
		model = model1;
		maxRange = maxRange1;
		safetyRating = safetyRating1;
		AWD = AWD1;
		price = price1;
	}
	
	public String display() { 
		//the model variable is an integer, 
		//the following code converts the integer to the string value associated with that integer for displaying purposes
		String printmodel = "";
		if (model == SEDAN) {
			printmodel = "SEDAN";
		}
		else if (model == SUV) {
			printmodel = "SUV";
		}
		else if (model == SPORTS) {
			printmodel = "SPORTS";
		}
		else if (model == MINIVAN) {
			printmodel = "MINIVAN";
		}
		//displaying the specs of a car
		return super.display() + "  "+ printmodel + "  " + price + "$  SF: " + safetyRating + "  RNG: " + maxRange + "  " ;
	}
	
	public boolean equals(Car other) { //checks to see if one car is equal to another car
		
		//I did this one differently than the assignment's instructions but it has the same function
		//originally sets the equal variable to true (in the super class), and then goes through all instance variables mentioned in the question
		//to check for their equality, if any of them aren't equal, set the variable to false.
		//return if it is equal or not.
		
		boolean equal = super.equals(other);
		
		if (this.getmodel()==(other.getmodel())) {
			
		}
		else {
			equal = false;
		}
		if (this.getAWD()!=other.getAWD()) {
			equal = false;
		}
		
		return equal;
	}
	public int compareTo(Car other) //the comparable compareTo method. Used for sorting price in the cardealership
	{ 
	    if (this.getprice() > other.getprice()) return 1;
	    if (this.getprice() < other.getprice()) return -1;
	    return 0;
	}

	
}
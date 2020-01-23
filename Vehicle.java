//Raj Mistry 500896324 CPS 209 Assignment 1


public class Vehicle {
	protected String color;
	protected String mfr;
	protected int power;
	protected int numWheels;
	public final static int ELECTRIC_MOTOR = 0;
	public final static int GAS_ENGINE = 1;
	protected int VIN;
	
	public Vehicle(String mfr1, String color1, int power1, int numWheels1) {
		VIN = 100 + (int)(Math.random() * ((499 - 100) + 1)); //random number between 100 and 499
		color = color1;
		mfr = mfr1;
		power = power1;
		numWheels = numWheels1;
	}
	
	//SET METHODS
	public void setcolor(String color1) {
		color = color1;
	}
	public void setmfr(String mfr1) {
		mfr = mfr1;
	}
	public void setpower(int power1) {
		power = power1;
	}
	public void setnumWheels(int numWheels1) {
		numWheels = numWheels1;
	}
	
	//GET METHODS
	public String getcolor() {
		return color;
	}
	public String getmfr() {
		return mfr;
	}
	public int getpower() {
		return power;
	}
	public int getnumWheels() {
		return numWheels;
	}
	public int getVIN() {
		return VIN;
	}
	
	public boolean equals(Vehicle other) { //1b
		//checks to see if the isntance variables are equal to each other and returns the result.
		//I did this a bit differently than the assignment said,
		//the way it works is by default they are equal, and then if any of the variables arent equal, it sets it to not equal.
		//this continues into the Car equals method
		boolean equal = true;
		if (this.getmfr().equals(other.getmfr())) {
		}
		else {
			equal = false;
		}
		if (this.getpower()!=other.getpower()) {
			equal = false;
		}
		if (this.getnumWheels()!=other.getnumWheels()) {
			equal = false;
		}
		
		return equal;
		
	}
	
	public String display() { //display the specs of a car.
		String displayString = " VIN: "+ this.getVIN()+" "+ this.getmfr()+"  "+this.getcolor();
		return displayString;
	}

}
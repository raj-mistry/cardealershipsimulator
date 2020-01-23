//Raj Mistry 500896324 CPS 209 Assignment 1


public class ElectricCar extends Car{
	protected int rechargeTime;
	protected String batteryType;
	
	//CONSTRUCTOR
	public ElectricCar(String mfr1, String color1, int model1, int power1, double safetyRating1,int maxRange1,boolean AWD1,double price1,int rechargeTime1, String batteryType1) {
		super(mfr1, color1, model1, power1, safetyRating1,maxRange1,AWD1,price1);
		rechargeTime = rechargeTime1;
		batteryType = batteryType1;
	}
	
	//GET METHODS
	
	public int getrechargeTime() {
		return rechargeTime;
	}
	public String getbatteryType() {
		return batteryType;
	}
	
	//SET METHODS
	
	public void setrechargeTime(int rechargeTime1) {
		rechargeTime = rechargeTime1;
	}
	public void setbatteryType(String batteryType1) {
		batteryType = batteryType1;
	}
	
	public String display() { //display the specs of a car
		String displayString = super.display()+" EL, BAT: "+getbatteryType()+" RCH: "+getrechargeTime();
		return displayString;
	}
	

}

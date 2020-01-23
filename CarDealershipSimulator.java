//Raj Mistry 500896324 CPS 209 Assignment 1

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CarDealershipSimulator 
{
	public static ArrayList<Car> importCars(String filename){ //this method is used to import a text file, and returns a list with the data
			ArrayList<Car> list = new ArrayList<>();// list of cars that i intend to return
			File inputFile = new File (filename); //get file
			Scanner in = null; //in will scan the entire file
			try {
				in = new Scanner (inputFile);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //scanner for file
			  //try and catch methods to catch errors in the importing of the file
			  while (in.hasNextLine()) { //will keep looping as long as theres a line of data
				  String newLine = in.nextLine(); //stores the current line of data
				  Scanner line = new Scanner(newLine); //scanner for line
				  String mfr = line.next(); //scans the first word (manufacturer)
				  String color = line.next(); //second word is colour
				  //input for model
				  int model = 0; 
				  //this block of code will check the next input to see which model it is, 
				  //and assigns an integer to this variable according to the model
				  //this is because, in my class, the model field is an integer.
				  
				  String modelinput = line.next();
				  if (modelinput.equals("SEDAN")) {
					  model = 0;
				  }
				  else if (modelinput.equals("SUV")) {
					  model = 1;
				  }
				  else if (modelinput.equals("SPORTS")) {
					  model = 2;
				  }
				  else if (modelinput.equals("MINIVAN")) {
					  model = 3;
				  }
				  
				  String powerinput = line.next();
				  //input for power
				  int power = 0;
				  if (powerinput.equals("GAS_ENGINE")) {
					  power = 1;
				  }
				  else if (powerinput.equals("ELECTRIC_MOTOR")) {
					  power = 0;
				  }
				  
				  //assign power variable an integer according to if the next word
				  //indicates the car is electric or gas.
				  
				  double safety = line.nextDouble(); //next word is a safety rating
				  
				  int range = line.nextInt(); //next word is the range
				  
				  //input for AWD
				  boolean AWD;
				  
				  if (line.next().equals("AWD")) {
					  AWD = true;
				  }
				  else {
					  AWD = false;
				  }
				  
				  //next word indicates if it is AWD or 2WD. if its "AWD", 
				  //set AWD variable to true, else make it false
				  
				  
				  double price = line.nextDouble(); //next word is the price variable
				  
				  Car c = null;
				  //CREATING THE CAR
				  
				  //if the car is gas powered, use this to construct the car
				  if (power == 1) {
					  c = new Car (mfr/*mfr*/, color/*color*/, model/*model*/, power/*power*/, safety/*safety*/, range/*range*/, AWD/*AWD*/, price/*price*/);
				  }
				  //otherwise, if its electric powered, get the remaining variables and use this to construct the electric car
				  else if (power == 0) {
					  int rechargeTime = line.nextInt();
					  String batteryType = "Lithium";
					  c = new ElectricCar (mfr/*mfr*/, color/*color*/, model/*model*/, power/*power*/, safety/*safety*/, range/*range*/, AWD/*AWD*/, price/*price*/, rechargeTime/*Rechargetime*/,batteryType/*BatteryType*/);
				  }
				  
				  list.add(c);
				  
				  //add the constructed car to the list
			  }
			  //once while loop is over, the list is complete, return the list.
			  return list;
	}
	public static void main(String[] args)
	{
	  // Create a CarDealership object
	  CarDealership dealership = new CarDealership(); //create empty dealership
	  ArrayList<Car> list = importCars("cars.txt");   //import the cars into a list
	  
	  //code I used to add the cars manually (not needed anymore and this includes 5/8 of the cars)
	  
	  //Car c1 = new Car ("Toyota"/*mfr*/, "blue"/*color*/, 0/*model*/,1/*power*/, 9.5/*safety*/, 500/*range*/, false/*AWD*/,25000/*price*/);
	  //Car c2 = new Car ("Honda"/*mfr*/, "red"/*color*/, 2/*model*/,1/*power*/, 9.2/*safety*/, 450/*range*/, false/*AWD*/,30000/*price*/);
	  //Car c3 = new Car ("Kia"/*mfr*/, "white"/*color*/, 3/*model*/,1/*power*/, 9.7/*safety*/, 550/*range*/, false/*AWD*/,20000/*price*/);
	  //Car c4 = new Car ("BMW"/*mfr*/, "black"/*color*/, 0/*model*/,1/*power*/, 9.6/*safety*/, 600/*range*/, true/*AWD*/,55000/*price*/);
	  //ElectricCar c5 = new ElectricCar ("Tesla"/*mfr*/, "red"/*color*/, 0/*model*/,0/*power*/, 9.1/*safety*/, 425/*range*/, true/*AWD*/,85000/*price*/, 30/*Rechargetime*/,"Lithium"/*BatteryType*/);
	  //list.add(c1);
	  //list.add(c2);
	  //list.add(c3);
	  //list.add(c4);
	  //list.add(c5); 
	  
	  
	  // See the cars file for car object details
	  // Add the car objects to the array list
      // The ADD command should hand this array list to CarDealership object via the addCars() method	  
	  
	  /*
	  Notes about the menu
	   * L = display inventory
	   * Q = Quit
	   * BUY = Buy a car (set reference variable to this car, remove it from list, what if i buy out of bounds?)
	   * RET = Return the car in ref variable to the dealership (set the ref var to null/ What if i return null?)
	   * ADD = Add an array list to the dealership
	   * SPR = sort price
	   * SSR = sort safetyrating
	   * SMR = sort max range
	   * FPR = filterprice (Also input 2 double values for min/max price)
	   * FEL = filterelectric
	   * FAW = filterAWD
	   * FCL = filter clear
	   *Make sure your code handles commands that are not recognized or the null string or a null reference etc. 
	   
	   */
	  
	  
	  //Car dealership menu
	  System.out.println("Welcome to CarDealership Simulator");
	  int refCar = 0; //this holds the bought car until it is returned
	  Scanner userInput = new Scanner(System.in); //scanner for user input
	  String command = "";
	  do{ //this do while keeps looping until the user enters "Q" to quit
		  try {
		  String commandLine = userInput.nextLine(); //commandLine holds the entire command the user enters
		  Scanner input = new Scanner(commandLine); //input scans the commandLine
		  command = input.next(); //this variable holds the first word in the command
		switch (command) { //this switch statement will run code based on what the command is.
			  case "BUY":
				  try {
					  if (!input.hasNext()) {
						  throw new NoSuchElementException("No VIN Given");
					  }
				  int VIN = input.nextInt();
				  String transaction = dealership.buyCar(VIN);
				  System.out.println(transaction); 
				  refCar = dealership.getTransactionIDfromVIN(VIN);
				  }catch(Exception e) {
					  System.out.println(e.getMessage());
				  }
				  //BUY will buy a car and display the car bought after the command is executed
				  break;
			  case "RET":
				  if (refCar==0) {
					  System.out.println("No car to return");
					  //print this if there is no car to return
				  }
				  else { //if there is a car to return
					  System.out.println(dealership.returnCar(refCar)); //return the car
					  refCar = 0; //set the variable holding the bought car to null since it is returned to the dealership
				  }
				  break;
			  case "ADD":
				  dealership = new CarDealership();
				  dealership.addCars(list);
				  refCar = 0;
				  //the way the ADD command works in my simulator is it loads a new set of cars based on the list
				  //as a result, all filters are also reset and the car reference for a previously bought car is removed.
				  break;
			  case "SPR":
				  dealership.sortByPrice(); //sorts by price
				  break;
			  case "SSR":
				  dealership.sortBySafetyRating(); //sorts by safety rating
				  break;
			  case "SMR":
				  dealership.sortByMaxRange(); //sorts by max range
				  break;
			  case "FPR": 
				  //if the command is FPR, the code will then look for more words to indicate the minimum price 
				  //and maximum price to filter the price by.
				  dealership.filterByPrice(input.nextDouble(),input.nextDouble()); 
				  break;
			  case "FEL":
				  dealership.filterByElectric(); //filter by electric
				  break;
			  case "FAW":
				  dealership.filterByAWD(); //filter by All Wheel Drive
				  break;
			  case "FCL":
				  dealership.FiltersClear(); // turns off all filters
				  break;
			  case "L":
				  dealership.displayInventory(); //displays the dealership inventory
				  break;
			  case "SALES":
				  
				  if (input.hasNext()) {
					  String nextCommand = input.next();
					  switch(nextCommand) {
					  case "TEAM":
						  dealership.displaySalesTeam();
						  break;
					  case "TOPSP":
						  dealership.displayTopEmployee();
						  break;
					  case "STATS":
						  dealership.displayDealershipStats();
						  break;
					  default:
						  try {
						  int month = Integer.parseInt(nextCommand);
						  if (month<0||month>11) {
							  throw new IllegalArgumentException("Not a valid month (Must be between 0-11)");
						  }
						  dealership.displaySalesByMonth(month);
						  }catch(Exception e) {
							  System.out.println(e.getMessage());
						  }
						  
					  }
				  }
				  else {
					  dealership.printAllTransactions();
				  }
				  break;
			  case "Q": //gives the user a final message before the program ends
				  System.out.println("Thank you for using CarDealership Simulator ");
				  return;
				  default:
					  System.out.println("Not a command"); 
					  //if the input does not match any of the commands, the program will let the user know
		  }
		  }
		  catch(Exception e) { //For every other unchecked exception.
			  //the try and catch in my code surrounds my entire menu. 
			  //If any of the input is invalid, the program will prompt the user to re-enter the command
			  System.out.println("Invalid attempt, please try again.");
		  }
		  } while(!command.equals("Q")); //loops the simulator until the user says to quit.
}
}

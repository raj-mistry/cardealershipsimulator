//Raj Mistry 500896324 CPS 209 Assignment 1

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class CarDealership {
	protected ArrayList<Car> cars;
	protected boolean filterElectric, filterAWD, filterPrice;
	protected double maxPrice, minPrice;
	protected SalesTeam salesTeam;
	protected AccountingSystem accountingSystem;
	
	//CONSTRUCTOR FOR THE DEALERSHIP
	public CarDealership() { //constructor will assign an empty list of cars to the dealership and all filters are off
		ArrayList<Car> cars1 = new ArrayList<Car>();
		cars = cars1;
		filterElectric = false;
		filterAWD = false;
		filterPrice = false;
		salesTeam = new SalesTeam();
		accountingSystem = new AccountingSystem();
	}
	
	
	public int dealershipsize() { //a method I created to get the amount of cars in the dealership
		return (cars.size()-1);
	}
	
	public void displaySalesTeam() {
		System.out.println(salesTeam.getAllEmployees());
		
	}
	
	//SALES STATS METHODS
	
	public double totalSales() { //returns total $ for the year
		ArrayList<Transaction> transactions = accountingSystem.getTransactions();
		double totalSales = 0;
		for (Transaction transaction :transactions) {
			
			if (transaction.getType()==transaction.buy) {
				totalSales = totalSales+transaction.getSalePrice();
			}
			else if (transaction.getType()==transaction.ret) {
				totalSales = totalSales - transaction.getSalePrice();
			}
		}
		return totalSales;
	}
	
	public int totalCarsSold(){ //returns total cars sold
		ArrayList<Transaction> transactions = accountingSystem.getTransactions();
		int totalCarsSold = 0;
		for (Transaction transaction :transactions) {
			if (transaction.getType()==transaction.buy) {
				totalCarsSold = totalCarsSold + 1;
			}
			//else if (transaction.getType()==transaction.ret) {
			//	totalCarsSold = totalCarsSold - 1;
			//}
		}
		return totalCarsSold;
	}
	public void displayMonthWithMostSales() { 
		//will call the method in accounting system to display the month(s) with the most sales
		accountingSystem.mostCarsSoldByMonth();
	}
	
	public int totalCarReturns() {
		//counts the number of transactions that are returned cars and returns that value.
		ArrayList<Transaction> transactions = accountingSystem.getTransactions();
		int totalReturns = 0;
		for (Transaction transaction :transactions) {
			if (transaction.getType()==transaction.ret) {
				totalReturns = totalReturns + 1;
			}
		}
		return totalReturns;
	}
	
	public void displayTopEmployee() { //this is for the return method
		//gets number of employees
		String employees = salesTeam.getAllEmployees();
		String[] names=employees.split(" "); //array of all employee names
		int[] employeeTransactions = new int[names.length-1]; //array of integers representing each employees # of transactions
		int mostTransactions = 0;
		for (int c = 0; c<employeeTransactions.length;c=c+1) { //gets number of transaction by each employee by their name
			employeeTransactions[c] = accountingSystem.amountofTransactionsbyEmployee(names[c]);
			if (employeeTransactions[c]>mostTransactions) {
				mostTransactions = employeeTransactions[c];
			}
		}
		//now we have most transactions by an employee, print all employees with that number of transactions
		for (int c = 0; c<employeeTransactions.length;c=c+1) {
			if (employeeTransactions[c]==mostTransactions) {
				System.out.println("Employee: "+names[c]+"  Number of Transactions: "+employeeTransactions[c]);
			}
		}
		
	}
	
	public void displayDealershipStats() { 
		//This method will display ALL dealership stats calculated using methods in this class 
		  double totalSales = totalSales();
		  double averageSalesPerMonth = (Math.round((100*(totalSales/12))))/100;
		  int totalCarsSold = totalCarsSold();
		  int totalCarReturns = totalCarReturns();
		  System.out.println("Total Sales: $"+totalSales+"\n");//$
		  System.out.println("Total Cars Sold: "+totalCarsSold+"\n");//#
		  System.out.println("Average Sales: $"+averageSalesPerMonth+"\n");//$
		  displayMonthWithMostSales();//#
		  System.out.println("Total Cars Returned: "+totalCarReturns);
	}
	
	public void displaySalesByMonth(int month) { //this method will display all transactions corresponding to a particular month (Used in SALES m)
		accountingSystem.printTransactionsbyMonth(month);
		
	}

	public void addCars(ArrayList<Car> newCars) { //add car method adds a list of cars to the current list of cars in the dealership
		cars.addAll(newCars);
	}
	
	public String buyCar(int VIN){ // removes a car of given index from the list, and return the bought car
		if (cars.size()==0) { //throwing exceptions according to invalid input
			throw new NullPointerException("No cars to buy");
		}
		if (VIN>499||VIN<100) {
			throw new IllegalArgumentException("Not a valid VIN (Must be between 100-499)");
		}
		Car boughtCar = null;
		String randomSalesPerson = "";
		Calendar date = null;
		int month = 0;
		int day = 0;
		int year = 2019;
		String transactionString = "";
		for (int c = 0 ; c<cars.size();c=c+1) {
			if (cars.get(c).getVIN()==VIN) { 
				//checks to see if VIN inputted matches the car's VIN in the list
				//If so, itll remove the car from the list and create a new transaction object in accounting system
				boughtCar = cars.remove(c);
				randomSalesPerson = salesTeam.getSalesPerson();
				month = (int)(Math.random()*11)+1;
				day = (int)(Math.random()*29)+1;
				date = new GregorianCalendar(2019, month, day);
				transactionString = accountingSystem.add(date, boughtCar, randomSalesPerson, "BUY", boughtCar.getprice());
				
			}
		}
		
		if (boughtCar==null) { //if the VIN was never found, the VIN doesnt exist in the list of cars, throw an exception.
			throw new IllegalArgumentException("VIN does not exist");
		}
		return transactionString; //if everything was successful, the car is bought, return the transaction string
		}

		/*
		Car boughtCar = null;
		if (index<cars.size()) {
			boughtCar = cars.remove(index);
		}
		return boughtCar;
		*/
	
	public void printAllTransactions() { //prints all transactions in the year 2019 using a method created in accounting system.
		//Calendar calendar = new GregorianCalendar();
		//int year = calendar.get(Calendar.YEAR);
		int year = 2019;
		accountingSystem.printTransactionsByYear(year);
	}
	
	public int getTransactionIDfromVIN(int VIN) { //A method I created to retrieve a transaction ID from a VIN, used for RETURN.
		ArrayList<Transaction> transactions = accountingSystem.getTransactions();
		int transactionID = 0;
		for (int c = 0 ; c<transactions.size();c=c+1) {
			if (transactions.get(c).getCar().getVIN()==VIN) {
				transactionID = transactions.get(c).getTransactionID();
				
			}
		}
		return transactionID;
	}
	
	public String returnCar(int transaction) { 
		//will add the car from the transaction back into the list of cars
		//will also create a new transaction for return, with a date after the buy date in the same month
		Transaction rt = accountingSystem.getTransaction(transaction);
		
		Calendar newdate = rt.getDate(); //creation of the new date in the same month after the buy date
		newdate = new GregorianCalendar(newdate.get(Calendar.YEAR), newdate.get(Calendar.MONTH), newdate.get(Calendar.DAY_OF_MONTH)+(1+(int)((Math.random()*(29-newdate.get(Calendar.DAY_OF_MONTH))))));
		String transactionString = accountingSystem.add(newdate, rt.getCar(), rt.getSalesPerson(), "RETURN", rt.getSalePrice());// creation of the new transaction object
		cars.add(rt.getCar());//returning the bought car to the list of car objects.
		
		return transactionString;
		/*
		if (car==null) { OLD METHOD Not part of A2
		}
		else {
			cars.add(car);
			*/
		}
	//Filters
	public void displayInventory() { //lists cars and their specs based on filters 
		ArrayList<Car> filtered = new ArrayList<Car>(cars); //creates a new arraylist of cars using the list of cars in the method
		for (int c = 0; c<filtered.size();c=c+1) { //loops through cars in the array list
			Car currentcar = filtered.get(c); //get the car of given index to examine.
			boolean hide = false; //by default show the car
			if (filterElectric) { // this will check to see if the current car is gas, if it is, it will hide it
				if (currentcar.getpower()!=Vehicle.ELECTRIC_MOTOR) { 
					hide = true;
				}
			}
			if (filterAWD) { //this will check if the current car is all wheel drive, if it will hide it
				if (currentcar.getAWD()==false) {
					hide = true;
				}
			}
			if (filterPrice) { //this will check if the current car is within the boundaries of min and max price, if it isnt, it will hide it
				if ((currentcar.getprice()<minPrice)||(currentcar.getprice()>maxPrice)) {
					hide = true;
				}
			}
			if (hide==false) { //if the car isn't supposed to be hidden, display the car.
				System.out.println(c +" "+ currentcar.display());
			}
		}
	}
	
	public void filterByElectric() { //sets the electric filter to true
		filterElectric = true;
	}
	
	public void filterByAWD() { //sets the AWD filter to true
		filterAWD = true;
	}
	
	public void filterByPrice(double minPrice, double maxPrice) { //sets the price filter to true, and sets the minimum and maximum price
		filterPrice = true;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	
	public void FiltersClear() { //removes all the filters that are currently applied.
		filterElectric = false;
		filterAWD = false;
		filterPrice = false;
	}
	
	//Sort methods
	
	public void sortByPrice() { //sorts price using the comparable interface.
		Collections.sort(cars);
	}
	
	
	
	public void sortBySafetyRating() { //sorts by safety rating using a comparator
		Comparator<Car> safetyRatingComparator = new Comparator<Car>() { 
			public int compare(Car o1, Car o2) {
				int compare = 0;
				if (o1.getsafetyRating()>o2.getsafetyRating()) {
					compare = -1;
				}
				else if(o1.getsafetyRating()<o2.getsafetyRating()) {
					compare = 1;
				}
				else {
					compare = 0;
				}
				
				return compare;
			}
		};
		Collections.sort(cars,safetyRatingComparator);
	}
	
	public void sortByMaxRange() { //sorts by range using a comparator
		Comparator<Car> maxRangeComparator = new Comparator<Car>() { 
			public int compare(Car o1, Car o2) {
				int compare = 0;
				if (o1.getmaxRange()>o2.getmaxRange()) {
					compare = -1;
				}
				else if(o1.getmaxRange()<o2.getmaxRange()) {
					compare = 1;
				}
				else {
					compare = 0;
				}
				
				return compare;
			}
		};
		Collections.sort(cars,maxRangeComparator);
	}
	

}

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class AccountingSystem {
	protected ArrayList<Transaction> transactions;
	
	public AccountingSystem() { //The constructor for the accounting system will create a new list of transactions.
		transactions = new ArrayList<Transaction>();
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) { //setter for transactions
		this.transactions = transactions;
	}
	
	public int amountofTransactionsbyEmployee(String name) { //this method returns the amount of transactions made by a single employee
		int amountofTransactions = 0;
		Iterator i = transactions.iterator();
		while (i.hasNext()) {
			Transaction currentTransaction = (Transaction) i.next();
			if (currentTransaction.getSalesPerson().equals(name)) {
				amountofTransactions=amountofTransactions+1;
			}
		}
		return amountofTransactions;
	}
	
	public void printTransactionsbyMonth(int month) { //this method displays all transactions within a particular month
		for (Transaction transaction : transactions) {
			if (transaction.getDate().get(Calendar.MONTH)==month) {
				System.out.println(transaction.display());
			}
		}
	}
	
	public void mostCarsSoldByMonth() {//this method displays the months with the most cars sold
		int[] sales = new int[12];
		int mostCarsSold = 0;

		//this portion creates an array of integers which represent the number of cars sold. 
		//the months correspond with the index of the array 
		//(ex. array[0] is number of cars sold in january)
		for (int month = 0; month<sales.length; month=month+1) { 
			int totalCarsSoldForCurrentMonth = 0;
			for (Transaction transaction : transactions) {
				if (transaction.getDate().get(Calendar.MONTH)==month) {
					if (transaction.getType() == transaction.buy) {
						totalCarsSoldForCurrentMonth = totalCarsSoldForCurrentMonth +1;
					}
					/*
					else if (transaction.getType() == transaction.ret) { //This is just extra code I removed to change how this method works
						totalCarsSoldForCurrentMonth = totalCarsSoldForCurrentMonth -1;
					}
					*/ //Put this back in if the total sales changes if a car is returned.
				}
			}
			sales[month] = totalCarsSoldForCurrentMonth; //putting number of cars sold into index
			
			if (totalCarsSoldForCurrentMonth>mostCarsSold) { //this portion will store the most cars sold in a single month
				mostCarsSold = totalCarsSoldForCurrentMonth;
			}
		}
		
		//Now we have total cars sold per month, and most cars sold, find highest month(s)
		System.out.print("Highest Sales Month(s) by Number of Cars Sold:");
		for (int month = 0; month<sales.length; month = month+1) { //this portion will now use the most cars sold to display all month(s) with the highest number of cars sold.
			if (sales[month]==mostCarsSold) {
				System.out.print(" "+getMonthFromInt(month)+": "+sales[month]+" ");
			}
		}
		System.out.println("\n");
		
	}
	
	public String getMonthFromInt(int month) { //will return the name of the month that corresponds to an integer. (ex. integer = 0, then return "January")
		String returnMonth = "";
		String[] months = {"Janurary", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		return months[month];
	}


	

	
	public String add(Calendar date, Car car, String salesPerson, String type, double salePrice) { 
		//an add method that creates a new transaction object and returns the String representation of the transaction
		int transactionID = (int)(Math.random()*(99-1))+1;
		Transaction newTransaction = new Transaction(date,car,salesPerson,type,salePrice);
		transactions.add(newTransaction);
		String returnString = newTransaction.display();
		return returnString;
		
	}
	
	public void printTransactionsByYear(int year) { //this method will display all transactions from a particular year (Used for SALES command)
		for (Transaction transaction : transactions) {
			if (transaction.getDate().get(Calendar.YEAR)==year) {
				System.out.println(transaction.display());
			}
		}
	}
	public Transaction getTransaction(int id) { //This method will return a transaction that matches the ID provided as a parameter (used for RETURN)
		Transaction currentTransaction = null;
		Transaction returnTransaction = null;
		for (int c = 0; c<transactions.size(); c=c+1) {
			currentTransaction = transactions.get(c);
			if (currentTransaction.getTransactionID()==id) {
				returnTransaction = currentTransaction;
			}
		}
		
		return returnTransaction;
	}
}

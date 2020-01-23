import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction {
	protected int transactionID;
	protected Calendar date;
	protected Car car;
	protected String salesPerson;
	protected String type;
	public final String buy = "BUY";
	public final String ret = "RETURN";
	protected double salePrice;
	
	
	public Transaction(Calendar date, Car car, String salesPerson, String type, double salePrice) {
		this.date = date;
		this.car = car;
		this.salesPerson = salesPerson;
		this.type = type;
		this.salePrice = salePrice;
		this.transactionID = 1+(int)(Math.random()*98);
		
	}
	
	public String display() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
		String dateFormatted = sdf.format(date.getTime());
		String displayString = "ID: "+transactionID+" "+dateFormatted+" "+type+" SalesPerson: "+salesPerson+" Car:"+car.display();
		return displayString;
	}
	
	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public String getBuy() {
		return buy;
	}

	public String getRet() {
		return ret;
	}
	

}

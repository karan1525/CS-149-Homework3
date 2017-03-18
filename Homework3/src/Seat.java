
public class Seat {
	
	private boolean isSold;
	private String customerName;
	 
	public Seat(String customerName){
		this.customerName = customerName;
		isSold = false;
	}
	
	public boolean isSold(){
		return this.isSold;
	}
	
	public void makeSold(){
		this.isSold = true;
	}
	
	public void setCustomerName(String name){
		this.customerName = name;
	}
	
	public String getCustomerName(){
		return this.customerName;
	}
	
}

import java.util.LinkedList;
import java.util.Queue;

public class LSeller extends Thread{
	private Queue<Customer> customerLine;
	private int timeToComplete;
	private boolean isOpen;
	private int ticketsSold;
	
	public LSeller(){
		ticketsSold =0;
		customerLine = new LinkedList();
		isOpen = true;

	}
	
	public void addCustomerToLine(){
	}
	
	public void helpCustomer(){
		
	}
	
	public void startSelling(){
		
	}
	


}

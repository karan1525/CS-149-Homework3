import java.util.LinkedList;
import java.util.Queue;

public class HSeller extends Thread{
	private Queue<Customer> customerLine;
	private int timeToCompleteSale;
	private boolean isOpen;
	private int ticketsSold;
	
	public HSeller(){
		ticketsSold =0;
		customerLine = new LinkedList();
		isOpen = true;
	}
	
	public void startSelling(){
		
	}
	
}

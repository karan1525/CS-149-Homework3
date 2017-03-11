import java.util.LinkedList;
import java.util.Queue;

public class MSeller extends Thread{
	private Queue<Customer> customerLine;
	private int timeToCompleteSale;
	private boolean isOpen;
	private int ticketsSold;

	public MSeller(){
		ticketsSold =0;
		customerLine = new LinkedList();
		isOpen = true;
	}
	

	public void startSelling(){
		
	}
}

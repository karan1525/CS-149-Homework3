import java.util.LinkedList;
import java.util.Queue;

public class MSeller extends Thread{
	private Queue<Customer> customerLine;
	private int timeToCompleteSale;
	private boolean isOpen;
	private int ticketsSold;
	private int numTurnedAway;

	public MSeller(){
		ticketsSold =0;
		customerLine = new LinkedList();
		isOpen = true;
	}
	

	public void startSelling(){
		System.out.println("MBOOTH");
	}
}

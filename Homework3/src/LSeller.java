import java.util.LinkedList;
import java.util.Queue;

public class LSeller extends Thread{
	private Queue<Customer> customerLine;
	private int timeToCompleteSale;
	private boolean isOpen;
	private int ticketsSold;
	private int numTurnedAway;
	
	public LSeller(){
		ticketsSold =0;
		customerLine = new LinkedList();
		isOpen = true;

	}
	
	
	public void startSelling(){
		System.out.println("LBOOTH");
	}
	


}

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class LSeller extends Thread {
	private Queue<Customer> customerLine;
	private int timeToCompleteSale;
	private boolean isOpen;
	private int ticketsSold;
	private int numTurnedAway;
	private Theater theater;
	private String sellerID;

	public LSeller(int numOfCustomers, Theater t, int ID) {
		ticketsSold = 0;
		isOpen = true;
		sellerID = "L" + String.valueOf(ID);
		this.theater = t;
		
		

		// Each seller can expect N customers to arrive at random times
		Comparator<Customer> comp = new ComparatorByArrivalTime();
		customerLine = new PriorityQueue<Customer>(numOfCustomers, comp);
		
		Random random = new Random();
		//1. Add based on arrival time first
		for (int i = 0; i < numOfCustomers; i++) {
			Customer c = new Customer(random.nextInt(60));
			customerLine.add(c);
		}
		//2. Then name the customer based on the arrival time
		int index = 1;
		for(Customer c : customerLine){
			if(numOfCustomers < 9) c.setName(sellerID + "0" + String.valueOf(index));
			else c.setName(sellerID + String.valueOf((index - 1) + numOfCustomers)); //for 10 or 15
			index++;
		}
	}
	
	public void startSelling() {
		System.out.println("LBOOTH");
		Random random = new Random();
		int completionTime = 0;
		
		for(Customer c :customerLine){
			theater.sellSeat(c, sellerID);
		}
		
		//I don't know how to get the time
		
//		while (getTime() >= 0 && getTime() < 60 && getTime() == completiontime ) {
//			System.out.println("started");
//			if (!theater.isFull()) {
//				if (customerLine.peek().getArrivalTime() == getTime()) {
//					Customer c = customerLine.remove();
//					theater.sellSeat(c, sellerID);
//					completiontime = getTime() + (random.nextInt(4) + 3);
//				}
//			}
//		}

	}

	public int getTicketsSold() {
		return ticketsSold;
	}

	public int getTimeToCompleteSale() {
		return timeToCompleteSale;
	}
	public void run(){
		startSelling();
	}
}

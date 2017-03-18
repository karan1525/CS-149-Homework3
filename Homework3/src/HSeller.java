import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class HSeller extends Thread {
	private Queue<Customer> customerLine;
	private int timeToCompleteSale;
	private boolean isOpen;
	private int ticketsSold;
	private int numTurnedAway;
	private String sellerID;
	private Theater theater;

	public HSeller(int numOfCustomers, Theater t, int ID) {
		ticketsSold = 0;
		sellerID = "H" + String.valueOf(ID);
		this.theater = t;
		isOpen = true;

		// Each seller can expect N customers to arrive at random times
		Comparator<Customer> comp = new ComparatorByArrivalTime();
		customerLine = new PriorityQueue<Customer>(numOfCustomers, comp);

		Random random = new Random();
		// 1. Add based on arrival time first
		for (int i = 0; i < numOfCustomers; i++) {
			Customer c = new Customer(random.nextInt(60));
			customerLine.add(c);
		}
		// 2. Then name the customer based on the arrival time
		int index = 1;
		for (Customer c : customerLine) {
			if (numOfCustomers < 9)
				c.setName(sellerID + "0" + String.valueOf(index));
			else
				c.setName(sellerID + String.valueOf(index)); // for 10 or 15
			index++;
		}
	}

	public void startSelling() {
		System.out.println("HBOOTH");
		//Not done - testing
		for(Customer c :customerLine){
			theater.sellSeat(c, sellerID);
		}
	}

	public int getTicketsSold() {
		return ticketsSold;
	}

	public int getTimeToCompleteSale() {
		return timeToCompleteSale;
	}

}

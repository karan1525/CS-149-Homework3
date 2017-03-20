import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class LSeller extends Thread {
	private ArrayList<Customer> customerLine;
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
		customerLine = new ArrayList<Customer>();

		Random random = new Random();
		// 1. Add based on arrival time first
		for (int i = 0; i < numOfCustomers; i++) {
			Customer c = new Customer(random.nextInt(60));
			customerLine.add(c);
		}
		Collections.sort(customerLine, comp);
		System.out.println("L" + ID + " "+ customerLine.toString());
		//2. Then name the customer based on the arrival time
		int index = 1;
		for(Customer c : customerLine){
			if(numOfCustomers < 9) c.setName(sellerID + "0" + String.valueOf(index));
			else c.setName(sellerID + String.valueOf((index - 1) + numOfCustomers)); //for 10 or 15
			index++;
		}
	}
	
	
	@Override
	public void run() {
		System.out.println("LBOOTH");
		
		Random random = new Random();
		int completionTime = 0;
		
		for(Customer c :customerLine){
			try {
				theater.sellSeat(c, sellerID);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

}

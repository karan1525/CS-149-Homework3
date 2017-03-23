import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MSeller extends Thread {
	private ArrayList<Customer> customerLine;
	private int timeToCompleteSale;
	private boolean isOpen;
	private int ticketsSold;
	private int numTurnedAway;
	private String sellerID;
	private Theater theater;
	private final int TIME = 60;

	public MSeller(int numOfCustomers, Theater t, int ID) {
		ticketsSold = 0;
		isOpen = true;
		sellerID = "M" + String.valueOf(ID);
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
		System.out.println("M" + ID + " " + customerLine.toString());

		// 2. Then name the customer based on the arrival time
		int index = 1;
		for (Customer c : customerLine) {
			if (index <= 9)
				c.setName(sellerID + "0" + String.valueOf(index));
			else
				c.setName(sellerID + String.valueOf(index)); // for 10 or 15
			index++;
		}
	}

	@Override
	public void run() {
		int currentCustomer = 0;
		int i = 0;
		while (i < TIME) {
			if (customerLine.get(currentCustomer).getArrivalTime() <= i) { // checks
																			// if
																			// customer
																			// arrival
																			// time
																			// is
																			// equal
																			// to
																			// time
				Random timeToProcess = new Random();
				try {
					theater.sellSeat(customerLine.get(currentCustomer), sellerID);
					currentCustomer++;
					i += timeToProcess.nextInt(4) + 1;

					if (currentCustomer == customerLine.size()) {
						break;
					}
					while (customerLine.get(currentCustomer).getArrivalTime() == i
							&& currentCustomer < customerLine.size() - 1) { // checks
																			// for
																			// other
																			// customers
																			// with
																			// same
																			// arrival
																			// time
						
						i += timeToProcess.nextInt(4) + 1;
						theater.sellSeat(customerLine.get(currentCustomer), sellerID);
						currentCustomer++;
						
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				i++;
			}

		}
		if (customerLine.size() - currentCustomer - 1 > 0) {
			numTurnedAway = customerLine.size() - currentCustomer - 1;
		}
	}

	public int getTicketsSold() {
		return ticketsSold;
	}

	public int getTimeToCompleteSale() {
		return timeToCompleteSale;
	}
	public int getNumTurnedAway(){
		return numTurnedAway;
		
	}
	public String getSellerID(){
		return sellerID;
	}

}

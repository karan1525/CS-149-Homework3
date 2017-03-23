import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LSeller extends Thread {
	private ArrayList<Customer> customerLine;
	private int timeToCompleteSale;
	private int ticketsSold;
	private int numTurnedAway;
	private Theater theater;
	private String sellerID;
	private int TIME = 60;

	public LSeller(int numOfCustomers, Theater t, int ID) {
		ticketsSold = 0;
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
		System.out.println("L" + ID + " " + customerLine.toString());
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
					int tTime = timeToProcess.nextInt(7) + 4;
					i += tTime;
					TimeUnit.SECONDS.sleep(tTime);
					
					theater.sellSeat(customerLine.get(currentCustomer), sellerID);
					currentCustomer++;

					

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
						int tTime2 = timeToProcess.nextInt(7) + 4;
						i += tTime2;
						TimeUnit.SECONDS.sleep(tTime2);
						theater.sellSeat(customerLine.get(currentCustomer), sellerID);
						currentCustomer++;
					}

				} catch (InterruptedException e) {
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

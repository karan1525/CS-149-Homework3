import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class Main implements Runnable {

	Thread runnable;
	private final int TIME = 60;

	public Main() {
		this.runnable = new Thread(this);
		System.out.println("START");
		runnable.start();
		try {
			runnable.join();
			System.out.println("DONE");
		} catch (InterruptedException e) {
		}
	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void run() {
		int numOfCustomers = 0;

		Scanner in = new Scanner(System.in);
		System.out.println("How many customers?: "); // get the number of
														// customers
		try { // read the input & create customers & put it into queue
			numOfCustomers = in.nextInt();
			in.close();
		} catch (Exception e) {
			System.out.println("Please enter the number of customers");
			System.exit(0);
		}

		Theater theater = new Theater(); // created theater
		// Seller for high-priced tickets
		HSeller hBooth = new HSeller(numOfCustomers, theater, 0);// create all
																	// threads
																	// and run
																	// them here

		// Sellers for medium-priced tickets
		MSeller m1Booth = new MSeller(numOfCustomers, theater, 1);
		MSeller m2Booth = new MSeller(numOfCustomers, theater, 2);
		MSeller m3Booth = new MSeller(numOfCustomers, theater, 3);
		MSeller m4Booth = new MSeller(numOfCustomers, theater, 4);

		// Sellers for low-priced tickets
		LSeller l1Booth = new LSeller(numOfCustomers, theater, 1);
		LSeller l2Booth = new LSeller(numOfCustomers, theater, 2);
		LSeller l3Booth = new LSeller(numOfCustomers, theater, 3);
		LSeller l4Booth = new LSeller(numOfCustomers, theater, 4);
		LSeller l5Booth = new LSeller(numOfCustomers, theater, 5);
		LSeller l6Booth = new LSeller(numOfCustomers, theater, 6);

		MSeller[] sellerM = { m1Booth, m2Booth, m3Booth, m4Booth };
		LSeller[] sellerL = { l1Booth, l2Booth, l3Booth, l4Booth, l5Booth, l6Booth };

		hBooth.start();
		m1Booth.start();
		m2Booth.start();
		m3Booth.start();
		m4Booth.start();
		l1Booth.start();
		l2Booth.start();
		l3Booth.start();
		l4Booth.start();
		l5Booth.start();
		l6Booth.start();

		try {
			hBooth.join();
			m1Booth.join();
			m2Booth.join();
			m3Booth.join();
			m4Booth.join();
			l1Booth.join();
			l2Booth.join();
			l3Booth.join();
			l4Booth.join();
			l5Booth.join();
			l6Booth.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("THEATER");// prints current theater
		theater.printTheater();
		System.out.println("The theather is full: " + theater.isFull());

		if (hBooth.getNumTurnedAway() != 0) { //When there are number of people who got turned away
			System.out.println(
					hBooth.getSellerID() + "\nNumber of tickets sold: " + (numOfCustomers - hBooth.getNumTurnedAway()) + "\nNumber of people got turned away: " + hBooth.getNumTurnedAway());
		}
		else{//When there are no people who got turned away
			System.out.println(
					hBooth.getSellerID() + "\nNumber of tickets sold: " + (numOfCustomers - hBooth.getNumTurnedAway()));
		}
		
		
		int index = 0;
		while (index < 4) {
			if (sellerM[index].getNumTurnedAway() != 0) { //When there are number of people who got turned away
				System.out.println(
						sellerM[index].getSellerID() + "\nNumber of tickets sold: " + (numOfCustomers - sellerM[index].getNumTurnedAway()) + "\nNumber of people got turned away: " + sellerM[index].getNumTurnedAway());
			}
			else{//When there are no people who got turned away
				System.out.println(
						sellerM[index].getSellerID() + "\nNumber of tickets sold: " + (numOfCustomers - sellerM[index].getNumTurnedAway()));
			}
			index++;
		}
		index = 0;
		while (index < 6) {
			if (sellerL[index].getNumTurnedAway() != 0) {
				System.out.println(
						sellerL[index].getSellerID() + "\nNumber of tickets sold: " + (numOfCustomers - sellerL[index].getNumTurnedAway()) +"\nNumber of people got turned away: " + sellerL[index].getNumTurnedAway());
			}
			else{//When there are no people who got turned away
				System.out.println(
						sellerL[index].getSellerID() + "\nNumber of tickets sold: " + (numOfCustomers - sellerL[index].getNumTurnedAway()));
			}
			index++;
		}
	}
}
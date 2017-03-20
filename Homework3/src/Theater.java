import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Theater {

	private Seat[][] theater;
	private final int ROWS = 10;
	private final int COLUMNS = 10;
	private static Semaphore sellerMLock = new Semaphore(1);
	private static Semaphore sellerLLock = new Semaphore(1);
	
	public Theater() {
		
		theater = new Seat[ROWS][COLUMNS]; // create 10x10 theater of seats

		for (int i = 0; i < ROWS; i++) { // fill empty theater with -- values
			for (int j = 0; j < COLUMNS; j++) {
				theater[i][j] = new Seat("-----");
			}
		}
	}

	/**
	 * The theater sells the ticket to customer
	 * @param customer customer's name 
	 * @param sellerID find the row based on the sellerID
	 * @throws InterruptedException 
	 */
	public void sellSeat(Customer customer, String sellerID) throws InterruptedException {
		int row = 0;
		
		switch (sellerID.substring(0, 1)) {
		case "H": // Row 1
			row = 1;
			while (!isFull()) {
				int col = nearestSeat(row);
				if (col != -1) {
					theater[row - 1][col] = new Seat(customer.getName());
					theater[row - 1][col].isSold();
					break;
				} else {
					row++;
				}
			}
			break;
		case "M": // Row 5 -> 6 -> 4 -> 7 -> etc( 3 -> 8 -> 2 -> 9 -> 1 -> 10 )
//			Queue<Customer> waitingMQueue = new PriorityQueue();
			row = 5;
			int gap = 0;
			sellerMLock.acquire();
			while (!isFull() && row <= 10 ) {
				int col = nearestSeat(row);
				if (col != -1) {
					theater[row - 1][col] = new Seat(customer.getName());
					break;
				} else { // if that row is full -> search for another row
					if (row <= 5) {
						row = row + gap; gap++;
					} else if (row > 5) {
						row = row - gap; gap++;
					}
				}
			}
			sellerMLock.release();
			break;
		case "L":// Row 10 -> 9 -> up
//			Queue<Customer> waitingLQueue = new PriorityQueue();
			row = 10;
			sellerLLock.acquire();
			while (!isFull()) {
				int col = nearestSeat(row);
				if (col != -1) {
					theater[row - 1][col] = new Seat(customer.getName());
					theater[row - 1][col].isSold();
					break;
				} else {
					row--;
				}
			}
			sellerLLock.release();
			break;
		}
	}

	public void printTheater() { // prints out customer names of theater
		for (int i = 0; i < ROWS; i++) {
			System.out.print("|");
			for (int j = 0; j < COLUMNS; j++) {
				System.out.print(theater[i][j].getCustomerName() + " ");
			}
			System.out.println("|");
		}
	}
	
	public boolean isFull() { // checks if theater is full by customer Name
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (theater[i][j].getCustomerName().equals("-----")) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Find the available nearest seat in the column
	 * @param row the seat row
	 * @return -1 if the row is full
	 */
	public int nearestSeat(int row) {
		for (int j = 0; j < COLUMNS; j++) {
			if (theater[row - 1][j].getCustomerName().equals("-----")) {
				return j;
			}
		}
		return -1;
	}

}

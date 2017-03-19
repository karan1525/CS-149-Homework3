import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class Main implements Runnable{

	Thread runnable;
	
	public Main(){
		this.runnable = new Thread(this);
		System.out.println("START");
		runnable.start();
		try {
			runnable.join();
			System.out.println("DONE");
		} catch (InterruptedException e) {
		}
	}
	
	public static void main(String[] args){
		new Main();
	}

	@Override
	public void run() {
		int numOfCustomers = 0;
		int time = 0; //Can run from 0 -> 59
		
		Scanner in = new Scanner(System.in);
		System.out.println("How many customers?: " ); //get the number of customers
		try{ //read the input & create customers & put it into queue
			numOfCustomers = in.nextInt();
			in.close();
		}
		catch (Exception e){
			System.out.println("Please enter the number of customers");
			System.exit(0);
		}

		Theater theater = new Theater(); //created theater
		//Seller for high-priced tickets
		HSeller hBooth = new HSeller(numOfCustomers, theater, 0);//create all threads and run them here
 		hBooth.startSelling();
 		
		//Sellers for medium-priced tickets
		for(int i = 0; i < 4; i++){
			MSeller mBooth = new MSeller(numOfCustomers, theater, i + 1);
			mBooth.startSelling();
		}
		
		//Sellers for low-priced tickets
		for(int i =0; i < 6; i++){
			LSeller lBooth = new LSeller(numOfCustomers, theater, i + 1);
			lBooth.startSelling();
		}

		System.out.println("THEATER");//prints current theater
		theater.printTheater(); 
		System.out.println("The theather is full: " + theater.isFull());
		
		
	}

	
	
}

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
		Scanner in = new Scanner(System.in);
		System.out.println("How many customers?: " ); //get the number of customers
		try{
			int numOfCustomers = in.nextInt();
			in.close();
		}
		catch (Exception e){
			System.out.println("Please enter the number of customers");
			System.exit(0);
		}

		Theater theater = new Theater(); //created theater
		
		
		HSeller hBooth = new HSeller();//create all threads
		
		for(int i = 0; i < 4; i++){
			MSeller mBooth = new MSeller();
			mBooth.startSelling();
		}
		
		for(int i =0; i < 6; i++){
			LSeller lBooth = new LSeller();
			lBooth.startSelling();
		}
		hBooth.startSelling();

		System.out.println("THEATER");//prints current theater
		theater.printTheater(); 
		System.out.println("The theather is full: " + theater.isFull());
		
		
	}

	
	
}

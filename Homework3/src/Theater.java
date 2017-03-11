
public class Theater {
	
	private Seat[][] theater;
	private final int ROWS = 10; 
	private final int COLUMNS = 10; 

	
	public Theater(){
		theater = new Seat[ROWS][COLUMNS]; //create 10x10 theater of seats
		
		for(int i = 0; i < ROWS; i++){ //fill empty theater with -- values
			for(int j =0; j < COLUMNS; j++){
				theater[i][j] = new Seat("-----");
			}
		}
	}
	
	
	public void sellSeat(Customer customer){ //sells a seat
		Seat seat = new Seat(customer.getName());
		
	}
	
	public void printTheater(){ //prints out customer names of theater
		
		for(int i = 0; i < ROWS; i++){ 
			System.out.print("|");
			for(int j =0; j < COLUMNS; j++){
				System.out.print(theater[i][j].getCustomerName() + " ");
			}
			System.out.println("|");
		}
	}
	
	public boolean isFull(){ //checks if theater is full by customer Name
		for(int i = 0; i < ROWS; i++){ 
			for(int j =0; j < COLUMNS; j++){
				if(theater[i][j].getCustomerName().equals("-----")){
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	
	
	
}

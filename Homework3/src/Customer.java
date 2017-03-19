
public class Customer {
	private int arrivalTime;
	private String name;

	public Customer(int arrivalTime) {
		this.name = "";
		this.arrivalTime = arrivalTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	@Override
	public String toString(){
		return name + arrivalTime;
	}

}

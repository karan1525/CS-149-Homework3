

import java.util.Comparator;
/**
 * Sorting the customers by arrival time
 */
public class ComparatorByArrivalTime implements Comparator<Customer>{

	public int compare(Customer c1, Customer c2) {
		if(c1.getArrivalTime() < c2.getArrivalTime()){return -1;}
		else if(c1.getArrivalTime() > c2.getArrivalTime()){return 1;} 
		else 
		{
			if(c1.getName().compareTo(c2.getName()) < 1){ return -1; }
			else{ return 1; }
		}
	}

}
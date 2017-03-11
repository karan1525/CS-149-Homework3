
public class Main implements Runnable{

	Thread runnable;
	
	public Main(){
		this.runnable = new Thread(this);
		runnable.start();
	}
	
	public static void main(String[] args){
		new Main();
	}

	@Override
	public void run() {
		
		
		HSeller hBooth = new HSeller();
		
		
		for(int i = 0; i < 4; i++){
			MSeller mBooth = new MSeller();
			mBooth.startSelling();
		}
		
		for(int i =0; i < 6; i++){
			LSeller lBooth = new LSeller();
			lBooth.startSelling();
		}
		hBooth.startSelling();

		
		
		
	}

	
	
}

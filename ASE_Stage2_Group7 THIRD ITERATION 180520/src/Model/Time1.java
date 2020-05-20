package Model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Time1 extends Observable{
private	int limit=25;
private volatile	int  passed=0;
    Timer timer= new Timer();
    


	TimerTask task=new TimerTask() {

		@Override
		public void run() {
			
	
			passed++;
			System.out.println("Time passed so far:"+ passed);
		System.out.println("ThreadName is "+Thread.currentThread().getName()+"\r\n");
		   if(passed==26) {
			
			   Thread.currentThread().interrupt();
              System.out.println("We tried");}}
		
		

	};
	
	
	public void start() {
		timer.scheduleAtFixedRate(task, 1000, 1000);
		}
	public void cancel(){
		timer.cancel();
	}
	public int getPassed() {
		return passed;
	}
	
	public void throwexception() throws KudasTimeException {
	throw new KudasTimeException();
	}

}

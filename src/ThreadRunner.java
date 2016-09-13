
import java.util.Random;

/**
 * This is a class which implements a separate thread for each runner.
 * 
 * @author Yugo Kato
 * @version 1.0
 */
public class ThreadRunner extends Thread {
	private double runnersSpeed;
	private double restPercentage;
	private int pos;
	private Random rn;
	private static final int COURSE_DISTANCE = 1000;
	private static volatile boolean finished;   // Shared by all threads. Set True by the winner
	private static Object obj = new Object();   // For synchronized block
	private Thread ct;
	
	public ThreadRunner(String runnersName, double runnersSpeed, double restPercentage){
		super(runnersName);  // Set thread name
		this.runnersSpeed = runnersSpeed;
		this.restPercentage = restPercentage;
		rn = new Random();
		pos = 0;
		finished = false;
	}

	/**
	 * Implements a loop that repeats until the runner has reached 1000 meters. 
	 * Each time through the loop, the thread decides whether it 
	 * should run or rest based on a random number and the percentage passed to the
	 * constructor (RestPercentage). If this random number indicates that the runner 
	 * should run, the class adds the speed value passed to the constructor (RunnersSpeed). 
	 * The run method sleeps for 100 milliseconds on each repetition of the loop.
	 * The first runner that reaches 1000 takes the synchronized flag (finished) and declares itself as the winner. 
	 */
	@Override
	public void run(){
		double n;
		ct = Thread.currentThread();
		try{
			while (finished == false && pos < COURSE_DISTANCE){  // Winner doesn't exist yet
				n = rn.nextInt(100)+1;   // generate a random number between 1-100
				if (n > restPercentage){  // move
					pos += runnersSpeed;
					if (pos < 1000){
						System.out.println(ct.getName() + ": " + pos);
					}
					else
						break;
				}
				Thread.sleep(100);  // rest or end of each move
			}

			// Reached 1000
			synchronized (obj){
				if (finished == false){  // This thread is the winner. Only the first thread can execute this block. 
					finished = true;  // The winner takes the flag
					Thread.sleep(200);  // Wait other runners to finish the last print 
					System.out.println(ct.getName() + ": " + COURSE_DISTANCE);
					System.out.println(ct.getName() + ": I finished!\n");
					MarathonRace.finishRace(ct.getName()); // Notify main thread that I'm the winner
				}
				else  // other than winner
					Thread.sleep(1000);
			}
		}
		catch (InterruptedException e){  // Interrupted by main thread
			System.out.println(ct.getName() + ": You beat me fair and square.");
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
}


import java.util.ArrayList;

/**
 * This is a class which manage ThreadRunner instances as a runners list.
 * The instance of this class is shared by RunnersDAO and RunnersFileIO classes.
 * 
 * @author Yugo Kato
 * @version 1.0
 */
public class RunnersList {
	private ArrayList<ThreadRunner> runners;

	/**
     * Class constructor
     */
	public RunnersList(){
		runners = new ArrayList<>();
	}
	
   /**
    * Returns runners list.
    * 
    * @return	A list of ThreadRunners instances
    */
	public ArrayList<ThreadRunner> getRunners(){
		return runners;
	}
	
   /**
    * Returns Hard coded default runners list.
    * 
    * @return	A list of default ThreadRunners instances
    */ 
	public ArrayList<ThreadRunner> getDefaultRunners(){
		ThreadRunner th1 = new ThreadRunner("Tortoise", 10, 0);
		ThreadRunner th2 = new ThreadRunner("Hare", 100, 90);
		runners.add(th1);
		runners.add(th2);
		return runners;
	}
	
   /**
    * Create an instance of ThreadRunner and adds into the list.
    * 
    * @param	runnersName			runner's name
    * @param runnersSpeed		runners speed
    * @param restPercentage		A percentage the runner will rest
    */ 
	public void addRunner(String runnersName, double runnersSpeed, double restPercentage){
		ThreadRunner th = new ThreadRunner(runnersName, runnersSpeed, restPercentage);
		runners.add(th);
	}

   /**
    * Clears the runners list.
    */ 
	public void clearRunners(){
		runners.clear();
	}
	
}

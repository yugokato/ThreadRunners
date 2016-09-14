
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is a class which handles core operations regarding the Marathon Race.
 * 
 * @author Yugo Kato
 * @version 1.0
 */

public class MarathonRace {

	private static ArrayList<ThreadRunner> runners;
	private RunnersDAO db;   // For handling database access
	private RunnersFileIO io;  // For handling xml/text files
	private RunnersList runList;  // For storing/exposing runners objects
	private static Scanner sc = new Scanner(System.in);
	
   /**
    * Class constructor 
    */
	public MarathonRace(){
		runList = new RunnersList();
	}

   /**
    * Displays the program menu.
    */
	public void displayMenu(){
		String s = "\nWelcome to the Marathon Race Runner Program\n\n"
				 + "Select your data source: \n\n"
				 + "1. Derby database\n"
				 + "2. XML file\n"
				 + "3. Text file\n"
				 + "4. Default two runners\n"
				 + "5. Exit\n";
		System.out.println(s);
	}

   /**
    * Validates user input (Program menu)
    * 
    * @param min		Minimum number of the menu
    * @param max 	Maximum number of the menu
    * @return choice Validated string number of the menu
    */	
	public int getChoice(int min, int max){
		String prompt = "Enter your choice: ";
		int choice = Validator.getIntRange(prompt, min, max);
		return choice;
	}
	
   /**
    * Validates user input (Filename). Checks if the file extension matches to the selected data source.
    * 
    * @param s			A filename which user specified
    * @return filename  	A validated filename
    */	
	public String getFileName(String s){
		String prompt = "";
		String filename;
		while (true){
			if (s.equals("xml")){  // Check if the filename is a valid xml format
				prompt = "Enter XML file name: ";
				filename = Validator.getString(prompt);
				if (! filename.contains(".xml")){
					System.out.println("This is not .xml file.\n");
					continue;
				}
				break;
			}
			else if (s.equals("txt")){  // Check if the filename is a valid text format
				prompt = "Enter text file name: ";
				filename = Validator.getString(prompt);
				if (! filename.contains(".txt")){
					System.out.println("This is not .txt file.\n");
					continue;
				}
				break;
			}
		}
		return filename;
	}

   /**
    * Waits until any key from the user is inputed
    */	
	public void waitInput(){
		System.out.println();
		System.out.print("Press any key to continue...");
		while (true){
			if (sc.hasNextLine())
				sc.nextLine();
				break;
		}
	}
	
   /**
    * Gets runners from the instance of RnnersList class and starts each runner as a thread.
    * 
    * @param s	A type of the data source for runners specified by the user
    */	
	public void startRace(String s){
		if (s.equals("default"))
			runners = runList.getDefaultRunners();
		else
			runners = runList.getRunners();
		
		try{
			System.out.print("Ready ");  
			Thread.sleep(1000);   
			System.out.print("set...");
			Thread.sleep(1000);   
			System.out.println("Go!\n");
		}
		catch (Exception e){}
		
		for (ThreadRunner th: runners){
			th.setDaemon(true);
			th.start();
		}
		
		for (ThreadRunner th: runners){
			try{
				th.join();
			}
			catch (InterruptedException e){
				System.out.println("The race was interrupeted.");
				break;
			}
		}
		
		runList.clearRunners();
	}
	
   /**
    * Reads runners data from the selected data source, and creates/sets runners in an instance of RunnersList class.
    * 
    * @param filename	A validated filename
    * @param s			A type of the data source for runners
    * @return			True if runners created successfully from the specified data source 
    */		
	public boolean createRunners(String filename, String s){
		if (s.equals("xml")){
			io = new RunnersFileIO(runList);
			return io.createRunnersXML(filename);
		}
		else if (s.equals("txt")){
			io = new RunnersFileIO(runList);
			return io.createRunnersTXT(filename);
		}
		else if (s.equals("db")){
			db = new RunnersDAO(runList);
			return db.createRunnersDB();
		}
		return false;
	}

   /**
    * Only one of the threads(the winner) can call when it finishes the race.
    * Declares the winner and interrupts all threads to concedes the race. 
    * 
    * @param winnerName	A name of the thread declared by the winner
    */	
	public static void finishRace(String winnerName){
		System.out.flush();
		System.out.printf("The race is over! %s is the winner.\n\n", winnerName);
		for (ThreadRunner th: runners){
			th.interrupt();
		}
	}
	
}

/**
 * <h1>Java programming, Comprehensive - Final Project: Marathon race runners</h1>
 * User Story: A sports company has hired you to write an application that would simulate a
 * marathon race between interesting groups of participants. They could be slow as a tortoise, as
 * fast as a hare, and anything in between. The runners differ in their speed and how often they need
 * to rest. Some may be slow and never rest; others may run fast but lose steam quickly and rest a
 * lot of the time. Still others could be anything in between.
 *
 * @author Yugo Kato
 * @version 1.0
 */

public class Main {	
   /**
    * This is the main method which handles user interaction in the application. 
    */
	public static void main(String[] args) {
		MarathonRace m = new MarathonRace();   // This instance provides all functionalities for handling the MarathonRace application
		
		int choice = 0;
		String filename;
				
		while (choice != 5){
			m.displayMenu();  // Display the program menu
			choice = m.getChoice(1, 5);
			switch (choice){
				case 1:  // Derby database
					if (m.createRunners(null, "db"))
						m.startRace("db");
					else
						System.out.println("Failed to create runners from the database");
					break;
				case 2:  // XML file
					filename = m.getFileName("xml");
					if (m.createRunners(filename, "xml"))
						m.startRace("xml");
					else
						System.out.printf("Failed to create runners from %s\n", filename);
					break;
				case 3:  // text file
					filename = m.getFileName("txt");
					if (m.createRunners(filename, "txt"))
						m.startRace("txt");
					else
						System.out.printf("Failed to create runners from %s\n", filename);
					break;
				case 4:  // default runners
						m.startRace("default");
					break;
				case 5:  // exit
					System.out.println("\nThank you for using my Marathon Race Program");
					break;
			}
			if (choice != 5)
				m.waitInput();   // Wait any key to be entered
		} 
	}
}

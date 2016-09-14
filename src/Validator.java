import java.util.Scanner;

/**
 * This is a class which validates user inputs.
 * 
 * @author Yugo Kato
 * @version 1.0
 */

class Validator {
	private static Scanner sc = new Scanner(System.in);
	
   /**
    * Validates string. Repeats until valid string is inputed.
    * 
    * @param prompt	A prompt which asks users to input data
    * @return		Validated string
    */	
	public static String getString(String prompt){
		String s;
		while (true){
			System.out.print(prompt);
			if (sc.hasNextLine()){
				s = sc.nextLine();
				if (s.isEmpty()){
					System.out.println("Invalid input. Try again.\n");
				}
				else
					return s;
			}
		}
	}
	
   /**
    * Validates integer. Repeats until valid integer is inputed.
    * 
    * @param prompt	A prompt which asks users to input data
    * @return		Validated integer
    */	
	public static int getIntRange(String prompt, int min, int max){
		int value;
		while (true){
			System.out.print(prompt);
			if (sc.hasNextInt()){
				value = sc.nextInt();
				if (value < min || value > max){
					sc.nextLine();
					System.out.println("Invalid input. Try again.\n");
				}
				else{
					sc.nextLine();
					return value;
				}
			}
			else{
				sc.nextLine();
				System.out.println("Invalid input. Try again.\n");
			}
				
		}
	}
}

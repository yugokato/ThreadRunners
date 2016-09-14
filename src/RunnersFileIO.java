import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This is a class which handles file I/O operations for reading runners from the data source and creating runner objects.
 * 
 * @author Yugo Kato
 * @version 1.0
 */
public class RunnersFileIO {
	private RunnersList runList;
	private String dirPath;
	
   /**
    * Class constructor
    * 
    *  @param runList	An instance of RunnersList class which holds all runners objects
    */
	public RunnersFileIO(RunnersList runList){
		this.runList = runList;
		dirPath = "./Resources";
	}
	
   /**
    * Creates runners objects(threads) from the specified xml file.
    * 
    * @param filename	A validated filename for a xml file
    * @return			True if runners created successfully from the specified data source
    */	
	public boolean createRunnersXML(String filename){
		Path filePath = Paths.get(dirPath, filename);
		try (FileReader file = new FileReader(filePath.toFile())){
			XMLInputFactory inputFactory = XMLInputFactory.newFactory();
			XMLStreamReader reader = inputFactory.createXMLStreamReader(file);
			String runnersName = "";
			double runnersSpeed = 0;
			double restPercentage = 0;
		
			// Parse xml file and create runners(threads)
			while(reader.hasNext()){
				int eventType = reader.getEventType();
				switch (eventType){
					case XMLStreamConstants.START_ELEMENT:
						String elementName = reader.getLocalName();
						if (elementName.equals("Runner")){
							runnersName = reader.getAttributeValue(0);
						}
						if (elementName.equals("RunnersMoveIncrement")){
							runnersSpeed = Double.parseDouble(reader.getElementText());
						}
						if (elementName.equals("RestPercentage")){
							restPercentage = Double.parseDouble(reader.getElementText());
						}
						break;
					case XMLStreamConstants.END_ELEMENT:
						elementName = reader.getLocalName();
						if (elementName.equals("Runner")){
							runList.addRunner(runnersName, runnersSpeed, restPercentage);  // create a runner
						}
						break;
					default:
						break;
				}
				reader.next();
			}
			return true;
		}
		catch (FileNotFoundException e){
			System.out.println("FILE NOT FOUND\n");
			return false;
		}
		catch (Exception e){
			System.out.println(e);
			return false;
		}
	}
	
   /**
    * Creates runners objects(threads) from the specified text file.
    * 
    * @param filename	A validated filename for a text file
    * @return			True if runners created successfully from the specified data source
    */	
	public boolean createRunnersTXT(String filename){
		String line;
		Path filePath = Paths.get(dirPath, filename);
		try(BufferedReader in = new BufferedReader(new FileReader(filePath.toFile()))){
			line = in.readLine();
			// Parse xml file and create runners(threads)
			while(line != null){
				String[] runnerArray = line.split(",");
				String runnersName = runnerArray[0].trim();
				int runnersSpeed = Integer.parseInt(runnerArray[1].trim());
				int restPercentage = Integer.parseInt(runnerArray[2].trim());
				runList.addRunner(runnersName, runnersSpeed, restPercentage);  // create a runner
				line = in.readLine();
			}
			return true;
		}
		catch(FileNotFoundException e){
			System.out.println("FILE NOT FOUND\n");
			return false;
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
		
	}

}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is a class for reading runners data from the embedded derby database(RunnersDB), and creating runner(thread) objects
 * 
 * @author Yugo Kato
 * @version 1.0
 */
public class RunnersDAO {
	private RunnersList runList;
	
   /**
    * Class constructor
    * 
    * @param runList	An instance of RunnersList class which holds all runners objects
    */
	public RunnersDAO(RunnersList runList){
		this.runList = runList;
	}
	
   /**
    * Get a connection to derby database. 
    * 
    * @return	An object of the connection
    */
	private Connection getConnection(){
		Connection conn = null;
		try{
			String dbDirectory = "Resources";
			System.setProperty("derby.system.home", dbDirectory);
			String url = "jdbc:derby:RunnersDB";
			String username = "";
			String password = "";
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		}
		catch (SQLException e){
			for (Throwable t: e)
				t.printStackTrace();	
			return null;
		}
	}
	
   /**
    * Disconnects the connection to derby database 
    * 
    * @return	True when connection was successfully closed
    */
	private boolean disconnect(){
		try{
			String shutdownURL = "jdbc:derby:RunnersDB;shutdown=true";
			DriverManager.getConnection(shutdownURL);
		}
		catch (SQLException e){
			if (e.getMessage().equals("Database 'RunnersDB' shutdown.")){
				return true;
			}
		}
		return false;
	}
	
   /**
    * Creates runners objects(threads) from the embedded derby database.
    * 
    * @return	True if runners created successfully from the derby database
    */	
	public boolean createRunnersDB(){
		String query = "SELECT * FROM RunnersStats";
		try	{
			Connection conn = getConnection();
			if (conn != null){
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					String runnersName = rs.getString("Name");
					double runnersSpeed = rs.getDouble("RunnersSpeed");
					double restPercentage = rs.getDouble("RestPercentage");
					runList.addRunner(runnersName, runnersSpeed, restPercentage);
				}
				rs.close();
				return true;
			}
			return false;
			
		}
		catch (SQLException e){
			System.err.println(e);
			return false;
		}		
		finally{
			disconnect();
		}
	}
}

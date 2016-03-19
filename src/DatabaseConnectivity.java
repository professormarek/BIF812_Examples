import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//NOTE: do not import com.mysql.jdbc.Driver
//bring it in instead by using the class loader
public class DatabaseConnectivity {

	public static void main(String[] args) {
		
		//use the class loader to import the JDBC driver code (jar)
		//don't forget to first include this JAR into your project (see slides)
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Did you forget to include the JAR file in the project???");

		}
		//to establish a database connection we need several pieces of information
		/*
		 * in this example I'll connect to the database running on the local host
		 * for you all, you can replace localhost with zenit.senecac.on.ca
		 * replace "biftest" with the name of YOUR database
		 * ex. jdbc:mysql://zenit.senecac.on.ca/your_database_name
		 */
		String databaseURI = "jdbc:mysql://localhost/biftest";
		
		//queries in java are represented as basic Strings
		//note: you can compose strings dynamically (using + operator etc.) 
		String query1 = "SELECT id, name, address FROM patients";
		String insertQuery = "INSERT INTO patients VALUES (NULL, 'Marek', '1 Seneca Way')";
		String updateQuery = "UPDATE patients SET address = 'a van down by the river' WHERE name in('Marek')";
		String deleteQuery = "DELETE FROM patients WHERE name = 'Marek'";
		//ex. drop a table
		//String dropTable = "DROP TABLE patients";
		//String createTable = "CREATE TABLE patients (id INT NOT NuLL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20) address VARCHAR(30))";
		//NOTE: that dropTabe and createTable need to be invoked uding the executeUpdate method, not executeQuery
		//using them is left as an exercise to you
		
		//let's actually connect to the database an execute some queries!
		//delcare references for the objects we're going to use;
		java.sql.Connection conn = null;
		Statement stmt = null;
		ResultSet records = null;
		//initiate the database connection
		String username = "bif";//replace bif with your zenit username
		String password = "java";//replace java with your zenit password
		boolean connected = false;
		System.out.println("attempting to connect to database: " + databaseURI + " with username: " + username + " and password: " + password);
		try {
			conn = DriverManager.getConnection(databaseURI, username, password);
			stmt = conn.createStatement();
			connected = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connected = false;
		}
		if(connected == true){
			System.out.println("requesting records from the database (SELECT query)");
			try {
				records = stmt.executeQuery(query1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				//this loop will iterate through all the records in the record set
				while(records.next()){
					int id = records.getInt("id");
					String address = records.getString("address");
					String name = records.getString("name");
					System.out.println("ID: " + id + " name: " + name + " address: "+ address);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//another example: insert a record
			System.out.println("Inserting a record into the table");
			try {
				//NOTE: we're using executeUpdate! to modify the table
				stmt.executeUpdate(insertQuery);
				System.out.println("The updated table is:");
				//retrieve the records again:
				records = stmt.executeQuery(query1);
				//use the same loop to diplay the updated table
				while(records.next()){
					int id = records.getInt("id");
					String address = records.getString("address");
					String name = records.getString("name");
					System.out.println("ID: " + id + " name: " + name + " address: "+ address);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//another example, update a record:
			try {
				System.out.println("Running update query to change Marek's address:");
				stmt.executeUpdate(updateQuery);
				System.out.println("The updated table is:");
				//retrieve the records again:
				records = stmt.executeQuery(query1);
				//use the same loop to diplay the updated table
				while(records.next()){
					int id = records.getInt("id");
					String address = records.getString("address");
					String name = records.getString("name");
					System.out.println("ID: " + id + " name: " + name + " address: "+ address);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//last example: delete query
			System.out.println("Run a query to delete one or more records");
			try {
				stmt.executeUpdate(deleteQuery);
				System.out.println("The updated table is:");
				//retrieve the records again:
				records = stmt.executeQuery(query1);
				//use the same loop to diplay the updated table
				while(records.next()){
					int id = records.getInt("id");
					String address = records.getString("address");
					String name = records.getString("name");
					System.out.println("ID: " + id + " name: " + name + " address: "+ address);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		System.out.println("Program completed successfully!");
	}

}

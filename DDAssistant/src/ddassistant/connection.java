package ddassistant;
import java.sql.*;

public class connection 
{
	public Connection connection = null;
	public Statement statement =  null;
	
	public void mysql()
	{
		try{

			
			Class.forName("com.mysql.jdbc.Driver");


			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/odb86mysqldb", "root", "Marines343");


			statement = connection.createStatement();


			}
			catch (SQLException sqlException)
			{
				System.out.println("1");
				System.out.println("Error!!" + sqlException.getMessage());
			}
			catch(ClassNotFoundException e)
			{
				System.out.println("2");
				System.out.println("error");
			}
	}

	public void closure () throws SQLException
	{

			connection.close();
			statement.close();
	}
}
	
	
package ddassistant;
import java.sql.*;
import java.util.*;

public class pullFile extends connection
{
	private ResultSet resultSet;
	protected String ID = new String();
	private ResultSetMetaData meta;
	//private ArrayList<String> info;
	private int count = 0;
	private int identifier;
	public <String> List<String> load()throws Exception
	{
		mysql();
		resultSet = statement.executeQuery("SELECT COUNT(ID) FROM well");
		resultSet.first();
		int max = Integer.parseInt(resultSet.getString(1));
		ArrayList<String> info = new ArrayList<String>(max);
		for (int x=1;x<=max;x++)
		{
			resultSet = statement.executeQuery("SELECT wellName FROM well WHERE ID="+x);
			resultSet.first();
			info.add((String) resultSet.getString(1).toString());
		}
		return (List<String>)info;
	}
	public Blob load(String name)throws Exception
	{
		mysql();
		resultSet = statement.executeQuery("SELECT wellBlob FROM well WHERE wellName ="+name);
		Blob blob = resultSet.getBlob(1);
		return blob;
		
		
	}
 	public boolean checkAvailable(String name)throws SQLException
	{
		mysql();
		boolean check = false;
		resultSet = statement.executeQuery("SELECT COUNT(ID) FROM well");
		resultSet.first();
		int max = Integer.parseInt(resultSet.getString(1));
		for (int x=1;x<=max;x++)
		{
			resultSet = statement.executeQuery("SELECT wellName FROM well WHERE ID="+x);
			resultSet.first();
			if(name.equals(resultSet.getObject(1).toString())){check = true;max=0;}
		}
		return check;
	}
	public int getIdentifier(String name)throws SQLException
	{
		resultSet = statement.executeQuery("SELECT ID FROM well WHERE well='"+name+'"');
		while(resultSet.next())
		{
			identifier = Integer.parseInt(resultSet.getObject(1).toString());
		}
		return identifier;
	}
}
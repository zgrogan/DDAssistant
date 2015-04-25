package ddassistant;
import java.sql.*;

public class newFile extends connection 
{
	protected pullFile NewWell = new pullFile();
	private ResultSet resultSet;
	pullFile pull = new pullFile();
	protected String ID = new String();
	String wellName = null;
	Blob wellBlob;
	
	public boolean save()throws SQLException
	{
		boolean success = false;
		int identifier;
		boolean taken;
		//getDataToStore();
		
		
		////////////////////////////////////////////////////testing////////////////////////
		wellName = "1123456789";//testing purposes well name
		//wellBlob = 0b001001010;//testing purposes blob
		////////////////////////////////////////////////////testing////////////////////////
		
		
		taken = pull.checkAvailable(wellName);
		if (!taken)
		{
			insertWellData(wellName,wellBlob);
			success = true;
		}
		else
		{
			System.out.println("Name taken could not save.\nWill update file.");
			updateWellData(wellName,wellBlob);
		}
			
		return success;
		
	}
	public void updateWellData(String wellName,Blob wellBlob)throws SQLException
	{
		mysql();
		statement.execute("UPDATE well SET wellBlob='"+wellBlob+"' WHERE wellName="+wellName);
		System.out.println("File udpate complete.");
	}
	public void insertWellData(String name,Blob wellBlob)throws SQLException
	{
		mysql();
		statement.execute("INSERT INTO well(wellName,wellBlob) VALUES('"+name+"',"+wellBlob+")");
		closure();
		
	}
	public void getDataToStore()
	{
		//wellName = object.getWellName();//unhash once connected to main code
		//wellBlob = ;//pull from main
	}
	//identifier = pull.getIdentifier(wellName);//use for pulling.

}
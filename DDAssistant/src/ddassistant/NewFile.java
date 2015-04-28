package ddassistant;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewFile extends Connection {
    protected PullFile NewWell = new PullFile();
    private ResultSet resultSet;
    PullFile pull = new PullFile();
    protected String ID = new String();
    String wellName = null;
    Blob wellBlob;

    public boolean save() throws SQLException {
        boolean success = false;
        int identifier;
        boolean taken;
        //getDataToStore();


        ////////////////////////////////////////////////////testing////////////////////////
        wellName = "1123456789";//testing purposes well name
        //wellBlob = 0b001001010;//testing purposes blob
        ////////////////////////////////////////////////////testing////////////////////////


        taken = pull.checkAvailable(wellName);
        if (!taken) {
            insertWellData(wellName, wellBlob);
            success = true;
        } else {
            System.out.println("Name taken could not save.\nWill update file.");
            updateWellData(wellName, wellBlob);
        }

        return success;

    }

    public void updateWellData(String wellName, Blob wellBlob) throws SQLException {
        mysql();
        statement.execute("UPDATE well SET wellBlob='" + wellBlob + "' WHERE wellName=" + wellName);
        System.out.println("File udpate complete.");
    }

    public void insertWellData(String name, Blob wellBlob) throws SQLException {
        mysql();
        statement.execute("INSERT INTO well(wellName,wellBlob) VALUES('" + name + "'," + wellBlob + ")");
        closure();

    }

    public void testConnection() throws SQLException, IOException, ClassNotFoundException {
        mysql();
        DDWell well = new DDWell();
        XStream stream = new XStream(new DomDriver());
        Clob wellClob = connection.createClob();
        Writer clobWriter = wellClob.setCharacterStream(1);
        clobWriter.write(stream.toXML(well));
        DDWell well2 = (DDWell)stream.fromXML(wellClob.getCharacterStream());
        System.out.println(well2);
        //String wellBlobString = wellBlob.getBytes(1, (int) wellBlob.length());
        //Blob wellBlob2 = new SerialBlob((SerialBlob)stream.fromXML(wellBlobString));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.out.println("1.1");
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        System.out.println("1.2");
        oos.writeObject(wellBlob);
        System.out.println("2");
        byte[] employeeAsBytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(employeeAsBytes);
        pstmt.setBinaryStream(1, bais, employeeAsBytes.length);
        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("3");
        ResultSet resultSet = statement.executeQuery("SELECT emp FROM Employee");
        while (resultSet.next()) {
            byte[] st = (byte[]) resultSet.getObject(1);
            ByteArrayInputStream baip = new ByteArrayInputStream(st);
            ObjectInputStream ois = new ObjectInputStream(baip);
            Employee emp = (Employee) ois.readObject();
            System.out.println("4");
        }
    }


    class Employee implements Serializable {
        int ID;
        String name;
        double salary;

        public Employee(int ID, String name, double salary) {
            this.ID = ID;
            this.name = name;
            this.salary = salary;
        }
    }

    public void getDataToStore() {
        //wellName = object.getWellName();//unhash once connected to main code
        //wellBlob = ;//pull from main
    }
}

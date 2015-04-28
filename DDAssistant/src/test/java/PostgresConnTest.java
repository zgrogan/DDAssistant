package test.java;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import ddassistant.DDWell;
import ddassistant.PostgresConn;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by zgrogan on 4/28/15.
 */
public class PostgresConnTest {
    @Test
    public void testConnection() throws SQLException {
        Connection conn = PostgresConn.getConnection();
        assertTrue(conn != null);
        Statement stmt = conn.createStatement();
        DDWell well = new DDWell("Awesome Well Name");
        XStream stream = new XStream(new DomDriver());
        String xml = stream.toXML(well);
        String query = "DELETE FROM wells WHERE well_name = '" + well.getWellName() + "';";
        stmt.execute(query);
        query = "INSERT INTO wells(well_name,well_xml) VALUES('" +
                well.getWellName() + "','" + xml + "');";
        stmt.execute(query);

        String xml2 = "";
        query = "SELECT * FROM wells WHERE well_name = '" + well.getWellName() + "';";
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next())
            xml2 = rs.getString(2);

        assertTrue(PostgresConn.getWellList().contains(well.getWellName()));

        DDWell well2 = (DDWell)stream.fromXML(xml2);

        query = "DELETE FROM wells WHERE well_name = '" + well.getWellName() + "';";
        stmt.execute(query);


        assertTrue(well.toString().equals(well2.toString()));
    }

    @Test
    public void testWellSaveLoadUpdateDelete() throws SQLException, InterruptedException {
        Connection conn = PostgresConn.getConnection();
        DDWell well = new DDWell("Another Well to Test");
        well.getTargetCurve().setTargetDepth(5000);
        well.addKickOff(1000, 2000, 0, 90);
        PostgresConn.save(well);
        DDWell well2 = PostgresConn.load(well.getWellName());
        assertTrue(well.toString().equals(well2.toString()));

        well.getTargetCurve().setTargetDepth(1000);
        well.addKickOff(250,500,0,90);
        well.addSurvey(250,0,0);
        PostgresConn.update(well);
        well2 = PostgresConn.load(well2.getWellName());
        assertTrue(well.toString().equals(well2.toString()));

        DDWell well3 = new DDWell("Another Well to Test");

        assertFalse(well3.toString().equals(well2.toString()));
        PostgresConn.delete(well);
    }
}
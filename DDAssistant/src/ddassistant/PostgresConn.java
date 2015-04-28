package ddassistant;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;

public final class PostgresConn {
    public static final String driver = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/DDAssistant";
    public static final String username = "postgres";
    public static final String password = "postgres";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static ArrayList<String> getWellList() throws SQLException {
        ArrayList<String> wellList = new ArrayList<>();
        Connection conn = getConnection();
        String query = "SELECT well_name FROM wells;";
        ResultSet rs = conn.createStatement().executeQuery(query);
        while(rs.next())
            wellList.add(rs.getString(1));
        return wellList;
    }

    public static void save(DDWell well) throws SQLException {
        Connection conn = getConnection();
        XStream xstream = new XStream(new DomDriver());
        String xml = xstream.toXML(well);
        String query = "INSERT INTO wells(well_name,well_xml) VALUES('"
                + well.getWellName() + "','" + xml + "');";
        conn.createStatement().execute(query);
    }

    public static void update(DDWell well) throws SQLException {
        XStream xstream = new XStream(new DomDriver());
        String xml = xstream.toXML(well);
        String query = "UPDATE wells SET well_xml='" + xml +
                "' WHERE well_name='" + well.getWellName() + "';";
        getConnection().createStatement().execute(query);
    }

    public static void delete(DDWell well) throws SQLException {
        String query = "DELETE FROM wells WHERE well_name='" + well.getWellName() + "';";
        getConnection().createStatement().execute(query);
    }

    public static DDWell loadWell(String wellName) throws SQLException {
        DDWell well = new DDWell();
        XStream xstream = new XStream(new DomDriver());
        String query = "SELECT * FROM wells WHERE well_name='" + wellName + "';";
        ResultSet rs = getConnection().createStatement().executeQuery(query);
        if(rs.next())
           well = (DDWell)xstream.fromXML(rs.getString(2));
        return well;
    }
}
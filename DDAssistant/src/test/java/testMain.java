package test.java;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import ddassistant.DDSurvey;
import ddassistant.DDWell;
import ddassistant.connection;
import ddassistant.newFile;
import ddassistant.pullFile;

public class testMain{
	private static String[] returned;
	@Test
	public void testSaveFile () throws SQLException {
		ArrayList<String> info = new ArrayList<String>(100);
		newFile next = new newFile();
		pullFile next2 = new pullFile();
		next.save();
		try {
	//		info.addAll(next2.load());
			for(int i=0;i<info.size();i++){
			    System.out.println(info.get(i));
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DDWell well = new DDWell();
		well.setWellName("This is a well name");
		well.createTargetCurve(10000);
		well.addKickOff(4000, 5000, 165, 85);
		double az = well.getTargetCurve().getAzimuthAt(6000);
		double inc = well.getTargetCurve().getInclinationAt(6000);

		// test addTargetCurve
		well.addTargetTurn(5000, 200, 130, 92);
		az = well.getTargetCurve().getAzimuthAt(6000);
		inc = well.getTargetCurve().getInclinationAt(6000);

		
		// test adding surveys
		DDSurvey survey = new DDSurvey(500, 0, 5);
		well.addSurvey(survey);
		well.addSurvey(600, 0, 5);

		DDWell well2 = new DDWell();
		assertTrue(well.toString().equals(well2.toString()));
		
		
	}
}
	


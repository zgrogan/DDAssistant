package test.java;
import ddassistant.DDSurvey;
import ddassistant.DDWell;
import ddassistant.NewFile;
import ddassistant.PullFile;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestMain{
	private static String[] returned;
	@Test
	public void testSaveFile () throws SQLException, ClassNotFoundException, IOException {
		ArrayList<String> info = new ArrayList<String>(100);
		NewFile next = new NewFile();
		PullFile next2 = new PullFile();
//		next.save();
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
		System.out.println("before");
		next.testConnection();
		System.out.println("after");
//		assertTrue(well.toString().equals(well2.toString()));


	}
}


	


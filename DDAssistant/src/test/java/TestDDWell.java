package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import ddassistant.DDSurvey;
import ddassistant.DDWell;

public class TestDDWell {
	@Test
	public void testDDWell() {
		// test kickoff
		DDWell well = new DDWell();
		well.createTargetCurve(10000);
		well.addKickOff(4000, 5000, 165, 85);
		double az = well.getTargetCurve().getAzimuthAt(6000);
		double inc = well.getTargetCurve().getInclinationAt(6000);
		assertTrue(az > 164.99 && az < 165.01);
		assertTrue(inc > 84.99 && inc < 85.01);

		// test addTargetCurve
		well.addTargetTurn(5000, 200, 130, 92);
		az = well.getTargetCurve().getAzimuthAt(6000);
		inc = well.getTargetCurve().getInclinationAt(6000);
		assertTrue(az > 129.99 && az < 130.01);
		assertTrue(inc > 91.99 && inc < 92.01);

		
		// test adding surveys
		DDSurvey survey = new DDSurvey(500, -45, 5);
		well.addSurvey(survey);
		well.addSurvey(600, -45, 5);
		assertTrue(well.getSurveys().size() == 3);
		assertTrue(well.getActualCurve().getInclinationAt(550) > 0 && well.getActualCurve().getInclinationAt(550) < 5);
	}
}

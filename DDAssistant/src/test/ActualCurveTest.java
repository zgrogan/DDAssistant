package test;
import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import ddassistant.ActualCurve;
import ddassistant.DDCurveData;
import ddassistant.DDSurvey;


public class ActualCurveTest {

	ActualCurve wd = new ActualCurve();
	@Test
	public void testaddSurvey() {
		DDSurvey s1 = new DDSurvey(0,0,0);
		DDSurvey s2 = new DDSurvey(250,0,0);
		LinkedList<DDSurvey> lls = new LinkedList<DDSurvey>();
		wd.addSurvey(0,0,0);
		wd.addSurvey(250,0,0);
		lls.add(s1);
		lls.add(s2);
		
		assertTrue(lls.equals(wd.getData()));
		
		
		
	}

}

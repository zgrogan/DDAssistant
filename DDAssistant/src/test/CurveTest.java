package test;
import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import ddassistant.ActualCurve;
import ddassistant.DDCurveData;
import ddassistant.DDSurvey;
import ddassistant.TargetCurve;
import javafx.geometry.Point3D;

@SuppressWarnings("restriction")
public class CurveTest {
	@Test
	public void testTargetCurve() {
		TargetCurve tc = new TargetCurve(10000f);
		assertTrue(tc.getPointAt(5000f).getY() == -5000.0);
		assertTrue(tc.getPointAt(5000f).getX() == 0.0);
		assertTrue(tc.getPointAt(5000f).getZ() == 0.0);
		assertTrue(tc.getAzimuthAt(8000) == 0.0);
		assertTrue(tc.getInclinationAt(8000) == 0.0);
		
		System.out.println(tc);
		tc.addTurn(5000, 500, 90, 90);
		System.out.println(tc);
	}
	/*
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
*/
}

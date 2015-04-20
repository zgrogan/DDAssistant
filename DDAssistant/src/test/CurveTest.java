package test;
import static org.junit.Assert.*;

import org.junit.Test;

import ddassistant.ActualCurve;
import ddassistant.TargetCurve;

@SuppressWarnings("restriction")
public class CurveTest {
	@Test
	public void testTargetCurve() {
		// check TargetCurve creation and straight down points.
		TargetCurve tc = new TargetCurve(10000f);
		assertTrue(tc.getPointAt(5000f).getY() == -5000.0);
		assertTrue(tc.getPointAt(5000f).getX() == 0.0);
		assertTrue(tc.getPointAt(5000f).getZ() == 0.0);
		assertTrue(tc.getAzimuthAt(8000) == 0.0);
		assertTrue(tc.getInclinationAt(8000) == 0.0);
		
		// add a turn
		tc.addTurn(5000, 500, 120, 45);
		double az8k = tc.getAzimuthAt(8000);
		double in8k = tc.getInclinationAt(8000);
		assertTrue(az8k > 119.99 && az8k < 120.01);
		assertTrue(in8k > 44.99 && in8k < 45.01);
		
		// add another turn & check directions are correct
		tc.addTurn(7000, 200, 130, 90);
		double az6k = tc.getAzimuthAt(6000);
		double in6k = tc.getInclinationAt(6000);
		az8k = tc.getAzimuthAt(8000);
		in8k = tc.getInclinationAt(8000);
		assertTrue(az6k > 119.99 && az6k < 120.01);
		assertTrue(in6k > 44.99 && in6k < 45.01);
		assertTrue(az8k > 129.99 && az8k < 130.01);
		assertTrue(in8k > 89.99 && in8k < 90.01);
		
		// check the middle of the turns
		double az1 = tc.getAzimuthAt(5250);
		double in1 = tc.getInclinationAt(5250);
		double az2 = tc.getAzimuthAt(7100);
		double in2 = tc.getInclinationAt(7100);
		assertTrue (az1 > 119.99 && az1 < 120.01);
		assertTrue (in1 > 20 && in1 < 25);
		assertTrue (az2 > 124 && az2 < 126);
		assertTrue (in2 > 65 && in2 < 70);		
		
		// test addKickOff method
		tc = new TargetCurve(10000);
		tc.addKickOff(5000, 6000, 167, 80);
		az1 = tc.getAzimuthAt(4999);
		az2 = tc.getAzimuthAt(9000);
		in1 = tc.getInclinationAt(4999);
		in2 = tc.getInclinationAt(9000);
		assertTrue(az1 == 0);
		assertTrue(az2 > 166.99 && az2 < 167.01);
		assertTrue(in1 ==0);
		assertTrue(in2 > 79.99 && in2 < 80.01);
	}
	
	@Test
	public void testActualCurve() {
		ActualCurve ac = new ActualCurve();
		assertTrue(ac.getDepth()==0);
		
		// check proper straight line between similar surveys
		ac.addSurvey(500, 150, 5);
		ac.addSurvey(1000, 150, 5);
		double az = ac.getAzimuthAt(750);
		double inc = ac.getInclinationAt(750);
		assertTrue(az > 149.999 && az < 150.001);
		assertTrue(inc > 4.999 && inc < 5.001);
		
		// check adding survey in middle of curve
		ac.addSurvey(750, 40, 85);
		assertTrue(ac.getAzimuthAt(625) > 94 && ac.getAzimuthAt(625) < 96);
		assertTrue(ac.getAzimuthAt(875) > 94 && ac.getAzimuthAt(875) < 96);
		assertTrue(ac.getInclinationAt(625) > 44 && ac.getInclinationAt(625) < 46);
		assertTrue(ac.getInclinationAt(875) > 44 && ac.getInclinationAt(875) < 46);
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

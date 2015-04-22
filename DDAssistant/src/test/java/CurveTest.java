package test.java;

import static org.junit.Assert.*;
import javafx.geometry.Point3D;

import org.junit.Test;

import ddassistant.ActualCurve;
import ddassistant.DDCurveData;
import ddassistant.TargetCurve;

@SuppressWarnings("restriction")
public class CurveTest {
	@Test
	public void testTargetCurve() {
		// check TargetCurve creation and straight down points.
		TargetCurve tc = new TargetCurve(10000f);
		assertTrue(tc.getPointAt(5000f).getY() == 5000.0);
		assertTrue(tc.getPointAt(5000f).getX() == 0.0);
		assertTrue(tc.getPointAt(5000f).getZ() == 0.0);
		assertTrue(tc.getAzimuthAt(8000) == 0.0);
		assertTrue(tc.getInclinationAt(8000) == 0.0);
		
		// add a turn
		tc.addTurn(5000, 500, 120, 30);
		double az8k = tc.getAzimuthAt(8000);
		double in8k = tc.getInclinationAt(8000);
		assertTrue(az8k > 119.99 && az8k < 120.01);
		assertTrue(in8k > 29.99 && in8k < 30.01);
		
		// add another turn & check directions are correct
		tc.addTurn(7000, 200, 130, 90);
		double az6k = tc.getAzimuthAt(6000);
		double in6k = tc.getInclinationAt(6000);
		az8k = tc.getAzimuthAt(8000);
		in8k = tc.getInclinationAt(8000);
		assertTrue(az6k > 119.99 && az6k < 120.01);
		assertTrue(in6k > 29.99 && in6k < 30.01);
		assertTrue(az8k > 129.99 && az8k < 130.01);
		assertTrue(in8k > 89.99 && in8k < 90.01);
		
		// check the middle of the turns
		double az1 = tc.getAzimuthAt(5250);
		double in1 = tc.getInclinationAt(5250);
		double az2 = tc.getAzimuthAt(7100);
		double in2 = tc.getInclinationAt(7100);
		assertTrue (az1 > 119.99 && az1 < 120.01);
		assertTrue (in1 > 14 && in1 < 16);
		assertTrue (az2 > 124 && az2 < 126);
		assertTrue (in2 > 59 && in2 < 61);		
		
		// test addKickOff method
		tc = new TargetCurve(10000);
		tc.addKickOff(5000, 6000, 167, 90);
		az1 = tc.getAzimuthAt(4999);
		az2 = tc.getAzimuthAt(9000);
		in1 = tc.getInclinationAt(4999);
		in2 = tc.getInclinationAt(9000);
		assertTrue(az1 == 0);
		assertTrue(az2 > 166.99 && az2 < 167.01);
		assertTrue(in1 ==0);
		assertTrue(in2 > 89.99 && in2 < 90.01);
		assertTrue(Math.abs(tc.getTVDAt(tc.getLandingDepth()) - 6000) < 0.01);
		assertTrue(Math.abs(tc.getTVDAt(tc.getLandingDepth() - 1) - 6000) > 0.01);
		
		// verify cardinal directions
		tc = new TargetCurve(10000);
		// turn north, east, south, west
		tc.addTurn(1000,100,0,90);
		tc.addTurn(2000, 100, 90, 90);
		tc.addTurn(3000,100,180,90);
		tc.addTurn(5000,100,-90,90);
		Point3D northVector = tc.getUnitVectorAt(1500);
		assertTrue(northVector.distance(DDCurveData.NORTH) < 0.0001);
		Point3D eastVector = tc.getUnitVectorAt(2500);
		assertTrue(eastVector.distance(DDCurveData.EAST) < 0.0001);
		Point3D southVector = tc.getUnitVectorAt(3500);
		assertTrue(southVector.distance(DDCurveData.SOUTH) < 0.0001);
		Point3D westVector = tc.getUnitVectorAt(5500);
		assertTrue(westVector.distance(DDCurveData.WEST) < 0.0001);
		
		// turn up
		tc.addTurn(6000, 100, -90, 180);
		Point3D upVector = tc.getUnitVectorAt(6500);
		assertTrue(upVector.distance(DDCurveData.UP) < 0.0001);
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
}

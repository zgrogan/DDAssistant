package ddassistant;

import java.text.DecimalFormat;
import java.util.LinkedList;

import javafx.geometry.Point3D;

// represents a directional driller's target curve.  maintains a target depth
// and landingDepth, as well as ability to add a kickOff curve based
// on vertical distance to landing depth.
@SuppressWarnings("restriction")
public class TargetCurve extends DDCurveData {

	private double targetDepth = 0;
	private double landingDepth = 0;

	public TargetCurve() {
		super();
		this.getPoints().add(ZERO);
	}

	public TargetCurve(DDCurveData ddCurveData) {
		super(ddCurveData);
	}

	public TargetCurve(double targetDepth) {
		this.targetDepth = targetDepth;
		Point3D start = new Point3D(0, 0, 0);
		Point3D end = new Point3D(0, targetDepth, 0);
		this.getPoints().add(start);
		this.getPoints().add(end);
	}

	public TargetCurve(LinkedList<Point3D> ret) {
		super(ret);
	}

	// lets user define a curve with a given landingDepth (TVD)
	public void addKickOff(double startDepth, double landingTVD, double newAzimuth,
			double newInclination) {
		// arclength = radius * angle;
		// get the angle
		double startAzimuth = this.getAzimuthAt(startDepth);
		double startInclination = this.getInclinationAt(startDepth);
		Point3D startVector = DDCurveData.sphereToCart(1, startAzimuth,
				startInclination);
		Point3D endVector = DDCurveData.sphereToCart(1, newAzimuth,
				newInclination);
		double angle = Math.PI * startVector.angle(endVector) / 180;

		// determine radius of circle
		double radius = Math.abs((landingTVD - startDepth)
				/ (Math.cos(newInclination * Math.PI / 180) - Math
						.cos(startInclination * Math.PI / 180)));
		
		double arcLength = radius * angle;
		// now add the turn
		addTurn(startDepth, arcLength, newAzimuth, newInclination);
		double error = 1;
		// hack, correct for inconsistencies in landing depth from expected value
		// inefficient, but effective
		while(Math.abs(error) > 0.00001) {
			error = landingTVD - this.getTVDAt(startDepth + arcLength);
			arcLength += error/2;
			addTurn(startDepth, arcLength, newAzimuth, newInclination);
		}
		// update landingDepth
		landingDepth = startDepth + arcLength;
	}

	// overridden addTurn method, updates final point and targetDepth if necessary.
	public void addTurn(double startDepth, double curveLength, double newAzimuth,
			double newInclination) {
		// add the turn
		super.addTurn(startDepth, curveLength, newAzimuth, newInclination);

		// update targetDepth or add last curve segment
		if (targetDepth <= startDepth + curveLength)
			targetDepth = startDepth + curveLength;
		else {
			Point3D endOfCurve = this.getPointAt(startDepth + curveLength);
			double segmentLength = targetDepth - (startDepth + curveLength);
			getPoints().add(
					endOfCurve.add(DDCurveData.sphereToCart(segmentLength,
							newAzimuth,  newInclination)));
		}
	}

	public double getTargetDepth() {
		return targetDepth;
	}

	public void setTargetDepth(double targetDepth) {
		this.targetDepth = targetDepth;
	}

	public double getLandingDepth() {
		return landingDepth;
	}

	// return string representing drilling accuracy example: 25.0 HI / 15.0 Left
	public String getHLLR(ActualCurve actualCurve, Double depth) {
		Point3D actualPoint = actualCurve.getPointAt(depth);
		Point3D upVector = getUpVector(actualCurve, depth);
		Point3D leftVector = getLeftVector(actualCurve, depth);
		System.out.println("up: " + upVector);
		System.out.println("left: " + leftVector);
		double actualAzimuth = actualCurve.getAzimuthAt(depth);
		double actualInclinaton = actualCurve.getInclinationAt(depth);
		// find closest point on target curve to actualPoint
		double distanceChange = 2;
		double distance = 0;
		Point3D targetPoint = getPointAt(depth);
		int count = 0;
		while(Math.abs(distanceChange) > 0.1 && count < 1000) {
			count++;
			targetPoint = this.getPointAt(depth);
			distance = actualPoint.distance(targetPoint);
			depth += distanceChange;
			if (distance - targetPoint.distance(actualPoint) >= 0)
				distanceChange /= -2;
		}


		// calculate hi/low left/right based on given points and angles
		Point3D differenceVector = targetPoint.subtract(actualPoint);
		double hi = distance * Math.cos(differenceVector.angle(upVector));
		double left = distance * Math.cos(differenceVector.angle(leftVector));

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		String hllr = df.format(Math.abs(hi)) + ((hi < 0) ? " Lo" : " Hi / ");
		hllr += df.format(Math.abs(left)) + ((left < 0) ? " Right" : " Left");

		return hllr;
	}
}

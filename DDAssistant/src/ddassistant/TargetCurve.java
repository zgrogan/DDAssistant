package ddassistant;

import java.util.LinkedList;

import javafx.geometry.Point3D;

import org.jfree.data.xy.XYSeries;

@SuppressWarnings("restriction")
public class TargetCurve extends DDCurveData {

	private float targetDepth = 0;

	public TargetCurve() {
		super();
	}

	public TargetCurve(DDCurveData ddCurveData) {
		super(ddCurveData);
	}

	public TargetCurve(float targetDepth) {
		this.targetDepth = targetDepth;
		Point3D start = new Point3D(0, 0, 0);
		Point3D end = new Point3D(0, -targetDepth, 0);
		this.getPoints().add(start);
		this.getPoints().add(end);
	}

	public TargetCurve(LinkedList<Point3D> ret) {
		super(ret);
	}

	// don't use, not working yet
	public void addKickOff(float startDepth, float endDepth, float newAzimuth,
			float newInclination) {
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
		double radius = Math.abs((endDepth - startDepth)
				/ (Math.cos(newInclination * Math.PI / 180) - Math
						.cos(startInclination * Math.PI / 180)));
		
		// determine length of curve
		double arcLength = radius * angle;
		// now add the turn
		addTurn(startDepth, arcLength, newAzimuth, newInclination);
	}

	// overridden addTurn method, updates final point and targetDepth if necessary.
	public void addTurn(float startDepth, float curveLength, float newAzimuth,
			float newInclination) {
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
							newAzimuth, 180 - newInclination)));
		}
	}

	public float getTargetDepth() {
		return targetDepth;
	}

	public void setTargetDepth(float targetDepth) {
		this.targetDepth = targetDepth;
	}
}

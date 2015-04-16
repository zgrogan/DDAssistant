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
	
	public void addKickOff(float startDepth, float endDepth, float newAzimuth, float newInclination) {
		LinkedList<Point3D> newPoints = new LinkedList<Point3D>();
		double startAzimuth = this.getAzimuthAt(startDepth);
		double startInclination = this.getInclinationAt(startDepth);
		Point3D startVector = DDCurveData.sphereToCart(1, startAzimuth,
				startInclination);
		Point3D endVector = DDCurveData.sphereToCart(1, newAzimuth,
				newInclination);
		double angle = startVector.angle(endVector);
	}

	public void addTurn(float startDepth, float curveLength, float newAzimuth,
			float newInclination) {
		LinkedList<Point3D> newPoints = new LinkedList<Point3D>();
		double startAzimuth = this.getAzimuthAt(startDepth);
		double startInclination = this.getInclinationAt(startDepth);
		Point3D startVector = DDCurveData.sphereToCart(1, startAzimuth,
				startInclination);
		Point3D endVector = DDCurveData.sphereToCart(1, newAzimuth,
				newInclination);
		double angle = startVector.angle(endVector);

		// keep all points before startDepth
		for (Point3D point : this.getCurveAbove(startDepth).getPoints()) {
			newPoints.add(point);
		}
		newPoints.add(this.getPointAt(startDepth));
		this.setPoints(newPoints); // discard the rest

		// we'll add a segment for each degree we turn
		int numCuts = (int) angle + 1;
		for (int i = 0; i < numCuts; i++) {
			double segmentLength = curveLength / numCuts;
			double segmentAzimuth = (newAzimuth - startAzimuth) / numCuts;
			double lastAzimuth = this.getAzimuthAt(startDepth + i
					* segmentLength);
			double segmentInclination = (newInclination - startInclination)
					/ numCuts;
			double lastInclination = this.getInclinationAt(startDepth + i
					* segmentLength);
			Point3D newSegment = this.getPoints().getLast().add(DDCurveData.sphereToCart(segmentLength,
					segmentAzimuth + lastAzimuth, segmentInclination
							+ lastInclination));
			this.getPoints().add(newSegment);
		}
		
		if (targetDepth <= startDepth + curveLength)
			targetDepth = startDepth + curveLength;
		else {// here we add a final point at target depth along desired path
			Point3D endOfCurve = this.getPointAt(startDepth + curveLength);
			double segmentLength = targetDepth - (startDepth + curveLength);
			getPoints().add(endOfCurve.add(DDCurveData.sphereToCart(segmentLength, newAzimuth, newInclination)));
		}
	}

	public void changeDirection(double depth, double azimuth,
			double inclination, double targetDepth) {
	}

	public XYSeries getXYSeries() {
		// TODO Auto-generated method stub
		return null;
	}

	public XYSeries getXZSeries() {
		// TODO Auto-generated method stub
		return null;
	}

	public float getTargetDepth() {
		return targetDepth;
	}

	public void setTargetDepth(float targetDepth) {
		this.targetDepth = targetDepth;
	}
}

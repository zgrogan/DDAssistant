package ddassistant;

import java.util.LinkedList;

import javafx.geometry.Point3D;

@SuppressWarnings("restriction")
public class DDCurveData {
	
	// unit vectors for cardinal directions
	static final Point3D NORTH = new Point3D(1,0,0);
	static final Point3D SOUTH = new Point3D(-1,0,0);
	static final Point3D EAST = new Point3D(0,0,1);
	static final Point3D WEST = new Point3D(0,0,-1);
	static final Point3D DOWN = new Point3D(0,-1,0);
	static final Point3D UP = new Point3D(0,1,0);
	static final Point3D ZERO = new Point3D(0,0,0);

	// 3D representation of curve points
	private LinkedList<Point3D> points;
	
	// default constructor
	public DDCurveData() {
		points = new LinkedList<Point3D>();
	}
	
	// copy constructor
	public DDCurveData(DDCurveData other) {
		for (Point3D point : other.getPoints()) {
			points.add(new Point3D(point.getX(), point.getY(), point.getZ()));
		}
	}
	
	// constructor with points
	public DDCurveData(LinkedList<Point3D> other) {
		this();
		for (Point3D point : other) {
			points.add(new Point3D(point.getX(), point.getY(), point.getZ()));
		}
	}

	public static double getDistance(Point3D pointA, Point3D pointB) {
		return pointA.distance(pointB);
	}

	// returns the point in the curve at the specified depth
	public Point3D getPointAt(double depth) {
		// return values, default to end of curve
		double x = points.getLast().getX();
		double y = points.getLast().getY();
		double z = points.getLast().getZ();

		// we will trace the curve, adding each segment until we surpass our
		// target.
		float loopLength = 0;
		for (int i = 0; i < points.size() && loopLength < depth; i++) {
			// If we are at the last point, do nothing
			if (i != points.size() - 1) {
				// store last loopLength
				float tempLength = loopLength;
				loopLength += getDistance(points.get(i), points.get(i + 1));

				// we found the segment to cut
				if (loopLength >= depth) {
					// avoid divide by zero
					double fractionToCut = 1.0f;
					if (loopLength != tempLength) {
						fractionToCut = (depth - tempLength)
								/ (loopLength - tempLength);
					}
					x = points.get(i).getX() + fractionToCut
							* (points.get(i + 1).getX() - points.get(i).getX());
					y = points.get(i).getY() + fractionToCut
							* (points.get(i + 1).getY() - points.get(i).getY());
					z = points.get(i).getZ() + fractionToCut
							* (points.get(i + 1).getZ() - points.get(i).getZ());
				}
			}
		}
		return new Point3D(x, y, z);
	}
	
	// helper method to convert spherical coordinates to cartesian
	public static Point3D sphereToCart(double magnitude, double azimuth, double inclination) {
		azimuth = azimuth * Math.PI / 180;
		inclination = inclination * Math.PI / 180;
		double x = magnitude * Math.cos(azimuth) * Math.sin(inclination);
		double y = magnitude * Math.cos(inclination);
		double z = magnitude * Math.sin(azimuth) * Math.sin(inclination);
		x = Math.abs(x) > 0.000001 ? x : 0;
		y = Math.abs(y) > 0.000001 ? y : 0;
		z = Math.abs(z) > 0.000001 ? z : 0;
		return new Point3D(x,y,z);
	}
	
	// get a directional unit vector from given azimuth and inclination
	public static Point3D getUnitVector(double azimuth, double inclination) {
		return sphereToCart(1,azimuth,inclination);
	}
	
	// get the inclination at a specified depth
	public double getInclinationAt(double depth) {
		double inclination;
		Point3D pointB = this.getPointAt(depth);
		Point3D pointA = this.getCurveAbove(depth).getPoints().getLast();
		Point3D diff = pointA.subtract(pointB);
		double dx = Math.abs(diff.getX()) > .000001 ? diff.getX() : 0;
		double dy = Math.abs(diff.getY()) > .000001 ? diff.getY() : 0;
		double dz = Math.abs(diff.getZ()) > .000001 ? diff.getZ() : 0;
		if (diff == ZERO)
			return 0;
		inclination = Math.acos(dy / Math.sqrt(dx * dx + dy * dy + dz * dz));
		return inclination * 180 / Math.PI;
	}
	
	// get the azimuth at a specified depth
	public double getAzimuthAt(double depth) {
		double azimuth;
		Point3D pointB = this.getPointAt(depth);
		Point3D pointA = this.getCurveAbove(depth).getPoints().getLast();
		Point3D diff = pointA.subtract(pointB);
		double dx = diff.getX();
		double dy = diff.getY();
		double dz = diff.getZ();
		if (dx == 0 && dz == 0)
			return 0;
		azimuth = Math.acos(dx / Math.sqrt(dx * dx + dz * dz));
		return azimuth * 180 / Math.PI;
	}

	public LinkedList<Point3D> getPoints() {
		return points;
	}
	
	public DDCurveData getCurveAbove(double depth) {
		LinkedList<Point3D> ret = new LinkedList<Point3D>();
		float loopDepth = 0;

		for (int i = 0; i < this.getPoints().size() && loopDepth < depth; i++) {
			ret.add(getPoints().get(i));
			if (i != getPoints().size() - 1) {
				loopDepth += getPoints().get(i).distance(getPoints().get(i + 1));
			}

		}
		return new DDCurveData(ret);
	}


	public void setPoints(LinkedList<Point3D> points) {
		this.points = points;
	}

	public String toString() {
		String ret = "";
		for(Point3D point : points) {
			ret += point + "\n";
		}
		return ret;
	}
}

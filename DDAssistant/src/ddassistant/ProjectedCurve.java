package ddassistant;

import javafx.geometry.Point3D;

import java.util.LinkedList;

/**
 * Created by zgrogan on 4/24/15.
 */
public class ProjectedCurve extends TargetCurve {
    // members
    private double projectionLength = 100;
    private ActualCurve actualCurve;
    private DDCurveData totalCurve;

    // constructors
    public ProjectedCurve(ActualCurve actualCurve, double projectionLength) throws IllegalArgumentException {
        if (projectionLength < 0.01) throw new IllegalArgumentException("projection must be > 0");
        this.actualCurve = actualCurve;
        this.projectionLength = projectionLength;
        initProjectedCurve();
    }

    public void updateProjectedCurve() {
        initProjectedCurve();
    }

    private void initProjectedCurve() {
        points = new LinkedList<Point3D>();
        if (actualCurve.getPoints().size() > 0)
            points.add(actualCurve.getPoints().getLast());
        else points.add(ZERO);
        double acLength = actualCurve.getDepth();
        double azimuth;
        double inclination;
        if (acLength > 0.01) {
            azimuth = actualCurve.getAzimuthAt(actualCurve.getDepth() - 0.1);
            inclination = actualCurve.getInclinationAt(actualCurve.getDepth() - 0.1);
        } else {
            azimuth = 0.0;
            inclination = 0.0;
        }
        points.add(sphereToCart(projectionLength, azimuth, inclination));
        totalCurve.setPoints(actualCurve.getPoints());
        totalCurve.getPoints().addAll(points);
    }

    @Override
    public Point3D getPointAt(double depth) {
        return totalCurve.getPointAt(depth);
    }

    @Override
    public void addTurn(double startDepth, double curveLength, double newAzimuth, double newInclination) {
        totalCurve.addTurn(startDepth,curveLength,newAzimuth,newInclination);

    }
}

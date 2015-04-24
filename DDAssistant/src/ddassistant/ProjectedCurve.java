package ddassistant;

import javafx.geometry.Point3D;

import java.util.LinkedList;

/**
 * Created by zgrogan on 4/24/15.
 */
public class ProjectedCurve extends TargetCurve {
    private ActualCurve actualCurve;
    public ProjectedCurve(ActualCurve actualCurve, double projectionLength) throws IllegalArgumentException {
        this.actualCurve = actualCurve;
        points = new LinkedList<Point3D>();
        points.add(actualCurve.getPoints().getLast());
        double acLength = actualCurve.getDepth();
        double azimuth;
        double inclination;
        if (acLength > 0.01) {
            azimuth = actualCurve.getAzimuthAt(actualCurve.getDepth() - 0.1);
            inclination = actualCurve.getInclinationAt(actualCurve.getDepth() - 0.1);
        }  else throw new IllegalArgumentException();
        points.add(points.getFirst().add(sphereToCart(projectionLength, azimuth,inclination)));

    }

    @Override
    public Point3D getPointAt(double depth) {
        return super.getPointAt(depth - actualCurve.getDepth());
    }
}

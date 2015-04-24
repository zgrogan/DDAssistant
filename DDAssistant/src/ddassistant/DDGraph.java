package ddassistant;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

import org.fxyz.shapes.composites.PolyLine3D;

import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Colee on 4/18/2015.
 */
public class DDGraph extends Region {
	private Box testBox;
	private PerspectiveCamera camera;
	private Group root;
	private DDGraphPane pane;
	private DDWell well;
	private double holeRadius = 0.75;

	private double depthProperty;
	private double azimuthProperty;
	private double inclinationProperty;
	private double zoomProperty;

	private PhongMaterial material;

	private LinkedList<org.fxyz.geometry.Point3D> points;
	private TargetCurve targetCurve;
	private PolyLine3D targetCurveLine;
	private LinkedList<Cylinder> targetCurveCylinders;
	private ActualCurve actualCurve;

	private SubScene subScene;

	public DDGraph(DDGraphPane pane, DDWell well) {
		this.well = well;
		this.pane = pane;
		this.targetCurve = well.getTargetCurve();
		createContent();
		build();
	}

	private void createContent() {
		root = new Group();
		subScene = new SubScene(root, pane.getWidth(), pane.getHeight());
		subScene.setFill(Color.ALICEBLUE);

		// Create Rotation and Translate Properties
		azimuthProperty = DDGraphPane.AZIMUTH_SLIDER_DEFAULT;
		inclinationProperty = DDGraphPane.INCLINATION_SLIDER_DEFAULT;
		zoomProperty = DDGraphPane.ZOOM_SLIDER_DEFAULT;
		depthProperty = DDGraphPane.DEPTH_SLIDER_DEFAULT;

		// Setup and Add Camera
		camera = new PerspectiveCamera(true);
		camera.getTransforms().addAll(
				new Rotate(azimuthProperty, Rotate.Y_AXIS),
				new Rotate(inclinationProperty, Rotate.X_AXIS),
				new Translate(0, depthProperty, -zoomProperty));
		camera.setFarClip(500);
		subScene.setCamera(camera);

		this.getChildren().add(subScene);
	}


	public DDGraph() {
		createContent();
	}

	// Displays the content from DDWell onto the graph
	public void build() {
		LinkedList<Point3D> targetPoints = well.getTargetPoints();
		LinkedList<Point3D> actualPoints = well.getActualPoints();

		// build a line
		points = new LinkedList<org.fxyz.geometry.Point3D>();
		for (Point3D point : targetPoints) {
			points.add(new org.fxyz.geometry.Point3D((float) point.getX(),
					(float) point.getY(), (float) point.getZ()));
		}

		// build a hole out of cylinders
		root.getChildren().remove(targetCurveCylinders);
		double depth = 0;
		targetCurveCylinders = new LinkedList<Cylinder>();
		for (int i = 0; i < targetPoints.size() - 1; i++) {
			Rotate rx = new Rotate();
			rx.setAxis(Rotate.X_AXIS);
			Rotate ry = new Rotate();
			rx.setAxis(Rotate.Y_AXIS);
			Rotate rz = new Rotate();
			rx.setAxis(Rotate.Z_AXIS);
			double height = targetPoints.get(i).distance(targetPoints.get(i+1));
			depth += height/2;
			Cylinder newCylinder = new Cylinder(holeRadius, height);
			double angle = targetPoints.get(i).angle(targetPoints.get(i + 1));
			Point3D axis = targetPoints.get(i+1).subtract(targetPoints.get(i));
			Point3D midpoint = targetPoints.get(i).midpoint(targetPoints.get(i+1));
			double az = targetCurve.getAzimuthAt(depth);
			double inc = targetCurve.getInclinationAt(depth);
			depth += height/2;
			newCylinder.setTranslateX(midpoint.getX());
			newCylinder.setTranslateY(midpoint.getY());
			newCylinder.setTranslateZ(midpoint.getZ());
			rx.setAngle(az);
			ry.setAngle(inc);
			newCylinder.getTransforms().addAll(ry, rx);

			if(height > 0.01)
				targetCurveCylinders.add(newCylinder);
		}
		this.setWidth(pane.getWidth());
		this.setHeight(pane.getHeight());
		root.getChildren().addAll(targetCurveCylinders);
	}

	public void changeZoom(double depth) {
		this.zoomProperty = depth;
		resetCameraPosition();
	}

	public void changeAzimuth(double azimuth) {
		this.azimuthProperty = azimuth;
		resetCameraPosition();
	}

	public void changeInclination(double inclination) {
		this.inclinationProperty = inclination;
		resetCameraPosition();
	}

	public void changeDepth(double depth) {
		this.depthProperty = depth;
		resetCameraPosition();
	}

	public void resetCameraPosition() {
		Point3D translateVector = DDCurveData.sphereToCart(zoomProperty,
				270 - azimuthProperty, 180 - inclinationProperty);
		Point3D depthAdjustedTranslateVector = targetCurve.getPointAt(
				this.depthProperty).add(translateVector);
		camera.getTransforms().remove(0, 3);
		camera.getTransforms().addAll(
				new Translate(depthAdjustedTranslateVector.getX(),
						depthAdjustedTranslateVector.getY(),
						depthAdjustedTranslateVector.getZ()),
				new Rotate(azimuthProperty, Rotate.Y_AXIS),
				new Rotate(inclinationProperty - 90, Rotate.X_AXIS));
	}

}

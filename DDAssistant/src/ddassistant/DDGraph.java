package ddassistant;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.LinkedList;

/**
 * Created by Colee on 4/18/2015.
 */
public class DDGraph extends StackPane {
	private Box testBox;
	private PerspectiveCamera camera;
	private Group root;
	private SplitPane parent;
	private DDWell well;
	private double holeRadius = 0.75;

	private double depthProperty;
	private double azimuthProperty;
	private double inclinationProperty;
	private double zoomProperty;

	private PhongMaterial redMaterial;
	private PhongMaterial greenMaterial;
	private PhongMaterial grayMaterial;

	private TargetCurve targetCurve;
	private LinkedList<Cylinder> targetCurveCylinders;
	private ActualCurve actualCurve;
	private ProjectedCurve projectedCurve;

	private SubScene subScene;

	public DDGraph(SplitPane parent, DDWell well) {
		this.well = well;
		this.parent = parent;
		parent.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			}
		});
		this.targetCurve = well.getTargetCurve();
		this.actualCurve = well.getActualCurve();
		this.projectedCurve = well.getProjectedCurve();
		createContent();
		build();
	}

	public DDGraph() {
		createContent();
	}

	private void createContent() {
		root = new Group();
		subScene = new SubScene(root, parent.getWidth(), parent.getHeight());
		subScene.setFill(Color.ALICEBLUE);

		// set materials
		redMaterial = new PhongMaterial();
		redMaterial.setDiffuseColor(Color.RED);
		greenMaterial = new PhongMaterial();
		greenMaterial.setDiffuseColor(Color.GREEN);
		grayMaterial = new PhongMaterial();
		grayMaterial.setDiffuseColor(Color.GRAY);

		// Create Rotation and Translate Properties
		azimuthProperty = DDGraphPane.AZIMUTH_SLIDER_DEFAULT;
		inclinationProperty = DDGraphPane.INCLINATION_SLIDER_DEFAULT;
		zoomProperty = DDGraphPane.ZOOM_SLIDER_DEFAULT;
		depthProperty = DDGraphPane.DEPTH_SLIDER_DEFAULT;

		// Setup and Add Camera
		camera = new PerspectiveCamera(true);
		camera.getTransforms().addAll(new Translate(0, 0, -zoomProperty));
		camera.setFarClip(500);
		subScene.setCamera(camera);

		targetCurveCylinders = new LinkedList<Cylinder>();
		this.getChildren().add(subScene);
	}

	private void drawCurve(DDCurveData curve, PhongMaterial material) {
		LinkedList<Point3D> points = well.getTargetPoints();
		LinkedList<Point3D> curvePoints = curve.getPoints();
		LinkedList<Cylinder> curveCylinders = new LinkedList<Cylinder>();
		double depth = 0;
		for (int i = 0; i < curve.getPoints().size() - 1; i++) {
			Rotate rx = new Rotate();
			rx.setAxis(Rotate.X_AXIS);
			Rotate ry = new Rotate();
			ry.setAxis(Rotate.Y_AXIS);
			Rotate rz = new Rotate();
			rz.setAxis(Rotate.Z_AXIS);
			double height = curvePoints.get(i).distance(curvePoints.get(i + 1));
			depth += height / 2;
			Cylinder newCylinder = new Cylinder(holeRadius, height);
			Point3D midpoint = curvePoints.get(i).midpoint(curvePoints.get(i + 1));
			double az = curve.getAzimuthAt(depth);
			double inc = curve.getInclinationAt(depth);
			depth += height / 2;
			newCylinder.setTranslateX(midpoint.getX());
			newCylinder.setTranslateY(midpoint.getY());
			newCylinder.setTranslateZ(midpoint.getZ());
			rx.setAngle(inc);
			ry.setAngle(90 - az);
			newCylinder.getTransforms().addAll(ry, rx);
			newCylinder.setMaterial(material);

			if (height > 0.01)
				curveCylinders.add(newCylinder);
		}

		root.getChildren().addAll(curveCylinders);

	}


	private void drawWindow(TargetCurve targetCurve, PhongMaterial greenMaterial) {
	}

	// Displays the content from DDWell onto the graph
	public void build() {
		// get rid of objects in the scene
		for (Object o : root.getChildren()) {
			if (o != subScene)
				root.getChildren().remove(o);
		}
		drawCurve(targetCurve, greenMaterial);
		drawWindow(targetCurve, greenMaterial);
		drawCurve(actualCurve, redMaterial);
		drawCurve(projectedCurve, grayMaterial);

		// build a hole out of cylinders
		for (Cylinder one : targetCurveCylinders) {
			root.getChildren().remove(one);
		}
		double depth = 0;
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

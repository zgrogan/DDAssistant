package ddassistant;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
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
	private boolean drawTargetCurve = true;
	private boolean drawTargetWIndow = true;
	private ActualCurve actualCurve;
	private boolean drawActualCurve = true;
	private ProjectedCurve projectedCurve;
	private boolean drawProjectedCurve = false;

	private SubScene subScene;

	private double width, height;

	public DDGraph(double width, double height) {
		this.width = width;
		this.height = height;
		createContent();
	}

	public void setWell(DDWell well){
		this.well = well;
		this.targetCurve = well.getTargetCurve();
		this.actualCurve = well.getActualCurve();
		build();
	}

	public void updateSize(double width, double height){
		subScene.setWidth(width);
		subScene.setHeight(height);
	}

	private void createContent() {
		root = new Group();
		subScene = new SubScene(root, width, height);
		subScene.setFill(Color.ALICEBLUE);

		// set materials
		redMaterial = new PhongMaterial();
		redMaterial.setSpecularColor(Color.WHITE);
		redMaterial.setDiffuseMap(new Image("lib/drillPipeTexture.png"));
		greenMaterial = new PhongMaterial();
		greenMaterial.setSpecularColor(Color.GREEN);
		greenMaterial.setDiffuseMap(new Image("lib/greenTransparent.png"));
		grayMaterial = new PhongMaterial();
		grayMaterial.setDiffuseColor(Color.GRAY);

		// Setup and Add Camera
		camera = new PerspectiveCamera(true);
		camera.getTransforms().addAll(new Rotate(), new Rotate(), new Translate(0, 0, -zoomProperty));
		camera.setFarClip(3000);
		subScene.setCamera(camera);

		this.getChildren().add(subScene);
	}

	private void drawWindow(TargetCurve curve, PhongMaterial material) {
		LinkedList<Point3D> points = well.getTargetPoints();
		LinkedList<Point3D> curvePoints = curve.getPoints();
		LinkedList<Box> curveBoxes = new LinkedList<Box>();
		double hi = well.getTargetWindowHi();
		double low = well.getTargetWindowLow();
		double left = well.getTargetWindowLeft();
		double right = well.getTargetWindowRight();
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
			Box newBox = new Box(hi + low, height, left + right);
			Point3D midpoint = curvePoints.get(i).midpoint(curvePoints.get(i + 1));
			double az = curve.getAzimuthAt(depth);
			double inc = curve.getInclinationAt(depth);
			depth += height / 2;
			newBox.setTranslateX(midpoint.getX());
			newBox.setTranslateY(midpoint.getY());
			newBox.setTranslateZ(midpoint.getZ());
			rx.setAngle(inc);
			ry.setAngle(90 - az);
			newBox.getTransforms().addAll(ry, rx);
			newBox.setMaterial(material);

			if (height > 0.01)
				curveBoxes.add(newBox);
		}

		root.getChildren().addAll(curveBoxes);
	}

	private void drawCurve(DDCurveData curve, PhongMaterial material) {
		LinkedList<Point3D> points = well.getTargetPoints();
		LinkedList<Point3D> curvePoints = curve.getPoints();
		LinkedList<Cylinder> curveCylinders = new LinkedList<Cylinder>();
		double depth = 0;
		for (int i = 0; i < curve.getPoints().size() - 1; i++) {
			// set up axes
			Rotate rx = new Rotate();
			rx.setAxis(Rotate.X_AXIS);
			Rotate ry = new Rotate();
			ry.setAxis(Rotate.Y_AXIS);
			Rotate rz = new Rotate();
			rz.setAxis(Rotate.Z_AXIS);

			// calculate dimensions and location of curve segment
			double height = curvePoints.get(i).distance(curvePoints.get(i + 1));
			depth += height / 2;
			Cylinder newCylinder = new Cylinder(holeRadius, height);
			Point3D midpoint = curvePoints.get(i).midpoint(curvePoints.get(i + 1));
			double az = curve.getAzimuthAt(depth);
			double inc = curve.getInclinationAt(depth);
			depth += height / 2;

			// move and rotate the object in place
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



	// Displays the content from DDWell onto the graph
	public void build() {
		// get rid of any objects in the scene
		LinkedList<Object> toRemove = new LinkedList<Object>();
		for (Object o : root.getChildren()) {
			if (o != subScene)
				toRemove.add(o);
		}
		root.getChildren().removeAll(toRemove);

		// draw specified objects
		if(drawTargetWIndow)
		  drawWindow(targetCurve, greenMaterial);
		if(drawTargetCurve)
		  drawCurve(targetCurve, greenMaterial);
		if(drawActualCurve)
		if (!actualCurve.getPoints().isEmpty());
		  drawCurve(actualCurve, redMaterial);
		if(drawProjectedCurve)
			drawCurve(projectedCurve, grayMaterial);
//		if (!projectedCurve.getPoints().isEmpty());
//		    drawCurve(projectedCurve, grayMaterial);

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

	public void redraw() {
		this.build();
		resetCameraPosition();
	}
}

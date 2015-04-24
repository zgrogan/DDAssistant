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

	private double depthProperty;
	private double azimuthProperty;
	private double inclinationProperty;
	private double zoomProperty;

	private PhongMaterial material;

	private LinkedList<org.fxyz.geometry.Point3D> points;
	private TargetCurve targetCurve;
	private ActualCurve actualCurve;

	private SubScene subScene;

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
		material = new PhongMaterial(Color.RED);

		// Create the Box
		testBox = new Box(5, 0.2, 0.2);
		testBox.setMaterial(material);

		this.getChildren().add(subScene);
	}

	public DDGraph(DDGraphPane pane, DDWell well) {
		this.well = well;
		this.pane = pane;
		this.targetCurve = well.getTargetCurve();
		createContent();
		build();
	}

	public DDGraph() {
		createContent();
	}

	// Displays the content from DDWell onto the graph
	public void build() {
		LinkedList<Point3D> targetPoints = well.getTargetPoints();
		LinkedList<Point3D> actualPoints = well.getActualPoints();

		points = new LinkedList<org.fxyz.geometry.Point3D>();
		for (Point3D point : targetPoints) {
			points.add(new org.fxyz.geometry.Point3D((float) point.getX(),
					(float) point.getY(), (float) point.getZ()));
		}
		PolyLine3D line3d = new PolyLine3D(points, 1, Color.BLACK);
		this.setWidth(pane.getWidth());
		this.setHeight(pane.getHeight());
		root.getChildren().addAll(line3d);
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
		System.out.println("TV" + translateVector);
		Point3D depthAdjustedTranslateVector = targetCurve.getPointAt(
				this.depthProperty).add(translateVector);
		System.out.println("DAJ" + depthAdjustedTranslateVector);
		camera.getTransforms().remove(0, 3);
		camera.getTransforms().addAll(
				new Translate(depthAdjustedTranslateVector.getX(),
						depthAdjustedTranslateVector.getY(),
						depthAdjustedTranslateVector.getZ()),
				new Rotate(azimuthProperty, Rotate.Y_AXIS),
				new Rotate(inclinationProperty - 90, Rotate.X_AXIS));

	}

}

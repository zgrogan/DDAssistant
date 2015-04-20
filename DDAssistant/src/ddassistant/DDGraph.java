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
public class DDGraph extends Region{
    private Box testBox;
    private PerspectiveCamera camera;
    private Group root;
    private DDWindow window;
    private DDWell well;

    private Rotate latRotate;
    private Rotate longRotate;
    private Translate zoomTranslate;

    private double depthProperty;
    private double latitudeProperty;
    private double longitudeProperty;
    private double zoomProperty;

    private PhongMaterial material;

    private void createContent(){
        root = new Group();
        SubScene subScene = new SubScene(root, DDWindow.WIDTH, DDWindow.HEIGHT);
        subScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    double mouseX = event.getX();
                    double mouseY = event.getY();


                }
            }
        });
        subScene.setFill(Color.ALICEBLUE);

        // Create Rotation and Translate Properties
        latitudeProperty = DDGraphPane.LATITUDE_SLIDER_DEFAULT;
        longitudeProperty = DDGraphPane.LONGITUDE_SLIDER_DEFAULT;
        zoomProperty = DDGraphPane.ZOOM_SLIDER_DEFAULT;
        depthProperty = DDGraphPane.DEPTH_SLIDER_DEFAULT;

        // Setup and Add Camera
        camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll(
                new Rotate(latitudeProperty, Rotate.Y_AXIS),
                new Rotate(longitudeProperty, Rotate.X_AXIS),
                new Translate(0, depthProperty, -zoomProperty)
        );

        camera.setFarClip(500);
        subScene.setCamera(camera);

        // Create and Add points
        List<org.fxyz.geometry.Point3D> points = new LinkedList<org.fxyz.geometry.Point3D>();
        for(int i = 0; i < 10; i++){
            org.fxyz.geometry.Point3D point = new org.fxyz.geometry.Point3D(i, (float) Math.pow(i, 2), i);
            points.add(point);
        }
        PolyLine3D line3d = new PolyLine3D(points, 1, Color.RED);
        root.getChildren().addAll(line3d);


        material = new PhongMaterial(Color.RED);
        // Create the Box
        testBox = new Box(5, 0.2, 0.2);
        testBox.setMaterial(material);







        // Build the scene graph




        this.getChildren().add(subScene);
    }
    public DDGraph(DDWell well){
        this.well = well;
    }

    public DDGraph(){
        createContent();
    }

    public void build(){

    }


    public void changeZoom(int zoom){
        this.zoomProperty = zoom;

        camera.getTransforms().remove(0, 3);
        camera.getTransforms().addAll(
                new Rotate(-latitudeProperty, Rotate.Y_AXIS),
                new Rotate(-longitudeProperty, Rotate.X_AXIS),
                new Translate(0, depthProperty, -zoomProperty)
        );
    }

    public void changeLatitude(int latitude){
        this.latitudeProperty = latitude;

        camera.getTransforms().remove(0, 3);
        camera.getTransforms().addAll(
                new Rotate(-latitudeProperty, Rotate.Y_AXIS),
                new Rotate(-longitudeProperty, Rotate.X_AXIS),
                new Translate(0, depthProperty, -zoomProperty)
        );
    }

    public void changeLongitude(int longitude){
        this.longitudeProperty = longitude;

        camera.getTransforms().remove(0, 3);
        camera.getTransforms().addAll(
                new Rotate(-latitudeProperty, Rotate.Y_AXIS),
                new Rotate(-longitudeProperty, Rotate.X_AXIS),
                new Translate(0, depthProperty, -zoomProperty)
        );
    }

    public void changeDepth(int depth){
        this.depthProperty = depth;

        camera.getTransforms().remove(0, 3);
        camera.getTransforms().addAll(
                new Rotate(-latitudeProperty, Rotate.Y_AXIS),
                new Rotate(-longitudeProperty, Rotate.X_AXIS),
                new Translate(testBox.getTranslateY(), testBox.getTranslateX(), -zoomProperty)
        );
    }

}

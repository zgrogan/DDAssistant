package ddassistant;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Colee on 4/14/2015.
 */
public class DDGraphPane extends Region{

    /*
    * Region->VBox->HBox->GraphControls
    *              |
    *              ->Graph
    *
    * */
    private final Label DEPTH_SLIDER_LABEL = new Label("Depth: ");
    private final Label ZOOM_SLIDER_LABEL = new Label("Zoom: ");
    private final Label LATITUDE_SLIDER_LABEL = new Label("Latitude: ");
    private final Label LONGITUDE_SLIDER_LABEL = new Label("Longitude: ");

    // Depth Attributes
    private final double DEPTH_SLIDER_MIN = 0;
    private double DEPTH_SLIDER_MAX = 100;
    public final static double DEPTH_SLIDER_DEFAULT = 0;

    // Zoom Attributes
    private final double ZOOM_SLIDER_MIN = 0;
    private final double ZOOM_SLIDER_MAX = 250;
    public final static double ZOOM_SLIDER_DEFAULT = 30;

    // Latitude Attributes
    private final double LATITUDE_SLIDER_MIN = -180;
    private final double LATITUDE_SLIDER_MAX = 180;
    public final static double LATITUDE_SLIDER_DEFAULT = 0;

    // Longitude Attributes
    private final double LONGITUDE_SLIDER_MIN = -180;
    private final double LONGITUDE_SLIDER_MAX = 180;
    public final static double LONGITUDE_SLIDER_DEFAULT = 0;

    private Slider depthSlider;
    private TextField depthTextField;

    private Slider zoomSlider;
    private TextField zoomTextField;

    private Slider latitudeSlider;
    private TextField latitudeTextField;

    private Slider longitudeSlider;
    private TextField longitudeTextField;

    private DDWindow window;
    private DDGraph graph;

    private HBox hbox; // HBox used to store all of the Graph Controls
    private VBox vbox; // VBox used to store

    private void initGraphControls(){
        // Depth Slider
        depthSlider = new Slider(DEPTH_SLIDER_MIN, DEPTH_SLIDER_MAX, DEPTH_SLIDER_DEFAULT);
        depthTextField = new TextField(Double.toString(DEPTH_SLIDER_DEFAULT));
        depthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                depthTextField.setText("" + getDepthSliderIntValue());
                graph.changeDepth(getDepthSliderIntValue());
            }
        });
        hbox.getChildren().addAll(DEPTH_SLIDER_LABEL, depthTextField, depthSlider);

        // Zoom Slider
        zoomSlider = new Slider(ZOOM_SLIDER_MIN, ZOOM_SLIDER_MAX, ZOOM_SLIDER_DEFAULT);
        zoomTextField = new TextField(Double.toString(ZOOM_SLIDER_DEFAULT));
        zoomSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                zoomTextField.setText("" + getZoomSliderIntValue());
                graph.changeZoom(getZoomSliderIntValue());
            }
        });
        hbox.getChildren().addAll(ZOOM_SLIDER_LABEL, zoomTextField, zoomSlider);

        // Latitude Slider
        latitudeSlider = new Slider(LATITUDE_SLIDER_MIN, LATITUDE_SLIDER_MAX, LATITUDE_SLIDER_DEFAULT);
        latitudeTextField = new TextField(Double.toString(LATITUDE_SLIDER_DEFAULT));
        latitudeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                latitudeTextField.setText("" + getLatitudeSliderIntValue());
                graph.changeLatitude(getLatitudeSliderIntValue());
            }
        });
        hbox.getChildren().addAll(LATITUDE_SLIDER_LABEL, latitudeTextField, latitudeSlider);

        // Longitude Slider
        longitudeSlider = new Slider(LONGITUDE_SLIDER_MIN, LONGITUDE_SLIDER_MAX, LONGITUDE_SLIDER_DEFAULT);
        longitudeTextField = new TextField(Double.toString(LONGITUDE_SLIDER_DEFAULT));
        longitudeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                longitudeTextField.setText("" + getLongitudeSliderIntValue());
                graph.changeLongitude(getLongitudeSliderIntValue());
            }
        });
        hbox.getChildren().addAll(LONGITUDE_SLIDER_LABEL, longitudeTextField, longitudeSlider);
    }

    public DDGraphPane(DDWindow window){
        this.window = window;
        vbox = new VBox();
        hbox = new HBox();
        initGraphControls();
        vbox.getChildren().add(hbox);
        this.getChildren().add(vbox);
    }

    public void createGraph(){
        graph = new DDGraph(window.getWell());
        vbox.getChildren().add(graph);
    }

    public void removeGraph(){
        vbox.getChildren().removeAll(graph);
        graph = null;
    }

    public int getZoomSliderIntValue(){
        return (int) zoomSlider.getValue();
    }

    public void setZoomSliderValue(double value){
        this.zoomSlider.setValue(value);
    }

    public int getLatitudeSliderIntValue(){
        return (int) latitudeSlider.getValue();
    }

    public void setLatitudeSliderValue(double value){
        this.latitudeSlider.setValue(value);
    }

    public int getLongitudeSliderIntValue(){
        return (int) longitudeSlider.getValue();
    }

    public void setLongitudeSliderValue(double value){
        this.longitudeSlider.setValue(value);
    }

    public int getDepthSliderIntValue(){
        return (int) depthSlider.getValue();
    }

    public void setDepthSliderValue(double value){
        this.depthSlider.setValue(value);
    }

}

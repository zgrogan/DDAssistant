package ddassistant;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.layout.Region;

/**
 * Created by Colee on 4/14/2015.
 */
@SuppressWarnings("restriction")
public class DDGraphPane extends HBox{
    private final Label DEPTH_SLIDER_LABEL = new Label("Depth: ");
    private final Label ZOOM_SLIDER_LABEL = new Label("Zoom: ");
    private final Label LATITUDE_SLIDER_LABEL = new Label("Latitude: ");
    private final Label LONGITUDE_SLIDER_LABEL = new Label("Longitude: ");
    private Scene ddGraphScene;

    // Depth Attributes
    private final double DEPTH_SLIDER_MIN = 0;
    private final double DEPTH_SLIDER_MAX = 100;
    private final double DEPTH_SLIDER_DEFAULT = 0;

    // Zoom Attributes
    private final double ZOOM_SLIDER_MIN = 0;
    private final double ZOOM_SLIDER_MAX = 100;
    private final double ZOOM_SLIDER_DEFAULT = 0;

    // Latitude Attributes
    private final double LATITUDE_SLIDER_MIN = -180;
    private final double LATITUDE_SLIDER_MAX = 180;
    private final double LATITUDE_SLIDER_DEFAULT = 0;

    // Longitude Attributes
    private final double LONGITUDE_SLIDER_MIN = -180;
    private final double LONGITUDE_SLIDER_MAX = 180;
    private final double LONGITUDE_SLIDER_DEFAULT = 0;

    private Slider depthSlider;
    private TextField depthTextField;

    private Slider zoomSlider;
    private TextField zoomTextField;

    private Slider latitudeSlider;
    private TextField latitudeTextField;

    private Slider longitudeSlider;
    private TextField longitudeTextField;
    
    private Region graph;
    private DDWell well;

    private void InitGraphControl(){
        // Depth Slider
        depthSlider = new Slider(DEPTH_SLIDER_MIN, DEPTH_SLIDER_MAX, DEPTH_SLIDER_DEFAULT);
        depthTextField = new TextField(Double.toString(DEPTH_SLIDER_DEFAULT));
        depthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // May want to handle this in a separate method
            }
        });
        this.getChildren().addAll(DEPTH_SLIDER_LABEL, depthTextField, depthSlider);

        // Zoom Slider
        zoomSlider = new Slider(ZOOM_SLIDER_MIN, ZOOM_SLIDER_MAX, ZOOM_SLIDER_DEFAULT);
        zoomTextField = new TextField(Double.toString(ZOOM_SLIDER_DEFAULT));
        zoomSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // May want to handle this in a separate method
            }
        });
        this.getChildren().addAll(ZOOM_SLIDER_LABEL, zoomTextField, zoomSlider);

        // Latitude Slider
        latitudeSlider = new Slider(LATITUDE_SLIDER_MIN, LATITUDE_SLIDER_MAX, LATITUDE_SLIDER_DEFAULT);
        latitudeTextField = new TextField(Double.toString(LATITUDE_SLIDER_DEFAULT));
        latitudeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // May want to handle this in a separate method
            }
        });
        this.getChildren().addAll(LATITUDE_SLIDER_LABEL, latitudeTextField, latitudeSlider);

        // Longitude Slider
        longitudeSlider = new Slider(LONGITUDE_SLIDER_MIN, LONGITUDE_SLIDER_MAX, LONGITUDE_SLIDER_DEFAULT);
        longitudeTextField = new TextField(Double.toString(LONGITUDE_SLIDER_DEFAULT));
        longitudeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // May want to handle this in a separate method
            }
        });
        this.getChildren().addAll(LONGITUDE_SLIDER_LABEL, longitudeTextField, longitudeSlider);
    }

    private void HandleDepthSlider(){

    }
    private void InitGraphPane(){
    	
    }
    

    public DDGraphPane(){
    	InitGraphControl();
        InitGraph();
        // InitGraphInfo();

        InitGraphPane();
    }
    private void InitGraph() {
		this.graph = new DDGraph(this, well);
	}

	public DDGraphPane(DDWell well){
    	this();
    	this.well = well;
    }


}

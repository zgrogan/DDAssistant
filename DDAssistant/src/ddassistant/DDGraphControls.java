package ddassistant;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
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
public class DDGraphControls extends VBox {

	/*
	 * Region->VBox->HBox->GraphControls | ->Graph
	 */
	private final Label DEPTH_SLIDER_LABEL = new Label("Depth: ");
	private final Label ZOOM_SLIDER_LABEL = new Label("Zoom: ");
	private final Label azimuth_SLIDER_LABEL = new Label("azimuth: ");
	private final Label INCLINATION_SLIDER_LABEL = new Label("Longitude: ");
	private static final double MAX_TEXTFIELD_WIDTH = 75;

	// Depth Attributes
	private final double DEPTH_SLIDER_MIN = 0;
	private double depthSliderMax = 1000;
	public final static double DEPTH_SLIDER_DEFAULT = 0;

	// Zoom Attributes
	private final double ZOOM_SLIDER_MIN = 0;
	private final double ZOOM_SLIDER_MAX = 250;
	public final static double ZOOM_SLIDER_DEFAULT = 30;

	// azimuth Attributes
	private final double AZIMUTH_SLIDER_MIN = -180;
	private final double AZIMUTH_SLIDER_MAX = 180;
	public final static double AZIMUTH_SLIDER_DEFAULT = 0;

	// Longitude Attributes
	private final double INCLINATION_SLIDER_MIN = 0;
	private final double INCLINATION_SLIDER_MAX = 180;
	public final static double INCLINATION_SLIDER_DEFAULT = 90;

	private Slider depthSlider;
	private TextField depthTextField;

	private Slider zoomSlider;
	private TextField zoomTextField;

	private Slider azimuthSlider;
	private TextField azimuthTextField;

	private Slider inclinationSlider;
	private TextField inclinationTextField;

	private DDWell well;
	private DDGraph graph;

	private VBox hbox; // HBox used to store all of the Graph Controls
	private VBox vbox; // VBox used to store
	
	//constructors
	public DDGraphControls(DDGraph graph, DDWell well) {
		this.well = well;
		this.graph = graph;
		createGraphControls();
		createListeners();
		SplitPane.setResizableWithParent(this, Boolean.TRUE);
	}

	private void createListeners() {
		// Slider Listeners
		depthSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				depthTextField.setText("" + getDepthSliderValue());
				graph.changeDepth(getDepthSliderValue());
			}
		});

		zoomSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
								Number oldValue, Number newValue) {
				zoomTextField.setText("" + getZoomSliderValue());
				graph.changeZoom(getZoomSliderValue());
			}
		});

		azimuthSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				azimuthTextField.setText("" + getAzimuthSliderValue());
				graph.changeAzimuth(getAzimuthSliderValue());
			}
		});
		
		inclinationSlider.valueProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
						inclinationTextField.setText("" + depthSlider.getValue());
						graph.changeInclination(inclinationSlider.getValue());
					}
				});
		// Text Field Listeners
		
	}
	
	private void createGraphControls() {
		hbox = new VBox();
		vbox = new VBox();
		// Depth Slider
		depthSlider = new Slider(DEPTH_SLIDER_MIN, depthSliderMax,
				DEPTH_SLIDER_DEFAULT);
		depthTextField = new TextField(Double.toString(DEPTH_SLIDER_DEFAULT));
		depthTextField.setMaxWidth(MAX_TEXTFIELD_WIDTH);
		hbox.getChildren().addAll(DEPTH_SLIDER_LABEL, depthTextField,
				depthSlider);

		// Zoom Slider
		zoomSlider = new Slider(ZOOM_SLIDER_MIN, ZOOM_SLIDER_MAX,
				ZOOM_SLIDER_DEFAULT);
		zoomTextField = new TextField(Double.toString(ZOOM_SLIDER_DEFAULT));
		zoomTextField.setMaxWidth(MAX_TEXTFIELD_WIDTH);
		hbox.getChildren().addAll(ZOOM_SLIDER_LABEL, zoomTextField, zoomSlider);

		// azimuth Slider
		azimuthSlider = new Slider(AZIMUTH_SLIDER_MIN, AZIMUTH_SLIDER_MAX,
				AZIMUTH_SLIDER_DEFAULT);
		azimuthTextField = new TextField(
				Double.toString(AZIMUTH_SLIDER_DEFAULT));
		azimuthTextField.setMaxWidth(MAX_TEXTFIELD_WIDTH);
		hbox.getChildren().addAll(azimuth_SLIDER_LABEL, azimuthTextField,
				azimuthSlider);

		// Longitude Slider
		inclinationSlider = new Slider(INCLINATION_SLIDER_MIN,
				INCLINATION_SLIDER_MAX, INCLINATION_SLIDER_DEFAULT);
		inclinationTextField = new TextField(
				Double.toString(INCLINATION_SLIDER_DEFAULT));
		inclinationTextField.setMaxWidth(MAX_TEXTFIELD_WIDTH);
		hbox.getChildren().addAll(INCLINATION_SLIDER_LABEL, inclinationTextField,
				inclinationSlider);
		
		vbox.getChildren().add(hbox);
		this.getChildren().add(vbox);
	}


	public void setWell(DDWell well){
		this.well = well;
	}

	public void removeGraph() {
		vbox.getChildren().removeAll(graph);
		graph = null;
	}

	public double getZoomSliderValue() {
		return zoomSlider.getValue();
	}

	public void setZoomSliderValue(double value) {
		this.zoomSlider.setValue(value);
	}

	public double getAzimuthSliderValue() {
		return azimuthSlider.getValue();
	}

	public void setAzimuthSliderValue(double value) {
		this.azimuthSlider.setValue(value);
	}

	public double getLongitudeSliderValue() {
		return inclinationSlider.getValue();
	}

	public void setLongitudeSliderValue(double value) {
		this.inclinationSlider.setValue(value);
	}

	public double getDepthSliderValue() {
		return depthSlider.getValue();
	}

	public void setDepthSliderValue(double value) {
		this.depthSlider.setValue(value);
	}

	public void redraw() {
		graph.build();
	}
}

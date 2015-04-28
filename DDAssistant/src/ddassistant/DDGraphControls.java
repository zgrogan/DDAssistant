package ddassistant;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Colee on 4/14/2015.
 */
public class DDGraphControls extends VBox {

	/*
	 * Region->VBox->HBox->GraphControls | ->Graph
	 */
	private final Label DEPTH_SLIDER_LABEL = new Label("Depth: ");
	private final Label ZOOM_SLIDER_LABEL = new Label("Zoom: ");
	private final Label AZIMUTH_SLIDER_LABEL = new Label("Rotate: ");
	private final Label INCLINATION_SLIDER_LABEL = new Label("Up/Down: ");
	private static final double MAX_TEXTFIELD_WIDTH = 75;

	// Depth Attributes
	private final double DEPTH_SLIDER_MIN = 0;
	private double depthSliderMax = 1000;
	public final static double DEPTH_SLIDER_DEFAULT = 0;

	// Zoom Attributes
	private final double ZOOM_SLIDER_MIN = 0;
	private final double ZOOM_SLIDER_MAX = 500;
	public final static double ZOOM_SLIDER_DEFAULT = 350;

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

	private DDWindow window;
	private DDGraph graph;

	private VBox hbox; // HBox used to store all of the Graph Controls
	private VBox vbox; // VBox used to store
	
	//constructors
	public DDGraphControls(DDGraph graph, DDWindow window) {
		this.window = window;
		this.graph = graph;
		createGraphControls();
		createListeners();
		//SplitPane.setResizableWithParent(this, Boolean.TRUE);
	}

	private void createListeners() {
		// Slider Listeners
		depthSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
								Number oldValue, Number newValue) {
				depthTextField.setText("" + (int) getDepthSliderValue());
				graph.changeDepth(getDepthSliderValue());
			}
		});

		/*zoomSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
								Number oldValue, Number newValue) {
				ZOOM_SLIDER_LABEL.setText("Zoom: " + (getZoomSliderValue() - ZOOM_SLIDER_MAX));
				graph.changeZoom(getZoomSliderValue());
			}
		});*/

		/*azimuthSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				try {
					AZIMUTH_SLIDER_LABEL.setText("Rotate: " + getAzimuthSliderValue());
					graph.changeAzimuth(getAzimuthSliderValue());
				}catch(){

				}
			}
		});*/
		
		/*inclinationSlider.valueProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
						try {
							INCLINATION_SLIDER_LABEL.setText("Up/Down: " + inclinationSlider.getValue());
							graph.changeInclination(inclinationSlider.getValue());
						}catch(){

						}
					}
				});*/

		// Text Field Listeners
		depthTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
				double newNumber = Double.parseDouble(newValue);
				if (newNumber >= DEPTH_SLIDER_MIN && newNumber <= depthSliderMax) {
					depthSlider.setValue(Double.parseDouble(newValue));
					graph.changeDepth(Double.parseDouble(depthTextField.getText()));
				} else {
					depthTextField.setText(oldValue);
				}

			}
		});

		zoomSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
								Number oldValue, Number newValue) {
				ZOOM_SLIDER_LABEL.setText("Zoom: " + (getZoomSliderValue() - ZOOM_SLIDER_MAX));
				graph.changeZoom(ZOOM_SLIDER_MAX - getZoomSliderValue());
			}
		});

		azimuthSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				AZIMUTH_SLIDER_LABEL.setText("Rotate: " + getAzimuthSliderValue());
				graph.changeAzimuth(getAzimuthSliderValue());
			}
		});

		inclinationSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				INCLINATION_SLIDER_LABEL.setText("Up/Down: " + inclinationSlider.getValue());
				graph.changeInclination(inclinationSlider.getValue());
			}
		});
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
		ZOOM_SLIDER_LABEL.setMaxWidth(100);
		zoomSlider = new Slider(ZOOM_SLIDER_MIN, ZOOM_SLIDER_MAX,
				ZOOM_SLIDER_DEFAULT);
		hbox.getChildren().addAll(ZOOM_SLIDER_LABEL, zoomSlider);

		// azimuth Slider
		AZIMUTH_SLIDER_LABEL.setMaxWidth(120);
		azimuthSlider = new Slider(AZIMUTH_SLIDER_MIN, AZIMUTH_SLIDER_MAX,
				AZIMUTH_SLIDER_DEFAULT);
		hbox.getChildren().addAll(AZIMUTH_SLIDER_LABEL, azimuthSlider);

		// Inclination Slider
		INCLINATION_SLIDER_LABEL.setMaxWidth(120);
		inclinationSlider = new Slider(INCLINATION_SLIDER_MIN,
				INCLINATION_SLIDER_MAX, INCLINATION_SLIDER_DEFAULT);
		hbox.getChildren().addAll(INCLINATION_SLIDER_LABEL, inclinationSlider);
		
		vbox.getChildren().add(hbox);
		this.getChildren().add(vbox);
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

	public double getInclinationSliderValue() {
		return inclinationSlider.getValue();
	}

	public void setInclinationSliderValue(double value) {
		this.inclinationSlider.setValue(value);
	}

	public double getDepthSliderValue() {
		return depthSlider.getValue();
	}

	public void setDepthSliderValue(double value) {
		this.depthSlider.setValue(value);
	}

	public void redraw() {
			graph.changeAzimuth(azimuthSlider.getValue());
			graph.changeDepth(depthSlider.getValue());
			graph.changeZoom(ZOOM_SLIDER_MAX - zoomSlider.getValue());
			graph.changeInclination(inclinationSlider.getValue());
			depthSliderMax = window.getWell().getTargetCurve().getTargetDepth();
			depthSlider.setMax(depthSliderMax);
			graph.build();
	}
}

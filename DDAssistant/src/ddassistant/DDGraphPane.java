package ddassistant;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

/**
 * Created by cgideon on 4/24/15.
 */
public class DDGraphPane extends StackPane {
    private static double HEIGHT;
    private static double WIDTH;

    // Private Fields
    private DDWindow window;
    private DDGraphControls ddGraphControls;
    private DDGraph ddGraph;
    private SplitPane splitPane;

    private double width;
    private double height;


    public DDGraphPane(DDWindow window){
        this.window = window;
        ScrollPane scrollPane = new ScrollPane();
        ScrollPane scrollGraph = new ScrollPane();
        splitPane = new SplitPane();

        ddGraph = new DDGraph(500, 500, window);

        ddGraphControls = new DDGraphControls(ddGraph, window);

        splitPane.setOrientation(Orientation.HORIZONTAL);
        splitPane.setDividerPosition(0,0.20);


        scrollPane.setContent(ddGraphControls);
        scrollGraph.setContent(ddGraph);
        splitPane.getItems().addAll(scrollPane, scrollGraph);

        splitPane.getDividers().get(0).positionProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                WIDTH = Screen.getPrimary().getVisualBounds().getWidth() * (1 - newValue.doubleValue());
                ddGraph.updateSize(WIDTH, HEIGHT);
            }
        });
        this.getChildren().addAll(splitPane);
    }

    public void updateSize(double height){
        HEIGHT = height;
        ddGraph.updateSize(WIDTH, HEIGHT);
    }

    public void setAzimuth(double azimuth){
        ddGraphControls.setAzimuthSliderValue(azimuth);
        ddGraphControls.redraw();
    }

    public void setInclination(double inclination){
        ddGraphControls.setInclinationSliderValue(inclination);
    }

    public void redrawGraph() {
        ddGraphControls.redraw();
    }
}

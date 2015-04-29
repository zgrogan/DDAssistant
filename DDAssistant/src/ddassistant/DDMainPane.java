package ddassistant;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

/**
 * Created by cgideon on 4/24/15.
 */
public class DDMainPane extends StackPane {

    // Private Fields
    private DDWindow window;
    private DDInformationPane ddInfoPane;
    private DDGraphPane ddGraphPane;
    private SplitPane splitPane;

    private double width, height;

    public DDMainPane(DDWindow window){
        this.window = window;
        splitPane = new SplitPane();
        ddGraphPane = new DDGraphPane(window);
        ddInfoPane = new DDInformationPane(window);

        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.setDividerPosition(0, 0.70);

        splitPane.getItems().addAll(ddGraphPane, ddInfoPane);
        splitPane.getDividers().get(0).positionProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                ddGraphPane.updateSize(Screen.getPrimary().getVisualBounds().getHeight() * newValue.doubleValue());
                ddInfoPane.updateSize(Screen.getPrimary().getVisualBounds().getHeight() * newValue.doubleValue());
            }
        });
        this.getChildren().addAll(splitPane);
    }

    public void setGraphAzimuth(double azimuth){
        ddGraphPane.setAzimuth(azimuth);
    }

    public void setGraphInclination(double inclination){
        ddGraphPane.setInclination(inclination);
    }

    public void redrawGraph() {
        ddGraphPane.redrawGraph();
    }

    public void refresh() {
        redrawGraph();
        refreshInfoPane();
    }

    private void refreshInfoPane() {
        ddInfoPane.refresh();
    }
}

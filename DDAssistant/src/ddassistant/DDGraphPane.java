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
    private DDWell well;
    private DDGraphControls ddGraphControls;
    private DDGraph ddGraph;
    private SplitPane splitPane;

    private double width;
    private double height;


    public DDGraphPane(){
        ScrollPane scrollPane = new ScrollPane();
        ScrollPane scrollGraph = new ScrollPane();
        splitPane = new SplitPane();

        ddGraph = new DDGraph(500, 500);

        ddGraphControls = new DDGraphControls(ddGraph, well);

        splitPane.setOrientation(Orientation.HORIZONTAL);
        splitPane.setDividerPosition(0, 0.1);


        scrollPane.setContent(ddGraphControls);
        scrollGraph.setContent(ddGraph);
        splitPane.getItems().addAll(scrollPane, scrollGraph);
        //ddGraph.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() * scrollGraph.getWidth());

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

    public void setWell(DDWell well){
        this.well = well;
        ddGraph.setWell(well);
        ddGraphControls.setWell(well);
    }
}

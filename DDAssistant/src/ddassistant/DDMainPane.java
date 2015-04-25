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
    private DDWell well;
    private DDInformationPane ddInfoPane;
    private DDGraphPane ddGraphPane;
    private SplitPane splitPane;

    private double width, height;


    public DDMainPane(){
        splitPane = new SplitPane();
        ddGraphPane = new DDGraphPane();
        ddInfoPane = new DDInformationPane(well);

        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.setDividerPosition(0, 0.70);

        splitPane.getItems().addAll(ddGraphPane, ddInfoPane);
        splitPane.getDividers().get(0).positionProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                ddGraphPane.updateSize(Screen.getPrimary().getVisualBounds().getHeight() * newValue.doubleValue());
            }
        });
        this.getChildren().addAll(splitPane);
    }

    public void setWell(DDWell well){
        this.well = well;
        ddGraphPane.setWell(well);
    }

    public void removeWell(){
        this.well = null;
    }
}

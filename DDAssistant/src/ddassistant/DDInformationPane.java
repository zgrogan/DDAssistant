package ddassistant;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Colee on 4/14/2015.
 */
public class DDInformationPane extends Region {

    private final String STRING_SURVEY_TAB = "Survey";
    private final String STRING_SLIDE_ROTATION_TAB = "Slide/Rotation";
    private final String STRING_PROJECTION_TAB = "Projection";
    private final String STRING_WELL_DATA_TAB = "Well Data";


    private TabPane infoTabPane;
    private double height;

    private TableView<String> surveyTable;
    /*
    * Tab surveyTab
    *
    * NEEDS EXPLANATION!
    * */
    private SurveyTab surveyTab;

    /*
    * Tab slide_rotationTab
    *
    * NEEDS EXPLANATION!
    * */
    private Tab slide_rotationTab;

    /*
    * Tab projectionTab
    *
    * NEEDS EXPLANATION!
    * */
    private Tab projectionTab;

    /*
    * Tab BHATab
    *
    * NEEDS EXPLANATION!
    * */
    private Tab BHATab;

    /*
    * Tab wellDataTab
    *
    * NEEDS EXPLANATION!
    * */
    private Tab wellDataTab;

    /*
    *   DDWindow window
    *
    *   This DDWindow variable is used to communicate with the DDWindow class to
    *       pass and receive information to and from the DDGraphControls and DDMenuBar
    * */
    private DDWindow window;

    private void initInformationPane(){


        infoTabPane = new TabPane();
        infoTabPane.setMinWidth(window.WIDTH);

        // Create Survey Tab
        surveyTab = new SurveyTab(window);
        infoTabPane.getTabs().add(surveyTab);

        // Create Slide/Rotation Tab
        slide_rotationTab = new Tab();
        slide_rotationTab.setText(STRING_SLIDE_ROTATION_TAB);
        slide_rotationTab.setClosable(false);
        infoTabPane.getTabs().add(slide_rotationTab);

        // Create Projection Tab
        projectionTab = new Tab();
        projectionTab.setText(STRING_PROJECTION_TAB);
        projectionTab.setClosable(false);
        infoTabPane.getTabs().add(projectionTab);

        // Create Well Data Tab
        wellDataTab = new Tab();
        wellDataTab.setText(STRING_WELL_DATA_TAB);
        wellDataTab.setClosable(false);
        infoTabPane.getTabs().add(wellDataTab);

        this.getChildren().addAll(infoTabPane);
    }

    public DDInformationPane(DDWindow window){
        this.window = window;
        initInformationPane();
    }

    public void updateSize(double height){
        this.height = height;
        surveyTab.updateSize(height);
    }

    public void refresh() {
        infoTabPane.getTabs().remove(surveyTab);
        infoTabPane.getTabs().add(new SurveyTab(window));
    }
}

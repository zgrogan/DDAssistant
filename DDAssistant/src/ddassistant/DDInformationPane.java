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
    private final String STRING_BHA_TAB = "BHA";
    private final String STRING_WELL_DATA_TAB = "Well Data";


    private TabPane infoTabPane;
    private ToolBar infoToolBar;

    private TableView<String> surveyTable;
    /*
    * Tab surveyTab
    *
    * NEEDS EXPLANATION!
    * */
    private Tab surveyTab;

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

    private DDWell well;

    private void initInformationPane(){
        //
        infoToolBar = new ToolBar();
        infoToolBar.setOrientation(Orientation.VERTICAL);
        Button add = new Button("+");
        infoToolBar.getItems().add(add);

        infoTabPane = new TabPane();
        infoTabPane.setMinWidth(window.WIDTH);

        // Create Survey Tab
        surveyTab = new Tab();
        surveyTab.setText(STRING_SURVEY_TAB);
        surveyTab.setClosable(false);
        surveyTable = new TableView<String>();
        surveyTab.setContent(surveyTable);
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

        // Create BHA Tab
        BHATab = new Tab();
        BHATab.setText(STRING_BHA_TAB);
        BHATab.setClosable(false);
        infoTabPane.getTabs().add(BHATab);

        // Create Well Data Tab
        wellDataTab = new Tab();
        wellDataTab.setText(STRING_WELL_DATA_TAB);
        wellDataTab.setClosable(false);
        infoTabPane.getTabs().add(wellDataTab);

        this.getChildren().addAll(infoToolBar, infoTabPane);
    }

    public DDInformationPane(DDWell well){
        this.well = well;
        initInformationPane();
    }
}

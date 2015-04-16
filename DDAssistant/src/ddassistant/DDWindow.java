package ddassistant;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by cgideon on 3/31/15.
 */
public class DDWindow extends Application {

    private double HEIGHT;
    private double WIDTH;
    final private int offset = 50;

    /*
    * BorderPane borderPanes
    *
    * BorderPane is used to help organize the panels(VBox, HBox, etc)
    * */
    private BorderPane borderPane;

    /*
    * MenuBar menuBar
    *
    * MenuBar is used to store Menu items like File, Edit, Help, etc
    * */
    private MenuBar menuBar;

    /*
    * Menu fileMenu
    *
    *
    * */
    private Menu fileMenu;

    /*
    * Menu helpMenu
    *
    *
    * */
    private Menu helpMenu;

    /*
    * VBox graphControlPanel
    *
    * This VBox will store all of our components that will control our graph
    * */
    private VBox graphControlPanel;

    /*
    * HBox informationPanel
    *
    *
    * */
    private VBox informationPanel;

    /*
    * TabPane infoTabPane
    *
    * NEEDS EXPLANATION!
    * */
    private TabPane infoTabPane;
    private Label depthSliderLabel;
    private Label zoomSliderLabel;
    private Label latitudeSliderLabel;
    private Label longitudeSliderLabel;
    private Label targetCheckBoxLabel;
    private Label actualCheckBoxLabel;
    private Label targetWindowCheckBoxLabel;
    private Label projectionCheckBoxLabel;
    private Label hiLowButtonLabel;
    private Label leftRightButtonLabel;

    private Slider depthSlider;

    private Slider zoomSlider;

    private Slider latitudeSlider;

    private Slider longitutdeSlider;

    private CheckBox targetCheckBox;
    private CheckBox actualCheckBox;
    private CheckBox targetWindowCheckBox;
    private CheckBox projectionCheckBox;

    private Button hiLowButton;
    private Button leftRightButton;



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
    * Scene scene
    *
    * Scene is used to store our BorderPane so we can pass it Stage
    * */
    private Scene scene;

    public void start(Stage primaryStage){

        depthSliderLabel = new Label("Depth");
        zoomSliderLabel = new Label("Zoom");
        latitudeSliderLabel = new Label("Latitude");
        longitudeSliderLabel = new Label("Longitude");
        targetCheckBoxLabel = new Label("Target");
        actualCheckBoxLabel = new Label("Actual");
        targetWindowCheckBoxLabel = new Label("Target Window");
        projectionCheckBoxLabel = new Label("Projection");
        hiLowButtonLabel = new Label("Hi/Low");
        leftRightButtonLabel = new Label("Left/Right");
        setScreenSize();
        initComponents();

        primaryStage.setTitle("DDAssistant");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setScreenSize(){
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        HEIGHT = screen.getHeight() - offset;
        WIDTH = screen.getWidth() - offset;
    }

    private MenuBar createMenuBar(){
        menuBar = new MenuBar();
        fileMenu = new Menu("File");

        helpMenu = new Menu("Help");

        menuBar.getMenus().addAll(fileMenu, helpMenu);
        return menuBar;
    }

    private VBox createControlPanel(){
        Label controlLabel = new Label("Graph Controls");
        Separator sep = new Separator();
        graphControlPanel = new VBox();
        graphControlPanel.setSpacing(5);
        // Add graph controls here(Sliders, checkboxes, etc)
        // SLIDER
        graphControlPanel.getChildren().addAll(controlLabel, sep);

        depthSlider = new Slider(0, 100, 0);
        // Going to set this right here, but may want to implement this into a class of its own
        depthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                depthSliderLabel.setText("Depth: " + (int)depthSlider.getValue());
            }
        });

        graphControlPanel.getChildren().addAll(depthSliderLabel, depthSlider);

        zoomSlider = new Slider(0, 100, 0);
        zoomSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                zoomSliderLabel.setText("Zoom: " + (int)zoomSlider.getValue());
            }
        });
        graphControlPanel.getChildren().addAll(zoomSliderLabel, zoomSlider);

        latitudeSlider = new Slider(-180, 180, -180);
        latitudeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                latitudeSliderLabel.setText("Latitude: " + (int)latitudeSlider.getValue());
            }
        });
        graphControlPanel.getChildren().addAll(latitudeSliderLabel, latitudeSlider);

        longitutdeSlider = new Slider(-180, 180, -180);
        longitutdeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                longitudeSliderLabel.setText("Longitude: " + (int)longitutdeSlider.getValue());
            }
        });
        graphControlPanel.getChildren().addAll(longitudeSliderLabel, longitutdeSlider);

        // CHECKBOX
        targetCheckBox = new CheckBox("Target");
        graphControlPanel.getChildren().addAll(targetCheckBox);

        actualCheckBox = new CheckBox("Actual");
        graphControlPanel.getChildren().addAll(actualCheckBox);

        targetWindowCheckBox = new CheckBox("Target Window");
        graphControlPanel.getChildren().addAll(targetWindowCheckBox);

        projectionCheckBox = new CheckBox("Projection");
        graphControlPanel.getChildren().addAll(projectionCheckBox);

        // BUTTONS
        hiLowButton = new Button("Hi/Low");
        graphControlPanel.getChildren().addAll(hiLowButton);

        leftRightButton = new Button("Left/Right");
        graphControlPanel.getChildren().addAll(leftRightButton);

        return graphControlPanel;
    }

    private VBox createInformationPanel(){
        informationPanel = new VBox();

        // Add TabPane here,etc.
        // Maybe separate creating tab pane into own method that returns TabPane
        // A place to write Strings to should go into each tab, maybe a ListView
        // to separate each individual item, see: http://docs.oracle.com/javafx/2/ui_controls/list-view.htm#CEGGEDBF
        infoTabPane = new TabPane();

        surveyTab = new Tab("Survey");
        TableView<String> table = new TableView<String>();
        TableColumn a = new TableColumn("String1");
        table.getColumns().add(a);
        surveyTab.setContent(table);
        slide_rotationTab = new Tab("Slide/Rotation");
        projectionTab = new Tab("Projection");
        BHATab = new Tab("BHA");
        wellDataTab = new Tab("Well Data");

        infoTabPane.getTabs().addAll(surveyTab, slide_rotationTab, projectionTab, BHATab, wellDataTab);

        informationPanel.getChildren().addAll(infoTabPane);
        return informationPanel;
    }

    private void initComponents(){
        borderPane = new BorderPane();

        borderPane.setTop(createMenuBar());

        Accordion accor0 = new Accordion();

        TitledPane tpane0 = new TitledPane();
        tpane0.setContent(createControlPanel());

        accor0.getPanes().add(tpane0);
        borderPane.setLeft(accor0);
        //borderPane.setLeft(createControlPanel());

        Accordion accor = new Accordion();
        TitledPane tpane = new TitledPane();
        tpane.setContent(createInformationPanel());
        accor.getPanes().add(tpane);

        borderPane.setBottom(accor);
        //borderPane.setBottom(createInformationPanel());
        Box a = new Box();
        borderPane.setRight(a);

        scene = new Scene(borderPane, WIDTH, HEIGHT);
    }
}

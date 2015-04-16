package ddassistant;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.beans.EventHandler;

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

    private Menu viewMenu;

    private Menu expandMenu;

    private Menu graphViewMenu;
    private Menu graphDirectionMenu;

    private RadioMenuItem targetMenuItem;
    private RadioMenuItem actualMenuItem;
    private RadioMenuItem targetWindowMenuItem;
    private RadioMenuItem projectionMenuItem;

    private MenuItem hiLowMenuItem;
    private MenuItem leftRightMenuItem;

    private MenuItem graphMenu;
    private MenuItem infoMenu;
    private MenuItem defaultMenu;

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
    private GridPane graphControlPanel;

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
    private ToolBar infoToolBar;
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
        initComponents(primaryStage);

        primaryStage.setTitle("DDAssistant");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setScreenSize(){
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        HEIGHT = screen.getHeight() - offset;
        WIDTH = screen.getWidth() - offset;
    }

    private HBox graphDisplayPanel;
    private HBox createGraphDisplayPanel(){
        graphDisplayPanel = new HBox();
        return graphDisplayPanel;
    }

    private BorderPane graphBorderPane;

    private Button open;
    private Button close;
    private VBox createInformationPanel(){
        informationPanel = new VBox();
        // Create ToolBar and add Buttons to them



        // Add TabPane here,etc.
        // Maybe separate creating tab pane into own method that returns TabPane
        // A place to write Strings to should go into each tab, maybe a ListView
        // to separate each individual item, see: http://docs.oracle.com/javafx/2/ui_controls/list-view.htm#CEGGEDBF

        infoTabPane = new TabPane();
        surveyTab = new Tab("Survey");
        surveyTab.setClosable(false);
        ListView listtable = new ListView();
        TableView<String> table = new TableView<String>();
        TableColumn a = new TableColumn("String1");
        listtable.getItems().add(a);
        surveyTab.setContent(listtable);
        //informationPanel.setVgrow(infoTabPane, Priority.ALWAYS);
        slide_rotationTab = new Tab("Slide/Rotation");
        projectionTab = new Tab("Projection");
        BHATab = new Tab("BHA");
        wellDataTab = new Tab("Well Data");

        infoTabPane.getTabs().addAll(surveyTab, slide_rotationTab, projectionTab, BHATab, wellDataTab);
        informationPanel.getChildren().addAll(infoTabPane);
        return informationPanel;
    }
    private void initComponents(Stage primaryStage){
        DDMenuPane ddMenuPane = new DDMenuPane();
        DDGraphPane ddGraphPane = new DDGraphPane();
        DDInformationPane ddInfoPane = new DDInformationPane();
        createInformationPanel();

        borderPane = new BorderPane();
        borderPane.setTop(ddMenuPane);
        borderPane.setCenter(ddGraphPane);

        SplitPane sPane = new SplitPane();
        /*sPane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                /*if(sPane.getDividers().get(0).getPosition() < 0.1){
                    borderPane.getChildren().remove(1);
                }
                System.out.println(sPane.getDividers().get(0).getPosition());
            }
        });*/
        sPane.setOrientation(Orientation.VERTICAL);
        sPane.getItems().addAll(ddGraphPane, informationPanel);
        sPane.setDividerPosition(0, 0.8);
        borderPane.setCenter(sPane);

        scene = new Scene(borderPane, WIDTH, HEIGHT);
    }
}

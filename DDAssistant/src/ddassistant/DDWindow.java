package ddassistant;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    * BorderPane borderPane
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
        graphControlPanel.getChildren().addAll(controlLabel, sep);
        // Add graph controls here(Sliders, checkboxes, etc.

        // This is just for show and should be taken out.
        for(int i = 0; i < 20; i++){
            Slider slide = new Slider();
            graphControlPanel.getChildren().add(slide);
        }
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
        borderPane.setLeft(createControlPanel());
        borderPane.setBottom(createInformationPanel());

        scene = new Scene(borderPane, WIDTH, HEIGHT);
    }
}

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
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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

    public static double HEIGHT;
    public static double WIDTH;

    /*
    * BorderPane borderPanes
    *
    * BorderPane is used to help organize the panels(VBox, HBox, etc)
    * */
    private BorderPane borderPane;
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

    private DDMenuPane ddMenuPane;
    private DDGraphPane ddGraphPane;
    private DDInformationPane ddInfoPane;

    /*
    *   DDWell well
    *
    *   At any time there should be only one DDWell object active.
    *
    * */
    private DDWell well;

    public void start(Stage primaryStage){
        setScreenSize();
        ddMenuPane = new DDMenuPane(this);
        ddGraphPane = new DDGraphPane(this);
        ddInfoPane = new DDInformationPane(this);
        createInformationPanel();

        borderPane = new BorderPane();
        borderPane.setTop(ddMenuPane);
        borderPane.setCenter(ddGraphPane);

        SplitPane sPane = new SplitPane();
        sPane.setOrientation(Orientation.VERTICAL);
        sPane.getItems().addAll(ddGraphPane, informationPanel);
        sPane.setDividerPosition(0, 0.8);
        borderPane.setCenter(sPane);
        scene = new Scene(borderPane, WIDTH, HEIGHT);

        primaryStage.setTitle("DDAssistant");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setScreenSize(){
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        HEIGHT = screen.getHeight();
        WIDTH = screen.getWidth();
    }


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
        table.setMinHeight(HEIGHT);
        TableColumn a = new TableColumn("String1");
        table.getColumns().add(0, a);
        table.getItems().add("Hello");
        surveyTab.setContent(table);
        slide_rotationTab = new Tab("Slide/Rotation");
        projectionTab = new Tab("Projection");
        BHATab = new Tab("BHA");
        wellDataTab = new Tab("Well Data");

        infoTabPane.getTabs().addAll(surveyTab, slide_rotationTab, projectionTab, BHATab, wellDataTab);
        ToolBar toolBar = new ToolBar();
        TextField textField = new TextField();
        Button button = new Button("Save");
        toolBar.getItems().addAll(textField, button);
        informationPanel.getChildren().addAll(infoTabPane);
        return informationPanel;
    }

    // These functions are the main communications between each pane

    /*
    *
    *  setWell
    *
    *
    * */
    public void setWell(DDWell well){
        this.well = well;
        ddGraphPane.createGraph();
    }

    public void removeWell(){
        this.well = null;
        ddGraphPane.removeGraph();
    }

    public void redrawGraph() {
        ddGraphPane.redraw();
    }

    public DDWell getWell(){
        return well;
    }
}

package ddassistant;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

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

    /*
    * Scene scene
    *
    * Scene is used to store our BorderPane so we can pass it Stage
    * */
    private Scene scene;

    /*
    *   DDMenuPane ddMenuPane
    *
    *
    * */
    private DDMenuPane ddMenuPane;

    /*
    *   DDMainPane ddMainPane
    *
    *
    * */
    private DDMainPane ddMainPane;

    /*
    *   DDWell well
    *
    *   At any time there should be only one DDWell object active.
    *
    * */
    private DDWell well = null;

    public void start(Stage primaryStage){
        this.well = new DDWell();
        setScreenSize();
        borderPane = new BorderPane();

        ddMenuPane = new DDMenuPane(this);
        borderPane.setTop(ddMenuPane);

        ddMainPane = new DDMainPane(this);
        borderPane.setCenter(ddMainPane);

        scene = new Scene(borderPane, WIDTH, HEIGHT);
        primaryStage.setTitle("DDAssistant");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void setScreenSize(){
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        HEIGHT = screen.getHeight();
        WIDTH = screen.getWidth();
    }

    // These functions are the main communications between each pane

    /*
    *   setWell()
    *
    *
    * */
    public void setWell(DDWell well){
        this.well = well;
        refresh();
    }

    private void refresh() {
        ddMainPane.refresh();
    }

    /*
    *   removeWell()
    *
    *
    * */

    /*
    *
    *
    *
    * */
    public void redrawGraph() {
        ddMainPane.redrawGraph();
    }

    public void setGraphSideView(){
        ddMainPane.setGraphAzimuth(90);
        ddMainPane.setGraphInclination(0);
    }

    /*
    *
    *
    *
    * */
    public DDWell getWell(){
        return well;
    }

    public static double getHEIGHT() {
        return HEIGHT;
    }
}

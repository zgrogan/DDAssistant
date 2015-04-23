package ddassistant;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;

/**
 * Created by Colee on 4/14/2015.
 */
public class DDInformationPane extends VBox {
    private TabPane infoTabPane;
    private ToolBar infoToolBar;

    private TableView<String> tableView;
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
    *       pass and receive information to and from the DDGraphPane and DDMenuBar
    * */
    private DDWindow window;
    private void initInformationPane(){

    }

    public DDInformationPane(DDWindow window){
        this.window = window;
        initInformationPane();
    }
}

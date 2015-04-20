package ddassistant;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

/**
 * Created by Colee on 4/14/2015.
 */


public class DDMenuPane extends MenuBar implements EventHandler<ActionEvent>{

    private final String string_FileMenu = "File";
    private final String string_ViewMenu = "View";
    private final String string_ExpandMenu = "Expand";
    private final String string_HelpMenu = "Help";
    private final String string_GraphViewMenu = "Graph View";
    private final String string_GraphDirectionMenu = "Graph Direction";
    private final String string_TargetMenuItem = "Target";
    private final String string_ActualMenuItem = "Actual";
    private final String string_TargetWindowMenuItem = "Target Window";
    private final String string_ProjectionMenuItem = "Projection";
    private final String string_HiLowMenuItem = "Hi/Low";
    private final String string_LeftRightMenuItem = "Left/Right";
    private final String string_GraphMenu = "Graph";
    private final String string_InfoMenu = "Information";
    private final String string_DefaultMenu = "Default";

    private MenuBar menuBar;

    /*
    * Menu fileMenu
    *
    *
    * */
    private Menu fileMenu;
    private Menu viewMenu;
    private Menu expandMenu;
    private Menu helpMenu;

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


    private void InitFileMenu(){
        fileMenu = new Menu(string_FileMenu);
        //fileMenu.getItems().addAll();
    }

    private void InitViewMenu(){
        viewMenu = new Menu(string_ViewMenu);

        InitGraphViewMenu();
        InitGraphDirectionMenu();

        viewMenu.getItems().addAll(graphViewMenu, graphDirectionMenu);
    }

    private void InitGraphViewMenu(){
        graphViewMenu = new Menu(string_GraphViewMenu);

        targetMenuItem = new RadioMenuItem(string_TargetMenuItem);
        actualMenuItem = new RadioMenuItem(string_ActualMenuItem);
        targetWindowMenuItem = new RadioMenuItem(string_TargetWindowMenuItem);
        projectionMenuItem = new RadioMenuItem(string_ProjectionMenuItem);

        graphViewMenu.getItems().addAll(targetMenuItem, actualMenuItem, targetWindowMenuItem, projectionMenuItem);
    }

    private void InitGraphDirectionMenu(){
        graphDirectionMenu = new Menu(string_GraphDirectionMenu);

        hiLowMenuItem = new MenuItem(string_HiLowMenuItem);
        leftRightMenuItem = new MenuItem(string_LeftRightMenuItem);

        graphDirectionMenu.getItems().addAll(hiLowMenuItem, leftRightMenuItem);
    }

    private void InitExpandMenu(){
        expandMenu = new Menu(string_ExpandMenu);

        graphMenu = new MenuItem(string_GraphMenu);
        infoMenu = new MenuItem(string_InfoMenu);
        defaultMenu = new MenuItem(string_DefaultMenu);

        expandMenu.getItems().addAll(graphMenu, infoMenu, defaultMenu);
    }

    private void InitHelpMenu(){
        helpMenu = new Menu(string_HelpMenu);
    }

    private void InitMainBar(){
        InitFileMenu();
        InitViewMenu();
        InitExpandMenu();
        InitHelpMenu();
        Button button = new Button();
        this.getMenus().addAll(fileMenu, viewMenu, expandMenu, helpMenu);
    }

    public DDMenuPane(){
        InitMainBar();
    }

    @Override
    public void handle(ActionEvent e){

    }
}

package ddassistant;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import javax.print.DocFlavor;

/**
 * Created by Colee on 4/26/2015.
 */
public class DDFileMenu extends Menu{
    private final String STRING_FILE = "File";
    private final String STRING_CREATE = "Create";
    private final String STRING_LOAD = "Load";
    private final String STRING_SAVE = "Save";

    private DDWell well;
    private DDWindow window;

    private MenuItem createMenuItem;
    private MenuItem loadMenuItem;
    private MenuItem saveMenuItem;

    private void initFileMenu(){
        createMenuItem = new MenuItem();
        createMenuItem.setText(STRING_CREATE);
        createMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DDWell well = new DDWell();
                window.setWell(well);
            }
        });

        loadMenuItem = new MenuItem();
        loadMenuItem.setText(STRING_LOAD);
        loadMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        saveMenuItem = new MenuItem();
        saveMenuItem.setText(STRING_SAVE);
        saveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        this.setText(STRING_FILE);
        this.getItems().addAll(createMenuItem, loadMenuItem, saveMenuItem);
    }

    public DDFileMenu(DDWindow window){
        this.window = window;
        initFileMenu();
    }


}

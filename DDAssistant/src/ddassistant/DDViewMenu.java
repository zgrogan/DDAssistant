package ddassistant;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Created by Colee on 4/26/2015.
 */
public class DDViewMenu extends Menu {
    private final String STRING_VIEW = "View";
    private final String STRING_SIDE_VIEW = "Side View";
    private final String STRING_TOP_VIEW = "Top View";

    private DDWindow window;
    private MenuItem sideViewMenuItem;
    private MenuItem topViewMenuItem;

    private void initViewMenu(){
        sideViewMenuItem = new MenuItem();
        sideViewMenuItem.setText(STRING_SIDE_VIEW);
        sideViewMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        topViewMenuItem = new MenuItem();
        topViewMenuItem.setText(STRING_TOP_VIEW);
        sideViewMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        this.setText(STRING_VIEW);
        this.getItems().addAll(sideViewMenuItem, topViewMenuItem);
    }

    public DDViewMenu(DDWindow window){
        this.window = window;
        initViewMenu();
    }
}

package ddassistant;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
                Label wellNameLabel = new Label("Well Name: ");
                final TextField wellNameTextField = new TextField();
                Button createButton = new Button("Create");
                Button cancelButton = new Button("Cancel");

                GridPane mainPane = new GridPane();
                mainPane.setHgap(5);
                mainPane.setPadding(new Insets(10));
                mainPane.addRow(0, wellNameLabel, wellNameTextField, createButton, cancelButton);

                Group root = new Group();
                root.getChildren().add(mainPane);
                Scene scene = new Scene(root, mainPane.getMinWidth(), mainPane.getMinHeight());

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                createButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        DDWell well = new DDWell();
                        well.setWellName(wellNameTextField.getText());
                        window.setWell(well);
                        stage.close();
                    }
                });

                cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.close();
                    }
                });

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
                try {
                    PostgresConn.save(window.getWell());
                } catch (Exception e) {
                    System.out.println(e.getCause());
                }
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

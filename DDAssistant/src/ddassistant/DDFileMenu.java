package ddassistant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import javax.print.DocFlavor;
import java.sql.SQLException;
import java.util.Observable;

/**
 * Created by Colee on 4/26/2015.
 */
public class DDFileMenu extends Menu{
    private final String STRING_FILE = "File";
    private final String STRING_CREATE = "Create";
    private final String STRING_LOAD = "Load";
    private final String STRING_SAVE = "Save";

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

                final Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                createButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        DDWell well = new DDWell(wellNameTextField.getText());
                        window.setWell(well);
                        window.redrawGraph();
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

        // load
        loadMenuItem = new MenuItem();
        loadMenuItem.setText(STRING_LOAD);
        loadMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label loadMenuLabel = new Label("Select a well from the list: ");

                ObservableList<String> data = null;
                try {
                    data = FXCollections.observableArrayList(PostgresConn.getWellList());
                } catch (Exception e) {

                }
                final ListView<String> listView = new ListView<String>(data);
                listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

                Button selectButton = new Button("Select");
                Button cancelButton = new Button("Cancel");

                GridPane mainPane = new GridPane();
                mainPane.setHgap(5);
                mainPane.setVgap(5);
                mainPane.setPadding(new Insets(10));
                mainPane.addRow(0, loadMenuLabel);
                mainPane.addRow(1, listView);
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.getChildren().addAll(selectButton, cancelButton);
                AnchorPane.setRightAnchor(cancelButton, 0d);
                AnchorPane.setLeftAnchor(selectButton, -mainPane.getMinWidth());
                mainPane.addRow(2, anchorPane);

                Group root = new Group();
                root.getChildren().add(mainPane);

                Scene scene = new Scene(root, mainPane.getMinWidth(), mainPane.getMinHeight());
                final Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                // select
                selectButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String selectedWell = listView.getSelectionModel().getSelectedItem();
                        DDWell well = null;
                        try {
                            well = PostgresConn.load(selectedWell);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
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

        saveMenuItem = new MenuItem();
        saveMenuItem.setText(STRING_SAVE);
        saveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    PostgresConn.save(window.getWell());
                } catch (Exception e) {
                    try {
                        PostgresConn.update(window.getWell());
                    } catch (Exception e2) {
                        System.out.println("unable to save");
                    }
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

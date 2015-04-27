package ddassistant;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.xml.soap.Text;

/**
 * Created by Colee on 4/14/2015.
 */


public class DDMenuPane extends MenuBar{

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
    private final String string_NewMenuItem = "New";

    private MenuBar menuBar;

    private DDFileMenu fileMenu;
    private DDViewMenu viewMenu;
    private DDTargetMenu targetMenu;
    /*
    * Menu fileMenu
    *
    *
    * */
    //private Menu fileMenu;
    //private Menu viewMenu;
    private Menu expandMenu;
    //private Menu targetMenu;
    private Menu helpMenu;

    // Sub View Menu
    private Menu graphViewMenu;
    private Menu graphDirectionMenu;

    // Sub GraphViewMenu
    private RadioMenuItem targetMenuItem;
    private RadioMenuItem actualMenuItem;
    private RadioMenuItem targetWindowMenuItem;
    private RadioMenuItem projectionMenuItem;

    // Sub Graph Direction Menu
    private MenuItem hiLowMenuItem;
    private MenuItem leftRightMenuItem;


    private MenuItem graphMenu;
    private MenuItem infoMenu;
    private MenuItem defaultMenu;

    // Sub File
    private MenuItem newMenuItem;

    private DDWindow window;

    public void setWell(){

    }

    public DDMenuPane(DDWindow window){
        this.window = window;
        initMenuBar();
    }

    /*private void InitFileMenu(){
        fileMenu = new Menu(string_FileMenu);

        newMenuItem = new MenuItem(string_NewMenuItem);
        newMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label depthLabel = new Label("Depth: ");
                Label azimuthLabel = new Label("Azimuth: ");
                Label inclinationLabel = new Label("Inclination: ");

                final TextField depthTextField = new TextField();
                final TextField azimuthTextField = new TextField();
                final TextField inclinationTextField = new TextField();

                Button createButton = new Button("Create");

                Button cancelButton = new Button("Cancel");



                HBox firstRowBox = new HBox();
                firstRowBox.getChildren().addAll(depthLabel, depthTextField);
                firstRowBox.setSpacing(5);
                HBox buttonBox = new HBox();
                buttonBox.getChildren().addAll(createButton, cancelButton);
                buttonBox.setSpacing(5);

                VBox mainBox = new VBox();
                mainBox.getChildren().addAll(firstRowBox, buttonBox);
                mainBox.setSpacing(5);

                Group newRoot = new Group();
                newRoot.getChildren().add(mainBox);
                Scene newScene = new Scene(newRoot, 100, 100);

                final Stage newDialog = new Stage();
                newDialog.setScene(newScene);
                newDialog.setTitle("New");
                newDialog.show();

                createButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(depthTextField.getText() == null){
                            javafx.scene.text.Text textBoxIsNull = new javafx.scene.text.Text();
                            textBoxIsNull.setText("Depth should be a positive number.");
                            mainBox.getChildren().add(textBoxIsNull);
                            newDialog.close();
                        }
                        if (window.getWell() != null) {
                            System.out.println("Well is not empty.");
                            window.removeWell();
                            DDWell well = new DDWell();
                            well.createTargetCurve(Double.valueOf(depthTextField.getText()));
                            window.setWell(well);
                        } else {
                            DDWell well = new DDWell();
                            well.createTargetCurve(Double.valueOf(depthTextField.getText()));
                            window.setWell(well);
                        }
                        window.redrawGraph();
                        newDialog.close();
                    }
                });
            }
        });

        fileMenu.getItems().addAll(newMenuItem);
    }*/

    /*private void InitViewMenu(){
        viewMenu = new Menu(string_ViewMenu);

        InitGraphViewMenu();
        InitGraphDirectionMenu();

        viewMenu.getItems().addAll(graphViewMenu, graphDirectionMenu);
    }*/

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

    private void initMenuBar(){
        fileMenu = new DDFileMenu(window);

        viewMenu = new DDViewMenu(window);

        targetMenu = new DDTargetMenu(window);

        this.getMenus().addAll(fileMenu, viewMenu, targetMenu);
    }

    /*private void InitTargetMenu() {
        targetMenu = new Menu("Target");

        MenuItem addCurveMenuItem = new MenuItem("Add a Turn");
        addCurveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label depthLabel = new Label("Depth: ");
                Label curveLengthLabel = new Label("Curve Length: ");
                Label azimuthLabel = new Label("New Azimuth: ");
                Label inclinationLabel = new Label("New Inclination: ");

                final TextField depthTextField = new TextField();
                final TextField lengthTextField = new TextField();
                final TextField azimuthTextField = new TextField();
                final TextField inclinationTextField = new TextField();

                Button createButton = new Button("Create");

                Button cancelButton = new Button("Cancel");

                VBox labelBox = new VBox();
                VBox textFieldBox = new VBox();
                VBox buttonBox = new VBox();

                buttonBox.getChildren().addAll(createButton, cancelButton);

                labelBox.getChildren().addAll(depthLabel, curveLengthLabel, azimuthLabel, inclinationLabel);
                textFieldBox.getChildren().addAll(depthTextField, lengthTextField, azimuthTextField, inclinationTextField);

                BorderPane borderPane = new BorderPane();
                borderPane.setLeft(labelBox);
                borderPane.setCenter(textFieldBox);
                borderPane.setBottom(buttonBox);

                Group newRoot = new Group();
                newRoot.getChildren().add(borderPane);
                Scene newScene = new Scene(newRoot, 400, 200);

                final Stage newDialog = new Stage();
                newDialog.setScene(newScene);
                newDialog.setTitle("New");
                newDialog.show();

                createButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (window.getWell() == null) {
                            System.out.println("Well does not exist.");
                        } else {
                            window.getWell().addTargetTurn(Double.valueOf(depthTextField.getText()), Double.valueOf(lengthTextField.getText()), Double.valueOf(azimuthTextField.getText()), Double.valueOf(inclinationTextField.getText()));
                            window.redrawGraph();
                        }
                        newDialog.close();
                    }
                });
            }
        });

        fileMenu.getItems().addAll(addCurveMenuItem);
    }*/
}

package ddassistant;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by Colee on 4/26/2015.
 */
public class DDTargetMenu extends Menu {
    private final String STRING_TARGET = "Target";
    private final String STRING_TARGET_CURVE = "New Target Curve";
    private final String STRING_KICK_OFF = "Kickoff";
    private final String STRING_ADD_TURN = "Add Turn";
    private final String STRING_TARGET_WINDOW = "Target Window";


    private DDWindow window;
    private DDWell well;

    private MenuItem targetCurveMenuItem;
    private MenuItem kickOffMenuItem;
    private MenuItem addTurnMenuItem;
    private MenuItem targetWindowMenuItem;


    private void initTargetMenu(){
        targetCurveMenuItem = new MenuItem();
        targetCurveMenuItem.setText(STRING_TARGET_CURVE);
        targetCurveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label depthLabel = new Label("Depth: ");

                final TextField depthTextField = new TextField();

                Button createButton = new Button("Create");
                Button cancelButton = new Button("Cancel");

                GridPane mainPane = new GridPane();
                mainPane.setHgap(5);
                mainPane.setVgap(5);
                mainPane.setPadding(new Insets(10));
                mainPane.addRow(0, depthLabel, depthTextField, createButton, cancelButton);



                Group newRoot = new Group();
                newRoot.getChildren().add(mainPane);
                Scene newScene = new Scene(newRoot, mainPane.getMinWidth(), mainPane.getMinHeight());

                final Stage newDialog = new Stage();
                newDialog.setScene(newScene);
                newDialog.setTitle("Create New Target");
                newDialog.show();

                createButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        if (window.getWell() != null) {
                            // Prompt to save or overwrite changes
                            System.out.println("Well is not empty.");
                            //window.removeWell();
                            //DDWell well = new DDWell();
                            well.getTargetCurve().set(new TargetCurve(Double.valueOf(depthTextField.getText())));
                            //well.createTargetCurve(Double.valueOf(depthTextField.getText()));
                            window.setWell(well);
                        } else if (window.getWell() == null) {
                            //DDWell well = new DDWell();
                            well.createTargetCurve(Double.valueOf(depthTextField.getText()));
                            window.setWell(well);
                        }
                        window.redrawGraph();
                        newDialog.close();
                    }
                });

                cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        newDialog.close();
                    }
                });
            }
        });

        kickOffMenuItem = new MenuItem();
        kickOffMenuItem.setText(STRING_KICK_OFF);
        kickOffMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label startDepthLabel = new Label("Start Depth: ");
                Label endTVDLabel = new Label("End TVD: ");
                Label newAzimuthLabel = new Label("New Azimuth: ");
                Label newInclinationLabel = new Label("New Inclination: ");

                final TextField startDepthTextField = new TextField();
                final TextField endTVDTextField = new TextField();
                final TextField newAzimuthTextField = new TextField();
                final TextField newInclinationTextField = new TextField();

                Button createButton = new Button("Create");
                Button cancelButton = new Button("Cancel");

                GridPane mainPane = new GridPane();
                mainPane.setHgap(5);
                mainPane.setVgap(5);
                mainPane.setPadding(new Insets(10));
                mainPane.addRow(0, startDepthLabel, startDepthTextField, createButton);
                mainPane.addRow(1, endTVDLabel, endTVDTextField, cancelButton);
                mainPane.addRow(2, newAzimuthLabel, newAzimuthTextField);
                mainPane.addRow(3, newInclinationLabel, newInclinationTextField);


                Group root = new Group();
                root.getChildren().add(mainPane);
                Scene scene = new Scene(root, mainPane.getMinWidth(), mainPane.getMinHeight());

                final Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Create KickOff");
                stage.show();

                createButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        window.getWell().addKickOff(
                                Double.valueOf(startDepthTextField.getText()),
                                Double.valueOf(endTVDTextField.getText()),
                                Double.valueOf(newAzimuthTextField.getText()),
                                Double.valueOf(newInclinationTextField.getText())
                        );
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

        addTurnMenuItem = new MenuItem();
        addTurnMenuItem.setText(STRING_ADD_TURN);
        addTurnMenuItem.setOnAction(new EventHandler<ActionEvent>() {
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

                GridPane mainPane = new GridPane();
                mainPane.setHgap(5);
                mainPane.setVgap(5);
                mainPane.setPadding(new Insets(10));
                mainPane.addRow(0, depthLabel, depthTextField, createButton);
                mainPane.addRow(1, curveLengthLabel, lengthTextField, cancelButton);
                mainPane.addRow(2, azimuthLabel, azimuthTextField);
                mainPane.addRow(3, inclinationLabel, inclinationTextField);

                Group root = new Group();
                root.getChildren().add(mainPane);
                Scene scene = new Scene(root, mainPane.getMinWidth(), mainPane.getMinHeight());

                final Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Add Turn");
                stage.show();

                createButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (window.getWell() == null) {
                            System.out.println("Well does not exist.");
                        } else {
                            window.getWell().addTargetTurn(
                                    Double.valueOf(depthTextField.getText()),
                                    Double.valueOf(lengthTextField.getText()),
                                    Double.valueOf(azimuthTextField.getText()),
                                    Double.valueOf(inclinationTextField.getText())
                            );
                            window.redrawGraph();
                        }
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

        targetWindowMenuItem = new MenuItem();
        targetWindowMenuItem.setText(STRING_TARGET_WINDOW);
        targetWindowMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label highTargetLabel = new Label("High Target Window: ");
                Label lowTargetLabel = new Label("Low Target Window: ");
                Label leftTargetLabel = new Label("Left Target Window: ");
                Label rightTargetLabel = new Label("Right Target Window: ");

                final TextField highTargetTextField = new TextField();
                final TextField lowTargetTextField = new TextField();
                final TextField leftTargetTextField = new TextField();
                final TextField rightTargetTextField = new TextField();

                Button createButton = new Button("Create");
                Button cancelButton = new Button("Cancel");

                GridPane mainPane = new GridPane();
                mainPane.setHgap(5);
                mainPane.setVgap(5);
                mainPane.setPadding(new Insets(10));
                mainPane.addRow(0, highTargetLabel, highTargetTextField, createButton);
                mainPane.addRow(1, lowTargetLabel, lowTargetTextField, cancelButton);
                mainPane.addRow(2, leftTargetLabel, leftTargetTextField);
                mainPane.addRow(3, rightTargetLabel, rightTargetTextField);

                Group root = new Group();
                root.getChildren().add(mainPane);
                Scene scene = new Scene(root, mainPane.getMinWidth(), mainPane.getMinHeight());
                final Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                createButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        well.setTargetWindowHi(Double.valueOf(highTargetTextField.getText()));
                        well.setTargetWindowLow(Double.valueOf(lowTargetTextField.getText()));
                        well.setTargetWindowLeft(Double.valueOf(leftTargetTextField.getText()));
                        well.setTargetWindowRight(Double.valueOf(rightTargetTextField.getText()));
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

        this.setText(STRING_TARGET);
        this.getItems().addAll(targetCurveMenuItem, kickOffMenuItem, addTurnMenuItem, targetWindowMenuItem);
    }

    /*
    *
    *
    * */
    public DDTargetMenu(DDWindow window){
        this.window = window;
        this.well = window.getWell();
        initTargetMenu();
    }
}

package ddassistant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SurveyTab extends Tab {

    private DDWell well;
    private DDWindow window;
    private TableView<DDSurvey> tableView;
    private final String TAB_NAME = "Surveys";

    public void setWell(DDWell well) {
        this.well = well;
    }

    public SurveyTab(DDWell well, DDWindow window) {
        this.window = window;
        this.well = well;
        initSurveyTab();
    }

    private void initSurveyTab() {
        // basic tab properties
        this.setClosable(false);
        this.setText(TAB_NAME);

        // build the table
        tableView = new TableView<DDSurvey>();
        final ObservableList<DDSurvey> surveys = FXCollections.observableList(well.getSurveys());
        tableView.setItems(surveys);
        TableColumn<DDSurvey,Double> depthCol = new TableColumn<DDSurvey,Double>("Depth");
        TableColumn<DDSurvey,Double> azimuthCol = new TableColumn<DDSurvey,Double>("Azimuth");
        TableColumn<DDSurvey,Double> inclinationCol = new TableColumn<DDSurvey,Double>("Inclination");
        depthCol.setCellValueFactory(new PropertyValueFactory<DDSurvey,Double>("depth"));
        azimuthCol.setCellValueFactory(new PropertyValueFactory<DDSurvey,Double>("azimuth"));
        inclinationCol.setCellValueFactory(new PropertyValueFactory<DDSurvey,Double>("inclination"));
        tableView.getColumns().setAll(depthCol, azimuthCol, inclinationCol);

        // textFields to add new survey
        final TextField addDepth = new TextField();
        final TextField addAzimuth = new TextField();
        final TextField addInclination = new TextField();

        addDepth.setPromptText("Depth");
        addAzimuth.setPromptText("Azimuth");
        addInclination.setPromptText("Inclination");

        addDepth.setMaxWidth(depthCol.getPrefWidth());
        addAzimuth.setMaxWidth(azimuthCol.getPrefWidth());
        addInclination.setMaxWidth(inclinationCol.getPrefWidth());

        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                well.addSurvey(new DDSurvey(Double.parseDouble(addDepth.getText()),
                                            Double.parseDouble(addAzimuth.getText()),
                                            Double.parseDouble(addInclination.getText())));
                window.redrawGraph();
                initSurveyTab();
            }
        });

        // addSurveyBox
        final HBox addSurveyBox = new HBox();
        addSurveyBox.getChildren().addAll(addDepth, addAzimuth, addInclination, addButton);
        addSurveyBox.setSpacing(3);

        // vbox contains everything in the tab
        final VBox surveyVBox = new VBox();
        surveyVBox.setSpacing(5);
        surveyVBox.setPadding(new Insets(10, 0, 0, 10));
        surveyVBox.getChildren().addAll(tableView, addSurveyBox);

        this.setContent(surveyVBox);
    }

}

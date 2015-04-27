package ddassistant;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.*;

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
        TableColumn<DDSurvey,String> hllrCol = new TableColumn<>("HiLow / LeftRight");
        depthCol.setCellValueFactory(new PropertyValueFactory<DDSurvey, Double>("depth"));
        azimuthCol.setCellValueFactory(new PropertyValueFactory<DDSurvey,Double>("azimuth"));
        inclinationCol.setCellValueFactory(new PropertyValueFactory<DDSurvey, Double>("inclination"));
        hllrCol.setCellValueFactory(new Callback<CellDataFeatures<DDSurvey, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(CellDataFeatures<DDSurvey, String> param) {
                return new ReadOnlyStringWrapper(well.getHLLR(param.getValue().getDepth()));
            }
        });
        tableView.getColumns().setAll(depthCol, azimuthCol, inclinationCol, hllrCol);

        depthCol.setEditable(true);
        azimuthCol.setEditable(true);
        inclinationCol.setEditable(true);

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

package ddassistant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SurveyTab extends Tab {

    private DDWell well;
    private TableView<DDSurvey> tableView;
    private final String TAB_NAME = "Surveys";

    public SurveyTab(DDWell well) {
        this.well = well;
        initSurveyTab();
    }

    private void initSurveyTab() {
        this.setClosable(false);
        this.setText(TAB_NAME);
        tableView = new TableView<DDSurvey>();
        ObservableList<DDSurvey> surveys = FXCollections.observableList(well.getSurveys());
        tableView.setItems(surveys);
        TableColumn<DDSurvey,Double> depthCol = new TableColumn<DDSurvey,Double>("Depth");
        depthCol.setCellValueFactory(new PropertyValueFactory("depth"));
        TableColumn<DDSurvey,Double> azimuthCol = new TableColumn<DDSurvey,Double>("Azimuth");
        azimuthCol.setCellValueFactory(new PropertyValueFactory("azimuth"));
        TableColumn<DDSurvey,Double> inclinationCol = new TableColumn<DDSurvey,Double>("Inclination");
        inclinationCol.setCellValueFactory(new PropertyValueFactory("inclination"));
        tableView.getColumns().setAll(depthCol, azimuthCol, inclinationCol);
    }
}

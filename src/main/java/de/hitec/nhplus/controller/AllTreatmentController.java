package de.hitec.nhplus.controller;

import de.hitec.nhplus.Main;
import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.PatientDao;
import de.hitec.nhplus.datastorage.TreatmentDao;
import de.hitec.nhplus.model.Patient;
import de.hitec.nhplus.model.Treatment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The <code>AllTreatmentController</code> manages the logic of the treatment view.
 * It handles displaying, filtering, adding, editing, and deleting treatments.
 */
public class AllTreatmentController {

    @FXML
    private TableView<Treatment> tableView;

    @FXML
    private TableColumn<Treatment, Integer> columnId;

    @FXML
    private TableColumn<Treatment, Integer> columnPid;

    @FXML
    private TableColumn<Treatment, String> columnDate;

    @FXML
    private TableColumn<Treatment, String> columnBegin;

    @FXML
    private TableColumn<Treatment, String> columnEnd;

    @FXML
    private TableColumn<Treatment, String> columnDescription;

    @FXML
    private ComboBox<String> comboBoxPatientSelection;

    @FXML
    private Button buttonDelete;

    private final ObservableList<Treatment> treatments = FXCollections.observableArrayList();
    private TreatmentDao dao;
    private final ObservableList<String> patientSelection = FXCollections.observableArrayList();
    private ArrayList<Patient> patientList;

    /**
     * Initializes the controller. Loads all treatments and configures UI components.
     */
    public void initialize() {
        readAllAndShowInTableView();
        comboBoxPatientSelection.setItems(patientSelection);
        comboBoxPatientSelection.getSelectionModel().select(0);

        this.columnId.setCellValueFactory(new PropertyValueFactory<>("tid"));
        this.columnPid.setCellValueFactory(new PropertyValueFactory<>("pid"));
        this.columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.columnBegin.setCellValueFactory(new PropertyValueFactory<>("begin"));
        this.columnEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        this.columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.tableView.setItems(this.treatments);
        this.buttonDelete.setDisable(true);
        this.tableView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldTreatment, newTreatment) ->
                        AllTreatmentController.this.buttonDelete.setDisable(newTreatment == null));

        this.createComboBoxData();
    }

    /**
     * Loads all treatments and displays them in the <code>TableView</code>.
     */
    public void readAllAndShowInTableView() {
        this.treatments.clear();
        comboBoxPatientSelection.getSelectionModel().select(0);
        this.dao = DaoFactory.getDaoFactory().createTreatmentDao();
        try {
            this.treatments.addAll(dao.readAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Fills the patient selection <code>ComboBox</code> with all available patients.
     */
    private void createComboBoxData() {
        PatientDao dao = DaoFactory.getDaoFactory().createPatientDAO();
        try {
            patientList = (ArrayList<Patient>) dao.readAll();
            this.patientSelection.add("alle");
            for (Patient patient : patientList) {
                this.patientSelection.add(patient.getSurname());
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    /**
     * Handles selection changes in the <code>ComboBox</code> and filters treatments by patient.
     */
    @FXML
    public void handleComboBox() {
        String selectedPatient = this.comboBoxPatientSelection.getSelectionModel().getSelectedItem();
        this.treatments.clear();
        this.dao = DaoFactory.getDaoFactory().createTreatmentDao();

        if (selectedPatient.equals("alle")) {
            try {
                this.treatments.addAll(this.dao.readAll());
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        Patient patient = searchInList(selectedPatient);
        if (patient != null) {
            try {
                this.treatments.addAll(this.dao.readTreatmentsByPid(patient.getPid()));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Searches for a patient in the internal list by surname.
     *
     * @param surname Surname of the patient.
     * @return Matching patient or null if not found.
     */
    private Patient searchInList(String surname) {
        for (Patient patient : this.patientList) {
            if (patient.getSurname().equals(surname)) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Handles the event when the delete button is clicked.
     * Deletes the selected treatment from database and removes it from the table.
     */
    @FXML
    public void handleDelete() {
        int index = this.tableView.getSelectionModel().getSelectedIndex();
        Treatment t = this.treatments.remove(index);
        TreatmentDao dao = DaoFactory.getDaoFactory().createTreatmentDao();
        try {
            dao.deleteById(t.getTid());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Opens the dialog to create a new treatment for the selected patient.
     * If no patient is selected, an alert is shown.
     */
    @FXML
    public void handleNewTreatment() {
        try {
            String selectedPatient = this.comboBoxPatientSelection.getSelectionModel().getSelectedItem();
            Patient patient = searchInList(selectedPatient);
            newTreatmentWindow(patient);
        } catch (NullPointerException exception) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Patient für die Behandlung fehlt!");
            alert.setContentText("Wählen Sie über die Combobox einen Patienten aus!");
            alert.showAndWait();
        }
    }

    /**
     * Handles double-clicks on a row in the <code>TableView</code> to open the treatment detail view.
     */
    @FXML
    public void handleMouseClick() {
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (tableView.getSelectionModel().getSelectedItem() != null)) {
                int index = this.tableView.getSelectionModel().getSelectedIndex();
                Treatment treatment = this.treatments.get(index);
                treatmentWindow(treatment);
            }
        });
    }

    /**
     * Opens the window to create a new treatment for a patient.
     *
     * @param patient The patient for whom the treatment is created.
     */
    public void newTreatmentWindow(Patient patient) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/NewTreatmentView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);

            // the primary stage should stay in the background
            Stage stage = new Stage();

            NewTreatmentController controller = loader.getController();
            controller.initialize(this, stage, patient);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Opens the window to view or edit a treatment.
     *
     * @param treatment The treatment to be shown.
     */

    public void treatmentWindow(Treatment treatment) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/TreatmentView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);

            // the primary stage should stay in the background
            Stage stage = new Stage();
            TreatmentController controller = loader.getController();
            controller.initializeController(this, stage, treatment);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

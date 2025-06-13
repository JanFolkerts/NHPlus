package de.hitec.nhplus.controller;

import de.hitec.nhplus.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * The <code>MainWindowController</code> manages the main view of the application. It handles the loading
 * and display of different views like patients, treatments, and caregivers into the center area of the
 * <code>BorderPane</code>.
 */
public class MainWindowController {

    @FXML
    private BorderPane mainBorderPane;

    /**
     * Handles the action event triggered when the user wants to view all patients.
     * Loads the <code>AllPatientView.fxml</code> and sets it into the center of the main layout.
     *
     * @param event the triggered action event
     */
    @FXML
    private void handleShowAllPatient(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/AllPatientView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Handles the action event triggered when the user wants to view all treatments.
     * Loads the <code>AllTreatmentView.fxml</code> and sets it into the center of the main layout.
     *
     * @param event the triggered action event
     */
    @FXML
    private void handleShowAllTreatments(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/AllTreatmentView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Handles the action event triggered when the user wants to view all caregivers.
     * Loads the <code>AllCaregiverView.fxml</code> and sets it into the center of the main layout.
     *
     * @param event the triggered action event
     */
    @FXML
    private void handleShowAllCaregiver(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/AllCaregiverView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

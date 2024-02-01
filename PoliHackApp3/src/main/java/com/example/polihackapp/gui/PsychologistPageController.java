package com.example.polihackapp.gui;

import com.example.polihackapp.Domain.Patient;
import com.example.polihackapp.Domain.User;
import com.example.polihackapp.Services.Service;
import com.example.polihackapp.Session;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PsychologistPageController {

    private Service service;
    private User loggedUser;
    public Label label_title;
    public Label create_test_label;
    public ComboBox<String> categoriesComboBox;
    private final ObservableList<Patient> model = FXCollections.observableArrayList();
    public TableView<Patient> patientsTableView;

    public TableColumn<Patient, String> patientFullName;

    public TableColumn<Patient, String> patientUsername;
    private Stage stage;

    @FXML
    private Label name;
    @FXML
    private Label username;
    @FXML
    private Label email;
    @FXML
    private TextField searchTextField;

    public void setData(Stage stage){
        this.stage = stage;
        ObservableList<String> options = FXCollections.observableArrayList("Pick Text After Image", "Pick Image After Text", "Order Objects");
        categoriesComboBox.setItems(options);
        setPsychologistInfo();
        Platform.runLater(() -> patientsTableView.lookup("TableHeaderRow").setVisible(false));
        patientsTableView.setStyle("-fx-table-header-background: transparent;");;

        Iterable<Patient> patients = service.getAllPatientsByPsychologist(loggedUser.getUsername());
        initModel(patients);
    }

    public void setService(Service service) {
        this.service = service;
    }

    private void initModel(Iterable<Patient> patients){
        List<Patient> patientsList = StreamSupport.stream(patients.spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(patientsList);
    }

    public void initialize(){
//        patientFullName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        patientUsername.setCellValueFactory(new PropertyValueFactory<>("username"));

        patientFullName.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getName()));
        patientUsername.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getUsername()));
        patientsTableView.setItems(model);
    }

    public void setPsychologistInfo() {
        loggedUser = Session.getLoggedUser();
        name.setText(loggedUser.getName());
        username.setText(loggedUser.getUsername());
        email.setText(loggedUser.getEmail());
    }

    public void handleFilterPatients(KeyEvent keyEvent) {
        String username = searchTextField.getText();
//        if(!username.isBlank()){
//            initModel(this.service.getPatientsFilteredOfUser(username, id_psiholog));
//            return;
//        }
        initModel(service.getAllPatientsByPsychologist(loggedUser.getUsername()));
    }

    public void handleClose(ActionEvent actionEvent) {
        this.stage.close();
    }

    public void handleLogOut(ActionEvent actionEvent) {
    }

    public void handleCreateTest(ActionEvent actionEvent) {}

}

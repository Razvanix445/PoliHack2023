package com.example.polihackapp.gui;

import com.example.polihackapp.Domain.Patient;
import com.example.polihackapp.Domain.Psychologist;
import com.example.polihackapp.Services.Service;
import com.example.polihackapp.Validators.ValidatorException;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class RegisterController {

    private Service service;
    private FadeTransition fadeOut;
    @FXML
    private CheckBox patientCheckBox;

    @FXML
    private CheckBox psychologistCheckBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;

    @FXML
    private Label nameXLabel;
    @FXML
    private Label usernameXLabel;
    @FXML
    private Label phoneXLabel;
    @FXML
    private Label emailXLabel;
    @FXML
    private Label passwordXLabel;
    @FXML
    private Label accountTypeX;

    private Stage stage;

    public void setData(Stage stage) {
        this.stage = stage;
        this.stage.setWidth(650);
        this.stage.setHeight(700);
        this.stage.centerOnScreen();
        setInvisibleXFields();
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void handleClose(ActionEvent actionEvent) {
        this.stage.close();
    }


    public void handleCheckPatient(ActionEvent actionEvent) {
        psychologistCheckBox.setSelected(false);
    }

    public void handleCheckPsychologist(ActionEvent actionEvent) {
        patientCheckBox.setSelected(false);
    }

    public void handleOpenLogin(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/polihackapp/views/Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            LoginController loginController = fxmlLoader.getController();
            loginController.setService(service);
            loginController.setData(stage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleRegisterAccount(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        String username = usernameTextField.getText();
        String phone = phoneTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        try {
            if(phone.isEmpty()) {
                try {
                    ArrayList<String> errs = new ArrayList<String>();
                    errs.add("Phone Number cannot be empty");
                    throw new ValidatorException(errs);
                }catch (ValidatorException e) {
                    showX(e.getError());
                }
            }
            if(patientCheckBox.isSelected() ) {
                Patient patient = new Patient(name,username,password,email,"REMOVE!", new java.sql.Date(new java.util.Date().getTime()),"");
                service.addPatient(patient);
                handleOpenLogin(new ActionEvent());
            }
            else if (psychologistCheckBox.isSelected()) {
                Psychologist psychologist = new Psychologist(name, username, password, email, phone);
                service.addPsychologist(psychologist);
                handleOpenLogin(new ActionEvent());
            }
            else{
                try {
                    ArrayList<String> errs = new ArrayList<String>();
                    errs.add("Account type is empty");
                    throw new ValidatorException(errs);
                }catch (ValidatorException e) {
                    showX(e.getError());
                }
            }
        } catch (ValidatorException e) {
            showX(e.getError());
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText(e.getError());
//            alert.show();
        }
        setInvisibleXFields();
    }

    private void showX(String i){
        if(i.contains("Username")) {
            fadeOut = new FadeTransition(Duration.seconds(2), usernameXLabel);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();
        }
        if  (i.contains("Name")) {
            fadeOut = new FadeTransition(Duration.seconds(2), nameXLabel);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();
        }
        if  (i.contains("Email")) {
            fadeOut = new FadeTransition(Duration.seconds(2), emailXLabel);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();
        }
        if  (i.contains("Password")) {
            fadeOut = new FadeTransition(Duration.seconds(2), passwordXLabel);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();
        }
        if  (i.contains("Phone")) {
            fadeOut = new FadeTransition(Duration.seconds(2), phoneXLabel);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();
        }
        if(i.contains("Account")){
            fadeOut = new FadeTransition(Duration.seconds(2), accountTypeX);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();
        }
    }

    private void setInvisibleXFields(){
        accountTypeX.setOpacity(0.0);
        passwordXLabel.setOpacity(0.0);
        emailXLabel.setOpacity(0.0);
        phoneXLabel.setOpacity(0.0);
        usernameXLabel.setOpacity(0.0);
        nameXLabel.setOpacity(0.0);
    }
}
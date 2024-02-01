package com.example.polihackapp.gui;

import com.example.polihackapp.Domain.Patient;
import com.example.polihackapp.Domain.Psychologist;
import com.example.polihackapp.Repositories.*;
import com.example.polihackapp.Services.Service;
import com.example.polihackapp.Validators.PatientValidator;
import com.example.polihackapp.Validators.ValidatorPsychologist;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        String url = "jdbc:postgresql://localhost:1234/polihack";
        String username = "postgres";
        String password = "2003razvi";


        RepoDBPatients repoPatients = new RepoDBPatients(url, username, password);
        PatientValidator validatorPatient = new PatientValidator(repoPatients);
        Repository<Long, Psychologist> repoPsychologists = new RepoDBPsychologists(url, username, password);
        ValidatorPsychologist validatorPsychologist = new ValidatorPsychologist(repoPsychologists);
        RepoDBTests repoTests = new RepoDBTests(url, username, password);
        RepoDBImages repoImages = new RepoDBImages(url, username, password);
        Service service = new Service(repoPatients, repoPsychologists, repoTests, repoImages, validatorPatient, validatorPsychologist);

        ValidatorPsychologist validator = new ValidatorPsychologist(repoPsychologists);

//        MyImage image = service.searchForImage(3L);
//        Image imageImage = new Image(image.getImage());

//        ImageView imageView = new ImageView(imageImage);
//        imageView.setPreserveRatio(true);
//        imageView.setFitWidth(400);

        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/example/polihackapp/views/SplashScreen.fxml"));
            AnchorPane pane=loader.load();
            SplashScreenController controller=loader.getController();
            controller.setData(stage);
            controller.setService(service);
            Scene scene=new Scene(pane);
            stage.initStyle(StageStyle.UNDECORATED);

            stage.setScene(scene);
            stage.show();


        }catch (Exception e){
            e.printStackTrace();
        }

//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/polihackapp/views/SplashScreen.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//
//        SplashScreenController controller = fxmlLoader.getController();
//        //controller.setImage(imageImage);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
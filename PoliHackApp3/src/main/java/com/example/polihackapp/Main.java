package com.example.polihackapp;

import com.example.polihackapp.Domain.Patient;
import com.example.polihackapp.Domain.Psychologist;
import com.example.polihackapp.Domain.Test;
import com.example.polihackapp.Repositories.*;
import com.example.polihackapp.Services.Service;
import com.example.polihackapp.Validators.PatientValidator;
import com.example.polihackapp.Validators.ValidatorException;
import com.example.polihackapp.Validators.ValidatorPsychologist;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {

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

//        File imageFile = new File("src/main/resources/com/example/polihackapp/images/appBackground.jpg");
//        List<String> variants = Arrays.asList("A", "B", "C");
//        String correctAnswer = "A";
//        String description = "T1I1";
//
//        repoTests.save(imageFile, variants, correctAnswer, description);

//        System.out.println(repoPsychologists.findAll());


        //=============================================================================================
        /* TESTS CRUD */
        // TEST 1
        List<Long> imageIDs1 = new ArrayList<>();
        imageIDs1.add(1L);
        imageIDs1.add(3L);
        List<String> variants1 = Arrays.asList("A", "B", "C");
        String correctAnswer1 = "A";
        String description1 = "T1";
//        repoTests.save(imageIDs1, variants1, correctAnswer1, description1);

        // TEST 2
//        List<Long> imageIDs2 = new ArrayList<>();
//        imageIDs2.add(2L);
//        List<String> variants2 = Arrays.asList("1", "2", "3");
//        String correctAnswer2 = "2";
//        String description2 = "T2";
//        repoTests.save(imageIDs2, variants2, correctAnswer2, description2);

//        System.out.println(repoTests.findOne(1L));
//        System.out.println(repoTests.findOne(2L));

//        List<Test> tests = repoTests.findAll();
//        tests.forEach(System.out::println);

//        Test test = new Test(1L, imageIDs1, variants1, correctAnswer1, description1);
//        service.addTest(test);

        //=============================================================================================
        /* IMAGES CRUD */
//        File imageFile = new File("src/main/resources/com/example/polihackapp/images/appBackground.jpg");
//        String description = "I1";
//
//        File imageFile2 = new File("src/main/resources/com/example/polihackapp/images/windows_xp.jpg");
//        String description2 = "I2";

        //save() method
//        repoImages.save(imageFile, description);
//        repoImages.save(imageFile2, description2);

        // findOne() method
//        System.out.println(repoImages.findOne(1L));

        // findAll() method
//        List<Image> images = repoImages.findAll();
//        for (Image image: images) {
//            System.out.println(image.getDescription());
//        }


        //=============================================================================================
        /* PSYCHOLOGISTS CRUD */
//        ArrayList<Long> list = new ArrayList<>();
//        list.add(1L);
//        list.add(2L);
//        Psychologist newPsychologist = new Psychologist(1L, "John Doe", "john.doe", "password", "john.doe@example.com",
//                "awdawd", list);

//        Optional<Psychologist> savedPsychologist =repoPsychologists.save(newPsychologist);
//        System.out.println("Saved Psychologist: " + savedPsychologist.orElse(null));

//        Optional<Psychologist> deletedPsychologist = repoPsychologists.delete(2L);
//        System.out.println("Deleted Psychologist: " + deletedPsychologist.orElse(null));


        //=============================================================================================
        /* PATIENTS CRUD */
//        Patient newPatient = new Patient(2L, "John Doe", "john.doe", "password", "john.doe@example.com",
//                "Gold", new java.sql.Date(new Date().getTime()), "Description", List.of(1L, 2L));
//
//        Optional<Patient> savedPatient = repoPatients.save(newPatient);
//        System.out.println("Saved Patient: " + savedPatient.orElse(null));
//
//        Optional<Patient> deletedPatient = repoPatients.delete(5L);
//        System.out.println("Deleted Patient: " + deletedPatient.orElse(null));
//
//        service.addPatient(newPatient);
//        service.removePatient(newPatient);
//        System.out.println(service.searchForPatient(3L));
//        System.out.println(service.getAllPatients());

//        try {
//            service.addPatient(new Patient("name1", "username1", "password1", "email1", "subscription", Date.valueOf(LocalDate.now()), "description1"));
//            service.addPsychologist(new Psychologist("name2", "username2", "password2", "email2", "AddedPhone"));
//        } catch (ValidatorException e) {
//            System.out.println(e.getError());
//        }

//        service.addHomework("username1", 10L);
//        service.removeHomework("username1", 2L);
//
//        System.out.println(service.getAllPatients());
//        System.out.println(service.getAllPsychologist());
    }
}
package com.example.polihackapp.Services;

import com.example.polihackapp.Domain.MyImage;
import com.example.polihackapp.Domain.Patient;
import com.example.polihackapp.Domain.Psychologist;
import com.example.polihackapp.Domain.Test;
import com.example.polihackapp.Repositories.RepoDBImages;
import com.example.polihackapp.Repositories.RepoDBPatients;
import com.example.polihackapp.Repositories.RepoDBTests;
import com.example.polihackapp.Repositories.Repository;
import com.example.polihackapp.Validators.*;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class Service {

    private RepoDBPatients repoPatients;
    private Repository<Long, Psychologist> repoPsychologists;
    private RepoDBTests repoTests;
    private RepoDBImages repoImages;
    private Validator<Patient> validatorPatients;
    private Validator<Psychologist> validatorPsychologist;
    private Validator<Test> validatorTests;

    public Service(RepoDBPatients patientRepository, Repository<Long, Psychologist> psychologistRepository, RepoDBTests repoTests, RepoDBImages repoImages, Validator<Patient> validatorPatients, Validator<Psychologist> validatorPsychologist) {
        this.repoPatients = patientRepository;
        this.repoPsychologists = psychologistRepository;
        this.repoTests = repoTests;
        this.repoImages = repoImages;
        this.validatorPatients = validatorPatients;
        this.validatorPsychologist = validatorPsychologist;
        this.validatorTests = new ValidatorTest();
    }

    public void addPatient(Patient patient) throws ValidatorException {
        validatorPatients.validate(patient);
        repoPatients.save(patient);
    }
    public void addPsychologist(Psychologist psychologist) throws ValidatorException {
        validatorPsychologist.validate(psychologist);
        repoPsychologists.save(psychologist);
    }


    public void removePatient(Patient patient) throws ValidatorException {
        validatorPatients.validate(patient);
        if (repoPatients.findOne(patient.getId()).isPresent())
            repoPatients.delete(patient.getId());
        else System.out.println("Cererea nu exista!");
    }
    public void removePatient(String username) throws ValidatorException {
        if (repoPatients.findOne(username).isPresent())
            repoPatients.delete(username);
        else System.out.println("Cererea nu exista!");
    }

    public void removePsychologist(Psychologist psychologist) throws ValidatorException {
        validatorPsychologist.validate(psychologist);
        if(repoPsychologists.findOne(psychologist.getId()).isPresent())
            repoPsychologists.delete(psychologist.getId());
        else System.out.println("Nu existaa");
    }
    public void removePsychologist(String username) throws ValidatorException {
        if(repoPsychologists.findOne(username).isPresent())
            repoPsychologists.delete(username);
        else System.out.println("Nu existaa");
    }


    public void updatePatient(Patient patient) {
        repoPatients.update(patient);
    }
    public void updatePsychologist(Psychologist psychologist) {
        repoPsychologists.update(psychologist);
    }


    public Psychologist findPsychologist(Long id){
        Optional<Psychologist> psychologist = repoPsychologists.findOne(id);
        if(psychologist.isPresent())
            return psychologist.get();
        else
            throw new IllegalArgumentException("Utilizatorul nu exista!");
    }
    public Psychologist findPsychologist(String username){
        Optional<Psychologist> psychologist = repoPsychologists.findOne(username);
        if(psychologist.isPresent())
            return psychologist.get();
        else
            throw new IllegalArgumentException("Utilizatorul nu exista!");
    }
    public Patient findPatient(Long patientID) {
        Optional<Patient> patient = repoPatients.findOne(patientID);
        if(patient.isPresent())
            return patient.get();
        else
            throw new IllegalArgumentException("Utilizatorul nu exista!");
    }
    public Patient findPatient(String username) {
        Optional<Patient> patient = repoPatients.findOne(username);
        if(patient.isPresent())
            return patient.get();
        else
            throw new IllegalArgumentException("Utilizatorul nu exista!");
    }


    public Iterable<Patient> getAllPatients() {
        return repoPatients.findAll();
    }
    public Iterable<Psychologist> getAllPsychologist(){
        return repoPsychologists.findAll();
    }

    public void addPatientPsychologist(String usernamePsychologist, String usernamePatient) {
        Optional<Psychologist>  psychologist= Optional.of(findPsychologist(usernamePsychologist));
        Optional<Patient> patient = Optional.of(findPatient(usernamePatient));

        if(psychologist.isPresent() && patient.isPresent()) {
            psychologist.get().addPatient(patient.get().getId());
            patient.get().setPsychologistID(Optional.of(psychologist.get().getId()));
            updatePsychologist(psychologist.get());
            updatePatient(patient.get());
        }
    }

    public void removePatientPsychologist(String usernamePsychologist, String usernamePatient){
        Optional<Psychologist>  psychologist= Optional.of(findPsychologist(usernamePsychologist));
        Optional<Patient> patient = Optional.of(findPatient(usernamePatient));

        if(psychologist.isPresent() && patient.isPresent()) {
            psychologist.get().removePatient(patient.get().getId());
            patient.get().removePsychlogistId();
            updatePsychologist(psychologist.get());
            updatePatient(patient.get());
        }
    }

    public void addHomework(String usernamePatient, Long homework) {
        Optional<Test> testOptional = repoTests.findOne(homework);
        Optional<Patient> patient = Optional.of(findPatient(usernamePatient));
        if(patient.isPresent() && testOptional.isPresent()){
            patient.get().addHomework(homework);
            updatePatient(patient.get());
        }
    }

    public void removeHomework(String usernamePatient, Long homework) {
        Optional<Patient> patient = Optional.of(findPatient(usernamePatient));
        if(patient.isPresent()){
            patient.get().removeHomework(homework);
            updatePatient(patient.get());
        }
    }

    public Iterable<Patient> getAllPatientsByPsychologist(String psychologistUsername) {
        return repoPatients.findPatientsByPsychologist(psychologistUsername);
    }

    /* TESTS AND IMAGES PART */
    public void addImage(File imageFile, String description) {
        try {
            repoImages.save(imageFile, description);
        } catch(Exception e) {
            throw new IllegalArgumentException("Image couldn't have been saved");
        }
    }

    public MyImage searchForImage(Long imageID) {
        Optional<MyImage> image = repoImages.findOne(imageID);
        if (image.isPresent()) {
            return image.get();
        }
        return image.get();
    }

    public List<MyImage> getAllImages() {
        return repoImages.findAll();
    }

    public void addTest(Test test) {
        try {
            validatorTests.validate(test);
            repoTests.save(test.getImageIDs(), test.getVariants(), test.getCorrectAnswer(), test.getDescription());
        } catch(Exception e) {
            throw new IllegalArgumentException("Test couldn't have been saved");
        }
    }

    public Test searchForTest(Long testID) {
        Optional<Test> test = repoTests.findOne(testID);
        if (test.isPresent()) {
            return test.get();
        }
        return test.get();
    }

    public List<Test> getAllTests() {
        return repoTests.findAll();
    }
}

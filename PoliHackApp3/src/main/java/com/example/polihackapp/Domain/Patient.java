package com.example.polihackapp.Domain;


import java.util.*;

public class Patient extends User {
    private String subscription;
    private java.sql.Date dateOfBirth;
    private String description;
    private List<Long> homeworks = new ArrayList<>();
    private Optional<Long> psychologistID;

    public Patient(String name, String username, String password, String email, String subscription, java.sql.Date dateOfBirth, String description) {
        super(name, username, password, email);
        this.subscription = subscription;
        this.dateOfBirth = dateOfBirth;
        this.description = description;
    }
    public Patient(Long id, String name, String username, String password, String email, String subscription, java.sql.Date dateOfBirth, String description, ArrayList<Long> homeworks, Optional<Long> psychologistID) {
        super(name, username, password, email);
        this.subscription = subscription;
        this.dateOfBirth = dateOfBirth;
        this.description = description;
        this.homeworks = homeworks;
        this.psychologistID = psychologistID;
        setId(id);
    }

    public String getSubscription() {
        return this.subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public java.sql.Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(java.sql.Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getHomeworks() {
        return this.homeworks;
    }

    public void setHomeworks(List<Long> homeworks) {
        this.homeworks = homeworks;
    }

    public void addHomework(Long homeworkID) {
        this.homeworks.add(homeworkID);
    }

    public void removeHomework(Long homeworkID) {
        this.homeworks.remove(homeworkID);
    }



    public Optional<Long> getPsychologistID() {
        return psychologistID;
    }

    public void setPsychologistID(Optional<Long> psychologistID) {
        this.psychologistID = psychologistID;
    }

    public void removePsychlogistId(){
        this.psychologistID = Optional.empty();
    }

    @Override
    public String toString() {
        return "Patient{" +
                super.toString() +
                "subscription='" + subscription + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", description='" + description + '\'' +
                ", homeworks=" + homeworks +
                ", psychologistID=" + psychologistID +
                "}\n";
    }
}
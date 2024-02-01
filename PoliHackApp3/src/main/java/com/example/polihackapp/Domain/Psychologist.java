package com.example.polihackapp.Domain;

import java.util.ArrayList;

public class Psychologist extends User {

    private String phoneNumber;
    private ArrayList<Long> patients;

    public Psychologist(String name, String username, String password, String email, String phoneNumber) {
        super(name, username, password, email);
        this.phoneNumber = phoneNumber;
        patients = new ArrayList<>();
    }

    public Psychologist(Long id, String name, String username, String password, String email, String phoneNumber, ArrayList<Long> patients) {
        super(name, username, password, email);
        this.phoneNumber = phoneNumber;
        this.patients = patients;
        setId(id);
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Long> getPatients() {
        return patients;
    }

    public void addPatient(Long along){
        patients.add(along);
    }

    public void removePatient(Long along){
        patients.remove(along);
    }

    public void setPatients(ArrayList<Long> patients) {
        this.patients = patients;
    }


    @Override
    public String toString() {
        return "Psychologist{" +
                super.toString() +
                "phoneNumber='" + phoneNumber + '\'' +
                ", patients=" + patients +
                "}\n";
    }
}















//package com.example.polihackapp.Domain;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class Psychologist extends User {
//
//    private long phoneNumber;
//    private List<Long> patients = new ArrayList<>();
//
//    public Psychologist(String name, String username, String password, String email, long phoneNumb) {
//        super(name, username, password, email);
//        this.phoneNumber = phoneNumber;
//        this.patients = patients;
//    }
//
//    public long getPhoneNumber() {
//        return this.phoneNumber;
//    }
//
//    public void setPhoneNumber() {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public List<Long> getPatients() {
//        return this.patients;
//    }
//
//    public void setPatients(List<Long> patients) {
//        this.patients = patients;
//    }
//
//    public void addPatient(Long patientID) {
//        this.patients.add(patientID);
//    }
//
//    public void removePatient(Long patientID) {
//        this.patients.remove(patientID);
//    }
//
//    @Override
//    public String toString() {
//        return "Psychologist{" +
//                "id=" + getId() +
//                ", name='" + getName() + '\'' +
//                ", username='" + getUsername() + '\'' +
//                ", password='" + getPassword() + '\'' +
//                ", email='" + getEmail() + '\'' +
//                ", phoneNumber=" + phoneNumber +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Psychologist that = (Psychologist) o;
//        return phoneNumber == that.phoneNumber;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(phoneNumber);
//    }
//}
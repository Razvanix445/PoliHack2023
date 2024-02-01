package com.example.polihackapp.Repositories;

import com.example.polihackapp.Domain.Patient;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class RepoDBPatients implements Repository<Long, Patient> {

    protected String url;
    protected String username;
    protected String password;

    public RepoDBPatients(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Optional<Patient> findOne(Long patientID) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("select * from Patient " +
                     "where id = ?");
        ) {
            statement.setInt(1, Math.toIntExact(patientID));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String subscription = resultSet.getString("subscription");
                Date dateOfBirth = resultSet.getDate("dateOfBirth");
                Optional<Long> psychologist = Optional.of(resultSet.getLong("psychologist_id"));
                String description = resultSet.getString("description");
                Array homeworksArray = resultSet.getArray("homeworks");
                Long[] homeworks = (Long[]) homeworksArray.getArray();
                Patient patient = new Patient(id, name, username, password, email, subscription, dateOfBirth, description, (ArrayList<Long>) Arrays.asList(homeworks), psychologist);
                patient.setId(patientID);
                return Optional.of(patient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Patient> findOne(String usernames){
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("select * from Patient " +
                     "where username = ?");
        ) {
            statement.setString(1, usernames);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String username1 = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String subscription = resultSet.getString("subscription");
                Date dateOfBirth = resultSet.getDate("dateOfBirth");
                Optional<Long> psychologist = Optional.of(resultSet.getLong("psychologist_id"));
                String description = resultSet.getString("description");
                Array homeworksArray = resultSet.getArray("homeworks");
                ArrayList<Long> homeworks = new ArrayList<>(Arrays.asList((Long[]) homeworksArray.getArray()));
                return Optional.of(new Patient(id, name, username1, password, email, subscription, dateOfBirth, description, homeworks, psychologist));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Patient> findAll() {
        Set<Patient> patients = new HashSet<>();
        String selectPatientsSQL = "SELECT * FROM Patient";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(selectPatientsSQL);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Long patientID = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String subscription = resultSet.getString("subscription");
                Date dateOfBirth = resultSet.getDate("dateOfBirth");
                String description = resultSet.getString("description");
                Optional<Long> psychologist = Optional.of(resultSet.getLong("psychologist_id"));
                Array homeworksArray = resultSet.getArray("homeworks");
                ArrayList<Long> homeworks = new ArrayList<>(Arrays.asList((Long[]) homeworksArray.getArray()));

                Patient patient = new Patient(patientID, name, username, password, email, subscription, dateOfBirth, description, homeworks, psychologist);
                patient.setId(patientID);
                patients.add(patient);
            }
            return patients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Patient> findPatientsByPsychologist(String psychologistUsername) {
        Set<Patient> patients = new HashSet<>();
        String selectPatientsSQL = "SELECT Patient.*\n" +
                "FROM Patient\n" +
                "JOIN Psychologist ON Patient.psychologist_id = Psychologist.id\n" +
                "WHERE Psychologist.username = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(selectPatientsSQL)
        ) {
            statement.setString(1, psychologistUsername);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Long patientID = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    String subscription = resultSet.getString("subscription");
                    Date dateOfBirth = resultSet.getDate("dateOfBirth");
                    String description = resultSet.getString("description");
                    Optional<Long> psychologist = Optional.of(resultSet.getLong("psychologist_id"));
                    Array homeworksArray = resultSet.getArray("homeworks");
                    ArrayList<Long> homeworks;
                    if (homeworksArray == null) {
                        homeworks = new ArrayList<>();
                    }
                    else {
                        homeworks = new ArrayList<>(Arrays.asList((Long[]) homeworksArray.getArray()));
                    }

                    Patient patient = new Patient(patientID, name, username, password, email, subscription, dateOfBirth, description, homeworks, psychologist);
                    patient.setId(patientID);
                    patients.add(patient);
                }
            }
            return patients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Patient> save(Patient entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null!");

        String insertSQL1 = "INSERT INTO Patient (name, username, password, email, subscription, dateOfBirth, description, homeworks, psychologist_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement1 = connection.prepareStatement(insertSQL1);
        ) {

            statement1.setString(1, entity.getName());
            statement1.setString(2, entity.getUsername());
            statement1.setString(3, entity.getPassword());
            statement1.setString(4, entity.getEmail());
            statement1.setString(5, entity.getSubscription());
            statement1.setDate(6, new java.sql.Date(entity.getDateOfBirth().getTime()));
            statement1.setString(7, entity.getDescription());
            Long[] homeworksArray = entity.getHomeworks().toArray(new Long[0]);
            Array homeworksSqlArray = connection.createArrayOf("BIGINT", homeworksArray);
            statement1.setArray(8, homeworksSqlArray);
            if(entity.getPsychologistID()!=null)
                statement1.setLong(9, entity.getPsychologistID().get());
            else
                statement1.setNull(9, Types.BIGINT);


            int result = statement1.executeUpdate();
            return result == 0 ? Optional.of(entity) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Patient> delete(Long patientID) {
        String deleteSQL = "DELETE FROM Patient WHERE id = ?";

        Optional<Patient> patientToDelete = findOne(patientID);
        if (patientToDelete.isPresent())
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement statement = connection.prepareStatement(deleteSQL);
            ) {
                statement.setLong(1, patientID);
                int result = statement.executeUpdate();
                if (result > 0)
                    return patientToDelete;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return Optional.empty();
    }

    @Override
    public Optional<Patient> delete(String userid) {
        String deleteSQL = "DELETE FROM Patient WHERE username = ?";

        Optional<Patient> patientToDelete = findOne(userid);
        if (patientToDelete.isPresent())
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement statement = connection.prepareStatement(deleteSQL);
            ) {
                statement.setString(1, userid);
                int result = statement.executeUpdate();
                if (result > 0)
                    return patientToDelete;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return Optional.empty();
    }

    @Override
    public Optional<Patient> update(Patient entity) {
        String updateSQL = "UPDATE Patient SET name = ?, username = ?, password = ?, email = ?, subscription = ?, dateOfBirth = ?, description = ?, homeworks = ?, psychologist_id = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(updateSQL);
        ) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getUsername());
            statement.setString(3, entity.getPassword());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getSubscription());
            statement.setDate(6, entity.getDateOfBirth());
            statement.setString(7, entity.getDescription());
            Long[] homeworksArray = entity.getHomeworks().toArray(new Long[0]);
            Array homeworksSqlArray = connection.createArrayOf("BIGINT", homeworksArray);
            statement.setArray(8, homeworksSqlArray);
            if(entity.getPsychologistID().isPresent())
                statement.setLong(9, entity.getPsychologistID().get());
            else
                statement.setNull(9,Types.BIGINT);
            statement.setLong(10,entity.getId());
            int result = statement.executeUpdate();
            return result == 0 ? Optional.empty() : Optional.of(entity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
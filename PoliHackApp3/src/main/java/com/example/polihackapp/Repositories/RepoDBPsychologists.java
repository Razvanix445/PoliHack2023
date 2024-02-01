package com.example.polihackapp.Repositories;

import com.example.polihackapp.Domain.Psychologist;

import java.util.Optional;
import java.sql.*;
import java.util.*;

public class RepoDBPsychologists implements Repository<Long, Psychologist>{
    private String url, username, password;

    public RepoDBPsychologists(String url, String username, String password) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    @Override
    public Optional<Psychologist> findOne(Long aLong) {
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = connection.prepareStatement("select * from Psychologist where id=?;")
        ){
            statement.setLong(1, aLong);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("numar_telefon");
                Array patientsArray = resultSet.getArray("patients");
                ArrayList<Long> patients = new ArrayList<>(Arrays.asList((Long[]) patientsArray.getArray()));

                Psychologist psychologist = new Psychologist(id,name,username,password,email,phoneNumber,patients);
                return Optional.of(psychologist);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Psychologist> findOne(String usernames) {
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = connection.prepareStatement("select * from Psychologist where username=?;")
        ){
            statement.setString(1, usernames);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("numar_telefon");
                Array patientsArray = resultSet.getArray("patients");
                ArrayList<Long> patients = new ArrayList<>(Arrays.asList((Long[]) patientsArray.getArray()));

                Psychologist psychologist = new Psychologist(id,name,username,password,email,phoneNumber,patients);
                return Optional.of(psychologist);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Psychologist> findAll() {
        Set<Psychologist> psychologists = new HashSet<>();
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = connection.prepareStatement("select * from Psychologist");
            ResultSet resultSet = statement.executeQuery();
        ){
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("numar_telefon");
                Array patientsArray = resultSet.getArray("patients");
                ArrayList<Long> patients = new ArrayList<>(Arrays.asList((Long[]) patientsArray.getArray()));

                Psychologist psychologist = new Psychologist(id,name,username,password,email,phoneNumber,patients);
                psychologists.add(psychologist);
            }
            return psychologists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Psychologist> save(Psychologist entity) {
        if(entity==null){
            throw new IllegalArgumentException("Entity must not be null");
        }
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = connection.prepareStatement("insert into Psychologist (name, username, password,email,numar_telefon,patients) values (?,?,?,?,?,?)");
        ) {
            statement.setString(1,entity.getName());
            statement.setString(2,entity.getUsername());
            statement.setString(3,entity.getPassword());
            statement.setString(4,entity.getEmail());
            statement.setString(5,entity.getPhoneNumber());
            Long[] data = entity.getPatients().toArray(new Long[0]);
            statement.setArray(6,connection.createArrayOf("bigint",data));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Psychologist> delete(Long aLong) {
        Optional<Psychologist> u = findOne(aLong);
        if(u.isEmpty())
            return u;
        try(Connection connection  = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Psychologist WHERE id = ?");
        ) {
            statement.setLong(1,aLong);
            statement.executeUpdate();
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Psychologist> delete(String userid) {
        Optional<Psychologist> u = findOne(userid);
        if(u.isEmpty())
            return u;
        try(Connection connection  = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Psychologist WHERE username = ?");
        ) {
            statement.setString(1,userid);
            statement.executeUpdate();
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Psychologist> update(Psychologist entity) {
        if(entity==null)
            throw new IllegalArgumentException("Entity must not be null1");
        Optional<Psychologist> ret = findOne(entity.getId());
        if(ret.isEmpty())
            return Optional.ofNullable(entity);
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = connection.prepareStatement("UPDATE Psychologist SET name = ?, username = ?, password =?, email =?, numar_telefon=?, patients=? WHERE id = ?");
        ) {
            statement.setString(1,entity.getName());
            statement.setString(2,entity.getUsername());
            statement.setString(3,entity.getPassword());
            statement.setString(4,entity.getEmail());
            statement.setString(5,entity.getPhoneNumber());
            Long[] data = entity.getPatients().toArray(new Long[0]);
            statement.setArray(6,connection.createArrayOf("bigint",data));
            statement.setLong(7,entity.getId());
            statement.executeUpdate();
            return Optional.empty();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
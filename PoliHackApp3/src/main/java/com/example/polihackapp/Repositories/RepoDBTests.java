package com.example.polihackapp.Repositories;

import com.example.polihackapp.Domain.Patient;
import com.example.polihackapp.Domain.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RepoDBTests {

    protected String url;
    protected String username;
    protected String password;

    public RepoDBTests(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Optional<Test> findOne(Long testID) {
        String selectSQL = "SELECT * FROM Tests WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(selectSQL)) {

            statement.setLong(1, testID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                List<Long> imageIDs = Arrays.asList((Long[]) resultSet.getArray("image_list").getArray());
                List<String> variants = Arrays.asList((String[]) resultSet.getArray("variants").getArray());
                String correctAnswer = resultSet.getString("correct_answer");
                String description = resultSet.getString("description");

                Test test = new Test(testID, imageIDs, variants, correctAnswer, description);
                return Optional.of(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Test> findAll() {
        List<Test> tests = new ArrayList<>();
        String selectSQL = "SELECT * FROM Tests";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                Long testID = resultSet.getLong("id");
                List<Long> imageIDs = Arrays.asList((Long[]) resultSet.getArray("image_list").getArray());
                List<String> variants = Arrays.asList((String[]) resultSet.getArray("variants").getArray());
                String correctAnswer = resultSet.getString("correct_answer");
                String description = resultSet.getString("description");

                Test test = new Test(testID, imageIDs, variants, correctAnswer, description);
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public void save(List<Long> imageIDs, List<String> variants, String correctAnswer, String description) {


        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Insert image data into the database
            String insertSQL = "INSERT INTO Tests (image_list, variants, correct_answer, description) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertSQL)) {
                statement.setArray(1, connection.createArrayOf("BIGINT", imageIDs.toArray()));
                statement.setArray(2, connection.createArrayOf("VARCHAR", variants.toArray()));
                statement.setString(3, correctAnswer);
                statement.setString(4, description);

                int affectedRows = statement.executeUpdate();
                System.out.println("Rows affected: " + affectedRows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private byte[] readImageFile(File imageFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(imageFile)) {
            byte[] imageData = new byte[(int) imageFile.length()];
            fis.read(imageData);
            return imageData;
        }
    }
}

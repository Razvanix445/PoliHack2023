package com.example.polihackapp.Domain;

import java.util.List;
import java.util.Objects;

public class Test {

    private Long id;
    private List<Long> imageIDs;
    private List<String> variants;
    private String correctAnswer;
    private String description;

    public Test(Long id, List<Long> imageIDs, List<String> variants, String correctAnswer, String description) {
        this.id = id;
        this.imageIDs = imageIDs;
        this.variants = variants;
        this.correctAnswer = correctAnswer;
    }

    public Long getTestID() {
        return this.id;
    }

    public void setTestID(Long id) {
        this.id = id;
    }

    public List<Long> getImageIDs() {
        return imageIDs;
    }

    public void setImage(List<Long> imageIDs) {
        this.imageIDs = imageIDs;
    }

    public void addImageID(Long imageID) {
        imageIDs.add(imageID);
    }

    public void removeImageID(Long imageID) {
        imageIDs.remove(imageID);
    }

    public List<String> getVariants() {
        return variants;
    }

    public void setVariants(List<String> variants) {
        this.variants = variants;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Tests{" +
                "imageIDs='" + imageIDs + '\'' +
                ", variants=" + variants +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test tests = (Test) o;
        return Objects.equals(imageIDs, tests.imageIDs) && Objects.equals(variants, tests.variants) && Objects.equals(correctAnswer, tests.correctAnswer) && Objects.equals(description, tests.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageIDs, variants, correctAnswer, description);
    }
}

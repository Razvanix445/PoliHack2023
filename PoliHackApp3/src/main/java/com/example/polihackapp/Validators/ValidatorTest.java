package com.example.polihackapp.Validators;

import com.example.polihackapp.Domain.Patient;
import com.example.polihackapp.Domain.Psychologist;
import com.example.polihackapp.Domain.Test;
import com.example.polihackapp.Repositories.Repository;

public class ValidatorTest implements Validator<Test> {

    @Override
    public void validate(Test entity) throws ValidationException {
        if (entity.getVariants().isEmpty() || entity.getDescription().isEmpty() || entity.getCorrectAnswer().isEmpty())
            throw new ValidationException("Invalid user data!");
        if (entity.getVariants() == null || entity.getDescription() == null || entity.getCorrectAnswer() == null)
            throw new ValidationException("Invalid user data!");
    }
}

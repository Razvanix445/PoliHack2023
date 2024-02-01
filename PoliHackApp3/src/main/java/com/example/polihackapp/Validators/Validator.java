package com.example.polihackapp.Validators;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}


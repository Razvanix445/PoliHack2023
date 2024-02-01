package com.example.polihackapp.Validators;

import com.example.polihackapp.Domain.Patient;
import com.example.polihackapp.Repositories.Repository;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class PatientValidator implements Validator<Patient> {
    Repository<Long, Patient> repo;

    public PatientValidator(Repository<Long, Patient> repo) {
        this.repo = repo;
    }

    @Override
    public void validate(Patient entity) throws ValidatorException {
        ArrayList<String> errs = new ArrayList<>();
        if(entity==null)
            errs.add("Psychologist is null");
        else
        {
            if(repo.findOne(entity.getUsername()).isPresent())
                errs.add("Username is taken");
            if (entity.getName()==null)
                errs.add("Name cannot be empty");
            if (entity.getEmail()==null)
                errs.add("Email cannot be empty");
            if (entity.getPassword()==null)
                errs.add("Password cannot be empty");
            if (entity.getUsername()==null)
                errs.add("Username cannot be empty");
        }

        if(!errs.isEmpty())
            throw new ValidatorException(errs);
    }
}
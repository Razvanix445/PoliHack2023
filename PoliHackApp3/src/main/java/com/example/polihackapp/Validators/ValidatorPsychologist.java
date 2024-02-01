package com.example.polihackapp.Validators;

import com.example.polihackapp.Domain.Psychologist;
import com.example.polihackapp.Repositories.Repository;

import java.util.ArrayList;

public class ValidatorPsychologist implements Validator<Psychologist>{
    Repository<Long, Psychologist> repo;

    public ValidatorPsychologist(Repository<Long, Psychologist> repo) {
        this.repo = repo;
    }

    @Override
    public void validate(Psychologist entity) throws ValidatorException {
        ArrayList<String> errs = new ArrayList<>();
        if(entity==null)
            errs.add("Psychologist is null");
        else
        {
            if(repo.findOne(entity.getUsername()).isPresent())
                errs.add("username is taken");
            if (entity.getName()==null)
                errs.add("Name cannot be empty");
            if (entity.getEmail()==null)
                errs.add("Email cannot be empty");
            if (entity.getPassword()==null)
                errs.add("Password cannot be empty");
            if (entity.getUsername()==null)
                errs.add("Username cannot be empty");
            if (entity.getPhoneNumber()==null)
                errs.add("Phone number cannot be empty");
        }

        if(!errs.isEmpty())
            throw new ValidatorException(errs);
    }
}

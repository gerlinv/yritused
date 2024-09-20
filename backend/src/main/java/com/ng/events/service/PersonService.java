package com.ng.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ng.events.model.Person;
import com.ng.events.repository.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Person findByIdentificationCode(Long identificationCode) {
        return personRepository.findByIdentificationCode(identificationCode);
    }
    
}

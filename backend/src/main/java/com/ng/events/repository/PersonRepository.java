package com.ng.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ng.events.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> { 
    
    Person findByIdentificationCode(Long identificationCode);

}

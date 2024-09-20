package com.ng.events.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ng.events.dto.EventDto;
import com.ng.events.dto.RegisterDto;
import com.ng.events.dto.ValidationException;
import com.ng.events.model.Event;
import com.ng.events.model.Person;
import com.ng.events.repository.EventRepository;

@Service
public class EventService {
    
    private final EventRepository eventRepository;
    private final PersonService personService;

    @Autowired
    public EventService(EventRepository eventRepository, PersonService personService) {
        this.eventRepository = eventRepository;
        this.personService = personService;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(EventDto eventDto) {
        if (eventDto.getStartTime().isAfter(eventDto.getEndTime())) {
            throw new ValidationException("Start time cannot be after the end time.");
        }

        if (eventDto.getMaxParticipants() < 0) {
            throw new ValidationException("Max participants cannot be a negative number.");
        }

        Event newEvent = new Event();
        newEvent.setName(eventDto.getName());
        newEvent.setMaxParticipants(eventDto.getMaxParticipants());
        newEvent.setStartTime(eventDto.getStartTime());
        newEvent.setEndTime(eventDto.getEndTime());

        Event createdEvent = eventRepository.save(newEvent);

        return createdEvent;
    }

    public Event registerToEvent(RegisterDto registerDto) {
        if (Long.toString(registerDto.getIdentificationCode()).length() != 11) {
            throw new ValidationException("Identification code must be exactly 11 characters long.");
        }

        Person existingPerson = personService.findByIdentificationCode(registerDto.getIdentificationCode());
        if (existingPerson != null) {
            throw new ValidationException("Person with this identification code already exists.");
        }

        Optional<Event> optionalEvent = eventRepository.findById(registerDto.getEventId());
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();

            Person newPerson = new Person();
            newPerson.setFirstName(registerDto.getFirstName());
            newPerson.setLastName(registerDto.getLastName());
            newPerson.setIdentificationCode(registerDto.getIdentificationCode());

            Person savedPerson = personService.savePerson(newPerson);

            if (event.getParticipants() != null) {
                event.getParticipants().add(savedPerson);
            } else {
                event.setParticipants(List.of(savedPerson));
            }
            Event updatedEvent = eventRepository.save(event);

            return updatedEvent;
        } else {
            throw new ValidationException("Event not found.");
        }
    }
    
}

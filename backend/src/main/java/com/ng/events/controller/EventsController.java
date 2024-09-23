package com.ng.events.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ng.events.dto.ErrorResponseDto;
import com.ng.events.dto.EventDto;
import com.ng.events.dto.RegisterDto;
import com.ng.events.model.Event;
import com.ng.events.service.EventService;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/api/event")
public class EventsController {
    
    @Autowired
    private EventService eventService;

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(ValidationException ex) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<?> getEvents() {
        List<Event> res = eventService.getAllEvents();
        return (res != null && !res.isEmpty()) ? new ResponseEntity<>(res, HttpStatus.OK) : new ResponseEntity<>("No events found", HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@Valid @RequestBody EventDto eventDto) {
        // CHECK IF USER IS ADMIN!!
        Event res = eventService.createEvent(eventDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> registerToEvent(@Valid @RequestBody RegisterDto personDto) {
        Event res = eventService.registerToEvent(personDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
}

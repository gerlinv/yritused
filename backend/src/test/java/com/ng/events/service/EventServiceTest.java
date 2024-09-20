package com.ng.events.service;

import com.ng.events.dto.EventDto;
import com.ng.events.dto.RegisterDto;
import com.ng.events.dto.ValidationException;
import com.ng.events.model.Event;
import com.ng.events.model.Person;
import com.ng.events.repository.EventRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private PersonService personService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEvent_Success() {
        EventDto eventDto = new EventDto();
        eventDto.setName("Test Event");
        eventDto.setStartTime(LocalDateTime.of(2024, 9, 19, 10, 0));
        eventDto.setEndTime(LocalDateTime.of(2024, 9, 19, 12, 0));
        eventDto.setMaxParticipants(100L);

        Event savedEvent = new Event();
        savedEvent.setName(eventDto.getName());
        savedEvent.setStartTime(eventDto.getStartTime());
        savedEvent.setEndTime(eventDto.getEndTime());
        savedEvent.setMaxParticipants(eventDto.getMaxParticipants());

        when(eventRepository.save(any(Event.class))).thenReturn(savedEvent);

        Event result = eventService.createEvent(eventDto);

        assertNotNull(result);
        assertEquals(eventDto.getName(), result.getName());
        assertEquals(eventDto.getStartTime(), result.getStartTime());
        assertEquals(eventDto.getEndTime(), result.getEndTime());
        assertEquals(eventDto.getMaxParticipants(), result.getMaxParticipants());
    }

    @Test
    public void testCreateEvent_StartTimeAfterEndTime() {
        EventDto eventDto = new EventDto();
        eventDto.setName("Test Event");
        eventDto.setStartTime(LocalDateTime.of(2024, 9, 19, 14, 0));
        eventDto.setEndTime(LocalDateTime.of(2024, 9, 19, 12, 0));
        eventDto.setMaxParticipants(100L);

        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            eventService.createEvent(eventDto);
        });

        assertEquals("Start time cannot be after the end time.", thrown.getMessage());
    }

    @Test
    public void testCreateEvent_NegativeMaxParticipants() {
        EventDto eventDto = new EventDto();
        eventDto.setName("Test Event");
        eventDto.setStartTime(LocalDateTime.of(2024, 9, 19, 10, 0));
        eventDto.setEndTime(LocalDateTime.of(2024, 9, 19, 12, 0));
        eventDto.setMaxParticipants(-10L);

        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            eventService.createEvent(eventDto);
        });

        assertEquals("Max participants cannot be a negative number.", thrown.getMessage());
    }

    @Test
    public void testRegisterToEvent_Success() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEventId(1L);
        registerDto.setFirstName("John");
        registerDto.setLastName("Doe");
        registerDto.setIdentificationCode(12345678901L);

        Event event = new Event();
        event.setId(1L);

        Person person = new Person();
        person.setFirstName(registerDto.getFirstName());
        person.setLastName(registerDto.getLastName());
        person.setIdentificationCode(registerDto.getIdentificationCode());

        when(eventRepository.findById(registerDto.getEventId())).thenReturn(Optional.of(event));
        when(personService.findByIdentificationCode(registerDto.getIdentificationCode())).thenReturn(null);
        when(personService.savePerson(any(Person.class))).thenReturn(person);
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event result = eventService.registerToEvent(registerDto);

        assertNotNull(result);
        assertTrue(result.getParticipants().contains(person));
    }

    @Test
    public void testRegisterToEvent_IdentificationCodeInvalid() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEventId(1L);
        registerDto.setFirstName("John");
        registerDto.setLastName("Doe");
        registerDto.setIdentificationCode(1234567890L); // 10 characters

        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            eventService.registerToEvent(registerDto);
        });

        assertEquals("Identification code must be exactly 11 characters long.", thrown.getMessage());
    }

    @Test
    public void testRegisterToEvent_PersonAlreadyExists() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEventId(1L);
        registerDto.setFirstName("John");
        registerDto.setLastName("Doe");
        registerDto.setIdentificationCode(12345678901L);

        Person existingPerson = new Person();
        existingPerson.setIdentificationCode(registerDto.getIdentificationCode());

        when(personService.findByIdentificationCode(registerDto.getIdentificationCode())).thenReturn(existingPerson);

        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            eventService.registerToEvent(registerDto);
        });

        assertEquals("Person with this identification code already exists.", thrown.getMessage());
    }

    @Test
    public void testRegisterToEvent_EventNotFound() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEventId(1L);
        registerDto.setFirstName("John");
        registerDto.setLastName("Doe");
        registerDto.setIdentificationCode(12345678901L);

        when(eventRepository.findById(registerDto.getEventId())).thenReturn(Optional.empty());

        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            eventService.registerToEvent(registerDto);
        });

        assertEquals("Event not found.", thrown.getMessage());
    }
}

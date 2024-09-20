package com.ng.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ng.events.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}

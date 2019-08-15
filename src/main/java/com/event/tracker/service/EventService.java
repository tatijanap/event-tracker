package com.event.tracker.service;

import java.util.Map;

import com.event.tracker.exception.ResourceNotFoundException;
import com.event.tracker.model.Event;
import org.springframework.stereotype.Service;

@Service
public interface EventService {

  Event createEvent(Integer clubId, Event event);

  Event updateEvent(Integer id, Map Fields) throws ResourceNotFoundException;

  Event getEvent(Integer id) throws ResourceNotFoundException;

  boolean deleteEvent(Integer id) throws ResourceNotFoundException;

}

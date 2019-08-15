package com.event.tracker.service.impl;

import java.lang.reflect.Field;
import java.util.Map;

import com.event.tracker.exception.ResourceNotFoundException;
import com.event.tracker.model.Event;
import com.event.tracker.repository.ClubRepository;
import com.event.tracker.repository.EventRepository;
import com.event.tracker.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

@Service
public class EventServiceImpl implements EventService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private ClubRepository clubRepository;

  @Override
  public Event createEvent(Integer clubId, Event event) {
    Assert.notNull(clubId, "Club Id must be provided!");
    Assert.notNull(event, "Entity must not be null!");
    return clubRepository.findById(clubId).map(club -> {
      event.setClub(club);
      return eventRepository.save(event);
    }).orElseThrow(() -> new ResourceNotFoundException("Club with id " + clubId + " not found"));
  }

  @Override
  public Event updateEvent(Integer id, Map fields) throws ResourceNotFoundException {
    Assert.notNull(id, "Id must not be null!");
    Event event = this.getEvent(id);
    Assert.notNull(event, "Entity must not be null!");
    if(!eventRepository.existsById(id)) {
      throw new ResourceNotFoundException("Club with id: " + id + " not found!");
    }
    fields.forEach((key,value)->{
      Field field = ReflectionUtils.findField(Event.class, (String)key);
      Assert.notNull(field, "Field: [" + key + "] does not exist!");
      ReflectionUtils.makeAccessible(field);
      ReflectionUtils.setField(field, event, value);
    });
    event.setEventId(id);
    return eventRepository.save(event);
  }

  @Override
  public Event getEvent(Integer id) throws ResourceNotFoundException {
    Assert.notNull(id, "Id must not be null!");
    Event event = eventRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("Event with id: " + id + " not found!")
        );
    return event;
  }

  @Override
  public boolean deleteEvent(Integer id) throws ResourceNotFoundException {
    Assert.notNull(id, "Id must not be null!");
    Event event = eventRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("Club with id: " + id + " not found!")
        );
    eventRepository.delete(event);
    return !eventRepository.existsById(id);
  }
}

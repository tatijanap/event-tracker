package com.event.tracker.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.event.tracker.constraint.APIRouteConstraints;
import com.event.tracker.model.Event;
import com.event.tracker.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIRouteConstraints.DEAFAULT_ROUTE)
@ExposesResourceFor(Event.class)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventsController {

  @Autowired
  private EventService eventService;

  private static final Logger logger = LoggerFactory.getLogger(EventsController.class);


  @PostMapping(APIRouteConstraints.CLUBS_ROUTE+"/{clubId}"+APIRouteConstraints.EVENTS_ROUTE)
  public ResponseEntity<Object> createEvent(
      @PathVariable("clubId") Integer clubId,
      @Valid @RequestBody Event event){
    logger.info("Creating event {}", event);
    Event newEvent = eventService.createEvent(clubId, event);
    return ResponseEntity.ok(newEvent);
  }

  @PatchMapping(APIRouteConstraints.EVENTS_ROUTE + "/{eventId}")
  public ResponseEntity<Object> updateEvent(
      @RequestBody Map<Object, Object> fields,
      @PathVariable("eventId") Integer id
  ){
    logger.info("Updating event with id {}", id);
    Event updatedEvent = eventService.updateEvent(id, fields);
    return ResponseEntity.ok(updatedEvent);
  }

  @DeleteMapping(APIRouteConstraints.EVENTS_ROUTE + "/{eventId}")
  public ResponseEntity<Object> deleteEvent(
      @PathVariable("eventId") Integer id
  ){
    logger.info("Deleting event with id {}", id);
    boolean isDeleted = eventService.deleteEvent(id);
    return ResponseEntity.ok(isDeleted ? 1 : 0);
  }

}

package com.event.tracker.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.event.tracker.constraint.APIRouteConstraints;
import com.event.tracker.model.Club;
import com.event.tracker.service.ClubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIRouteConstraints.DEAFAULT_ROUTE)
@ExposesResourceFor(Club.class)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClubsController {

  @Autowired
  private ClubService clubService;

  private static final Logger logger = LoggerFactory.getLogger(ClubsController.class);

  @RequestMapping(value = APIRouteConstraints.CLUBS_ROUTE, method = RequestMethod.GET)
  public ResponseEntity<Object> findClubs(
      @RequestParam(name = "name", required = false, defaultValue = "") String name) {
    logger.info("Fetching clubs with name {}", name);
    List<Club> clubs = clubService.getClubs(name);
    for(Club club: clubs) {
      club.add(linkTo(methodOn(ClubsController.class).findClub(club.getClubId())).withSelfRel());
    }
    return new ResponseEntity<>(clubs, HttpStatus.OK);
  }

  @RequestMapping(value = APIRouteConstraints.CLUBS_ROUTE + "/{clubId}", method = RequestMethod.GET)

  public ResponseEntity<Object> findClub(@PathVariable("clubId") Integer id) {
    logger.info("Fetching club with id {}", id);
    Club club = clubService.getClub(id);
    club.add(linkTo(methodOn(ClubsController.class).findClub(id)).withSelfRel());
    return new ResponseEntity<>(club, HttpStatus.OK);
  }


  @PostMapping(APIRouteConstraints.CLUBS_ROUTE)
  public ResponseEntity<Object> createClub(@Valid @RequestBody Club club){
    logger.info("Creating club {}", club);
    Club newClub = clubService.createClub(club);
    return ResponseEntity.ok(newClub);
  }

  @RequestMapping(value = APIRouteConstraints.CLUBS_ROUTE + "/{clubId}", method = RequestMethod.PATCH)
  public ResponseEntity<Object> updateClub(
      @RequestBody Map<Object, Object> fields,
      @PathVariable("clubId") Integer id
  ){
    logger.info("Updating club with id {}", id);
    Club updatedClub = clubService.updateClub(id, fields);
    return ResponseEntity.ok(updatedClub);
  }

  @RequestMapping(value = APIRouteConstraints.CLUBS_ROUTE + "/{clubId}", method = RequestMethod.DELETE)
  public ResponseEntity<Object> deleteClub(
      @PathVariable("clubId") Integer id
  ){
    logger.info("Deleting club with id {}", id);
    boolean isDeleted = clubService.deleteClub(id);
    return ResponseEntity.ok(isDeleted ? 1 : 0);
  }

}

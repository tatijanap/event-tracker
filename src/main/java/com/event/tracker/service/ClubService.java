package com.event.tracker.service;

import java.util.List;
import java.util.Map;

import com.event.tracker.exception.ResourceNotFoundException;
import com.event.tracker.model.Club;
import org.springframework.stereotype.Service;

@Service
public interface ClubService {

  Club createClub(Club club);

  Club updateClub(Integer id, Map fields) throws ResourceNotFoundException;

  Club getClub(Integer id) throws ResourceNotFoundException;

  List<Club> getClubs(String name);

  boolean deleteClub(Integer id) throws ResourceNotFoundException;
}

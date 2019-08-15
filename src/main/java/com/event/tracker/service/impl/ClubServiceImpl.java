package com.event.tracker.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.event.tracker.exception.ResourceNotFoundException;
import com.event.tracker.model.Club;
import com.event.tracker.repository.ClubRepository;
import com.event.tracker.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

@Service
public class ClubServiceImpl implements ClubService {

  @Autowired
  private ClubRepository clubRepository;

  @Override
  public Club createClub(Club club) {
    Assert.notNull(club, "Entity must not be null!");
    return clubRepository.save(club);
  }

  @Override
  public Club updateClub(Integer id, Map fields) throws ResourceNotFoundException {
    Assert.notNull(id, "Id must not be null!");
    Club club = this.getClub(id);
    Assert.notNull(club, "Entity must not be null!");
    if(!clubRepository.existsById(id)) {
      throw new ResourceNotFoundException("Club with id: " + id + " not found!");
    }
    fields.forEach((key,value)->{
      Field field = ReflectionUtils.findField(Club.class, (String)key);
      Assert.notNull(field, "Field: [" + key + "] does not exist!");
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, club, value);
    });
    club.setClubId(id);
    return clubRepository.save(club);
  }

  @Override
  public Club getClub(Integer id) throws ResourceNotFoundException {
    Assert.notNull(id, "Id must not be null!");
    Club club = clubRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("Club with id: " + id + " not found!")
        );
    return club;
  }

  @Override
  public List<Club> getClubs(String name) {
    List<Club> clubs = clubRepository.findByNameContainsIgnoreCase(name);
    return clubs;
  }

  @Override
  public boolean deleteClub(Integer id) throws ResourceNotFoundException {
    Assert.notNull(id, "Id must not be null!");
    Club club = clubRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("Club with id: " + id + " not found!")
        );
    clubRepository.delete(club);
    return !clubRepository.existsById(id);
  }

}

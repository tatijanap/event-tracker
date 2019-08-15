package com.event.tracker.repository;

import java.util.List;

import com.event.tracker.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {
  List<Club> findByNameContainsIgnoreCase(String name);
}

package com.event.tracker.model;

import java.util.List;

import javax.persistence.*;

import com.event.tracker.constraint.DbTableConstraints;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = DbTableConstraints.CLUB_TABLE_NAME)
@JsonPropertyOrder({"clubId", "name"})
public class Club extends AuditModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "club_id_sequence_generator")
  @SequenceGenerator(name="club_id_sequence_generator", sequenceName = "tracker_club_id_seq", allocationSize = 1)
  @Column(name="id")
  @JsonProperty("clubId")
  private Integer clubId;

  @Column(name="name")
  @JsonProperty("name")
  private String name;

  @Column(name="president")
  @JsonProperty("president")
  private String president;

  @Column(name="short_description")
  @JsonProperty("description")
  private String description;

  @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
  @JsonProperty("events")
  private List<Event> events;


  public Integer getClubId() {
    return clubId;
  }

  public void setClubId(Integer clubId) {
    this.clubId = clubId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPresident() {
    return president;
  }

  public void setPresident(String president) {
    this.president = president;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }
}

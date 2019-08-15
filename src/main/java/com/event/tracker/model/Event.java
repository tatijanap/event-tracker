package com.event.tracker.model;

import java.util.Date;

import javax.persistence.*;

import com.event.tracker.constraint.DbTableConstraints;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = DbTableConstraints.EVENT_TABLE_NAME)
public class Event extends AuditModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "event_id_sequence_generator")
  @SequenceGenerator(name = "event_id_sequence_generator", sequenceName = "tracker_event_id_seq", allocationSize = 1)
  @Column(name = "id")
  @JsonProperty("eventId")
  private Integer eventId;

  @Column(name = "name")
  @JsonProperty("name")
  private String name;

  @Column(name = "date")
  @JsonProperty("date")
  private Date date;

  @Column(name = "attendance")
  @JsonProperty("attendance")
  private Integer attendance;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Club.class, optional = false)
  @JoinColumn(name = "club_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Club club;

  public Integer getEventId() {
    return eventId;
  }

  public void setEventId(Integer eventId) {
    this.eventId = eventId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getAttendance() {
    return attendance;
  }

  public void setAttendance(Integer attendance) {
    this.attendance = attendance;
  }

  public Club getClub() {
    return club;
  }

  public void setClub(Club club) {
    this.club = club;
  }
}

package com.luan.eventsmicroservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "event")
@Table(name = "event")
@Data
@EqualsAndHashCode(of = "id")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private final Integer maxParticipants;
    private final String date;
    private final String title;
    private final String description;

    private Event (Integer maxParticipants, String date, String title, String description) {
        this.maxParticipants = maxParticipants;
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public static Event create (Integer maxParticipants, String date, String title, String description) {
        return new Event(maxParticipants, date, title, description);
    }
}

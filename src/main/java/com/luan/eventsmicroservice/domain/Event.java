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
    private final String id;
    private final Integer maxParticipants;
    private final String date;
    private final String title;
    private final String description;

    private Event (String id, Integer maxParticipants, String date, String title, String description) {
        this.id = id;
        this.maxParticipants = maxParticipants;
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public static Event create (String id, Integer maxParticipants, String date, String title, String description) {
        return new Event(id, maxParticipants, date, title, description);
    }
}

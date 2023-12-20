package com.luan.eventsmicroservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "subscription")
@Table(name = "subscription")
@Data
@EqualsAndHashCode(of = "id")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private final Event event;

    private final String participantEmail;

    private Subscription (Event event, String participantEmail) {
        this.event = event;
        this.participantEmail = participantEmail;
    }

    public static Subscription create (Event event, String participantEmail) {
        return new Subscription(event, participantEmail);
    }
}

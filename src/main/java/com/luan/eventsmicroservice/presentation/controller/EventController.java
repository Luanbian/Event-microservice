package com.luan.eventsmicroservice.presentation.controller;

import com.luan.eventsmicroservice.core.dtos.EventRequestDto;
import com.luan.eventsmicroservice.core.dtos.SubscriptionRequestDto;
import com.luan.eventsmicroservice.data.services.EventService;
import com.luan.eventsmicroservice.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/upcomingEvents")
    public List<Event> getUpcomingEvents() {
        return eventService.getUpcomingEvents();
    }

    @PostMapping
    public Event createEvent(@RequestBody EventRequestDto eventRequest) {
        return eventService.createEvent(eventRequest);
    }

    @PostMapping("/{eventId}/register")
    public void registerNewParticipant(@PathVariable String eventId, @RequestBody SubscriptionRequestDto subscriptionRequest) {
        eventService.registerParticipant(eventId, subscriptionRequest.participantEmail());
    }
}

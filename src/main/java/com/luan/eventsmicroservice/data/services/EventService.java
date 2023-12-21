package com.luan.eventsmicroservice.data.services;

import com.luan.eventsmicroservice.core.dtos.EmailRequestDto;
import com.luan.eventsmicroservice.core.dtos.EventRequestDto;
import com.luan.eventsmicroservice.domain.Event;
import com.luan.eventsmicroservice.domain.Subscription;
import com.luan.eventsmicroservice.exceptions.EventFullException;
import com.luan.eventsmicroservice.exceptions.EventNotFoundException;
import com.luan.eventsmicroservice.repositories.EventRepository;
import com.luan.eventsmicroservice.repositories.SubscriptionRepository;
import com.luan.eventsmicroservice.services.EmailServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EmailServiceClient emailServiceClient;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository.findUpcomingEvents(LocalDateTime.now());
    }

    public Event createEvent(EventRequestDto eventRequest) {
        Event newEvent = Event.create(
                eventRequest.maxParticipants(),
                eventRequest.date(),
                eventRequest.title(),
                eventRequest.description()
        );
        return eventRepository.save(newEvent);
    }

    public void registerParticipant(String eventId, String participantEmail) {
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);

        if (isEventFull(event)) throw new EventFullException();

        Subscription subscription = Subscription.create(event,participantEmail);
        subscriptionRepository.save(subscription);

        sendMailToNewSubscription(subscription.getParticipantEmail());
    }

    private Boolean isEventFull(Event event) {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions.size() >= event.getMaxParticipants();
    }

    private void sendMailToNewSubscription(String participantEmail) {
        EmailRequestDto emailRequestDto = new EmailRequestDto(participantEmail, "Você está inscrito!", "Parabéns e viva Cristo Rei");
        emailServiceClient.SendEmail(emailRequestDto);
    }
}

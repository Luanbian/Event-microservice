package com.luan.eventsmicroservice.exceptions;

public class EventFullException extends RuntimeException {
    public EventFullException() {
        super("Evento lotado");
    }

    public EventFullException(String message) {
        super(message);
    }
}

package com.marketboro.demo.event;

import com.marketboro.demo.order.AlertEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final ApplicationEventPublisher applicationEventPublisher;

    public EventService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishAlertEvent(String destination, String message) {
        AlertEvent event = new AlertEvent(this, destination, message);
        applicationEventPublisher.publishEvent(event);
    }
}

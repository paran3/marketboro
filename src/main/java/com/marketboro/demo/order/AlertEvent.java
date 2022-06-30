package com.marketboro.demo.order;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter
public class AlertEvent extends ApplicationEvent {

    private String destination;
    private String message;


    public AlertEvent(Object source, String destination, String message) {
        super(source);
        this.destination = destination;
        this.message = message;
    }

}

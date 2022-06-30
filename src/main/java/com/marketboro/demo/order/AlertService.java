package com.marketboro.demo.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AlertService implements ApplicationListener<AlertEvent> {

    @Override
    public void onApplicationEvent(AlertEvent event) {
        log.info("To : " + event.getDestination() + ", Message : " + event.getMessage());
    }
}

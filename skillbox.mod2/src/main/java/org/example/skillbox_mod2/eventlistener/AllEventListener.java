package org.example.skillbox_mod2.eventlistener;

import lombok.extern.log4j.Log4j2;
import org.example.skillbox_mod2.eventlistener.event.AddEvent;
import org.example.skillbox_mod2.eventlistener.event.DeleteEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AllEventListener {

    @EventListener
    public void handleAddUserEvent(AddEvent addEvent) {
        log.info("User ({}) added", addEvent.message());
    }


    @EventListener
    public void handleDeleteUserEvent(DeleteEvent deleteEvent) {
        log.info("User ({}) deleted", deleteEvent.message());
    }
}

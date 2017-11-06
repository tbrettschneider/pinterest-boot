package de.tb.showroom.pinterest.repositories;

import de.tb.showroom.pinterest.model.Pin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.PostPersist;

@Service
public class LivePinsListener {

    @Autowired
    private SimpMessagingTemplate webSocket;

    @Async
    public void pushChangesToWebSocket(Pin pin) {
        System.out.println("Pin stored in db: " + pin);
        webSocket.convertAndSend("/topic/pins", pin);
    }

    @PostPersist
    void onPostPersist(Pin pin) {
        AutowireHelper.autowire(this, this.webSocket);
        this.pushChangesToWebSocket(pin);
    }
}

package com.onionsoap.econosim.event;

import javafx.event.Event;
import javafx.event.EventType;

public class PaydayEvent extends Event {
    public static final EventType<PaydayEvent> ANY = new EventType<>(Event.ANY, "PAYDAY");

    public PaydayEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}

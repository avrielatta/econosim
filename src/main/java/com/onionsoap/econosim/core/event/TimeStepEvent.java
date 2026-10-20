package com.onionsoap.econosim.core.event;

import javafx.event.Event;
import javafx.event.EventType;

public class TimeStepEvent extends Event {
    public static final EventType<TimeStepEvent> ANY = new EventType<>(Event.ANY, "TIME_STEP");
    public static final EventType<TimeStepEvent> DAY = new EventType<>(Event.ANY, "DAY");
    public static final EventType<TimeStepEvent> WEEK = new EventType<>(Event.ANY, "WEEK");
    public static final EventType<TimeStepEvent> MONTH = new EventType<>(Event.ANY, "MONTH");
    public static final EventType<TimeStepEvent> YEAR = new EventType<>(Event.ANY, "YEAR");

    public TimeStepEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}

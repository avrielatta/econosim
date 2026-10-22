package com.onionsoap.econosim.core.event;

public class Event {
    public static final EventType<Event> ANY;
    protected EventType<? extends Event> eventType;

    public Event(EventType<? extends Event> eventType) {
        this.eventType = eventType;
    }

    public EventType<? extends Event> getEventType() { return this.eventType; }

    static {
        ANY = EventType.ROOT;
    }
}

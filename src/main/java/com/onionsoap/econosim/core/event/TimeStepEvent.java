package com.onionsoap.econosim.core.event;

import com.onionsoap.econosim.core.simulation.SimTime;

public class TimeStepEvent extends Event {
    public static final EventType<TimeStepEvent> ANY = new EventType<>(Event.ANY, "TIME_STEP");
    public static final EventType<TimeStepEvent> DAY = new EventType<>(TimeStepEvent.ANY, "DAY");
    public static final EventType<TimeStepEvent> WEEK = new EventType<>(TimeStepEvent.ANY, "WEEK");
    public static final EventType<TimeStepEvent> MONTH = new EventType<>(TimeStepEvent.ANY, "MONTH");
    public static final EventType<TimeStepEvent> YEAR = new EventType<>(TimeStepEvent.ANY, "YEAR");

    private final SimTime.TimeInterval interval;

    public TimeStepEvent(EventType<? extends Event> eventType) throws IllegalArgumentException {
        super(eventType);

        if (eventType == DAY) {
            this.interval = SimTime.TimeInterval.DAY;
        } else if (eventType == WEEK) {
            this.interval = SimTime.TimeInterval.WEEK;
        } else if (eventType == MONTH) {
            this.interval = SimTime.TimeInterval.MONTH;
        } else if (eventType == YEAR) {
            this.interval = SimTime.TimeInterval.YEAR;
        } else {
            throw new IllegalArgumentException(
                    "Unsupported event type: " + eventType
            );
        }
    }

    public SimTime.TimeInterval getInterval() {
        return this.interval;
    }
}

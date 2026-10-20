package com.onionsoap.econosim.core.simulation;

import com.onionsoap.econosim.core.event.TimeStepEvent;
import com.onionsoap.econosim.core.registry.Registries;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public class SimTime implements EventHandler<TimeStepEvent> {
    public static final int WEEK = 7;
    public static final int MONTH = 28;
    public static final int YEAR = 336;

    private int days;
    private Runnable onTick = () -> {};

    public SimTime() {
        this.days = 0;
    }

    public int getDays() { return this.days; }
    public int getYears() { return this.days / YEAR; }
    public int getMonths() { return this.days / MONTH; }
    public int getWeeks() { return this.days / WEEK; }

    public void setDays(int days) { this.days = days; }

    public void incrementTime(TimeInterval interval) {
        switch (interval) {
            case DAY:
                this.days++;
                break;
            case WEEK:
                this.days += WEEK;
                break;
            case MONTH:
                this.days += MONTH;
                break;
            case YEAR:
                this.days += YEAR;
                break;
        }
    }

    @Override
    public void handle(TimeStepEvent timeStepEvent) throws RuntimeException {
        EventType<? extends Event> type = timeStepEvent.getEventType();

        if (type == TimeStepEvent.DAY) {
            this.incrementTime(TimeInterval.DAY);
        } else if (type == TimeStepEvent.WEEK) {
            this.incrementTime(TimeInterval.WEEK);
        } else if (type == TimeStepEvent.MONTH) {
            this.incrementTime(TimeInterval.MONTH);
        } else if (type == TimeStepEvent.YEAR) {
            this.incrementTime(TimeInterval.YEAR);
        } else {
            throw new RuntimeException("Unknown event type");
        }

        this.onTick.run();
    }

    public void setOnTick(Runnable onTick) { this.onTick = onTick; }

    /**
     * Resets the clock/day count to 0.
     */
    public void reset() { this.days = 0; }

    public enum TimeInterval {
        DAY,
        WEEK,
        MONTH,
        YEAR
    }
}

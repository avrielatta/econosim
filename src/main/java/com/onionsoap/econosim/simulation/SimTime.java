package com.onionsoap.econosim.simulation;

import com.onionsoap.econosim.event.TimeStepEvent;
import com.onionsoap.econosim.registry.Registries;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public class SimTime implements EventHandler<TimeStepEvent> {
    private int years;
    private int months;
    private int weeks;
    private int days;
    private TimeState currentTimeState;
    private Runnable onTick = () -> {};

    public SimTime() {
        this.years = 0;
        this.months = 0;
        this.weeks = 0;
        this.days = 0;
        this.currentTimeState = TimeState.PEACE;

        Registries.TIME.register(this);
    }

    public int getYears() { return this.years; }
    public int getMonths() { return this.months; }
    public int getWeeks() { return this.weeks; }
    public int getDays() { return this.days; }
    public TimeState getCurrentTimeState() { return this.currentTimeState; }

    public void setYears(int years) { this.years = years; }
    public void setMonths(int months) { this.months = months; }
    public void setWeeks(int weeks) { this.weeks = weeks; }
    public void setDays(int days) { this.days = days; }
    public void setCurrentTimeState(TimeState currentTimeState) { this.currentTimeState = currentTimeState; }

    public void incrementTime() {
        switch (this.currentTimeState) {
            case PEACE:
                this.incrementMonths();
                break;
            case WAR:
                this.incrementWeeks();
                break;
        }
    }

    public void incrementYears() {
        this.months = 0;
        this.weeks = 0;
        this.days = 0;

        this.years++;
    }

    public void incrementMonths() {
        this.weeks = 0;
        this.days = 0;

        if (this.months == 11) {
            this.months = 0;
            this.incrementYears();
        } else {
            this.months++;
        }
    }

    public void incrementWeeks() {
        this.days = 0;

        if (this.weeks == 3) {
            this.weeks = 0;
            this.incrementMonths();
        } else {
            this.weeks++;
        }
    }

    public void incrementDays() {
        if (this.days == 6) {
            this.days = 0;
            this.incrementWeeks();
        } else {
            this.days++;
        }
    }

    @Override
    public void handle(TimeStepEvent timeStepEvent) throws RuntimeException {
        EventType<? extends Event> type = timeStepEvent.getEventType();

        if (type == TimeStepEvent.DAY) {
            this.incrementDays();
        } else if (type == TimeStepEvent.WEEK) {
            this.incrementWeeks();
        } else if (type == TimeStepEvent.MONTH) {
            this.incrementMonths();
        } else if (type == TimeStepEvent.YEAR) {
            this.incrementYears();
        } else {
            throw new RuntimeException("Unknown event type");
        }

        this.onTick.run();
    }

    public void setOnTick(Runnable onTick) { this.onTick = onTick; }

    /**
     * Resets the clock/day count to 0.
     */
    public void reset() {
        this.years = this.months = this.weeks = this.days = 0;
    }

    /**
     * Checks the total number of elapsed days since this SimTime object
     * was created.
     * @return the total number of elapsed days
     */
    public int totalDays() {
        return (((this.years * 12) + months) * 4 + weeks) * 7 + days;
    }

    public enum TimeState {
        PEACE,
        WAR
    }

    public enum TimeInterval {
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }
}

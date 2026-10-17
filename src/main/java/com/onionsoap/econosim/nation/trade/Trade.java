package com.onionsoap.econosim.nation.trade;

import com.onionsoap.econosim.nation.Nation;
import com.onionsoap.econosim.simulation.SimTime;

public class Trade {
    private final Nation nationA;
    private final Nation nationB;
    private final Payload<?> nationAOffer;
    private final Payload<?> nationBOffer;
    private final SimTime.TimeInterval timeInterval;
    private final SimTime timeSinceLastTrade;

    public Trade(
            Nation nationA,
            Nation nationB,
            Payload<?> nationAOffer,
            Payload<?> nationBOffer,
            SimTime.TimeInterval timeInterval
    ) {
        this.nationA = nationA;
        this.nationB = nationB;
        this.nationAOffer = nationAOffer;
        this.nationBOffer = nationBOffer;
        this.timeInterval = timeInterval;
        this.timeSinceLastTrade = new SimTime();
        this.timeSinceLastTrade.setOnTick(this::checkTradeInterval);
    }

    public Nation getNationA() { return this.nationA; }
    public Nation getNationB() { return this.nationB; }
    public Payload<?> getNationAOffer() { return this.nationAOffer; }
    public Payload<?> getNationBOffer() { return this.nationBOffer; }
    public SimTime.TimeInterval getTimeInterval() { return this.timeInterval; }
    public SimTime getTimeSinceLastTrade() { return this.timeSinceLastTrade; }

    private int intervalInDays() {
        return switch (this.timeInterval) {
            case DAILY -> 1;
            case WEEKLY -> 7;
            case MONTHLY -> 28;
            case YEARLY -> 336;
        };
    }

    public void checkTradeInterval() {
        if (this.timeSinceLastTrade.totalDays() >= this.intervalInDays()) {
            this.executeTrade();
            timeSinceLastTrade.reset();
        }
    }

    private void executeTrade() {}
}

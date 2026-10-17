package com.onionsoap.econosim.registry.bus;

import com.onionsoap.econosim.event.TimeStepEvent;
import com.onionsoap.econosim.registry.Registries;
import com.onionsoap.econosim.simulation.SimTime;

public final class TimeRegistry {
    private TimeRegistry() {}

    public static void fire(TimeStepEvent event) {
        for (SimTime handler : Registries.TIME.getEntries()) {
            handler.handle(event);
        }
    }
}

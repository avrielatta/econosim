package com.onionsoap.econosim.core.registry.bus;

import com.onionsoap.econosim.core.agent.market.Market;
import com.onionsoap.econosim.core.event.TimeStepEvent;
import com.onionsoap.econosim.core.registry.Registries;

public final class MarketRegistry {
    private MarketRegistry() {}

    public static void payWages(TimeStepEvent event) {
        for (Market m : Registries.MARKET.getEntries()) {
            m.payEmployees(event);
        }
    }
}

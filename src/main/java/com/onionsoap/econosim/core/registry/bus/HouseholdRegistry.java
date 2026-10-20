package com.onionsoap.econosim.core.registry.bus;

import com.onionsoap.econosim.core.agent.Household;
import com.onionsoap.econosim.core.event.PaydayEvent;
import com.onionsoap.econosim.core.registry.Registries;

public class HouseholdRegistry {
    private HouseholdRegistry() {}

    public static void fire(PaydayEvent event) {
        for (Household house : Registries.HOUSEHOLD.getEntries()) {
            house.updateFunds(event);
        }
    }
}

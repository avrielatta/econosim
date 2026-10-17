package com.onionsoap.econosim.registry.bus;

import com.onionsoap.econosim.agent.Household;
import com.onionsoap.econosim.event.PaydayEvent;
import com.onionsoap.econosim.registry.Registries;

public class HouseholdRegistry {
    private HouseholdRegistry() {}

    public static void fire(PaydayEvent event) {
        for (Household house : Registries.HOUSEHOLD.getEntries()) {
            house.updateFunds(event);
        }
    }
}

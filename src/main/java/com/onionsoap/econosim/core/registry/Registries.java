package com.onionsoap.econosim.core.registry;

import com.onionsoap.econosim.core.agent.Household;
import com.onionsoap.econosim.core.agent.Person;
import com.onionsoap.econosim.core.agent.market.Market;
import com.onionsoap.econosim.core.simulation.SimTime;

public final class Registries {
    private Registries() {}

    public static final Registry<Person> PERSON = new Registry<>();
    public static final Registry<Household> HOUSEHOLD = new Registry<>();
    public static final Registry<Market> MARKET = new Registry<>();
}

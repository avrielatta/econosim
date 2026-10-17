package com.onionsoap.econosim.registry;

import com.onionsoap.econosim.agent.Household;
import com.onionsoap.econosim.agent.Person;
import com.onionsoap.econosim.simulation.SimTime;

public final class Registries {
    private Registries() {}

    public static final Registry<SimTime> TIME = new Registry<>();
    public static final Registry<Person> PERSON = new Registry<>();
    public static final Registry<Household> HOUSEHOLD = new Registry<>();
}

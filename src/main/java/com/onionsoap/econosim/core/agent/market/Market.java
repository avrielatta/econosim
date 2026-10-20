package com.onionsoap.econosim.core.agent.market;

import com.onionsoap.econosim.core.agent.Employer;
import com.onionsoap.econosim.core.agent.market.labor.Job;
import com.onionsoap.econosim.core.registry.Registries;

import java.util.HashMap;
import java.util.Map;

public class Market extends Employer {
    private final String name;

    public Market(String name) {
        this(name, new HashMap<>(), new HashMap<>());
    }

    public Market(
            String name,
            Map<Job, Integer> vacancies,
            Map<Job, Long> wages
    ) {
        this.name = name;

        if (vacancies.isEmpty())
            this.totalVacancies = 0;

        this.vacancies.putAll(vacancies);
        this.wages.putAll(wages);
        this.employed = 0;
        this.laborCost = 0;

        Registries.MARKET.register(this);
    }

    public String getName() { return this.name; }

    protected void calculateLaborCost() {
        if (this.totalVacancies != 0) {
            this.wages.forEach((_, salary) -> this.laborCost += salary);
        }
    }

    public void close() {
        Registries.MARKET.unregister(this);
    }
}

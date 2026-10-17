package com.onionsoap.econosim.agent.market;

import com.onionsoap.econosim.agent.Employer;
import com.onionsoap.econosim.agent.market.labor.Job;

import java.util.HashMap;
import java.util.Map;

public class Market extends Employer {
    private final String name;

    public Market(String name) {
        this(name, new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

    public Market(
            String name,
            Map<Job, Integer> vacancies,
            Map<Job, Integer> requiredSkills,
            Map<Job, Long> wages
    ) {
        this.name = name;

        if (vacancies.isEmpty())
            this.totalVacancies = 0;

        this.vacancies.putAll(vacancies);
        this.requiredSkills.putAll(requiredSkills);
        this.wages.putAll(wages);
        this.employed = 0;
        this.laborCost = 0;
    }

    protected void calculateLaborCost() {
        if (this.totalVacancies != 0) {
            this.wages.forEach((_, salary) -> this.laborCost += salary);
        }
    }

    public String getName() { return this.name; }
}

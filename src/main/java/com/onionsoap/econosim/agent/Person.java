package com.onionsoap.econosim.agent;

import com.onionsoap.econosim.agent.market.labor.Job;
import com.onionsoap.econosim.registry.Registries;
import com.onionsoap.econosim.resource.Currency;

/**
 * Smallest autonomous agent. Can perform labor, consume goods and services, own property, and manipulate capital.
 */
public class Person {
    private String name;
    private int skill;
    private Job job;
    private Currency funds;

    public Person(String name, int skill) {
        this.name = name;
        this.skill = skill;

        Registries.PERSON.register(this);
    }

    public String getName() { return this.name; }
    public int getSkill() { return this.skill; }
    public Job getJob() { return this.job; }
    public Currency getFunds() { return this.funds; }

    public void setJob(Job job) { this.job = job; }

    public void increaseSkillLevel() {
        if (this.skill <= 3) {
            this.skill++;
        }
    }

    public void receivePayment(long amount) {
        this.funds.addCount(amount);
    }
}

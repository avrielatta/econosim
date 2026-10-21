package com.onionsoap.econosim.core.agent;

import com.onionsoap.econosim.core.agent.market.labor.Job;
import com.onionsoap.econosim.core.nation.Nation;
import com.onionsoap.econosim.core.registry.Registries;
import com.onionsoap.econosim.core.resource.currency.Account;
import com.onionsoap.econosim.core.resource.currency.Currency;

/**
 * Smallest autonomous agent. Can perform labor, consume goods and services, own property, and manipulate capital.
 */
public class Person {
    private final String name;
    private int skill;
    private Job job;
    private final Account account;

    public Person(String name, int skill) {
        this.name = name;
        this.skill = skill;
        this.account = new Account();

        Registries.PERSON.register(this);
    }

    public String getName() { return this.name; }
    public int getSkill() { return this.skill; }
    public Job getJob() { return this.job; }
    public Account getAccount() { return this.account; }
    public Currency getPrimaryHolding() { return this.account.checkPrimaryHolding(); }
    public Currency getFinancialHolding(Nation issuer) { return this.account.checkHolding(issuer); }

    public void setJob(Job job) { this.job = job; }

    public void increaseSkillLevel() {
        if (this.skill <= 3) {
            this.skill++;
        }
    }
}

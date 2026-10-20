package com.onionsoap.econosim.core.agent;

import com.onionsoap.econosim.core.event.PaydayEvent;
import com.onionsoap.econosim.core.registry.Registries;
import com.onionsoap.econosim.core.resource.currency.Currency;

import java.util.Set;

/**
 * Comprised of 1 or more {@link Person}.
 */
public class Household {
    private Set<Person> members;
    private Currency funds;

    public Household(Set<Person> members) {
        this.members = members;

        Registries.HOUSEHOLD.register(this);
    }

    public Set<Person> getMembers() { return this.members; }
    public void setMembers(Set<Person> members) { this.members = members; }

    public void updateFunds(PaydayEvent event) {
        if (event.getEventType() == PaydayEvent.ANY) {
            // reset funds
            this.funds.setCount(0);

            // iterate
            for (Person member : members) {
                // and collect new fund totals
                this.funds.addCount(member.getFunds().getCount());
            }
        }
    }
}

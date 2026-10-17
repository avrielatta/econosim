package com.onionsoap.econosim.nation;

import com.onionsoap.econosim.resource.Currency;

import java.util.Map;

public class Nation {
    private final String name;
    private Map<Nation, Currency> reserve;
    private Currency moneySupply;
    private Currency debt;
    private Currency privateWealth;

    public Nation(String name, int startingReserve) {
        this.name = name;
        this.reserve = Map.of(this, new Currency(this, startingReserve));
    }

    public String getName() { return this.name; }

    public Map<Nation, Currency> getReserve() { return this.reserve; }

    public Currency getDomesticReserve() {
        return this.reserve.get(this);
    }

    public Currency getSpecificReserve(Nation nation) {
        return this.reserve.get(nation);
    }

    public void setDomesticReserve(int count) {
        this.reserve.get(this).setCount(count);
    }

    public void setSpecificReserve(Nation nation, int count) {
        this.reserve.get(nation).setCount(count);
    }
}

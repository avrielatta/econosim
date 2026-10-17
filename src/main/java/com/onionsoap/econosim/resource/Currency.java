package com.onionsoap.econosim.resource;

import com.onionsoap.econosim.nation.Nation;
import com.onionsoap.econosim.nation.trade.Tradeable;

public class Currency extends Good implements Tradeable {
    private final Nation issuer;

    public Currency(Nation issuer, int count) {
        super(count);
        this.issuer = issuer;
    }

    public Nation getIssuer() { return this.issuer; }
}

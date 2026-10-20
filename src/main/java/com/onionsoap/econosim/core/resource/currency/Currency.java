package com.onionsoap.econosim.core.resource.currency;

import com.onionsoap.econosim.core.nation.Nation;
import com.onionsoap.econosim.core.nation.trade.Tradeable;
import com.onionsoap.econosim.core.resource.Good;

public class Currency extends Good implements Tradeable {
    private final Nation issuer;

    public Currency(Nation issuer, long count) {
        super(count);
        this.issuer = issuer;
    }

    public Nation getIssuer() { return this.issuer; }
}

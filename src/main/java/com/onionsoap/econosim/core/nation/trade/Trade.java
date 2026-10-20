package com.onionsoap.econosim.core.nation.trade;

import com.onionsoap.econosim.core.nation.Nation;
import com.onionsoap.econosim.core.simulation.SimTime;

public class Trade {
    private final Nation nationA;
    private final Nation nationB;
    private final Payload<?> nationAOffer;
    private final Payload<?> nationBOffer;

    public Trade(
            Nation nationA,
            Nation nationB,
            Payload<?> nationAOffer,
            Payload<?> nationBOffer
    ) {
        this.nationA = nationA;
        this.nationB = nationB;
        this.nationAOffer = nationAOffer;
        this.nationBOffer = nationBOffer;
    }

    public Nation getNationA() { return this.nationA; }
    public Nation getNationB() { return this.nationB; }
    public Payload<?> getNationAOffer() { return this.nationAOffer; }
    public Payload<?> getNationBOffer() { return this.nationBOffer; }

    private void executeTrade() {}
}

package com.onionsoap.econosim.core.simulation;

import java.util.Random;

public class SimRandom extends Random {
    private static final long seed = 12345L;

    public SimRandom() {
        super(seed);
    }
}

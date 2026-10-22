package com.onionsoap.econosim.core.event.bus;

import com.onionsoap.econosim.core.event.TimeStepEvent;

public final class Buses {
    public static final EventBus<TimeStepEvent> TIME_STEP_EVENT_BUS = new EventBus<>();
}

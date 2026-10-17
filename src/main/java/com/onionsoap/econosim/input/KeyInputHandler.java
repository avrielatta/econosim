package com.onionsoap.econosim.input;

import com.onionsoap.econosim.event.TimeStepEvent;
import com.onionsoap.econosim.registry.bus.TimeRegistry;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyInputHandler extends InputHandler<KeyEvent> {
    public KeyInputHandler(InputMode mode) { super(mode); }

    @Override
    void handleDefaultInputEvents(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            TimeRegistry.fire(new TimeStepEvent(TimeStepEvent.DAY));
        }
    }
}

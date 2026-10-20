package com.onionsoap.econosim.core.input;

import com.onionsoap.econosim.core.event.TimeStepEvent;
import com.onionsoap.econosim.core.registry.bus.TimeRegistry;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyInputHandler extends InputHandler<KeyEvent> {
    public KeyInputHandler(InputMode mode) { super(mode); }

    @Override
    void handleDefaultInputEvents(KeyEvent event) {
//        if (event.getCode() == KeyCode.SPACE) {
//            TimeRegistry.fire(new TimeStepEvent(TimeStepEvent.DAY));
//        }
    }
}

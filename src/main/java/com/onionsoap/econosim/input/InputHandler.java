package com.onionsoap.econosim.input;

import javafx.event.Event;
import javafx.event.EventHandler;

public abstract class InputHandler<T extends Event> implements EventHandler<T> {
    protected InputMode mode;

    public InputHandler(InputMode mode) { this.mode = mode; }

    @Override
    public void handle(T event) {
        switch (this.mode) {
            case DEFAULT:
                this.handleDefaultInputEvents(event);
        }
    }

    abstract void handleDefaultInputEvents(T event);

    public enum InputMode {
        DEFAULT
    }
}

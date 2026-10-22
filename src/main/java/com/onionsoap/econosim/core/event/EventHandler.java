package com.onionsoap.econosim.core.event;

@FunctionalInterface
public interface EventHandler<T extends Event> extends EventListener {
    void handle(T event);
}

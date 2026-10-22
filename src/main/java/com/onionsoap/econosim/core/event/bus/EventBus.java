package com.onionsoap.econosim.core.event.bus;

import com.onionsoap.econosim.core.event.Event;
import com.onionsoap.econosim.core.event.EventHandler;
import com.onionsoap.econosim.core.registry.Registry;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus<T extends Event> {
    private final List<EventHandler<T>> handlers;

    public EventBus() {
        this.handlers = new CopyOnWriteArrayList<>();
    }

    public void subscribe(EventHandler<T> handler) {
        this.handlers.add(handler);
    }

    public void unsubscribe(EventHandler<T> handler) {
        handlers.remove(handler);
    }

    public void subscribeRegistry(Registry<? extends EventHandler<T>> registry) {
        for (EventHandler<T> handler : registry.getEntries()) {
            this.subscribe(handler);
        }
    }

    public void unsubscribeRegistry(Registry<? extends EventHandler<T>> registry) {
        for (EventHandler<T> handler : registry.getEntries()) {
            this.unsubscribe(handler);
        }
    }

    public void propagateEvent(T event) {
        for (EventHandler<? super T> handler : this.handlers) handler.handle(event);
    }
}

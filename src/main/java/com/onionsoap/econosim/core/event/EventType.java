package com.onionsoap.econosim.core.event;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public final class EventType<T extends Event> {
    public static final EventType<Event> ROOT = new EventType<>("EVENT");
    private List<EventType<? extends T>> subTypes;
    private final EventType<? super T> superType;
    private final String name;

    public EventType(EventType<? super T> superType, String name) throws NullPointerException {
        if (superType == null) {
            throw new NullPointerException("EventType.superType cannot be null");
        } else {
            this.superType = superType;
            this.name = name;
            // register this as a subtype of the supertype
            superType.register(this);
        }
    }

    /**
     * ONLY meant to be used within this class for the <code>ROOT</code> field.
     * @param name
     */
    private EventType(String name) {
        this.superType = null;
        this.name = name;
    }

    public EventType<? super T> getSuperType() { return this.superType; }
    public String getName() { return this.name; }
    public String toString() { return this.name != null ? this.name : super.toString(); }

    private synchronized void register(EventType<? extends T> subType) {
        if (this.subTypes == null) this.subTypes = new CopyOnWriteArrayList<>();

        for (EventType<? extends T> type: this.subTypes) {
            if (type.equals(subType) || Objects.equals(subType.name, type.name)) {
                String str = String.valueOf(subType);
                throw new IllegalArgumentException(
                        "EventType \"" + str + "\"with parent \"" + subType.getSuperType() + "\" already exists"
                );
            }
        }

        this.subTypes.add(subType);
    }
}
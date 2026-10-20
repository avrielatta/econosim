package com.onionsoap.econosim.core.registry;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Registry<T> {
    private final List<T> entries = new CopyOnWriteArrayList<>();
    private final List<T> readOnlyEntries = Collections.unmodifiableList(entries);

    public List<T> getEntries() { return readOnlyEntries; }

    public void register(T entry) { entries.add(entry); }
    public void unregister(T entry) { entries.remove(entry); }
}

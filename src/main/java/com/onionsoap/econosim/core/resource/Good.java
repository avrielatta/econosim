package com.onionsoap.econosim.core.resource;

public abstract class Good {
    private long count;

    protected Good(long count) { this.count = count; }

    public long getCount() { return this.count; }
    public void setCount(long count) { this.count = count; }

    public void addCount(long toAdd) { this.count += toAdd; }
    public void removeCount(long toRemove) { this.count -= toRemove; }
}

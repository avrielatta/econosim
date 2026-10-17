package com.onionsoap.econosim.resource;

public abstract class Good {
    private long count;

    protected Good(int count) { this.count = count; }

    public long getCount() { return this.count; }
    public void setCount(int count) { this.count = count; }

    public void addCount(long toAdd) { this.count += toAdd; }
    public void removeCount(long toRemove) { this.count -= toRemove; }
}

package com.onionsoap.econosim.nation.trade;

// should be a one-size-fits-all container for any and all tradeable objects
public class Payload<T extends Tradeable> {
    private final T contents;

    public Payload(T contents) {
        this.contents = contents;
    }

    public T unpack() { return this.contents; }
    public Class<? extends Tradeable> getType() { return this.contents.getClass(); }
}

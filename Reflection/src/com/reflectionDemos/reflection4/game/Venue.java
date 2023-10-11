package com.reflectionDemos.reflection4.game;

import java.util.concurrent.atomic.AtomicInteger;

class Venue {
    private String venueName;
    private Address address;
    private static AtomicInteger integer = new AtomicInteger();

    public Venue(Address address) {
        this.venueName = "DEFAULT-VENUE-"+integer.incrementAndGet();
        this.address = address;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "venueName='" + venueName + '\'' +
                ", address=" + address +
                '}';
    }
}

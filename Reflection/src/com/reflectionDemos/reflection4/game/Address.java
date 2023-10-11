package com.reflectionDemos.reflection4.game;

import java.util.concurrent.atomic.AtomicInteger;

class Address {
    private String zipcode;
    private String city;
    private static AtomicInteger integer = new AtomicInteger();

    public Address() {
        this.zipcode = "DEFAULT-ZIPCODE-"+integer.incrementAndGet();
        this.city = "DEFAULT-CITY-"+integer.get();
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

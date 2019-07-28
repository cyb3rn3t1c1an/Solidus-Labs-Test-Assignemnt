package com.soliduslabs.giftcard.domain;

public class Gift {
    private final String name;
    private final int price;

    public Gift(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " " + price;
    }

    public static Gift fromString(final String string) {
        String[] args = string.replace(" ", "").split(",");

        if (args.length != 2) {
            throw new IllegalArgumentException("Unable to create new gift from string: " + string);
        }

        return new Gift(args[0], Integer.parseInt(args[1]));
    }
}

package com.soliduslabs.giftcard.domain;

public class Tuple<T> {
    private final T first;
    private final T second;

    public Tuple(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return first + ", " + second;
    }
}

package com.example.approject;

public class Stick {
    private double length;

    // Constructor
    public Stick() {
        this.length = 0;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void extend() {
    }

    public void reset() {
        length = 0;
    }
}

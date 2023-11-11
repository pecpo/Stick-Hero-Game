package com.example.approject;

public class Player {
    private double stickLength;
    private double positionX;
    private boolean isAlive;
    private boolean isFlipped;

    public Player() {
        this.stickLength = 0;
        this.positionX = positionX;
        this.isAlive = true;
        this.isFlipped = false;
    }


    public double getStickLength() {
        return stickLength;
    }

    public double getPositionX() {
        return positionX;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void extendStick() {

    }

    public void moveForward() {

    }

    public boolean isStickLengthMatch(double gapWidth) {
        return stickLength >= gapWidth;
    }

    public void die() {
        isAlive = false;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

}

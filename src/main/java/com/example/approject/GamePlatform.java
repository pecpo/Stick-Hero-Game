package com.example.approject;

public class GamePlatform {
    private double width;
    private double perfectPoint;
    private double positionX;
    private boolean isVisible;

    public GamePlatform(double width, double perfectPoint, double height, double positionX, double positionY) {
        this.width = width;
        this.perfectPoint = perfectPoint;
        this.positionX = positionX;
        this.isVisible = true;
    }

    public double getWidth() {
        return width;
    }

    public double getPositionX() {
        return positionX;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void hide() {
        isVisible = false;
    }

    public double getPerfectPoint() {
        return perfectPoint;
    }

}

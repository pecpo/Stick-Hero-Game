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

    public void setWidth(double width) {
        this.width = width;
    }

    public void setPerfectPoint(double perfectPoint) {
        this.perfectPoint = perfectPoint;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
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

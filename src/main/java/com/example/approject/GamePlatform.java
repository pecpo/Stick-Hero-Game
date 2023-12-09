package com.example.approject;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class GamePlatform {
    private static double width;
    private double perfectPoint;
    private double positionX;
    private boolean isVisible;

    public static double getDistanceX() {
        return distanceX;
    }

    public static void setDistanceX(double distance) {
        GamePlatform.distanceX = distance;
    }

    private static double distanceX;

    public GamePlatform(double width, double perfectPoint, double height, double positionX, double positionY) {
        this.width = width;
        this.perfectPoint = perfectPoint;
        this.positionX = positionX;
        this.isVisible = true;
    }

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double widths) {
        width = widths;
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

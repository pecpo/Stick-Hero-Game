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
    public static void generate(AnchorPane anchorPane){
        Random random = new Random();
        if(Controller.getFirst()==1){
            double left=Controller.getLeft();
            double width = 30;
            GamePlatform.setWidth(width);
            Rectangle platform = new Rectangle(width, 300);
            platform.setFill(Color.BLACK);
            AnchorPane.setLeftAnchor(platform, 0.0);
            AnchorPane.setBottomAnchor(platform, 0.0);
            anchorPane.getChildren().add(platform);
            Controller.setFirst(0);
            return;
        }
        double left=Controller.getLeft();
        left+= random.nextDouble() * 290 + 60;
        double width = random.nextDouble() * 50 + 30;
        GamePlatform.setWidth(width);
        Rectangle platform = new Rectangle(width, 300);
        platform.setFill(Color.BLACK);
        AnchorPane.setLeftAnchor(platform, left);
        AnchorPane.setBottomAnchor(platform, 0.0);
        anchorPane.getChildren().add(platform);
        Controller.setLeft(left);
        GamePlatform.setDistanceX(left);
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

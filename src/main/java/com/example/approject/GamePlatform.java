package com.example.approject;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

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
    public static double generate(AnchorPane anchorPane,double positionX){
        Random random = new Random();
        if(Controller.getFirst()==1){
            double left=positionX;
            double width = random.nextDouble() * 50 + 30; // Random width between 50 and 150
            double height = random.nextDouble() * 300 + 100; // Random height between 100 and 400
            left+= random.nextDouble()*50+0;
            Rectangle platform = new Rectangle(width, height);
            platform.setFill(Color.BLACK);
            AnchorPane.setLeftAnchor(platform, left);
            AnchorPane.setBottomAnchor(platform, 0.0);
            anchorPane.getChildren().add(platform);
            Controller.setFirst(0);
            return left;
        }
        double left=positionX;
        left+= random.nextDouble()*290+60;
        double width = random.nextDouble() * 50 + 30; // Random width between 50 and 150
        double height = random.nextDouble() * 300 + 100; // Random height between 100 and 400
        Rectangle platform = new Rectangle(width, height);
        platform.setFill(Color.BLACK);
        AnchorPane.setLeftAnchor(platform, left);
        AnchorPane.setBottomAnchor(platform, 0.0);
        anchorPane.getChildren().add(platform);
        return left;
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

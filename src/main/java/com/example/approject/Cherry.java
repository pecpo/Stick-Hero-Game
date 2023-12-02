package com.example.approject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class Cherry {

    private double positionX;
    private boolean isVisible;

    public Cherry(double positionX) {
        this.positionX = positionX;
    }

    public static ImageView initialize(AnchorPane pillarPane,double left) {
        Image image1 = new Image("cherry.png");
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitHeight(15);
        imageView1.setFitWidth(15);
        double x=left+((Controller.getLeft()-left)*(0.5));
        imageView1.setX(x);
        imageView1.setY(500);
        pillarPane.getChildren().add(imageView1);
        return imageView1;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}

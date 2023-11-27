package com.example.approject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Cherry {

    private double positionX;
    private boolean isVisible;

    public Cherry(double positionX) {
        this.positionX = positionX;
    }

    public static void initialize(AnchorPane pillarPane,double left) {
        Image image1 = new Image("cherry.png");
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitHeight(15);
        imageView1.setFitWidth(15);
        imageView1.setX(left);
        imageView1.setY(500);
        pillarPane.getChildren().add(imageView1);
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

package com.example.approject;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Vector;

public class Player {
    private double stickLength;
    private Stick stick;
    private Vector<Cherry> cherries;
    private double positionX;
    private boolean isAlive;
    private boolean isFlipped;

    public Player() {
        this.stickLength = 0;
        this.stick=null;
        this.cherries = new Vector<>();
        this.positionX = positionX;
        this.isAlive = true;
        this.isFlipped = false;
    }

    public void setStickLength(double stickLength) {
        this.stickLength = stickLength;
    }

    public Stick getStick() {
        return stick;
    }

    public void setStick(Stick stick) {
        this.stick = stick;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
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

    public static void moveForward(ImageView imageView){
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), imageView);
        transition.setToX(imageView.getTranslateX() + GamePlatform.getDistanceX());
        transition.play();
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

    public Vector<Cherry> getCherries() {
        return cherries;
    }

    public void setCherries(Vector<Cherry> cherries) {
        this.cherries = cherries;
    }
    public void addCherry(Cherry cherry){
        cherries.add(cherry);
    }
}

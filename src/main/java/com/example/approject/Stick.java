package com.example.approject;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Stick {
    Rectangle stickRectangle = new Rectangle();
    public int initial;

    public Stick() {
        stickRectangle.setHeight(200);
        stickRectangle.setY(500);
        stickRectangle.setWidth(10);
        stickRectangle.setFill(Color.BLACK);
    }
    public void initialize(Line line){
        line.setStartX(15);
        line.setStartY(500);
        line.setEndX(15);
        line.setEndY(550);
    }


}

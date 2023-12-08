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
//    public double getLength() {
//        return length;
//    }
//
//    public void setLength(double lengths) {
//        length = lengths;
//    }

    public void extend(KeyEvent event){
//        while(event.getCode()== KeyCode.SPACE){
//            Controller.setIsSpacePressed(true);
//            StickLine.setEndY(StickLine.getEndY()+100);
//            length+=1;
//        }
    }

    public void reset(KeyEvent event){
//        if(event.getCode()== KeyCode.SPACE){
//            Controller.setIsSpacePressed(false);
//            GamePlatform.setDistanceX(length);
//            length=0;
//        }
    }
}

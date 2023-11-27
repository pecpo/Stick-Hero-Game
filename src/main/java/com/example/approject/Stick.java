package com.example.approject;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Stick {
    private static double length;
    private static double initial;

    public Line getStickLine() {
        return StickLine;
    }

    private final Line StickLine=new Line(0,0,0,0);
    public Stick() {
        length = 0;
        initial =0;
        StickLine.setStroke(Color.BLACK);
    }
    public void initialize(Line line){
        line.setStartX(Controller.getLeft()+GamePlatform.getWidth());
        line.setStartY(500);
        line.setEndX(Controller.getLeft()+GamePlatform.getWidth());
        line.setEndY(500);
    }
    public double getLength() {
        return length;
    }

    public void setLength(double lengths) {
        length = lengths;
    }

    public void extend(KeyEvent event){
        while(event.getCode()== KeyCode.SPACE){
            Controller.setIsSpacePressed(true);
            StickLine.setEndY(StickLine.getEndY()+100);
            length+=1;
        }
    }

    public void reset(KeyEvent event){
        if(event.getCode()== KeyCode.SPACE){
            Controller.setIsSpacePressed(false);
            GamePlatform.setDistanceX(length);
            length=0;
        }
    }
}

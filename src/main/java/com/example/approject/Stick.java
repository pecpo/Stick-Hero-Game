package com.example.approject;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.Scene;
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

    public double getLength() {
        return length;
    }

    public void setLength(double lengths) {
        length = lengths;
    }

    public void extend(){

    }

    public void reset() {
        length=StickLine.getEndX()-StickLine.getStartX();
    }
}

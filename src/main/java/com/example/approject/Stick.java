package com.example.approject;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Stick {
    private static double length;
    private static double initial;
    private final Line StickLine=new Line();
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

    public void extend(Scene scene, Timeline extendTimeline){
        scene.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                initial =event.getX();
                StickLine.setStartX(initial);
                StickLine.setStartY(0);
                StickLine.setEndX(initial);
                StickLine.setEndY(0);
                extendTimeline.play();
            }
        });
    }

    public void reset(Scene scene, Timeline extendTimeline) {
        scene.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                extendTimeline.stop();
            }
        });
        length=StickLine.getEndX()-StickLine.getStartX();
    }
}

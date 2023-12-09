package com.example.approject;

public class BlackSBDecorator extends SBDecorator {
    public BlackSBDecorator(Board b) {
        super(b);
    }

    @Override
    public void update() {
        super.update();
        setStyle("-fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold;");
    }
}

package com.example.approject;

import javafx.scene.text.Text;

public abstract class SBDecorator extends Text implements Board {
    private Board decoratedBoard;
    public SBDecorator(Board b) {
        this.decoratedBoard = b;
    }
    public void update() {
        decoratedBoard.update();
    }
}

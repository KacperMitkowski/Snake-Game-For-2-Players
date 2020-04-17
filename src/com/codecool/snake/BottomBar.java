package com.codecool.snake;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class BottomBar {
    private Game game;
    private HBox hBox;

    public BottomBar(Game game) {
        this.game = game;

        hBox = new HBox(50);
        hBox.setPrefHeight(50);
        hBox.setId("bottomBar");

        addRestartButton();
        addExitButton();
    }

    private void addRestartButton() {
        Button restartButton = new Button("Restart");
        restartButton.setOnMouseClicked(game.restartButtonEventHandler);
        hBox.getChildren().add(restartButton);
    }

    private void addExitButton() {
        Button exitButton = new Button("Exit");
        exitButton.setOnMouseClicked(game.exitGameButtonEventHandler);
        hBox.getChildren().add(exitButton);
    }

    public HBox getHbox() {
        return hBox;
    }
}

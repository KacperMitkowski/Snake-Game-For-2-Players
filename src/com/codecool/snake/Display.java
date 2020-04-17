package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;

import java.util.List;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Display {
    public static final int BOTTOM_BAR_HEIGHT = 50;
    private Pane gameBoard;
    private BottomBar bottomBar;
    private DelayedModificationList<GameEntity> gameObjects = new DelayedModificationList<>();

    public Display(BorderPane borderPane) {
        this.bottomBar = new BottomBar(Globals.getInstance().game);
        gameBoard = new Pane();
        borderPane.setCenter(gameBoard);
        borderPane.setBottom(bottomBar.getHbox());
        borderPane.setId("body");
    }


    public void addToGameBoard(GameEntity entity) {
        gameBoard.getChildren().add(entity);
        gameObjects.add(entity);
    }

    public void removeFromGameBoard(GameEntity entity) {
        gameBoard.getChildren().remove(entity);
        gameObjects.remove(entity);
    }

    public List<GameEntity> getObjectList() {
        return gameObjects.getList();
    }

    public void frameFinished() {
        gameObjects.doPendingModifications();
    }

    public void updateSnakeHeadDrawPosition(GameEntity snakeHead) {
        gameBoard.getChildren().remove(snakeHead);
        gameBoard.getChildren().add(snakeHead);
    }

    public void clear() {
        gameBoard.getChildren().clear();
        gameObjects.clear();
    }
}

package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Random;

// class for holding all static stuff
public class Globals {
    public static final double WINDOW_WIDTH = 1500;
    public static final double WINDOW_HEIGHT = 900;
    private static Globals instance = null;
    public Display display;
    public BottomBar bottomBar;
    public Game game;
    public GameLoop gameLoop;
    private Resources resources;


    public static Globals getInstance() {
        if (instance == null) {
            instance = new Globals();
        }
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead1", new Image("snake_head_1.png"));
        resources.addImage("SnakeBody1", new Image("snake_body_1.png"));
        resources.addImage("SnakeHead2", new Image("snake_head_2.png"));
        resources.addImage("SnakeBody2", new Image("snake_body_2.png"));
        resources.addImage("SimpleEnemy1", new Image("simple_enemy_1.png"));
        resources.addImage("ShootingEnemy1", new Image("shooting_enemy_1.gif"));
        resources.addImage("PowerUp1", new Image("powerup_1.gif"));
        resources.addImage("PowerUp2", new Image("powerup_2.gif"));
        resources.addImage("PowerUp3", new Image("powerup_3.gif"));
        resources.addImage("Arrow1", new Image("arrow_1.png"));
        resources.addImage("Arrow2", new Image("arrow_2.png"));
        resources.addImage("Arrow3", new Image("arrow_3.png"));
    }

    public Image getImage(String name) {
        return resources.getImage(name);
    }

    public void startGame() {
        gameLoop.start();
    }

    public void stopGame() {
        gameLoop.stop();
    }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}

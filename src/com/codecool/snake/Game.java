package com.codecool.snake;

import com.codecool.snake.entities.enemies.ShootingEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;
import com.sun.javafx.geom.Vec2d;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.stage.Stage;


public class Game extends BorderPane {
    public int numberOfAllEnemies;
    private int numberOfSimpleEnemies;
    private int numberOfShootingEnemies;
    private int numberOfPowerUps;
    private Snake snake1;
    private Snake snake2;
    private GameTimer gameTimer = new GameTimer();
    private Stage primaryStage;

    public Game(int numberOfSimpleEnemies, int numberOfShootingEnemies, int numberOfPowerUps) {
        this.numberOfSimpleEnemies = numberOfSimpleEnemies;
        this.numberOfShootingEnemies = numberOfShootingEnemies;
        this.numberOfPowerUps = numberOfPowerUps;
        numberOfAllEnemies = numberOfSimpleEnemies + numberOfShootingEnemies;

        BottomBar bottomBar = new BottomBar(this);

        Globals.getInstance().game = this;
        Globals.getInstance().bottomBar = bottomBar;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies();
        spawnPowerUps();

        GameLoop gameLoop = new GameLoop(snake1, snake2);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        int snakeSpeed = 2;
        snake1 = new Snake(new Vec2d(500, 500), 1, snakeSpeed);
        snake2 = new Snake(new Vec2d(200, 500), 2, snakeSpeed);
    }

    private void spawnEnemies() {
        for(int i = 0; i < numberOfSimpleEnemies; ++i) {new SimpleEnemy(); }
        for(int i = 0; i < numberOfShootingEnemies; ++i) {new ShootingEnemy(); }
    }

    private void spawnPowerUps() {
        for(int i = 0; i < numberOfPowerUps; ++i) {new SimplePowerUp();}
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }


    public EventHandler<MouseEvent> restartButtonEventHandler = e -> {
        primaryStage.close();
        Globals.getInstance().stopGame();
        Main main = new Main();
        main.start(new Stage());
    };

    public EventHandler<MouseEvent> exitGameButtonEventHandler = e -> {
        Platform.exit();
    };
}

package com.codecool.snake.entities.enemies;

import com.codecool.snake.Display;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.items.PlayerArrow;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;

import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;


public class SimpleEnemy extends GameEntity implements Animatable, Interactable {
    private static Random rnd = new Random();
    private Point2D heading;
    private double direction;
    private double imageWidth;
    private double imageHeight;
    private int speed;

    public SimpleEnemy() {
        // randomizing enemy speed
        this.speed = rnd.nextInt(3) + 1;

        prepareEnemyImageDetails();
        prepareToMove();
    }

    private void prepareEnemyImageDetails() {
        setImage(Globals.getInstance().getImage("SimpleEnemy1"));
        Image simpleEnemy = Globals.getInstance().getImage("SimpleEnemy1");
        imageWidth = simpleEnemy.getWidth();
        imageHeight = simpleEnemy.getHeight();
    }

    private void prepareToMove() {
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT - Display.BOTTOM_BAR_HEIGHT);
        direction = rnd.nextDouble() * 360;
        heading = Utils.directionToVector(direction, speed);
    }

    private void handleBouncing() {
        if (getX() + imageWidth > Globals.WINDOW_WIDTH || getX() < 0) {
            direction = -direction;
            this.speed += 0.5;
        }
        if (getY() + imageHeight > Globals.WINDOW_HEIGHT - Display.BOTTOM_BAR_HEIGHT || getY() < 0) {
            direction = 180 - direction;
            this.speed += 0.5;
        }
    }

    @Override
    public void step() {
        handleBouncing();
        heading = Utils.directionToVector(direction, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead || entity instanceof SnakeBody || entity instanceof PlayerArrow) {
            this.destroy();
            Globals.getInstance().game.numberOfAllEnemies--;
        }
    }
}

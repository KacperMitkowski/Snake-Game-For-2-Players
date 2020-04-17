package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.ShootingEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 2;
    private Snake snake;
    private double headRotation;

    public SnakeHead(Snake snake, Vec2d position, int player) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead" + player));
        setPosition(position);
    }

    public void updateRotation(SnakeControl turnDirection, double speed) {
        headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }
        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);

        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SimpleEnemy || entity instanceof ShootingEnemy) {
            snake.removePart(2);
        }
        if (entity instanceof SimplePowerUp) {
            snake.addPart(2);
            snake.increaseSpeed(0.5);
            entity.destroy();
        }
    }

    public double getHeadRotation() {
        return headRotation;
    }
}

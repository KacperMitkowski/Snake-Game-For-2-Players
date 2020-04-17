package com.codecool.snake.entities.items;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.ShootingEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.snakes.Snake;
import javafx.geometry.Point2D;

public class PlayerArrow extends GameEntity implements Animatable, Interactable {
    private Snake snake;
    private double direction;

    public PlayerArrow(Snake snake, int player) {
        this.snake = snake;
        this.direction = snake.direction;

        setImage(Globals.getInstance().getImage("Arrow" + player));
        setX(snake.getHead().getX());
        setY(snake.getHead().getY());
    }

    @Override
    public void step() {
        Point2D heading = Utils.directionToVector(direction, 4);
        if(this.isOutsideMap()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SimpleEnemy || entity instanceof ShootingEnemy) {
            entity.destroy();
            this.destroy();
            snake.addPart(1);
        }
    }
}

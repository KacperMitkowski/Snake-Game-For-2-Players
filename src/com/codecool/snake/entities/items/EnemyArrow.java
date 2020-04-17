package com.codecool.snake.entities.items;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.ShootingEnemy;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

public class EnemyArrow extends GameEntity implements Animatable, Interactable {
    private ShootingEnemy shootingEnemy;

    public EnemyArrow(ShootingEnemy shootingEnemy) {
        this.shootingEnemy = shootingEnemy;

        setImage(Globals.getInstance().getImage("Arrow3"));
        setX(shootingEnemy.getX());
        setY(shootingEnemy.getY());
    }

    @Override
    public void step() {
        Point2D heading = Utils.directionToVector(shootingEnemy.heading, 4);
        if(this.isOutsideMap()) {
            destroy();
            shootingEnemy.arrowInPlay = false;
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead || entity instanceof SnakeBody) {
            Globals.getInstance().gameLoop.stop();
        }
    }
}

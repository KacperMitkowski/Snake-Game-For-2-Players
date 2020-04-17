package com.codecool.snake.entities.enemies;

import com.codecool.snake.Display;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.items.EnemyArrow;
import com.codecool.snake.entities.items.PlayerArrow;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;

import java.util.Random;

public class ShootingEnemy extends GameEntity implements Animatable, Interactable {
    private Random rnd = new Random();
    public boolean arrowInPlay = false;
    public double heading = rnd.nextDouble() * 360;

    public ShootingEnemy() {
        setImage(Globals.getInstance().getImage("ShootingEnemy1"));

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT - Display.BOTTOM_BAR_HEIGHT);
    }

    @Override
    public void step() {
        if(!arrowInPlay) {
            EnemyArrow enemyArrow = new EnemyArrow(this);
            enemyArrow.step();
            arrowInPlay = true;
        }
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead || entity instanceof SnakeBody || entity instanceof PlayerArrow) {
            this.destroy();
            Globals.getInstance().game.numberOfAllEnemies--;
        }
    }
}

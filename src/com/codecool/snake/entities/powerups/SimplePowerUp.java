package com.codecool.snake.entities.powerups;

import com.codecool.snake.Display;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public class SimplePowerUp extends GameEntity implements Interactable {
    public SimplePowerUp() {
        // randomizing powerup image to show
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(3) + 1;
        setImage(Globals.getInstance().getImage("PowerUp" + randomNumber));

        // randomizing powerup position
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT - Display.BOTTOM_BAR_HEIGHT);
    }

    @Override
    public void apply(GameEntity entity) {
    }
}

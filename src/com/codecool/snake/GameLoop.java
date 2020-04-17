package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;

import java.util.List;

public class GameLoop {
    private Snake snake1;
    private Snake snake2;
    private boolean running = false;

    public GameLoop(Snake snake1, Snake snake2) {
        this.snake1 = snake1;
        this.snake2 = snake2;
    }

    public void start() {
        running = true;
    }

    public void stop() { running = false; }

    public void step() {
        if(running) {
            snake1.step(1);
            snake2.step( 2);
            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                }
            }
            checkCollisions();
            checkGameOver();
        }
        Globals.getInstance().display.frameFinished();
    }

    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable){
                        if(objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())){
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }

    private void checkGameOver() {
        int numberOfEnemies = Globals.getInstance().game.numberOfAllEnemies;
        if(numberOfEnemies <= 0) {
            this.stop();
        }
    }
}

package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Display;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.items.PlayerArrow;
import com.codecool.snake.eventhandler.InputHandler;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class Snake extends GameEntity {
    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;
    public double direction;
    private double speed;
    private ArrayList<PlayerArrow> arrows = new ArrayList<>();
    private int player;

    public Snake(Vec2d position, int player, int speed) {
        this.player = player;
        this.speed = speed;
        head = new SnakeHead(this, position, player);
        body = new DelayedModificationList<>();
        addPart(4);
    }

    public void step(int player) {
        handleArrowShooting(player);

        direction = head.getHeadRotation();
        SnakeControl turnDir = getSnakeDirection(player);
        head.updateRotation(turnDir, speed);
        updateSnakeBodyHistory();
        body.doPendingModifications();

        handleSnakeMovingOutsideMap();
    }

    private void handleArrowShooting(int player) {
        if (player == 1) {
            if (InputHandler.getInstance().isKeyPressed(KeyCode.ENTER) && arrows.size() == 0) {
                arrows.add(new PlayerArrow(this, player));
            }
        } else {
            if (InputHandler.getInstance().isKeyPressed(KeyCode.S) && arrows.size() == 0) {
                arrows.add(new PlayerArrow(this, player));
            }
        }

        for (int i = 0; i < arrows.size(); i++) {
            arrows.get(i).step();
            if (arrows.get(i).isOutsideMap()) {
                arrows.remove(arrows.get(i));
            }
        }
    }

    private void handleSnakeMovingOutsideMap() {
        double x = head.getX();
        double y = head.getY();

        if (x > Globals.WINDOW_WIDTH) {
            head.setX(0);
            head.setY(y);
        }

        if (x < 0) {
            head.setX(Globals.WINDOW_WIDTH);
            head.setY(y);
        }

        if (y < 0) {
            head.setX(x);
            head.setY(Globals.WINDOW_HEIGHT - Display.BOTTOM_BAR_HEIGHT);
        }

        if (y > Globals.WINDOW_HEIGHT - Display.BOTTOM_BAR_HEIGHT) {
            head.setX(x);
            head.setY(0);
        }
    }

    private SnakeControl getSnakeDirection(int player) {
        SnakeControl turnDir = SnakeControl.INVALID;

        if (player == 1) {
            if (InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
            if (InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;

        } else {
            if (InputHandler.getInstance().isKeyPressed(KeyCode.A)) turnDir = SnakeControl.TURN_LEFT;
            if (InputHandler.getInstance().isKeyPressed(KeyCode.D)) turnDir = SnakeControl.TURN_RIGHT;
        }

        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(this, position, player);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void removePart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            GameEntity parent = getLastPart();
            body.remove(parent);
            body.doPendingModifications();

            if (body.isEmpty()) {
                Globals.getInstance().gameLoop.stop();
            }
            parent.destroy();
        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for (GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if (result != null) return result;
        return head;
    }

    public SnakeHead getHead() {
        return head;
    }

    public void increaseSpeed(double additionalSpeed) {
        speed += additionalSpeed;
    }
}

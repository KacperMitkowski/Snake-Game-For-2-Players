package com.codecool.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game(5,3,3);
        Scene scene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        scene.getStylesheets().add("style.css");
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        game.start(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Exiting..");
    }
}

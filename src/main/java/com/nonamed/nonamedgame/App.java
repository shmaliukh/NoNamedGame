package com.nonamed.nonamedgame;

import com.nonamed.nonamedgame.game_objects.micro.HeroPerson;
import com.nonamed.nonamedgame.scenes.GameWorld;
import com.nonamed.nonamedgame.scenes.MainMenu;
import com.nonamed.nonamedgame.scenes.PauseMenu;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends javafx.application.Application {
    public static boolean isAbleToUseEscButton = false;

    public static Stage stage; // window
    public static HeroPerson HERO = new HeroPerson("Кличко", 896, 476);
    public static GameWorld gameWorld = new GameWorld();
    public static final Pane miniMap = new Pane();

    static {
        miniMap.setLayoutX(10);
        miniMap.setLayoutY(814);
        miniMap.setPrefWidth(512);
        miniMap.setPrefHeight(256);
        miniMap.setStyle("-fx-background-color: RED");
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Hackathon test game (noNamed)");
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setFullScreen(true);
//        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setScene(new MainMenu().mainMenuScene);


        stage.addEventFilter(KeyEvent.KEY_PRESSED, (keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE && isAbleToUseEscButton) {
                stage.setScene(new PauseMenu().loseMenuScene);
            }
        }));


        stage.show();
    }
}
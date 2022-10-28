package com.nonamed.nonamedgame;

import com.nonamed.nonamedgame.scenes.MainMenu;
import com.nonamed.nonamedgame.scenes.PauseMenu;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends javafx.application.Application {
    public static boolean isAbleToUseEscButton = false;

    public static Stage stage; // window

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Hackathon test game (noNamed)");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setScene(new MainMenu().mainMenuScene);






        stage.addEventFilter(KeyEvent.KEY_PRESSED, (keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE && isAbleToUseEscButton) {
                stage.setScene(new PauseMenu().loseMenuScene);
            }
        }));


        stage.show();
    }
}
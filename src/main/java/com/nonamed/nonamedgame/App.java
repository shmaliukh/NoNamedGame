package com.nonamed.nonamedgame;

import com.nonamed.nonamedgame.scenes.MainMenu;
import javafx.stage.Stage;

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
        stage.setFullScreen(true);

        stage.setScene(new MainMenu().mainMenuScene);






//        stage.addEventFilter(KeyEvent.KEY_PRESSED, (keyEvent -> {
//            if (keyEvent.getCode() == KeyCode.ESCAPE && isAbleToUseEscButton) {
//                stage.setScene(new PauseMenu().loseMenuScene);
//            }
//        }));


        stage.show();
    }
}
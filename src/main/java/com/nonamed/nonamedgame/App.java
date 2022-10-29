package com.nonamed.nonamedgame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class App extends Application {

    private static final FXMLLoader fxmlLoaderMainMenu = new FXMLLoader(App.class.getResource("fxmls/mainMenu.fxml"));
    private static final FXMLLoader fxmlLoaderGameScene = new FXMLLoader(App.class.getResource("fxmls/mainScreen.fxml"));
    private static final FXMLLoader fxmlLoaderPauseMenuScene = new FXMLLoader(App.class.getResource("fxmls/pauseMenu.fxml"));
    private static final FXMLLoader fxmlLoaderResultMenuScene = new FXMLLoader(App.class.getResource("fxmls/loseMenu.fxml"));
    private static final FXMLLoader fxmlLoaderSettingMenuScene = new FXMLLoader(App.class.getResource("fxmls/settingsMenu.fxml"));

    public static Pane mainMenuPane;
    public static Pane gamePane;
    public static Pane pauseMenuPane;
    public static Pane resultMenuPane;
    public static Pane settingMenuPane;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static GameWorld gameWorld;
    public static Hero HERO;
    public static Enemy enemy;
    public static boolean isStopped = false;
    private static Stage stage;

    static {
        try {
            mainMenuPane = fxmlLoaderMainMenu.load();
            gamePane = fxmlLoaderGameScene.load();
            pauseMenuPane = fxmlLoaderPauseMenuScene.load();
            resultMenuPane = fxmlLoaderResultMenuScene.load();
            settingMenuPane = fxmlLoaderSettingMenuScene.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final Scene mainMenuScene = new Scene(mainMenuPane);
    public final Scene gameScene = new Scene(gamePane);
    public final Scene pauseMenuScene = new Scene(pauseMenuPane);
    public final Scene resultMenuScene = new Scene(resultMenuPane);
    public final Scene settingScene = new Scene(settingMenuPane);

    public static Stage getStage() {
        return stage;
    }

    public static void setStageScene(Scene necessaryScene) {
        stage.setScene(necessaryScene);
    }

    public static void execute() {
        gameWorld = new GameWorld();
        HERO = new Hero();
        new Enemy();

        for (int i = 0; i < 10; i++) {
            new Enemy();
        }

        Thread enemySpawn = new Thread(() -> {
            while (true) {

                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        int countOfSpawn = (int) (Math.random() * 10);
                        System.out.println("Завспавнилося " + countOfSpawn);
                        for (int i = 0; i < countOfSpawn; i++) {
                            if (!isStopped)
                                enemies.add(new Enemy());
                        }
                    }
                });
            }
        });
        enemySpawn.start();
    }

    public static void stopGame() {
        HERO.getTimerHeroMove().stop();
        for (Enemy value : enemies) {
            value.getTimerEnemyMove().stop();
        }
    }

    public static void startGame() {
        HERO.getTimerHeroMove().start();
        for (Enemy value : enemies) {
            value.getTimerEnemyMove().start();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setScene(mainMenuPane.getScene());

        stage.setTitle("NoNamed game");
        stage.setMinWidth(1200);
        stage.setMinHeight(720);
        stage.setFullScreenExitHint("");


        addKeyHandlerToMainStage();

        //execute();


        stage.show();
    }

    public void addKeyHandlerToMainStage() {
        stage.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                if (App.stage.getScene() != pauseMenuScene
                        && App.stage.getScene() != mainMenuScene) {
                    System.out.println("Відкрита пауза");
                    //stage.close();
                    isStopped = true;
                    stopGame();
                    App.stage.setScene(pauseMenuPane.getScene());
                }
            }

        });
    }
}
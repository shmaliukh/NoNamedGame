package com.nonamed.nonamedgame;

import com.nonamed.nonamedgame.enemies.SvinoPes;
import com.nonamed.nonamedgame.enemies.SvinoPesBuryat;
import com.nonamed.nonamedgame.enemies.SvinoPesHach;
import com.nonamed.nonamedgame.enemies.SvinoPesOrk;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.nonamed.nonamedgame.Config.RANDOM;
import static com.nonamed.nonamedgame.Config.setUpProfileConfig;
import static com.nonamed.nonamedgame.StaticData.MAIN_MENU_SOUND;
import static com.nonamed.nonamedgame.StaticData.PlANE_SOUND;

public class App_old extends Application {

    public static final String WRONG_WAY = "wrong-way";
    public static final String GOOD_WAY = "good-way";
    private static final FXMLLoader fxmlLoaderMainMenu = new FXMLLoader(App_old.class.getResource("fxmls/mainMenu.fxml"));
    private static final FXMLLoader fxmlLoaderGameScene = new FXMLLoader(App_old.class.getResource("fxmls/mainScreen.fxml"));
    private static final FXMLLoader fxmlLoaderPauseMenuScene = new FXMLLoader(App_old.class.getResource("fxmls/pauseMenu.fxml"));
    private static final FXMLLoader fxmlLoaderResultMenuScene = new FXMLLoader(App_old.class.getResource("fxmls/loseMenu.fxml"));
    private static final FXMLLoader fxmlLoaderSettingMenuScene = new FXMLLoader(App_old.class.getResource("fxmls/settingsMenu.fxml"));
    private static final FXMLLoader fxmlLoaderPreviewScene = new FXMLLoader(App_old.class.getResource("fxmls/preview.fxml"));
    public static MediaPlayer MEDIA_PLAYER = new MediaPlayer(PlANE_SOUND);
    public static Pane mainMenuPane;
    public static Pane gamePane;
    public static Pane pauseMenuPane;
    public static Pane resultMenuPane;
    public static Pane settingMenuPane;
    public static Pane previewPane;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static ArrayList<GameWorldObjects> gameWorldObjects = new ArrayList<>();
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
            previewPane = fxmlLoaderPreviewScene.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final Scene mainMenuScene = new Scene(mainMenuPane);
    public final Scene gameScene = new Scene(gamePane);
    public final Scene pauseMenuScene = new Scene(pauseMenuPane);
    public final Scene resultMenuScene = new Scene(resultMenuPane);
    public final Scene settingScene = new Scene(settingMenuPane);
    public final Scene previewScene = new Scene(previewPane);

    public static Stage getStage() {
        return stage;
    }

    public static void setStageScene(Scene necessaryScene) {
        stage.setScene(necessaryScene);
    }

    public static void execute() {
        setUpProfileConfig();
        MEDIA_PLAYER.stop();
        MEDIA_PLAYER = new MediaPlayer(PlANE_SOUND);
        MEDIA_PLAYER.setVolume(Config.VOLUME_LEVEL);
        MEDIA_PLAYER.play();
        gameWorld = new GameWorld();
        HERO = new Hero();
        enemies.add(new Enemy());
        enemies.add(new SvinoPesHach());

        gameWorldObjects.add(new GameWorldObjects(1));

//        for (int i = 0; i < 10; i++) {
//            enemies.add(new SvinoPesOrk());
//        }

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
                        int nextInt = 0;
                        int countOfSpawn = (int) (Math.random() * 10);
                        System.out.println("Завспавнилося " + countOfSpawn);
                        for (int i = 0; i < countOfSpawn; i++) {
                            if (!isStopped) {
                                nextInt = RANDOM.nextInt(100);
                                if (nextInt > 90) {
                                    enemies.add(new Enemy());
                                } else if (nextInt > 75) {
                                    enemies.add(new SvinoPesBuryat());
                                } else if (nextInt > 50) {
                                    enemies.add(new SvinoPesHach());
                                } else if (nextInt > 25) {
                                    enemies.add(new SvinoPes());
                                } else {
                                    enemies.add(new SvinoPesOrk());
                                }
                            }
                        }
                    }
                });
            }
        });
        enemySpawn.start();
        //gameWorldObjects.add(new GameWorldObjects(1));
                for (int i = 0; i < 10; i++) {
            gameWorldObjects.add(new GameWorldObjects(1));
        }
        Thread gameWorldObjectSpawn = new Thread(() -> {
            while (true) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        int nextInt = 0;
                        int countOfSpawn = (int) (Math.random() * 10);

                        for (int i = 0; i < countOfSpawn; i++) {
                            if (!isStopped) {
                                nextInt = RANDOM.nextInt(100);
                                if (nextInt > 90) {
                                    gameWorldObjects.add(new GameWorldObjects(1));
                                } else if (nextInt < 90) {
                                    gameWorldObjects.add(new GameWorldObjects(2));
                                }
                            }
                        }
                    }
                });
            }


        });
        //gameWorldObjectSpawn.start();
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

    public static void updatePosition(String direction) {
        for (GameWorldObjects gameWorldObject : App_old.gameWorldObjects) {
            if (direction.equals("UP")) {
                gameWorldObject.setPosY(gameWorldObject.getPosY() - Config.HERO_SPEED);
                gameWorldObject.getCollisionRectangle().setY(gameWorldObject.getCollisionRectangle().getY() + Config.HERO_SPEED);
                gameWorldObject.getBonusBagImage().setY(gameWorldObject.getBonusBagImage().getY() + Config.HERO_SPEED);
                gameWorldObject.getBonusImage().setY(gameWorldObject.getBonusImage().getY() + Config.HERO_SPEED);
            }
            if (direction.equals("DOWN")) {
                gameWorldObject.setPosY(gameWorldObject.getPosY() + Config.HERO_SPEED);
                gameWorldObject.getCollisionRectangle().setY(gameWorldObject.getCollisionRectangle().getY() - Config.HERO_SPEED);
                gameWorldObject.getBonusBagImage().setY(gameWorldObject.getBonusBagImage().getY() - Config.HERO_SPEED);
                gameWorldObject.getBonusImage().setY(gameWorldObject.getBonusImage().getY() - Config.HERO_SPEED);
            }
            if (direction.equals("RIGHT")) {
                gameWorldObject.setPosX(gameWorldObject.getPosX() + Config.HERO_SPEED);
                gameWorldObject.getCollisionRectangle().setX(gameWorldObject.getCollisionRectangle().getX() - Config.HERO_SPEED);
                gameWorldObject.getBonusBagImage().setX(gameWorldObject.getBonusBagImage().getX() - Config.HERO_SPEED);
                gameWorldObject.getBonusImage().setX(gameWorldObject.getBonusImage().getX() - Config.HERO_SPEED);
            }
            if (direction.equals("LEFT")) {
                gameWorldObject.setPosX(gameWorldObject.getPosX() - Config.HERO_SPEED);
                gameWorldObject.getCollisionRectangle().setX(gameWorldObject.getCollisionRectangle().getX() + Config.HERO_SPEED);
                gameWorldObject.getBonusBagImage().setX(gameWorldObject.getBonusBagImage().getX() + Config.HERO_SPEED);
                gameWorldObject.getBonusImage().setX(gameWorldObject.getBonusImage().getX() + Config.HERO_SPEED);
            }
        }
    }

    public static GameWorldObjects objectCollisionDetectWithHero() {
        for (GameWorldObjects gameWorldObject : gameWorldObjects) {
//            System.out.println("collision");
            if (App_old.HERO.getBodyCollision().getBoundsInParent().intersects(gameWorldObject.getCollisionRectangle().getBoundsInParent())) {
                return gameWorldObject;
            }
        }
        return null;
    }

    @Override
    public void start(Stage primaryStage) {

        MEDIA_PLAYER = new MediaPlayer(MAIN_MENU_SOUND);
        MEDIA_PLAYER.setVolume(Config.VOLUME_LEVEL);
        MEDIA_PLAYER.play();

        stage = primaryStage;
        stage.setScene(mainMenuPane.getScene());
        stage.getIcons().add(StaticData.LOGO_IMAGE);

        stage.setTitle("NoNamed game");
        stage.setMinWidth(1200);
        stage.setMinHeight(720);
        stage.setFullScreenExitHint("");

        addKeyHandlerToMainStage();
        stage.show();
    }

    public void addKeyHandlerToMainStage() {
        stage.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                if (App_old.stage.getScene() != pauseMenuScene
                        && App_old.stage.getScene() != mainMenuScene && App_old.stage.getScene() != pauseMenuScene) {
                    System.out.println("Відкрита пауза");
                    //stage.close();
                    isStopped = true;
                    stopGame();
                    App_old.stage.setScene(pauseMenuPane.getScene());
                }
            }

        });
    }

//    public static String objectCollisionDetectWithHero(){
//        for (GameWorldObjects gameWorldObject : gameWorldObjects) {
//            System.out.println("collision");
//            if (App.HERO.getBodyCollision().getBoundsInParent().intersects(gameWorldObject.getObj_v1().getBoundsInParent())) {
//                return WRONG_WAY;
//            }
//        }
//        return GOOD_WAY;
//    }

}
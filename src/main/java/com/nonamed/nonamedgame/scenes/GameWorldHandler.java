package com.nonamed.nonamedgame.scenes;


import com.nonamed.nonamedgame.App;
import com.nonamed.nonamedgame.Config;
import com.nonamed.nonamedgame.game_objects.BaseObject;
import com.nonamed.nonamedgame.game_objects.micro.AbstractPerson;
import com.nonamed.nonamedgame.game_objects.micro.DarkPerson;
import com.nonamed.nonamedgame.game_objects.micro.HeroPerson;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public final class GameWorldHandler implements Serializable {

    public static String MISSION_1 = "   Goal: " +
            "\n* kill " + Config.HERO_ENEMY_GOAL + " enemy" +
            "\n* find " + Config.HERO_KEY_GOAL + " keys";
    public static String MISSION_V_4 = "    Mission: " +
            "\n✔ kill enemies" +
            "\n✔ find " + Config.HERO_KEY_GOAL + " keys" +
            "\n* go home";
    public static String MISSION_V_2 = "    Mission: " +
            "\n✔ kill enemies" +
            "\n* find " + Config.HERO_KEY_GOAL + " keys";
    public static String MISSION_V_3 = "    Mission: " +
            "\n* kill enemies" +
            "\n✔ find " + Config.HERO_KEY_GOAL + " keys";

    public static HeroPerson HERO = new HeroPerson("Кличко", 200, 200);
    public List<AbstractPerson> personArrayList = new ArrayList<>();
    public List<ImageView> keyArrayList = new ArrayList<>();
    public List<BaseObject> baseObjectArrayList = new ArrayList<>();
    Text missionText = new Text();
    ImageView homeImageView;
    ImageView keyImageView = new ImageView();

    Rectangle homeBorder;
    private int idToSet = 0;
    private GameWorld gameWorld;
    private AnimationTimer timer;

    public GameWorldHandler() {
        gameWorld = new GameWorld();
        initTimer();
        addMissionText();

        gameWorld.registerObject(HERO.getGroup());
    }

    private void addHeroHomeImageView() {
        homeImageView = new ImageView(new Image("heroHome.png"));
        homeImageView.setX(100);
        homeImageView.setY(300);
        homeBorder = new Rectangle();
        homeBorder.setX(180);
        homeBorder.setY(470);
        homeBorder.setWidth(120);
        homeBorder.setHeight(100);
        homeBorder.setFill(Color.BLUE);
        homeBorder.setOpacity(0);


        gameWorld.getGamePane().getChildren().add(homeImageView);
        gameWorld.getGamePane().getChildren().add(homeBorder);
    }

    private void addKeyHomeImageView(int posX, int posY) {
        keyImageView = new ImageView(new Image("keyHome.png"));
        keyImageView.setX(posX);
        keyImageView.setY(posY);
        keyArrayList.add(keyImageView);
        gameWorld.getGamePane().getChildren().add(keyImageView);
    }

    private void addMissionText() {
        setUpGoalText();
        missionText.setX(1050);
        missionText.setY(30);
        missionText.setFont(Font.font(20));
        gameWorld.getGamePane().getChildren().add(missionText);
    }

    private void initTimer() {
        Thread enemySpawn = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //System.out.println("Spawn enemy");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        addPersonToScene(new DarkPerson(800, 450));
                    }
                });
            }
        });
        enemySpawn.start();
        Thread keySpawn = new Thread(() -> {
            for (int i = 0; i < Config.HERO_KEY_GOAL; i++) {
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Key spawn");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        addKeyHomeImageView(ThreadLocalRandom.current().nextInt(50, 1100 + 1), ThreadLocalRandom.current().nextInt(50, 620 + 1));
                    }
                });
            }


        });
        keySpawn.start();

        App.isAbleToUseEscButton = true;

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!keySpawn.isAlive() && !gameWorld.getGamePane().getChildren().contains(keyImageView)) {
                    Config.IS_KEY_GOAL = true;
                    setUpGoalText();
                }

                if (HERO.getGroup().getBoundsInParent().intersects(keyImageView.getBoundsInParent())) {
                    if (keyArrayList.contains(keyImageView)) {
                        gameWorld.getGamePane().getChildren().remove(keyImageView);
                    }
                }
                if (HERO.getHealth() < 0) {
                    personArrayList.forEach(o -> o = null);
                    App.stage.setScene(new LoseMenu().loseMenuScene);
                    App.isAbleToUseEscButton = false;
                    timer.stop();
                } else if (Config.HERO_ENEMY_GOAL == 0 && Config.IS_KEY_GOAL) {
                    setUpGoalText();
                    if (HERO.getGroup().getBoundsInParent().intersects(homeBorder.getBoundsInParent())) {
                        System.out.println("Hero Win");
                        App.stage.setScene(new WinMenu().winMenuScene);
                        App.isAbleToUseEscButton = false;
                        timer.stop();
                    }
                }
                for (AbstractPerson person : personArrayList) {
                    if (person.getHealth() <= 0) {
                        gameWorld.getGamePane().getChildren().remove(person.getGroup());
                        personArrayList.remove(person);
                        if (Config.HERO_ENEMY_GOAL > 0) {
                            Config.HERO_ENEMY_GOAL -= 1;

                        }
                        setUpGoalText();
                    } else {
                        person.move();
                    }
                }

                HERO.move();
            }
        };
        timer.start();

    }


    public void setUpGoalText() {
        MISSION_1 = "   Goal: " +
                "\n* kill " + Config.HERO_ENEMY_GOAL + " enemy" +
                "\n* find " + Config.HERO_KEY_GOAL + " keys";
        MISSION_V_4 = "    Goal: " +
                "\n✔ kill enemies" +
                "\n✔ find keys" +
                "\n* go home";
        MISSION_V_2 = "    Goal: " +
                "\n✔ kill enemies" +
                "\n* find " + Config.HERO_KEY_GOAL + " keys";
        MISSION_V_3 = "    Goal: " +
                "\n* kill enemies" +
                "\n✔ find " + Config.HERO_KEY_GOAL + " keys";

        if (Config.HERO_ENEMY_GOAL == 0 && Config.IS_KEY_GOAL) {
            missionText.setText(MISSION_V_4);
        } else if (Config.HERO_ENEMY_GOAL == 0) {
            missionText.setText(MISSION_V_2);
        } else if (Config.IS_KEY_GOAL) {
            missionText.setText(MISSION_V_3);
        } else
            missionText.setText(MISSION_1);
    }

    public void addPersonToScene(AbstractPerson person) {
        person.setId(idToSet);
        gameWorld.registerObject(person.getGroup());
        personArrayList.add(person);
        idToSet += 1;
    }

//    public void addBaseObjectToScene(BaseObject baseObject) {
//        baseObject.setId(idToSet);
//        gameWorld.registerObject(idToSet, baseObject.getImageView());
//        personArrayList.add(baseObject);
//        idToSet += 1;
//    }
}

package com.nonamed.nonamedgame;

import com.nonamed.nonamedgame.game_objects.Hud;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

import static com.nonamed.nonamedgame.App_old.HERO;
import static com.nonamed.nonamedgame.utils.HeroSoundService.*;

@Setter
@Getter
public class GameWorldObjects {

    public final int mapBoundXMin = -800;
    public final int mapBoundXMax = 2700;
    public final int mapBoundYMin = -800;
    public final int mapBoundYMax = 2000;
    private Rectangle collisionRectangle = new Rectangle();
    private int posX = ThreadLocalRandom.current().nextInt(100, 1000);
    private int posY = ThreadLocalRandom.current().nextInt(100, 500);
    private Rectangle miniMapPoint;
    private AnimationTimer timerObjectAction;
    private ImageView bonusBagImage = new ImageView();
    private ImageView bonusImage = new ImageView();


    private Group bonusGroup;
    private int variant;

    public GameWorldObjects(int variant) {
        this.variant = variant;
        if (variant == 1) {
            bonusBagImage = new ImageView(new Image("LukyBox.png"));
            bonusImage = new ImageView(new Image("Varenyk.gif"));
        } else if (variant == 2) {
            bonusBagImage = new ImageView(new Image("LukyCan.png"));
            bonusImage = new ImageView(new Image("apple.gif"));
        } else if (variant == 3) {
            bonusBagImage = new ImageView(new Image("LukyBox.png"));
            bonusImage = new ImageView(new Image("CoinFinal.gif"));
        } else if (variant == 4) {
            bonusBagImage = new ImageView(new Image("LukyCan.png"));
            bonusImage = new ImageView(new Image("battery.gif"));
        }

        collisionRectangle = new Rectangle();


        collisionRectangle.setX(findPointX());
        collisionRectangle.setY(findPointY());
        posX = (int) collisionRectangle.getX() - 1000;
        posY = (int) collisionRectangle.getY() - 1000;
        collisionRectangle.setWidth(64);
        collisionRectangle.setHeight(64);

        collisionRectangle.setFill(Color.RED);
        collisionRectangle.setOpacity(0);


        bonusBagImage.setX(collisionRectangle.getX() - 10);
        bonusBagImage.setY(collisionRectangle.getY() - 10);
        bonusImage.setX(collisionRectangle.getX() + 16);
        bonusImage.setY(collisionRectangle.getY() + 16);


        miniMapPoint = new Rectangle();
        miniMapPoint.setWidth(5);
        miniMapPoint.setHeight(5);
        miniMapPoint.setFill(Color.GREEN);

        calculateAndUpdateMiniMapPoint();

        timerObjectAction = new AnimationTimer() {
            @Override
            public void handle(long l) {

                objectCollisionDetectWithPersonForBonus();
                objectCollisionToGetBonus();

            }
        };

        timerObjectAction.start();

        bonusGroup = new Group();
        bonusGroup.getChildren().addAll(collisionRectangle, bonusImage, bonusBagImage, miniMapPoint);


        App_old.gamePane.getChildren().add(collisionRectangle);
        App_old.gamePane.getChildren().add(bonusImage);
        App_old.gamePane.getChildren().add(bonusBagImage);
        App_old.gameWorld.getMiniMap().getChildren().add(miniMapPoint);
    }

    public GameWorldObjects(String name, int posX, int posY) {
        collisionRectangle = new Rectangle();
        collisionRectangle.setX(posX);
        collisionRectangle.setY(posY);
        switch (name) {
            case "treeV1" -> {
                collisionRectangle.setWidth(140);
                collisionRectangle.setHeight(335);
            }
            case "christmasTree" -> {
                collisionRectangle.setWidth(160);
                collisionRectangle.setHeight(340);
            }
            case "homeWithTesla" -> {
                collisionRectangle.setWidth(450);
                collisionRectangle.setHeight(250);
            }
            case "bigHouse1" -> {
                collisionRectangle.setWidth(550);
                collisionRectangle.setHeight(1050);
            }
            case "greenHouseWithTree" -> {
                collisionRectangle.setWidth(470);
                collisionRectangle.setHeight(250);
            }
            case "ATB" -> {
                collisionRectangle.setWidth(450);
                collisionRectangle.setHeight(250);
            }


        }

        collisionRectangle.setFill(Color.RED);
        collisionRectangle.setOpacity(0.3);

        timerObjectAction = new AnimationTimer() {
            @Override
            public void handle(long l) {

                objectCollisionDetectWithPerson();

            }
        };

        timerObjectAction.start();


        App_old.gamePane.getChildren().add(collisionRectangle);
        //App_old.gameWorld.getMiniMap().getChildren().add(miniMapPoint);
    }

    public Rectangle getMiniMapPoint() {
        return miniMapPoint;
    }

    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void calculateAndUpdateMiniMapPoint() {
        int currentPosX = (int) collisionRectangle.getX();
        int currentPosY = (int) collisionRectangle.getY();
        int percentagePosX = currentPosX * 100 / 2500;
        int percentagePosY = currentPosY * 100 / 2500;
        int miniMapPosX = 512 * percentagePosX / 100;
        int miniMapPosY = 256 * percentagePosY / 100;

        getMiniMapPoint().setX(Math.abs(miniMapPosX));
        getMiniMapPoint().setY(Math.abs(miniMapPosY));

    }

    public int findPointX() {
        int posX = 0;
        boolean find = true;
        posX = ThreadLocalRandom.current().nextInt(mapBoundXMin, mapBoundXMax);
        for (int i = 0; i < App_old.gameWorldObjects.size(); i++) {
            if (App_old.gameWorldObjects.get(i).posX < posX &&
                    App_old.gameWorldObjects.get(i).posX + App_old.gameWorldObjects.get(i).collisionRectangle.getWidth() < posX ||
                    App_old.gameWorldObjects.get(i).posX > posX &&
                            App_old.gameWorldObjects.get(i).posX + App_old.gameWorldObjects.get(i).collisionRectangle.getWidth() > posX) {
                find = false;
            } else {
                find = true;
                break;
            }
        }
        if (find)
            posX = -10000;
        return posX;
    }

    public int findPointY() {
        int posY = 0;
        boolean find = true;
        posY = ThreadLocalRandom.current().nextInt(mapBoundYMin, mapBoundYMax);
        for (int i = 0; i < App_old.gameWorldObjects.size(); i++) {
            if (App_old.gameWorldObjects.get(i).posY < posY &&
                    App_old.gameWorldObjects.get(i).posY + App_old.gameWorldObjects.get(i).collisionRectangle.getHeight() < posY ||
                    App_old.gameWorldObjects.get(i).posY > posY &&
                            App_old.gameWorldObjects.get(i).posY + App_old.gameWorldObjects.get(i).collisionRectangle.getHeight() > posY) {
                find = false;
            } else {
                find = true;
                break;
            }
        }
        if (find)
            posY = -10000;
        return posY;
    }


    public void objectCollisionToGetBonus() {
        if (HERO.getBodyCollision().getBoundsInParent().intersects(bonusImage.getBoundsInParent())) {
            if (bonusBagImage.getOpacity() == 0) {
                App_old.gamePane.getChildren().remove(collisionRectangle);
                App_old.gamePane.getChildren().remove(bonusImage);
                App_old.gamePane.getChildren().remove(bonusBagImage);
                App_old.gameWorld.getMiniMap().getChildren().remove(miniMapPoint);
                Hud.heroBonusHealthCount.setText(" x " + Config.HERO_BONUS_HEALTH_COUNT);
                Hud.heroBonusEnergyCount.setText(" x " + Config.HERO_BONUS_ENERGY_COUNT);
                Hud.heroBonusBatteryCount.setText(" x " + Config.HERO_BONUS_BATTERY_COUNT);
            }
        }
    }

    public void removeByIndex() {
        for (int i = 0; i < App_old.gameWorldObjects.size(); i++) {
            if (App_old.gameWorldObjects.get(i).getPosX() == posX) {
                if (App_old.gameWorldObjects.get(i).getVariant() == 1) {
                    HERO.setHealth((int) (HERO.getHealth()+ Config.HERO_HEALTH * 0.20));
//                    setEnergy((int) (getEnergy() + Config.HERO_HEALTH * 0.10));
//                    setHealth((int) (getHealth() + Config.HERO_ENERGY * 0.10));
                    Config.HERO_BONUS_HEALTH_COUNT += 1;
                    HERO.SCORE += 25;
                } else if (App_old.gameWorldObjects.get(i).getVariant() == 2) {
                    HERO.setHealth((int) (HERO.getHealth()+ Config.HERO_HEALTH * 0.10));
                    HERO.setEnergy((int) (HERO.getEnergy()+ Config.HERO_ENERGY * 0.10));
                    Config.HERO_BONUS_ENERGY_COUNT += 1;
                    HERO.SCORE += 25;
                } else if (App_old.gameWorldObjects.get(i).getVariant() == 3) {
//                    HERO.setHealth((int) (HERO.getHealth()+ Config.HERO_HEALTH * 0.20));
                    HERO.SCORE += 100;
                } else if (App_old.gameWorldObjects.get(i).getVariant() == 4) {
                    HERO.setEnergy((int) (HERO.getEnergy()+ Config.HERO_ENERGY * 0.20));
                    Config.HERO_BONUS_BATTERY_COUNT += 1;
                    HERO.SCORE += 50;
                }

                App_old.gameWorldObjects.remove(App_old.gameWorldObjects.get(i));

                break;
            }
        }
    }

    public void objectCollisionDetectWithPersonForBonus() {
        if (HERO.getLeftKickCollision().getBoundsInParent().intersects(collisionRectangle.getBoundsInParent())) {
            if (HERO.isLeftKick()) {
                bonusBagImage.setOpacity(0);
                removeByIndex();
            }
        }
        if (HERO.getRightKickCollision().getBoundsInParent().intersects(collisionRectangle.getBoundsInParent())) {
            if (HERO.isRightKick()) {
                bonusBagImage.setOpacity(0);
                removeByIndex();
            }
        }


    }


    public String objectCollisionDetectWithPerson() {
        if (HERO.getBodyCollision().getBoundsInParent().intersects(collisionRectangle.getBoundsInParent())) {

            return "wrong-way";
        }
        return "good-way";
    }


}

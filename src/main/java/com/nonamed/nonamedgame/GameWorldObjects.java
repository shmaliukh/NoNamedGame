package com.nonamed.nonamedgame;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Setter
@Getter
public class GameWorldObjects {

    private Rectangle collisionRectangle = new Rectangle();
    private int posX = ThreadLocalRandom.current().nextInt(100, 2500);
    private int posY = ThreadLocalRandom.current().nextInt(100, 2500);

    private Rectangle miniMapPoint;

    private AnimationTimer timerObjectAction;

    public GameWorldObjects(int variant) {
        collisionRectangle = new Rectangle();
        collisionRectangle.setX(posX);
        collisionRectangle.setY(posY);
        collisionRectangle.setWidth(125);
        collisionRectangle.setHeight(320);

        collisionRectangle.setFill(Color.RED);

        miniMapPoint = new Rectangle();
        miniMapPoint.setWidth(5);
        miniMapPoint.setHeight(5);
        miniMapPoint.setFill(Color.GREEN);

        calculateAndUpdateMiniMapPoint();

        timerObjectAction = new AnimationTimer() {
            @Override
            public void handle(long l) {

                objectCollisionDetectWithPerson();

            }
        };

        timerObjectAction.start();


        App.gamePane.getChildren().add(collisionRectangle);
        App.gameWorld.getMiniMap().getChildren().add(miniMapPoint);
    }

    public GameWorldObjects(int variant, String name, int posX, int posY) {
        collisionRectangle = new Rectangle();
        collisionRectangle.setX(posX);
        collisionRectangle.setY(posY);
        switch (name) {
            case "treeV1" -> {
                collisionRectangle.setWidth(125);
                collisionRectangle.setHeight(320);
            }
            case "houseV1" -> {
                collisionRectangle.setWidth(450);
                collisionRectangle.setHeight(250);
            }
            case "houseV2" -> {
                collisionRectangle.setWidth(780);
                collisionRectangle.setHeight(300);
            }
            case "houseV3" -> {
                collisionRectangle.setWidth(450);
                collisionRectangle.setHeight(400);
            }
            case "houseV4" -> {
                collisionRectangle.setWidth(300);
                collisionRectangle.setHeight(400);
            }
        }

        collisionRectangle.setFill(Color.RED);
        collisionRectangle.setOpacity(0.3);

        miniMapPoint = new Rectangle();
        miniMapPoint.setWidth(5);
        miniMapPoint.setHeight(5);
        miniMapPoint.setFill(Color.GREEN);

        calculateAndUpdateMiniMapPoint();

        timerObjectAction = new AnimationTimer() {
            @Override
            public void handle(long l) {

                objectCollisionDetectWithPerson();

            }
        };

        timerObjectAction.start();


        App.gamePane.getChildren().add(collisionRectangle);
        App.gameWorld.getMiniMap().getChildren().add(miniMapPoint);
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

    public String objectCollisionDetectWithPerson() {
        if (App.HERO.getBodyCollision().getBoundsInParent().intersects(collisionRectangle.getBoundsInParent())) {

            return "wrong-way";
        }
        return "good-way";
    }


}

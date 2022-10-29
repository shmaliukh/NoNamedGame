package com.nonamed.nonamedgame;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.ThreadLocalRandom;

public class GameWorldObjects {
    private Rectangle obj_v1;
    private final int posX = ThreadLocalRandom.current().nextInt(100, 2500);
    private final int posY = ThreadLocalRandom.current().nextInt(100, 2500);

    private Rectangle miniMapPoint;

    private AnimationTimer timerObjectAction;

    public Rectangle getMiniMapPoint() {
        return miniMapPoint;
    }

    public Rectangle getObj_v1() {
        return obj_v1;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public GameWorldObjects(int variant){
        obj_v1 = new Rectangle();
        obj_v1.setX(posX);
        obj_v1.setY(posY);
        obj_v1.setWidth(64);
        obj_v1.setHeight(128);
        obj_v1.setFill(Color.GREEN);

        miniMapPoint = new Rectangle();
        miniMapPoint.setWidth(5);
        miniMapPoint.setHeight(5);
        miniMapPoint.setFill(Color.GREEN);

        calculateAndUpdateMiniMapPoint();

        timerObjectAction = new AnimationTimer() {
            @Override
            public void handle(long l) {

                objectCollisionDetectWithPerson();

            }};

        timerObjectAction.start();


        App.gamePane.getChildren().add(obj_v1);
        App.gameWorld.getMiniMap().getChildren().add(miniMapPoint);
    }





    public void calculateAndUpdateMiniMapPoint(){
        int currentPosX = (int) obj_v1.getX();
        int currentPosY = (int) obj_v1.getY();
        int percentagePosX = currentPosX * 100 / 2500;
        int percentagePosY = currentPosY * 100 / 2500;
        int miniMapPosX = 512 * percentagePosX / 100;
        int miniMapPosY = 256 * percentagePosY / 100;

        getMiniMapPoint().setX(Math.abs(miniMapPosX));
        getMiniMapPoint().setY(Math.abs(miniMapPosY));

    }

    public String objectCollisionDetectWithPerson(){
            if (App.HERO.getBodyCollision().getBoundsInParent().intersects(obj_v1.getBoundsInParent())){
                return  "wrong-way";
        }
        return "good-way";
    }


}

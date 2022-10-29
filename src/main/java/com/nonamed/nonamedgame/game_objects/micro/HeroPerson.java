package com.nonamed.nonamedgame.game_objects.micro;

import com.nonamed.nonamedgame.App;
import com.nonamed.nonamedgame.Config;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Getter;

import static com.nonamed.nonamedgame.Config.*;
import static com.nonamed.nonamedgame.StaticData.*;
import static com.nonamed.nonamedgame.scenes.GameWorldHandler.HERO;
import static com.nonamed.nonamedgame.scenes.GameWorldHandler.gameWorld;

@Getter
public class HeroPerson extends AbstractPerson {

    boolean fightLeft = false ;
    boolean fightRight = false ;

    int energy;

    public static final int WINDOW_WIDTH_CENTER = WINDOW_WIDTH / 2 - HERO_WIDTH / 2;
    public static final int WINDOW_HEIGHT_CENTER = WINDOW_HEIGHT / 2 - HERO_HEIGHT / 2;
    public static final int Y_TO_STOP = WORLD_HEIGHT - HERO_HEIGHT;
    public static final int X_TO_STOP = WORLD_WIDTH - HERO_WIDTH;
    public static final int WORLD_X_STOP = WORLD_WIDTH - WINDOW_WIDTH;
    public static final int WORLD_Y_STOP = (-WORLD_HEIGHT + WINDOW_HEIGHT);

    public HeroPerson(String name, int posX, int posY) {
        this.name = name;
        this.health = HERO_HEALTH;
        this.damage = HERO_DAMAGE;
        this.energy = 5000;
        this.posX = posX;
        this.posY = posY;

        setUpPersonPicturesGroup();
//        initTimerToMovePerson();
        setUpKeyHandlerActions(App.stage); // FIXME
    }

    @Override
    protected void setUpPersonPicturesGroup() {
        imageView = new ImageView(HERO_ANIMATED);
        group.getChildren().add(imageView);
        initNameText();
        initHealthLine(HERO_HEALTH, Color.GREEN);
    }

    public void setUpKeyHandlerActions(Stage scene) {
        onKeyPressed(scene);
        onKeyReleased(scene);
    }

    private void onKeyReleased(Stage scene) {
        scene.addEventFilter(KeyEvent.KEY_RELEASED, (keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP -> isUP = false;
                case DOWN -> isDOWN = false;
                case RIGHT -> isRIGHT = false;
                case LEFT -> isLEFT = false;
            }
            moveStatus = WAIT;
        }));
    }

    private void onKeyPressed(Stage scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP -> moveByDirection(MOVE_UP);
                case DOWN -> moveByDirection(MOVE_DOWN);
                case RIGHT -> moveByDirection(MOVE_RIGHT);
                case LEFT -> moveByDirection(MOVE_LEFT);
            }
        }));
    }

    private void moveByDirection(String moveDirectionStr) {
        if (!moveStatus.equals(moveDirectionStr)) {
            moveStatus = moveDirectionStr;
            switch (moveDirectionStr) {
                case MOVE_UP -> isUP = true;
                case MOVE_DOWN -> isDOWN = true;
                case MOVE_RIGHT -> isRIGHT = true;
                case MOVE_LEFT -> isLEFT = true;
            }
            //System.out.println("pos: " + posX + " | " + posY);
        }
    }

    protected void upDatePerson() {
        speed = Config.HERO_SPEED;
        --energy;
        if(fightLeft){
            imageView.setImage(HERO_FIGHT_LEFT);
            HERO.fightLeft = false;
        }
        else if(fightRight){
            imageView.setImage(HERO_FIGHT_RIGHT);
            HERO.fightRight = false;
        } else {
            imageView.setImage(HERO_ANIMATED);
        }
        upDateAllPersonImages(HERO_HEALTH);

    }


    public void move() {
        double worldLayoutY = gameWorld.getGamePane().getLayoutY();
        if (isUP) {
            if (getPosY() > 0) {
                setPosY(getPosY() - speed);
//                if(worldLayoutY > 0 && getPosX() >= WINDOW_HEIGHT_CENTER) {
                    gameWorld.getGamePane().setLayoutY(worldLayoutY + speed);
//                }

            }
            upDatePerson();
        }
        else if (isDOWN) {
            if (getPosY() < Y_TO_STOP) {
                setPosY(getPosY() + speed);
//                System.out.println(worldLayoutY);
//                if(worldLayoutY >= WORLD_Y_STOP && getPosX() <= WINDOW_HEIGHT_CENTER){
                    gameWorld.getGamePane().setLayoutY(worldLayoutY - speed);
//                }
            }
            upDatePerson();
        }
        double worldLayoutX = gameWorld.getGamePane().getLayoutX();
        if (isRIGHT) {
            if (getPosX() < X_TO_STOP) {
                setPosX(getPosX() + speed);
//                if (worldLayoutX <= WORLD_X_STOP && getPosX() >= WINDOW_WIDTH_CENTER){
                    gameWorld.getGamePane().setLayoutX(worldLayoutX - speed);
//                }
            }
            upDatePerson();
//            imageView.setImage(IMAGE_RIGHT);
        }
        else if (isLEFT) {
            if (getPosX() > 0) {
                setPosX(getPosX() - speed);
//                if (worldLayoutX <= 0  && getPosX() <= WINDOW_WIDTH_CENTER){
                        gameWorld.getGamePane().setLayoutX(worldLayoutX + speed);
//                }
            }
            upDatePerson();
        }





    }

}